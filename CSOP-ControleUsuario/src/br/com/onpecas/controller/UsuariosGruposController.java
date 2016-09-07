package br.com.onpecas.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.onpecas.view.CSOPControllerUsers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class UsuariosGruposController implements Initializable{

	@FXML Button btnUserInsert, btnUserDelete, btnUserEdit;
	@FXML Button btnGroupInsert, btnGroupDelete, btnGroupEdit;
	@FXML Button btnFiltrar;

	CSOPControllerUsers csop;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		csop = new CSOPControllerUsers();
		AtribuirBotoes();
	}

	public void InsertGroup(){
		csop.LoadGroup();
	}
	
	public void AtribuirBotoes(){
		btnGroupInsert.setOnAction(l-> InsertGroup());
	}

}
