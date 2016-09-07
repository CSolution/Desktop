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
	@FXML Button btnFiltrar;

	@FXML TableView<Grupo> tblGroup;
	@FXML TableColumn<Grupo, String> clnGroupNome, clnGroupObservacao;

	CSOPControllerUsers csop;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		csop = new CSOPControllerUsers();
		AtribuirBotoes();

		AtualizarTblGroup();
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
			Alerta.showError("Erro", "Selecione um grupo.");
		}
	}

	public void AtribuirBotoes(){
		btnGroupInsert.setOnAction(l-> InsertGroup());
		btnGroupEdit.setOnAction(l-> UpdateGroup());
		btnGroupDelete.setOnAction(l-> DeleteGroup());
	}

	public void AtualizarTblGroup(){
		clnGroupNome.setCellValueFactory(new PropertyValueFactory<Grupo, String>("nome"));
		clnGroupObservacao.setCellValueFactory(new PropertyValueFactory<Grupo, String>("descricao"));

		List<Grupo> lstGrupo = new ArrayList<>();

		lstGrupo = Grupo.Select();
		ObservableList<Grupo> data = FXCollections.observableList(lstGrupo);
        tblGroup.setItems(data);
	}

}
