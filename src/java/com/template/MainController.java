package com.template;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import com.template.FrameworkDAO;
import com.template.FrameworkDTO;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;

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
    @FXML private TableView<FrameworkDTO> tblFrameworks;
    @FXML private TableColumn<FrameworkDTO, Integer>  colId;
    @FXML private TableColumn<FrameworkDTO, String>  colName;
    @FXML private TableColumn<FrameworkDTO, String>  colTecnology;
    @FXML private TableColumn<FrameworkDTO, String>  colHighestVersion;
    @FXML private TableColumn<FrameworkDTO, String>  colProjectType;

    private FrameworkDTO getDTO(){
        int id = Integer.parseInt(txtID.getText());
        String name = txtNome.getText();
        String highest_version = txtMaiorVersao.getText();
        String project_type = txtTipoProjeto.getText();
        String tecnology = txtTecnologia.getText();

        FrameworkDTO dto = new FrameworkDTO();
        dto.setId(id);
        dto.setProjectType(project_type);
        dto.setName(name);
        dto.setHighestVersion(highest_version);
        dto.setTecnology(tecnology);

        return dto;
    }

    @FXML
    private void initialize()
    {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTecnology.setCellValueFactory(new PropertyValueFactory<>("tecnology"));
        colHighestVersion.setCellValueFactory(new PropertyValueFactory<>("projectType"));
        colProjectType.setCellValueFactory(new PropertyValueFactory<>("highestVersion"));
        carregarFrameworks();
    }

    @FXML
    private void btnSalvarAction(ActionEvent event){
        FrameworkDTO dto = getDTO();
        FrameworkDAO dao = new FrameworkDAO();
        dao.postFramework(dto);
        carregarFrameworks();
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
        FrameworkDTO dto = getDTO();
        FrameworkDAO dao = new FrameworkDAO();
        dao.updateFramework(dto);
        carregarFrameworks();
    }

    @FXML
    private void btnDeletarAction(){
        int id = Integer.parseInt(txtID.getText());
        FrameworkDAO dao = new FrameworkDAO();
        dao.deleteFramework(id);
        carregarFrameworks();
    }

    @FXML
    private void carregarFrameworks(){
        FrameworkDAO dao = new FrameworkDAO();
        ArrayList<FrameworkDTO> list = dao.getAllFrameworks();
        tblFrameworks.setItems(FXCollections.observableArrayList(list));
    }

    @FXML
    private void carregarCampos(){
        //SelectModel é a representação interna tabela
        FrameworkDTO dto = tblFrameworks.getSelectionModel().getSelectedItem();
        if(dto != null){
            txtID.setText(String.valueOf(dto.getId()));
            txtNome.setText(dto.getName());
            txtMaiorVersao.setText(dto.getHighestVersion());
            txtTecnologia.setText(dto.getTecnology());
            txtTipoProjeto.setText(dto.getProjectType());
        }
    }
}
