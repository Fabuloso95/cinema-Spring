package org.elis.service.impl;

import java.util.Optional;
import org.elis.dto.AuthResponseDTO;
import org.elis.dto.LoginRequestDTO;
import org.elis.dto.RichiestaRegistrazioneDTO;
import org.elis.dto.UtenteDTO;
import org.elis.model.Ruolo;
import org.elis.model.Utente;
import org.elis.repository.UtenteRepository;
import org.elis.service.AuthService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService, UserDetailsService 
{
    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UtenteRepository utenteRepository, PasswordEncoder passwordEncoder) 
    {
        this.utenteRepository = utenteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UtenteDTO registraCliente(RichiestaRegistrazioneDTO registrazioneDTO) 
    {
        if (utenteRepository.existsByUsername(registrazioneDTO.getUsername())) 
        {
            throw new RuntimeException("Username già in uso");
        }

        Utente nuovoUtente = new Utente();
        nuovoUtente.setNome(registrazioneDTO.getNome());
        nuovoUtente.setCognome(registrazioneDTO.getCognome());
        nuovoUtente.setUsername(registrazioneDTO.getUsername());
        nuovoUtente.setPassword(passwordEncoder.encode(registrazioneDTO.getPassword()));
        nuovoUtente.setRuolo(Ruolo.CLIENTE);
        nuovoUtente.setDataNascita(registrazioneDTO.getDataNascita());

        Utente utenteRegistrato = utenteRepository.save(nuovoUtente);

        UtenteDTO utenteDTO = new UtenteDTO();
        utenteDTO.setId(utenteRegistrato.getId());
        utenteDTO.setNome(utenteRegistrato.getNome());
        utenteDTO.setCognome(utenteRegistrato.getCognome());
        utenteDTO.setUsername(utenteRegistrato.getUsername());
        utenteDTO.setDataNascita(utenteRegistrato.getDataNascita());
        utenteDTO.setRuolo("CLIENTE");
        utenteDTO.setPassword(utenteRegistrato.getPassword());

        return utenteDTO;
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO loginDTO)
    {
        Optional<Utente> utenteOptional = utenteRepository.findByUsername(loginDTO.getUsername());
        if (utenteOptional.isEmpty()) 
        {
            throw new RuntimeException("Utente non trovato");
        }
        Utente utente = utenteOptional.get();

        if (!passwordEncoder.matches(loginDTO.getPassword(), utente.getPassword())) 
        {
            throw new RuntimeException("Password non valida");
        }

        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setToken("test_token");
        authResponseDTO.setUsername(utente.getUsername());
        authResponseDTO.setRuolo(utente.getRuolo().name());
        return authResponseDTO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
    {
        Optional<Utente> utenteOptional = utenteRepository.findByUsername(username);
        if (utenteOptional.isEmpty()) 
        {
            throw new UsernameNotFoundException("Utente non trovato con username: " + username);
        }
        Utente utente = utenteOptional.get();

        List<String> roles = new ArrayList<>();
        roles.add(utente.getRuolo().name());

        return User.builder()
                .username(utente.getUsername())
                .password(utente.getPassword())
                .roles(roles.toArray(new String[0]))
                .build();
    }
}