package com.example.Odontoprev.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.example.Odontoprev.model.Paciente;
import com.example.Odontoprev.model.Endereco;
import com.example.Odontoprev.service.PacienteService;
import com.example.Odontoprev.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public String gerenciarPacientes() {
        return "pacientes/gerenciar_pacientes";
    }

    @GetMapping("/cadastrar")
    public String cadastrarPaciente(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "pacientes/cadastrar_paciente";
    }

    @PostMapping("/salvar")
    public String salvarPaciente(@ModelAttribute Paciente paciente,
                                 @RequestParam("dataNascimento") String dataNascimentoStr,
                                 @RequestParam("cep") String cep,
                                 @RequestParam("numero") String numero,
                                 @RequestParam("bairro") String bairro,
                                 @RequestParam("cidade") String cidade,
                                 @RequestParam("estado") String estado,
                                 @RequestParam("pais") String pais) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            paciente.setDataNascimento(sdf.parse(dataNascimentoStr));

            Endereco endereco = enderecoService.buscarOuCriarEndereco(cep, numero, bairro, cidade, estado, pais);
            paciente.setEndereco(endereco);

            pacienteService.salvar(paciente);
            return "redirect:/pacientes/listar";
        } catch (ParseException e) {
            throw new RuntimeException("Erro ao converter a data de nascimento!", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar paciente!", e);
        }
    }

    @GetMapping("/listar")
    public String listarPacientes(Model model) {
        List<Paciente> pacientes = pacienteService.listarTodos();
        model.addAttribute("pacientes", pacientes);
        return "pacientes/listar_pacientes";
    }

    @GetMapping("/editar/{id}")
    public String editarPaciente(@PathVariable Long id, Model model) {
        Optional<Paciente> pacienteOpt = pacienteService.buscarPorId(id);
        if (pacienteOpt.isPresent()) {
            model.addAttribute("paciente", pacienteOpt.get());
            return "pacientes/editar_paciente";
        }
        return "redirect:/pacientes/listar";
    }

    @PostMapping("/atualizar")
    public String atualizarPaciente(@ModelAttribute Paciente paciente,
                                    @RequestParam("dataNascimento") String dataNascimentoStr,
                                    @RequestParam("cep") String cep,
                                    @RequestParam("numero") String numero,
                                    @RequestParam("bairro") String bairro,
                                    @RequestParam("cidade") String cidade,
                                    @RequestParam("estado") String estado,
                                    @RequestParam("pais") String pais) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            paciente.setDataNascimento(sdf.parse(dataNascimentoStr));

            Endereco endereco = enderecoService.buscarOuCriarEndereco(cep, numero, bairro, cidade, estado, pais);
            paciente.setEndereco(endereco);

            pacienteService.atualizar(paciente);
            return "redirect:/pacientes/listar";
        } catch (ParseException e) {
            throw new RuntimeException("Erro ao converter a data de nascimento!", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar paciente!", e);
        }
    }

}
