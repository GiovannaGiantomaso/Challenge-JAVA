package com.example.Odontoprev.controller;

import org.springframework.ui.Model;
import com.example.Odontoprev.DTOs.GastoPacienteDTO;
import com.example.Odontoprev.DTOs.PacienteTratamentoDTO;
import com.example.Odontoprev.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/pacientes-tratamentos")
    public String mostrarRelatorioPacientesTratamentos(Model model) {
        List<PacienteTratamentoDTO> relatorio = relatorioService.obterRelatorioPacientesTratamentos();
        model.addAttribute("relatorioPacientesTratamentos", relatorio);
        return "relatorios/pacientes-tratamentos";
    }

    @GetMapping("/gastos-pacientes")
    public String mostrarRelatorioGastosPacientes(Model model) {
        List<GastoPacienteDTO> relatorio = relatorioService.obterRelatorioGastosPacientes();
        model.addAttribute("relatorioGastosPacientes", relatorio);
        return "relatorios/gastos-pacientes";
    }
}
