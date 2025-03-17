package com.example.Odontoprev.repository;

import com.example.Odontoprev.DTOs.GastoPacienteDTO;
import com.example.Odontoprev.DTOs.PacienteTratamentoDTO;
import org.hibernate.dialect.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
    public class RelatorioRepository {

        @Autowired
        private JdbcTemplate jdbcTemplate;

        // Relatório de pacientes e tratamentos
        public List<PacienteTratamentoDTO> getRelatorioPacientesTratamentos() {
            return jdbcTemplate.execute((ConnectionCallback<List<PacienteTratamentoDTO>>) conn -> {
                List<PacienteTratamentoDTO> lista = new ArrayList<>();
                try (CallableStatement stmt = conn.prepareCall("{call PKG_RELATORIOS.relatorio_pacientes_tratamentos_proc(?)}")) {
                    stmt.registerOutParameter(1, OracleTypes.CURSOR);
                    stmt.execute();

                    try (ResultSet rs = (ResultSet) stmt.getObject(1)) {
                        while (rs.next()) {
                            lista.add(new PacienteTratamentoDTO(
                                    rs.getLong("id_paciente"),
                                    rs.getString("nome_paciente"),
                                    rs.getLong("id_tratamento"),
                                    rs.getString("descricao_tratamento"),
                                    rs.getString("data_tratamento"),
                                    rs.getString("observacoes")
                            ));
                        }
                    }
                }
                return lista;
            });
        }

        // Relatório de gastos por paciente
        public List<GastoPacienteDTO> getRelatorioGastosPacientes() {
            return jdbcTemplate.execute((ConnectionCallback<List<GastoPacienteDTO>>) conn -> {
                List<GastoPacienteDTO> lista = new ArrayList<>();
                try (CallableStatement stmt = conn.prepareCall("{call PKG_RELATORIOS.relatorio_gastos_pacientes_proc(?)}")) {
                    stmt.registerOutParameter(1, OracleTypes.CURSOR);
                    stmt.execute();

                    try (ResultSet rs = (ResultSet) stmt.getObject(1)) {
                        while (rs.next()) {
                            lista.add(new GastoPacienteDTO(
                                    rs.getLong("id_paciente"),
                                    rs.getString("nome_paciente"),
                                    rs.getInt("total_tratamentos"),
                                    rs.getString("custo_total_formatado")
                            ));
                        }
                    }
                }
                return lista;
            });
        }
    }
