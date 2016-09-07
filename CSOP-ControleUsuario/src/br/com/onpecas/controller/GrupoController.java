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
	Grupo grupo;

	static int OID_GRUPOAUXILIO;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		OID_GRUPOAUXILIO = 0;
		AtribuirBotoes();

		//Foi criado um objeto do tipo grupo, caso ele seja nulo,
		//quer dizer que a instancia oi feita pelo primeiro modo
		if(grupo != null){
			PrepararUpdate(grupo);
		}

	}

	//Ter� a op��o de instanciar dois modelos da classe
	//O primeiro modelo s� ser� passado o Stage, para que possa ser fechado depois
	public GrupoController(Stage myStage) {
		// TODO Auto-generated constructor stub
		this.myStage = myStage;
	}

	//O segundo � passado, alem do stage, um objeto do tipo Grupo, para ser usado na edi��o
	public GrupoController(Stage myStage, Grupo grupo) {
		// TODO Auto-generated constructor stub
		this.myStage = myStage;
		this.grupo = grupo;
		//PrepararUpdate(grupo);
	}

	//Esse m�todo serve para atribuir os bot�es � suas funcionalidade
	private void AtribuirBotoes() {
		btnCadastrarGrupo.setOnAction(l-> InserirAtualizarGrupo());
		btnCancelar.setOnAction(l-> myStage.close());
	}

	//Metodo para se comunicar com a classe model e inserir os dados no banco
	public void InserirAtualizarGrupo(){
		String nome = txtNome.getText();
		String descricao = txtDescricao.getText();
		int oid_grupo = OID_GRUPOAUXILIO;

		switch (btnCadastrarGrupo.getText()){
			case "Cadastrar":
				if(!nome.isEmpty()){
					Grupo grupo = new Grupo();

					grupo.setNome(nome);
					grupo.setDescricao(descricao);

					Grupo.Insert(grupo);

					myStage.close();

				}else{
					Alerta.showError("N�o � poss�vel criar o grupo", "O grupo precisa de um nome para ser criado.");
				}

				break;
			case "Atualizar":
				if(!nome.isEmpty()){
					Grupo grupo = new Grupo();

					grupo.setNome(nome);
					grupo.setDescricao(descricao);
					grupo.setOid_grupo(oid_grupo);

					Grupo.Update(grupo);

					myStage.close();

				}else{
					Alerta.showError("N�o � poss�vel criar o grupo", "O grupo precisa de um nome para ser atualizado.");
				}
				break;
		}

	}

	//Metodo que sera usado para orientar na atualiza��o dos dados
	//Esse metodo ir� receber um objeto do tipo Grupo e separara seus componentes para os TextField
	//Tambem mudar� o nome do bot�o para Atualizar
	public void PrepararUpdate(Grupo grupo){

		txtNome.setText(grupo.getNome());
		txtDescricao.setText(grupo.getDescricao());
		OID_GRUPOAUXILIO = grupo.getOid_grupo();

		System.out.println(OID_GRUPOAUXILIO);

		this.btnCadastrarGrupo.setText("Atualizar");
	}
}
