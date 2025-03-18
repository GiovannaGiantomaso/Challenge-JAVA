package com.example.Odontoprev.repository;

import com.example.Odontoprev.model.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Long> {
    List<Auditoria> findByTabelaAfetada(String tabelaAfetada);
}
