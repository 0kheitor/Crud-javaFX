package com.template.controller;

import com.template.service.FrameworkService;
import com.template.validator.FrameworkValidator;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.template.model.dao.FrameworkDAO;
import com.template.model.dto.FrameworkDTO;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import com.template.util.DialogUtil;

public class MainController
{

    @FXML private Button btnSalvar;
    @FXML private Button btnDeletar;
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

    //SERVICE
    private FrameworkService service = new FrameworkService();

    //SUPPORT FUNCTIONS ******
    private void updateButtonsStatus() {
        boolean hasEmptyField = isAnyFieldEmpty();
        boolean validId = FrameworkValidator.isValidId(txtID.getText());

        setButtonsStatus(hasEmptyField || !validId);
        btnDeletar.setDisable(!validId);
    }

    private FrameworkDTO getDTO(){
        int id = Integer.parseInt(txtID.getText());
        String name = txtNome.getText();
        String highest_version = txtMaiorVersao.getText();
        String project_type = txtTipoProjeto.getText();
        String tecnology = txtTecnologia.getText();

        FrameworkDTO frameworkDTO = new FrameworkDTO();
        frameworkDTO.setId(id);
        frameworkDTO.setProjectType(project_type);
        frameworkDTO.setName(name);
        frameworkDTO.setHighestVersion(highest_version);
        frameworkDTO.setTecnology(tecnology);

        return frameworkDTO;
    }

    private void logInfo(String message){
        txtArea.appendText( '\n' + message);
    }

    // ******

    //BUTTON CONTROL FUNCTIONS ******
    private void setButtonsStatus(Boolean off){
        //.setDisable(disableDelete); //o delete só depende do ID
        btnAtualizar.setDisable(off);
        btnSalvar.setDisable(off);
    }

    private boolean isAnyFieldEmpty(){
        boolean nameField = txtNome.getText().trim().isEmpty();
        boolean highestVersionField = txtMaiorVersao.getText().trim().isEmpty();
        boolean projectTypeField = txtTipoProjeto.getText().trim().isEmpty();
        boolean tecnologyField = txtTecnologia.getText().trim().isEmpty();
        return nameField || highestVersionField || projectTypeField || tecnologyField;
    }

    private void ClearAction(){
        txtID.clear();
        txtNome.clear();
        txtMaiorVersao.clear();
        txtTipoProjeto.clear();
        txtTecnologia.clear();
        setButtonsStatus(true);
    }

    //******

    @FXML
    private void initialize()
    {
        setButtonsStatus(true);
        btnDeletar.setDisable(true);
        logInfo("<APLICATION>: INITIALIZED.");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTecnology.setCellValueFactory(new PropertyValueFactory<>("tecnology"));
        colHighestVersion.setCellValueFactory(new PropertyValueFactory<>("highestVersion"));
        colProjectType.setCellValueFactory(new PropertyValueFactory<>("projectType"));

        txtID.textProperty().addListener((observable, oldValue, newValue) -> updateButtonsStatus());

        txtNome.textProperty().addListener((observable, oldValue, newValue) -> updateButtonsStatus());
        txtMaiorVersao.textProperty().addListener((observable, oldValue, newValue) -> updateButtonsStatus());
        txtTecnologia.textProperty().addListener((observable, oldValue, newValue) -> updateButtonsStatus());
        txtTipoProjeto.textProperty().addListener((observable, oldValue, newValue) -> updateButtonsStatus());
        carregarFrameworks();
    }

    @FXML
    private void btnSalvarAction(ActionEvent event){
        FrameworkDTO frameworkDTO = getDTO();
        logInfo("<CREATE> CREATED ID " + frameworkDTO.getId());
        service.save(frameworkDTO);
        carregarFrameworks();
    }

    @FXML
    private void btnLimparAction(ActionEvent event){
        ClearAction();
    }

    @FXML
    private void btnAtualizarAction(){
        FrameworkDTO frameworkDTO = getDTO();
        service.update(frameworkDTO);
        logInfo("<UPDATE> UPDATE ON ID " + frameworkDTO.getId());
        carregarFrameworks();
        ClearAction();
    }

    @FXML
    private void btnDeletarAction(){
        int id = Integer.parseInt(txtID.getText());

        logInfo("<DELETE_TRY> ON ID " + id);

        if(DialogUtil.showConfirmation("Tem certeza que deseja realizar esta ação? Esta operação não pode ser desfeita.")){
            logInfo("<DELETE_CONFIRMATION> ON ID " + id);
        }else return;

        service.delete(id);
        carregarFrameworks();
        ClearAction();
    }

    @FXML
    private void carregarFrameworks(){
        ArrayList<FrameworkDTO> frameworksList = service.getAll();
        tblFrameworks.setItems(FXCollections.observableArrayList(frameworksList));

    }

    @FXML
    private void carregarCampos(){
        //SelectModel é a representação interna tabela
        FrameworkDTO frameworkDTO = tblFrameworks.getSelectionModel().getSelectedItem();
        if(frameworkDTO != null){
            txtID.setText(String.valueOf(frameworkDTO.getId()));
            txtNome.setText(frameworkDTO.getName());
            txtMaiorVersao.setText(frameworkDTO.getHighestVersion());
            txtTecnologia.setText(frameworkDTO.getTecnology());
            txtTipoProjeto.setText(frameworkDTO.getProjectType());
        }
    }
}
