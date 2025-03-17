package com.example.Odontoprev.DTOs;

public class PacienteTratamentoDTO {
    private Long idPaciente;
    private String nomePaciente;
    private Long idTratamento;
    private String descricaoTratamento;
    private String dataTratamento;
    private String observacoes;

    public PacienteTratamentoDTO(Long idPaciente, String nomePaciente, Long idTratamento, String descricaoTratamento, String dataTratamento, String observacoes) {
        this.idPaciente = idPaciente;
        this.nomePaciente = nomePaciente;
        this.idTratamento = idTratamento;
        this.descricaoTratamento = descricaoTratamento;
        this.dataTratamento = dataTratamento;
        this.observacoes = observacoes;
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

    public Long getIdTratamento() {
        return idTratamento;
    }

    public void setIdTratamento(Long idTratamento) {
        this.idTratamento = idTratamento;
    }

    public String getDescricaoTratamento() {
        return descricaoTratamento;
    }

    public void setDescricaoTratamento(String descricaoTratamento) {
        this.descricaoTratamento = descricaoTratamento;
    }

    public String getDataTratamento() {
        return dataTratamento;
    }

    public void setDataTratamento(String dataTratamento) {
        this.dataTratamento = dataTratamento;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
