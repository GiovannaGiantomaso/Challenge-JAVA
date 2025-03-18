package com.example.Odontoprev.controller;

import com.example.Odontoprev.model.Paciente;
import com.example.Odontoprev.model.Endereco;
import com.example.Odontoprev.service.PacienteService;
import com.example.Odontoprev.service.EnderecoService;
import com.example.Odontoprev.service.TratamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private TratamentoService tratamentoService;

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

    @PostMapping("/excluir/{id}")
    public String excluirPacientePost(@PathVariable Long id) {
        pacienteService.excluir(id);
        return "redirect:/pacientes/listar";
    }

    @DeleteMapping("/excluir/{id}")
    public String excluirPaciente(@PathVariable Long id) {
        pacienteService.excluir(id);
        return "redirect:/pacientes/listar";
    }

    @GetMapping("/adicionar-tratamento/{id}")
    public String adicionarTratamento(@PathVariable Long id, Model model) {
        Optional<Paciente> pacienteOpt = pacienteService.buscarPorId(id);

        if (pacienteOpt.isEmpty()) {
            return "redirect:/pacientes/listar";
        }

        model.addAttribute("paciente", pacienteOpt.get());
        model.addAttribute("tratamentos", tratamentoService.listarTodos());

        return "pacientes/adicionar_tratamento";
    }

    @PostMapping("/adicionar-tratamento")
    public String salvarTratamentoPaciente(
            @RequestParam("idPaciente") Long idPaciente,
            @RequestParam("idTratamento") Long idTratamento,
            @RequestParam("dataTratamento") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataTratamento,
            @RequestParam("observacoes") String observacoes) {

        pacienteService.adicionarTratamentoPaciente(idPaciente, idTratamento, dataTratamento, observacoes);
        return "redirect:/pacientes/listar";
    }

    @PostMapping("/api/adicionar-tratamento")
    public ResponseEntity<String> adicionarTratamento(@RequestBody Map<String, Object> request) {
        try {
            Long idPaciente = Long.valueOf(request.get("idPaciente").toString());
            Long idTratamento = Long.valueOf(request.get("idTratamento").toString());
            String observacoes = request.get("observacoes").toString();
            Date dataTratamento = new Date();

            pacienteService.adicionarTratamentoPaciente(idPaciente, idTratamento, dataTratamento, observacoes);
            return ResponseEntity.ok("✅ Tratamento adicionado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(" Erro ao adicionar tratamento: " + e.getMessage());
        }
    }
}
