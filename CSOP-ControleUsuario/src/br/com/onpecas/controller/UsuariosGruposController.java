package br.com.onpecas.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.com.onpecas.helper.Alerta;
import br.com.onpecas.model.*;
import br.com.onpecas.view.CSOPControllerUsers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class UsuariosGruposController implements Initializable{

	@FXML Button btnUserInsert, btnUserDelete, btnUserEdit;
	@FXML Button btnGroupInsert, btnGroupDelete, btnGroupEdit;
	@FXML Button btnFiltrar, btnPermissoes;

	@FXML TableView<Grupo> tblGroup;
	@FXML TableColumn<Grupo, String> clnGroupNome, clnGroupObservacao;

	@FXML ComboBox<Grupo> cboGrupo;

	@FXML TableView<Usuario> tblUser;
	@FXML TableColumn<Usuario, String> clnUserNome, clnUserLogin, clnUserGrupo;

	CSOPControllerUsers csop;
	List<Grupo> lstGrupo;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		csop = new CSOPControllerUsers();
		lstGrupo = new ArrayList<>();

		AtribuirBotoes();
		AtualizarTblGroup();
		AtualizarTblUser();

		cboGrupo.getItems().addAll(lstGrupo);
	}

	public void InsertGroup(){
		csop.LoadGroup(null);
	}

	public void DeleteGroup(){
		Grupo grupo = tblGroup.getSelectionModel().getSelectedItem();

		int result = JOptionPane.showConfirmDialog(null,"Deseja Excluir ? ","Excluir",JOptionPane.YES_NO_CANCEL_OPTION);

        if(result ==JOptionPane.YES_OPTION){
        	if(grupo != null){
        		Grupo.Delete(grupo);
        		AtualizarTblGroup();
        	}else{
    			Alerta.showError("Erro", "Nenhum grupo selecionado");
    		}
        }
	}
	public void UpdateGroup(){
		Grupo grupo = tblGroup.getSelectionModel().getSelectedItem();

		if(grupo != null){
			csop.LoadGroup(grupo);
		}else{
			Alerta.showError("Erro", "Selecione um usuario.");
		}
	}

	public void InsertUser(){
		csop.LoadUser(null);
	}

	public void DeleteUser(){
		Usuario usuario = tblUser.getSelectionModel().getSelectedItem();

		int result = JOptionPane.showConfirmDialog(null,"Deseja Excluir ? ","Excluir",JOptionPane.YES_NO_CANCEL_OPTION);

        if(result ==JOptionPane.YES_OPTION){
        	if(usuario != null){
        		Usuario.Delete(usuario);
        		AtualizarTblUser();
        	}else{
    			Alerta.showError("Erro", "Nenhum usuario selecionado");
    		}
        }
	}
	public void UpdateUser(){
		Usuario usuario = tblUser.getSelectionModel().getSelectedItem();

		if(usuario != null){
			csop.LoadUser(usuario);
		}else{
			Alerta.showError("Erro", "Selecione um usuario.");
		}
	}
	public void AtribuirBotoes(){
		btnGroupInsert.setOnAction(l-> InsertGroup());
		btnGroupEdit.setOnAction(l-> UpdateGroup());
		btnGroupDelete.setOnAction(l-> DeleteGroup());

		btnUserInsert.setOnAction(l-> InsertUser());
		btnUserEdit.setOnAction(l-> UpdateUser());
		btnUserDelete.setOnAction(l-> DeleteUser());

	}

	public void AtualizarTblGroup(){
		clnGroupNome.setCellValueFactory(new PropertyValueFactory<Grupo, String>("nome"));
		clnGroupObservacao.setCellValueFactory(new PropertyValueFactory<Grupo, String>("descricao"));

		lstGrupo = new ArrayList<Grupo>();
		//lstGrupo = Grupo.Select();
		ObservableList<Grupo> data = FXCollections.observableList(lstGrupo);
        tblGroup.setItems(data);
	}

	public void AtualizarTblUser(){
		clnUserNome.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nomecompleto"));
		clnUserLogin.setCellValueFactory(new PropertyValueFactory<Usuario, String>("login"));
		clnUserGrupo.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nomeGrupo"));

		List<Usuario> lstGrupo =new ArrayList<Usuario>();;
		//List<Usuario> lstGrupo = Usuario.Select();
		ObservableList<Usuario> data = FXCollections.observableList(lstGrupo);
        tblUser.setItems(data);
	}

}
