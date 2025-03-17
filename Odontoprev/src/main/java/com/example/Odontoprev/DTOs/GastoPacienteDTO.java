package com.example.Odontoprev.DTOs;

public class GastoPacienteDTO {
    private Long idPaciente;
    private String nomePaciente;
    private int totalTratamentos;
    private String custoTotal;

    public GastoPacienteDTO(Long idPaciente, String nomePaciente, int totalTratamentos, String custoTotal) {
        this.idPaciente = idPaciente;
        this.nomePaciente = nomePaciente;
        this.totalTratamentos = totalTratamentos;
        this.custoTotal = custoTotal;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public int getTotalTratamentos() {
        return totalTratamentos;
    }

    public void setTotalTratamentos(int totalTratamentos) {
        this.totalTratamentos = totalTratamentos;
    }

    public String getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(String custoTotal) {
        this.custoTotal = custoTotal;
    }
}
