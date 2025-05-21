package org.elis.service;

import org.elis.dto.*;
import java.util.*;

public interface AdminService
{
    List<AdminDTO> getAllAdmins();
    AdminDTO getAdminById(Long id);
    AdminDTO createAdmin(AdminDTO adminDTO);
    AdminDTO updateAdmin(Long id, AdminDTO adminDTO);
    void deleteAdmin(Long id);
    Optional<AdminDTO> findByUsername(String username);
}