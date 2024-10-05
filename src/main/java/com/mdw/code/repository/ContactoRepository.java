package com.mdw.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdw.code.model.Contacto;

@Repository
public interface  ContactoRepository extends JpaRepository<Contacto, Long> {

}
