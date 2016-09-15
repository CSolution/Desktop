package br.com.onpecas.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.com.onpecas.helper.Helper;
import br.com.onpecas.model.*;
import br.com.onpecas.view.CallScene;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PermissaoController implements Initializable {

	@FXML CheckBox CMSAcessar;
	@FXML Button btnSalvar,btnCancelar;
	@FXML Label lbGrupo;

	CallScene scene;
	Stage myStage;
	Grupo grupo;
	Permissao permissao;
	//variavel que serve para verificar se tem registro do grupo nas permiss�es
	Boolean existe;

	int aux;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		VerificarCheckbox(permissao);

		scene = new CallScene();

		AtribuirBotoes();
		lbGrupo.setText(grupo.getNome());

	}

	public PermissaoController(Stage myStage, Grupo grupo) {
		this.myStage = myStage;
		this.grupo = grupo;

		VerificarExistencia(grupo);
	}

	//Chama a fun��o que verifica se o grupo tem permiss�es.Caso o resultado seja true,� chamado a fun��o select,
	//no qual retorna um objeto,caso false,� criado um novo objeto.
	public void VerificarExistencia(Grupo grupo){

		if(Permissao.Buscar(grupo)){

			permissao=Permissao.Select(grupo);
			existe = true;

		}else{

			permissao = new Permissao();
			existe=false;
		}
	}
	public void AtribuirBotoes(){

		btnSalvar.setOnAction(l-> InsertPermissao());
		btnCancelar.setOnAction(l-> VoltarTela());

	}

	private void VoltarTela() {
		Helper.AUXGROUP.setValue(1);
		myStage.close();

	}


	//caso a variavel 'existe' seja false, � inserido um novo registro no banco,caso contr�rio, � apenas atualizado
	private void InsertPermissao() {

		if(CMSAcessar.isSelected()){

			permissao.setAcs_cms(1);

		}else{

			permissao.setAcs_cms(0);

		}

		if(existe){

			Permissao.Update(permissao);

		}else{
		Permissao.Insert(permissao, grupo.getOid_grupo());
		}

		VoltarTela();

	}

	//Fun��o que seta os valores para a checkbox.
	public void VerificarCheckbox(Permissao permissao){

		if(permissao.getAcs_cms() == 1){

			CMSAcessar.setSelected(true);

		}

	}

}
