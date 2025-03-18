package com.example.Odontoprev.service;

import com.example.Odontoprev.model.Auditoria;
import com.example.Odontoprev.repository.AuditoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuditoriaService {

    @Autowired
    private AuditoriaRepository auditoriaRepository;

    public List<Auditoria> listarTodas() {
        return auditoriaRepository.findAll();
    }

    public List<Auditoria> listarPorTabela(String tabelaAfetada) {
        return auditoriaRepository.findByTabelaAfetada(tabelaAfetada);
    }
}
