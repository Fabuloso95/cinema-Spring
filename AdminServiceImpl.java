package org.elis.service.impl;

import java.util.*;
import java.util.stream.Collectors;
import org.elis.dto.*;
import org.elis.model.*;
import org.elis.repository.AdminRepository;
import org.elis.service.*;
import org.elis.exceptionhandler.exception.RisorsaNonTrovataException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AdminServiceImpl implements AdminService 
{
    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public AdminServiceImpl(AdminRepository adminRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) 
    {
        this.adminRepository = adminRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<AdminDTO> getAllAdmins() 
    {
        List<Admin> admins = adminRepository.findAll();
        return admins.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AdminDTO getAdminById(Long id) 
    {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Admin non trovato con id: " + id));
        return mapEntityToDTO(admin);
    }

    @Override
    @Transactional
    public AdminDTO createAdmin(AdminDTO adminDTO) 
    {
        Admin admin = mapDTOToEntity(adminDTO);

        if (adminDTO.getPassword() != null && !adminDTO.getPassword().isEmpty()) 
        {
            admin.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
        } 
        else 
        {
            throw new IllegalArgumentException("Password non fornita per la creazione admin.");
        }

        if (admin.getRuolo() == null) 
        {
            admin.setRuolo(Ruolo.ADMIN);
        }
        Admin nuovoAdmin = adminRepository.save(admin);

        return mapEntityToDTO(nuovoAdmin);
    }

    @Override
    @Transactional
    public AdminDTO updateAdmin(Long id, AdminDTO adminDTO) 
    {
        Admin existingAdmin = adminRepository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Admin non trovato con id: " + id));

        updateEntityFromDTO(existingAdmin, adminDTO);

        Admin adminAggiornato = adminRepository.save(existingAdmin);
        return mapEntityToDTO(adminAggiornato);
    }

    @Override
    @Transactional
    public void deleteAdmin(Long id)
    {
        adminRepository.deleteById(id);
    }

    @Override
    public Optional<AdminDTO> findByUsername(String username) 
    {
        Optional<Admin> admin = adminRepository.findByUsername(username);
        return admin.map(this::mapEntityToDTO);
    }

    private AdminDTO mapEntityToDTO(Admin admin) 
    {
        if (admin == null) return null;
        AdminDTO dto = modelMapper.map(admin, AdminDTO.class);
        if (admin.getRuolo() != null) 
        {
            dto.setRuolo(admin.getRuolo().name());
        }
        dto.setPassword(null);
        return dto;
    }

    private Admin mapDTOToEntity(AdminDTO adminDTO) 
    {
        if (adminDTO == null) return null;
        Admin entity = modelMapper.map(adminDTO, Admin.class);
        if (adminDTO.getRuolo() != null) 
        {
            entity.setRuolo(Ruolo.valueOf(adminDTO.getRuolo()));
        }
        return entity;
    }

    private void updateEntityFromDTO(Admin existingAdmin, AdminDTO adminDTO) 
    {
        if (existingAdmin == null || adminDTO == null) return;
        if (adminDTO.getNome() != null) existingAdmin.setNome(adminDTO.getNome());
        if (adminDTO.getCognome() != null) existingAdmin.setCognome(adminDTO.getCognome());
        if (adminDTO.getUsername() != null) existingAdmin.setUsername(adminDTO.getUsername());
        if (adminDTO.getRuolo() != null) existingAdmin.setRuolo(Ruolo.valueOf(adminDTO.getRuolo()));
        if (adminDTO.getDataNascita() != null) existingAdmin.setDataNascita(adminDTO.getDataNascita());
    }
}