package br.com.onpecas.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.onpecas.helper.Alerta;
import br.com.onpecas.model.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class UserController implements Initializable{

	@FXML TextField txtNomeCompleto, txtLogin, txtEmail;

	@FXML ComboBox<Grupo> cboGrupo;

	@FXML PasswordField txtSenha;

	@FXML Button btnCadastrar, btnCancelar;

	Stage myStage;
	Usuario usuario;

	static int OID_USUARIOAUXILIO;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		OID_USUARIOAUXILIO = 0;
		AtribuirBotoes();

		cboGrupo.getItems().addAll(Grupo.Select());

		//Foi criado um objeto do tipo grupo, caso ele seja nulo,
		//quer dizer que a instancia oi feita pelo primeiro modo
		if(usuario != null){
			cboGrupo.getSelectionModel().select(usuario.getGrupo().getOid_grupo());

			PrepararUpdate(usuario);
		}
	}

	public UserController(Stage myStage) {
		// TODO Auto-generated constructor stub
		this.myStage = myStage;
	}

	public UserController(Stage myStage, Usuario usuario) {
		// TODO Auto-generated constructor stub
		this.myStage = myStage;
		this.usuario = usuario;
	}

	public void AtribuirBotoes(){
		btnCadastrar.setOnAction(l-> InserirAtualizarGrupo());
		btnCancelar.setOnAction(l-> myStage.close());
	}

	public void InserirAtualizarGrupo(){
		String nomeCompleto = txtNomeCompleto.getText();
		String login = txtLogin.getText();
		String email = txtEmail.getText();
		String senha = txtSenha.getText();
		Grupo grupo = cboGrupo.getSelectionModel().getSelectedItem();

		int oid_usuario = OID_USUARIOAUXILIO;

		switch (btnCadastrar.getText()){
			case "Cadastrar":
				if(!login.isEmpty() && !senha.isEmpty()){
					if(grupo != null){
						Usuario usuario = new Usuario();

						usuario.setNomeCompleto(nomeCompleto);
						usuario.setEmail(email);
						usuario.setLogin(login);
						usuario.setSenha(senha);
						usuario.setGrupo(grupo);

						Usuario.Insert(usuario);

						myStage.close();
					}else{
						Alerta.showError("N�o � poss�vel cadastrar", "O usuario precisa de um grupo.");
					}

				}else{
					Alerta.showError("N�o � poss�vel cadastrar", "O usuario precisa de pelo menos login e senha.");
				}

				break;
			case "Atualizar":
				if(!login.isEmpty() && !senha.isEmpty()){
					if(grupo != null){
						Usuario usuario = new Usuario();

						usuario.setNomeCompleto(nomeCompleto);
						usuario.setEmail(email);
						usuario.setLogin(login);
						usuario.setSenha(senha);
						usuario.setGrupo(grupo);
						usuario.setOid_usuario(oid_usuario);

						Usuario.Update(usuario);

						myStage.close();
					}else{
						Alerta.showError("N�o � poss�vel atualizar", "O usuario precisa de um grupo.");
					}

				}else{
					Alerta.showError("N�o � poss�vel atualizar", "O usuario precisa de pelo menos login e senha.");
				}
				break;
		}
	}

	public void PrepararUpdate(Usuario usuario){
		txtNomeCompleto.setText(usuario.getNomeCompleto());
		txtLogin.setText(usuario.getLogin());
		txtEmail.setText(usuario.getEmail());
		txtSenha.setText(usuario.getSenha());

		OID_USUARIOAUXILIO = usuario.getOid_usuario();

		this.btnCadastrar.setText("Atualizar");
	}
}