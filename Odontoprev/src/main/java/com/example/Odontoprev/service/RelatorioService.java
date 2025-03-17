package com.example.Odontoprev.service;

import com.example.Odontoprev.DTOs.GastoPacienteDTO;
import com.example.Odontoprev.DTOs.PacienteTratamentoDTO;
import com.example.Odontoprev.repository.RelatorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatorioService {

    @Autowired
    private RelatorioRepository relatorioRepository;

    public List<PacienteTratamentoDTO> obterRelatorioPacientesTratamentos() {
        return relatorioRepository.getRelatorioPacientesTratamentos();
    }

    public List<GastoPacienteDTO> obterRelatorioGastosPacientes() {
        return relatorioRepository.getRelatorioGastosPacientes();
    }
}
