package com.example.Odontoprev.controller;

import com.example.Odontoprev.model.Auditoria;
import com.example.Odontoprev.service.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AuditoriaController {

    @Autowired
    private AuditoriaService auditoriaService;

    @GetMapping("/auditoria")
    public String listarAuditoria(@RequestParam(value = "tabela", required = false) String tabelaAfetada, Model model) {
        List<Auditoria> auditoria;

        if (tabelaAfetada != null && !tabelaAfetada.isEmpty()) {
            auditoria = auditoriaService.listarPorTabela(tabelaAfetada);
        } else {
            auditoria = auditoriaService.listarTodas();
        }

        model.addAttribute("auditoria", auditoria);
        return "auditoria/lista";
    }
}
