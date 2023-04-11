package com.nadhem.livres.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nadhem.livres.entities.Livre;

public interface LivreRepository extends JpaRepository<Livre, Long> {
	
}
