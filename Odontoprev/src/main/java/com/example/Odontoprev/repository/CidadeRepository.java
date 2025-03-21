package com.example.Odontoprev.repository;

import com.example.Odontoprev.model.Cidade;
import com.example.Odontoprev.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    Optional<Cidade> findByNomeAndEstado(String nome, Estado estado);
}
