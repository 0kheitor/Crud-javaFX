package com.template;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import com.template.FrameworkDAO;
import com.template.FrameworkDTO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class MainController
{

    @FXML private Button btnSalvar;
    @FXML private Button btnDeletar;
    @FXML private Button btnLimpar;
    @FXML private Button btnEditar;
    @FXML private TextField txtNome;
    @FXML private TextField txtTecnologia;
    @FXML private TextField txtTipoProjeto;
    @FXML private TextField txtMaiorVersao;
    @FXML private TextField txtID;
    @FXML private TableView tblFrameworks;

    @FXML
    private void initialize()
    {
        System.out.println("FXML loaded successfully!");
    }

    @FXML
    private void btnSalvarAction(ActionEvent event){
        int id = Integer.parseInt(txtID.getText());
        String name = txtNome.getText();
        String highest_version = txtMaiorVersao.getText();
        String project_type = txtTipoProjeto.getText();
        String tecnology = txtTecnologia.getText();

        FrameworkDTO dto = new FrameworkDTO();
        //dto.setId(id);
        dto.setProjectType(project_type);
        dto.setName(name);
        dto.setHighestVersion(highest_version);
        dto.setTecnology(tecnology);

        FrameworkDAO dao = new FrameworkDAO();
        dao.postFramework(dto);

        //carregarFrameworks();
    }

    @FXML
    private void btnLimparAction(ActionEvent event){
        txtID.clear();
        txtNome.clear();
        txtMaiorVersao.clear();
        txtTipoProjeto.clear();
        txtTecnologia.clear();
    }

    @FXML
    private void btnAtualizarAction(){
        int id = Integer.parseInt(txtID.getText());
        String name = txtNome.getText();
        String highest_version = txtMaiorVersao.getText();
        String project_type = txtTipoProjeto.getText();
        String tecnology = txtTecnologia.getText();

        FrameworkDTO dto = new FrameworkDTO();
        //dto.setId(id);
        dto.setProjectType(project_type);
        dto.setName(name);
        dto.setHighestVersion(highest_version);
        dto.setTecnology(tecnology);

        FrameworkDAO dao = new FrameworkDAO();
        dao.updateFramework(dto);

        //carregarFrameworks();
    }

    @FXML
    private void btnDeletarAction(){
        int id = Integer.parseInt(txtID.getText());
        FrameworkDAO dao = new FrameworkDAO();
        dao.deleteFramework(id);

        //carregarFramework();
    }

    @FXML
    private void carregarFrameworks(){
        FrameworkDAO dao = new FrameworkDAO();
        ArrayList<FrameworkDTO> list = dao.getAllFrameworks();
        tblFrameworks.setItems(FXCollections.observableArrayList(list));
    }

}
