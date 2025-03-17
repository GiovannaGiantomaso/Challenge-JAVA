package com.example.Odontoprev.service;

import com.example.Odontoprev.model.Paciente;
import com.example.Odontoprev.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    public void salvar(Paciente paciente) {
        if (paciente.getEndereco() == null || paciente.getEndereco().getId() == null) {
            throw new RuntimeException("❌ O paciente precisa ter um endereço válido.");
        }

        String resultadoValidacaoEmail = pacienteRepository.validarEmail(paciente.getEmail());
        if (!"OK".equals(resultadoValidacaoEmail)) {
            throw new RuntimeException("❌ E-mail inválido: " + paciente.getEmail());
        }

        pacienteRepository.inserirPaciente(
                paciente.getNome(),
                new java.sql.Date(paciente.getDataNascimento().getTime()),
                paciente.getIdGenero(),
                paciente.getTelefone(),
                paciente.getEmail(),
                paciente.getEndereco().getId()
        );
    }

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return pacienteRepository.findById(id);
    }

    @Transactional
    public void atualizar(Paciente paciente) {
        if (paciente.getEndereco() == null || paciente.getEndereco().getId() == null) {
            throw new RuntimeException("❌ O paciente precisa ter um endereço válido.");
        }

        Optional<Paciente> pacienteExistente = pacienteRepository.findById(paciente.getId());
        if (pacienteExistente.isPresent()) {
            pacienteRepository.atualizarPaciente(
                    paciente.getId(),
                    paciente.getNome(),
                    new java.sql.Date(paciente.getDataNascimento().getTime()),
                    paciente.getIdGenero(),
                    paciente.getTelefone(),
                    paciente.getEmail(),
                    paciente.getEndereco().getId()
            );
        } else {
            throw new RuntimeException("Paciente não encontrado para atualização.");
        }
    }


}
