package org.elis.repository;

import java.util.Optional;

import org.elis.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> 
{
	 Optional<Admin> findById(Long id);
     Optional<Admin> findByUsername(String username);
}