package com.template;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.template.FrameworkDAO;
import com.template.FrameworkDTO;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.Optional;

public class MainController
{

    @FXML private Button btnSalvar;
    @FXML private Button btnDeletar;
    @FXML private Button btnLimpar;
    @FXML private Button btnAtualizar;
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
    @FXML private TextArea txtArea;

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

    private void logInfo(String message){
        txtArea.appendText( '\n' + message);
    }

    private void setButtonsStatus(Boolean off){
        //.setDisable(disableDelete); //o delete só depende do ID
        btnAtualizar.setDisable(off);
        btnSalvar.setDisable(off);
    }

    private boolean isAnyFieldEmpty(){
        boolean nameField = txtNome.getText().trim().isEmpty();
        boolean iddField = txtID.getText().trim().isEmpty();
        boolean highestVersionField = txtMaiorVersao.getText().trim().isEmpty();
        boolean projectTypeField = txtTipoProjeto.getText().trim().isEmpty();
        boolean tecnologyField = txtTecnologia.getText().trim().isEmpty();

        return nameField || iddField || highestVersionField || projectTypeField || tecnologyField;
    }

    @FXML
    private void initialize()
    {

        setButtonsStatus(true);
        btnDeletar.setDisable(true);
        logInfo("<APLICATION>: INITIALIZED.");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTecnology.setCellValueFactory(new PropertyValueFactory<>("tecnology"));
        colHighestVersion.setCellValueFactory(new PropertyValueFactory<>("projectType"));
        colProjectType.setCellValueFactory(new PropertyValueFactory<>("highestVersion"));

        txtID.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.trim().isEmpty()) {
                setButtonsStatus(true);
                btnDeletar.setDisable(true);
            } else {
                btnDeletar.setDisable(false);
                if(!isAnyFieldEmpty()) setButtonsStatus(false);
            }
        });

        txtNome.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.trim().isEmpty()) {
                setButtonsStatus(true);
            } else if(!isAnyFieldEmpty()) setButtonsStatus(false);
        });

        txtMaiorVersao.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.trim().isEmpty()) {
                setButtonsStatus(true);
            } else if(!isAnyFieldEmpty()) setButtonsStatus(false);
        });

        txtTecnologia.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.trim().isEmpty()) {
                setButtonsStatus(true);
            } else if(!isAnyFieldEmpty()) setButtonsStatus(false);
        });

        txtTipoProjeto.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.trim().isEmpty()) {
                setButtonsStatus(true);
            } else if(!isAnyFieldEmpty()) setButtonsStatus(false);
        });

        carregarFrameworks();
    }

    @FXML
    private void btnSalvarAction(ActionEvent event){
        FrameworkDTO dto = getDTO();
        FrameworkDAO dao = new FrameworkDAO();
        logInfo("<CREATE> CREATED" + dto.getId());
        dao.postFramework(dto);
        carregarFrameworks();
    }

    private void ClearAction(){
        txtID.clear();
        txtNome.clear();
        txtMaiorVersao.clear();
        txtTipoProjeto.clear();
        txtTecnologia.clear();
        setButtonsStatus(true);
    }

    @FXML
    private void btnLimparAction(ActionEvent event){
        ClearAction();
    }

    @FXML
    private void btnAtualizarAction(){
        FrameworkDTO dto = getDTO();
        FrameworkDAO dao = new FrameworkDAO();
        dao.updateFramework(dto);
        logInfo("<UPDATE> UPDATE ON " + dto.getId());
        carregarFrameworks();
        ClearAction();
    }

    @FXML
    private void btnDeletarAction(){
        int id = Integer.parseInt(txtID.getText());

        logInfo("<DELETE_TRY> ON " + id);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("DELETE CONFIRMATION");
        alert.setHeaderText("Você está prestes a excluir um registro.");
        alert.setContentText("Tem certeza que deseja realizar esta ação? Esta operação não pode ser desfeita.");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            logInfo("<DELETE_CONFIRMATION> ON " + id);
        }else return;

        FrameworkDAO dao = new FrameworkDAO();
        dao.deleteFramework(id);
        carregarFrameworks();
        ClearAction();
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
