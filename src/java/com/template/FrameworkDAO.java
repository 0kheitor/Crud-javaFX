package com.template;

import java.sql.*;
import com.template.FrameworkDTO;
import com.template.Conexao;

import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;

public class FrameworkDAO {

    private static final Logger logger = Logger.getLogger(FrameworkDAO.class.getName());

    /**
     * ESSE METODO SAI DA NOMECLATURA PADRAO POIS SE DESTINA SOMETE A MOSTRAR OS
     * DADOS A FUNCIONALIDADE DE RETORNAR OS DTO's FICARIA A CARGO DE
     * getFramework e getAllFrameworks (teoricamente)
     */
    public ArrayList<FrameworkDTO> getAllFrameworks() {
        ArrayList<FrameworkDTO> list = new ArrayList<>();
        //FUNCAO DQL DE VISUALIZAR (R DO CRUD)
        try (Connection c = new Conexao().conectaBD();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM framework")) {

            try (ResultSet rs = ps.executeQuery()) {




                while (rs.next()) {
                    FrameworkDTO dto = new FrameworkDTO();
                    dto.setId(rs.getInt("id"));
                    dto.setHighestVersion(rs.getString("highest_version"));
                    dto.setName(rs.getString("name"));
                    dto.setTecnology(rs.getString("tecnology"));
                    dto.setProjectType(rs.getString("project_type"));

                    list.add(dto);
                }


            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "<ERROR> erro ao listar os usuários", e);
        }

        return list;

    }

    public void postFramework(FrameworkDTO fw) {

        //FUNCAO DML DE INSERÇÃO (C DO CRUD)
        try (Connection c = new Conexao().conectaBD();
             PreparedStatement ps = c.prepareStatement("INSERT INTO framework (name ,tecnology, project_type ,highest_version, id) VALUES(?,?,?,?,?)")) {

            ps.setInt(5, fw.getId());
            ps.setString(1, fw.getName());
            ps.setString(2, fw.getTecnology());
            ps.setString(3, fw.getProjectType());
            ps.setString(4, fw.getHighestVersion());

            int result = ps.executeUpdate();

            if (result == 1) {
                logger.log(Level.FINE, "<POST> SUCCESSO, " + result + " DADO(S) INSERIDO(S)");
            } else {
                logger.log(Level.FINE, "<POST> FALHA, NENHUM DADO FOI INSERIDO");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "<ERROR> " + e.getMessage(), e);
        }

    }

    public void deleteFramework(int id) {
        //FUNCAO DML DE DELETAR (D DO CRUD)

        try (Connection c = new Conexao().conectaBD();
             PreparedStatement ps = c.prepareStatement("DELETE FROM framework WHERE id = ?")) {

            ps.setInt(1, id);
            int result = ps.executeUpdate();

            if (result == 1) {
                logger.log(Level.FINE, "<DELETE> SUCCESSO, " + result + " DADO(S) DELETADO(S)");
            } else {
                logger.log(Level.WARNING, "<DELETE> FALHA, NENHUM DADO FOI DELETADO");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "<ERROR> " + e.getMessage(), e);
        }

    }

    public void updateFramework(FrameworkDTO fw) {
        //FUNCAO DML DE ATUALIZAR (U DO CRUD)

        try (Connection c = new Conexao().conectaBD();
             PreparedStatement ps = c.prepareStatement("UPDATE framework SET name = ?,tecnology = ?, project_type = ?, highest_version = ? WHERE id = ? ")) {

            ps.setString(1, fw.getName());
            ps.setString(2, fw.getTecnology());
            ps.setString(3, fw.getProjectType());
            ps.setString(4, fw.getHighestVersion());
            ps.setInt(5, fw.getId());
            int result = ps.executeUpdate();

            if (result == 1) {
                logger.log(Level.FINE, "<UPDATE> SUCCESSO, " + result + " DADO(S) ATUALIZADO(S)");
            } else {
                logger.log(Level.WARNING, "<UPDATE> FALHA, NENHUM DADO FOI ATUALIZADO");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "<ERROR> " + e.getMessage(), e);
        }

    }

}