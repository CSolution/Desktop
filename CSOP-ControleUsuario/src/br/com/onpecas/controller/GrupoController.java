package br.com.onpecas.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.onpecas.helper.Alerta;
import br.com.onpecas.model.Grupo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GrupoController implements Initializable {

	@FXML Button btnCadastrarGrupo, btnCancelar;
	@FXML TextField txtNome;
	@FXML TextArea txtDescricao;

	Stage myStage;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		AtribuirBotoes();
	}

	public GrupoController(Stage myStage) {
		// TODO Auto-generated constructor stub
		this.myStage = myStage;
	}

	private void AtribuirBotoes() {
		btnCadastrarGrupo.setOnAction(l-> InserirGrupo());
		btnCancelar.setOnAction(l-> myStage.close());
	}

	public void InserirGrupo(){
		String nome = txtNome.getText();
		String descricao = txtDescricao.getText();

		if(!nome.isEmpty()){
			Grupo grupo = new Grupo();

			grupo.setNome(nome);
			grupo.setDescricao(descricao);

			Grupo.Insert(grupo);
		}else{
			Alerta.showError("N�o � poss�vel criar o grupo", "O grupo precisa de um nome para ser criado");
		}
	}
}
