package com.example.Odontoprev.controller;

import com.example.Odontoprev.model.Tratamento;
import com.example.Odontoprev.service.TratamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tratamentos")
public class TratamentoController {

    @Autowired
    private TratamentoService tratamentoService;

    private static final Logger logger = LoggerFactory.getLogger(TratamentoController.class);

    @GetMapping
    public String gerenciarTratamentos() {
        return "tratamentos/gerenciar_tratamentos";
    }

    @GetMapping("/listar")
    public String listarTratamentos(Model model) {
        List<Tratamento> tratamentos = tratamentoService.listarTodos();
        model.addAttribute("tratamentos", tratamentos);
        return "tratamentos/listar_tratamentos";
    }

    @GetMapping("/cadastrar")
    public String cadastrarTratamento(Model model) {
        model.addAttribute("tratamento", new Tratamento());
        return "tratamentos/cadastrar_tratamento";
    }

    @PostMapping("/salvar")
    public String salvarTratamento(@ModelAttribute Tratamento tratamento) {
        logger.info("Recebendo novo tratamento: Descrição={}, Tipo={}, Custo={}",
                tratamento.getDescricao(), tratamento.getTipo(), tratamento.getCusto());

        try {
            tratamentoService.salvar(tratamento);
        } catch (Exception e) {
            logger.error("Erro ao salvar tratamento: {}", e.getMessage());
            return "tratamentos/cadastrar_tratamento";
        }

        return "redirect:/tratamentos/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarTratamento(@PathVariable Long id, Model model) {
        Optional<Tratamento> tratamento = tratamentoService.buscarPorId(id);
        if (tratamento.isPresent()) {
            model.addAttribute("tratamento", tratamento.get());
            return "tratamentos/editar_tratamento";
        }
        return "redirect:/tratamentos/listar";
    }

    @PostMapping("/atualizar")
    public String atualizarTratamento(@ModelAttribute Tratamento tratamento) {
        logger.info("Atualizando tratamento: ID={}, Descrição={}, Tipo={}, Custo={}",
                tratamento.getId(), tratamento.getDescricao(), tratamento.getTipo(), tratamento.getCusto());

        try {
            tratamentoService.salvar(tratamento);
        } catch (Exception e) {
            logger.error("Erro ao atualizar tratamento: {}", e.getMessage());
            return "tratamentos/editar_tratamento";
        }

        return "redirect:/tratamentos/listar";
    }
}
