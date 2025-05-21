package org.elis.service.impl;

import java.util.*;
import java.util.stream.Collectors;
import org.elis.dto.*;
import org.elis.model.*;
import org.elis.repository.*;
import org.elis.service.ClienteService;
import org.elis.exceptionhandler.exception.RisorsaNonTrovataException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class ClienteServiceImpl implements ClienteService
{
    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;
    private final TesseraFedeltaRepository tesseraFedeltaRepository;
    private final PasswordEncoder passwordEncoder;
    private final BigliettoRepository bigliettoRepository;


    public ClienteServiceImpl(ClienteRepository clienteRepository, ModelMapper modelMapper, TesseraFedeltaRepository tesseraFedeltaRepository, PasswordEncoder passwordEncoder, BigliettoRepository bigliettoRepository)
    {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
        this.tesseraFedeltaRepository = tesseraFedeltaRepository;
        this.passwordEncoder = passwordEncoder;
        this.bigliettoRepository = bigliettoRepository;
    }

    @Override
    public List<ClienteDTO> getAllClienti()
    {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO getClienteById(Long id)
    {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Cliente non trovato con id: " + id));
        return mapEntityToDTO(cliente);
    }

    @Override
    @Transactional
    public ClienteDTO createCliente(ClienteDTO clienteDTO)
    {
        Cliente cliente = mapDTOToEntity(clienteDTO);

        if (clienteDTO.getPassword() != null && !clienteDTO.getPassword().isEmpty())
        {
            cliente.setPassword(passwordEncoder.encode(clienteDTO.getPassword()));
        }
        else
        {
             throw new IllegalArgumentException("Password non fornita per la creazione cliente.");
        }

        if (cliente.getRuolo() == null)
        {
            cliente.setRuolo(Ruolo.CLIENTE);
        }


        if (clienteDTO.getTesseraFedelta() != null)
        {
            TesseraFedeltaDTO tesseraDTO = clienteDTO.getTesseraFedelta();

            TesseraFedelta tesseraFedelta = tesseraFedeltaRepository.findByCodiceTesseraIgnoreCase(tesseraDTO.getCodiceTessera())
                    .orElseGet(() ->
                    {
                        TesseraFedelta nuovaTessera = modelMapper.map(tesseraDTO, TesseraFedelta.class);
                        nuovaTessera.setCliente(cliente);
                        return nuovaTessera;
                    });

            cliente.setTesseraFedelta(tesseraFedelta);
             if (tesseraFedelta.getCliente() == null)
             {
                 tesseraFedelta.setCliente(cliente);
             }
        }
        Cliente nuovoCliente = clienteRepository.save(cliente);

        return mapEntityToDTO(nuovoCliente);
    }

    @Override
    @Transactional
    public ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO)
    {
        Cliente existingCliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Cliente non trovato con id: " + id));

        updateEntityFromDTO(existingCliente, clienteDTO);

        if (clienteDTO.getTesseraFedelta() != null)
        {
             TesseraFedeltaDTO tesseraDTO = clienteDTO.getTesseraFedelta();

             if (tesseraDTO.getId() != null)
             {
                 TesseraFedelta existingTessera = tesseraFedeltaRepository.findById(tesseraDTO.getId())
                         .orElseThrow(() -> new RisorsaNonTrovataException("Tessera Fedelta non trovata con ID: " + tesseraDTO.getId()));

                 modelMapper.map(tesseraDTO, existingTessera);

                 existingCliente.setTesseraFedelta(existingTessera);
                 if (existingTessera.getCliente() == null || !existingTessera.getCliente().getId().equals(existingCliente.getId()))
                 {
                      existingTessera.setCliente(existingCliente);
                 }
             }
             else
             {
                 if (existingCliente.getTesseraFedelta() != null)
                 {
                      throw new IllegalArgumentException("Cliente ha già una tessera fedelta. Impossibile crearne una nuova senza ID nell'update.");
                 }
                  TesseraFedelta nuovaTessera = modelMapper.map(tesseraDTO, TesseraFedelta.class);
                  nuovaTessera.setCliente(existingCliente);
                  existingCliente.setTesseraFedelta(nuovaTessera);
             }
        }
        else
        {
             if (existingCliente.getTesseraFedelta() != null)
             {
                  existingCliente.setTesseraFedelta(null);
             }
        }
        Cliente clienteAggiornato = clienteRepository.save(existingCliente);

        return mapEntityToDTO(clienteAggiornato);
    }

    @Override
    @Transactional
    public void deleteCliente(Long id)
    {
        clienteRepository.deleteById(id);
    }

    @Override
    public Optional<ClienteDTO> findByUsername(String username)
    {
        Optional<Cliente> cliente = clienteRepository.findByUsername(username);
        return cliente.map(this::mapEntityToDTO);
    }

    @Override
    public Optional<ClienteDTO> findByTesseraFedeltaCodiceTessera(String codiceTessera)
    {
        Optional<Cliente> cliente = clienteRepository.findByTesseraFedeltaCodiceTessera(codiceTessera);
        return cliente.map(this::mapEntityToDTO);
    }

    @Override
    public List<ClienteDTO> findByDataNascitaBefore(java.time.LocalDate data)
    {
        List<Cliente> clientes = clienteRepository.findByDataNascitaBefore(data);
        return clientes.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClienteDTO> findByTesseraFedeltaIsNotNull()
    {
        List<Cliente> clientes = clienteRepository.findByTesseraFedeltaIsNotNull();
        return clientes.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClienteDTO> findByTesseraFedeltaIsNull()
    {
        List<Cliente> clientes = clienteRepository.findByTesseraFedeltaIsNull();
        return clientes.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProfiloUtenteDTO updateProfilo(Long idCliente, ProfiloUtenteDTO profiloDTO) {
        Cliente existingCliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RisorsaNonTrovataException("Cliente non trovato con id: " + idCliente));

        modelMapper.map(profiloDTO, existingCliente);

        Cliente updatedCliente = clienteRepository.save(existingCliente);

        return modelMapper.map(updatedCliente, ProfiloUtenteDTO.class);
    }

    @Override
    public StoricoAcquistiDTO getStoricoAcquisti(Long idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RisorsaNonTrovataException("Cliente non trovato con id: " + idCliente));

        List<Biglietto> biglietti = bigliettoRepository.findByCliente(cliente);

        StoricoAcquistiDTO storicoDTO = new StoricoAcquistiDTO();
        storicoDTO.setIdCliente(cliente.getId());
        storicoDTO.setUsernameCliente(cliente.getUsername());

        List<BigliettoDTO> bigliettiDTO = biglietti.stream()
                .map(this::mapBigliettoEntityToDTO)
                .collect(Collectors.toList());
        storicoDTO.setBigliettiAcquistati(bigliettiDTO);

        storicoDTO.setNumeroTotaleBiglietti(biglietti.size());
        double totaleSpeso = biglietti.stream()
                .mapToDouble(Biglietto::getPrezzoFinale)
                .sum();
        storicoDTO.setTotaleSpeso(totaleSpeso);

        return storicoDTO;
    }


    private ClienteDTO mapEntityToDTO(Cliente cliente)
    {
        if (cliente == null) return null;
        ClienteDTO dto = modelMapper.map(cliente, ClienteDTO.class);
        if (cliente.getRuolo() != null)
        {
            dto.setRuolo(cliente.getRuolo().name());
        }
        dto.setPassword(null);
        return dto;
    }

     private Cliente mapDTOToEntity(ClienteDTO clienteDTO)
     {
         if (clienteDTO == null) return null;
         Cliente entity = modelMapper.map(clienteDTO, Cliente.class);
         if (clienteDTO.getRuolo() != null)
         {
             entity.setRuolo(Ruolo.valueOf(clienteDTO.getRuolo()));
         }
         return entity;
     }

     private void updateEntityFromDTO(Cliente existingCliente, ClienteDTO clienteDTO)
     {
         if (existingCliente == null || clienteDTO == null) return;
         if (clienteDTO.getNome() != null) existingCliente.setNome(clienteDTO.getNome());
         if (clienteDTO.getCognome() != null) existingCliente.setCognome(clienteDTO.getCognome());
         if (clienteDTO.getUsername() != null) existingCliente.setUsername(clienteDTO.getUsername());
         if (clienteDTO.getRuolo() != null) existingCliente.setRuolo(Ruolo.valueOf(clienteDTO.getRuolo()));
         if (clienteDTO.getDataNascita() != null) existingCliente.setDataNascita(clienteDTO.getDataNascita());
     }

     private BigliettoDTO mapBigliettoEntityToDTO(Biglietto biglietto)
     {
         if (biglietto == null) return null;
         return modelMapper.map(biglietto, BigliettoDTO.class);
     }
}