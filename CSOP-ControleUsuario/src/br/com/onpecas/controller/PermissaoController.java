package br.com.onpecas.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class PermissaoController implements Initializable {

	@FXML CheckBox CMSAcessar;
	@FXML Button btnSalvar,btnCancelar;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		AtribuirBotoes();
		
	}

	public void AtribuirBotoes(){
		
		btnSalvar.setOnAction(l-> InsertPermissao());
		btnCancelar.setOnAction(l-> VoltarTela());
		
	}

	private Object VoltarTela() {
		// TODO Auto-generated method stub
		return null;
	}

	private void InsertPermissao() {
		
		System.out.println(CMSAcessar.isSelected());
		
	}

}
