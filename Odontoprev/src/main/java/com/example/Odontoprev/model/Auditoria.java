package com.example.Odontoprev.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "AUDITORIA")
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_AUDITORIA")
    private Long id;

    @Column(name = "TABELA_AFETADA")
    private String tabelaAfetada;

    @Column(name = "TIPO_OPERACAO")
    private String tipoOperacao;

    @Column(name = "USUARIO")
    private String usuario;

    @Column(name = "DATA_OPERACAO")
    private LocalDateTime dataOperacao;

    @Column(name = "ID_REGISTRO")
    private Long idRegistro;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTabelaAfetada() { return tabelaAfetada; }
    public void setTabelaAfetada(String tabelaAfetada) { this.tabelaAfetada = tabelaAfetada; }

    public String getTipoOperacao() { return tipoOperacao; }
    public void setTipoOperacao(String tipoOperacao) { this.tipoOperacao = tipoOperacao; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public LocalDateTime getDataOperacao() { return dataOperacao; }
    public void setDataOperacao(LocalDateTime dataOperacao) { this.dataOperacao = dataOperacao; }

    public Long getIdRegistro() { return idRegistro; }
    public void setIdRegistro(Long idRegistro) { this.idRegistro = idRegistro; }
}
