package br.com.onpecas.view;

import java.io.IOException;

import br.com.onpecas.controller.*;
import br.com.onpecas.model.Grupo;
import br.com.onpecas.model.Usuario;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.*;

//Classe que serve para carregar e controlar as telas

public class CallScene {

	/*Esse m�todo serve para carregar a tela de controle de permiss�es*/
	public void LoadPermission(Grupo grupo){

		Stage secondStage = new Stage();

		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Permissoes.fxml"));

	    loader.setController(new PermissaoController(secondStage, grupo));
		try {
			ScrollPane module= (ScrollPane) loader.load();

			Scene scene = new Scene(module);

			secondStage.setScene(scene);
			secondStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*Esse m�todo serve para carregar a tela que insere e atualiza grupo
	 * Ela recebe um objeto do tipo grupo,
	 * caso o objeto seja nulo, o controller ir� inserir, caso contr�rio, ele ir� atualizar
	 * PS: � criada uma nova tela (Stage)
	 * */
	public  void LoadGroup(Grupo grupo){

		Stage secondStage = new Stage();

		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("GrupoCRUD.fxml"));


		if(grupo == null){

	        loader.setController(new GrupoController(secondStage));
		}else{

	        loader.setController(new GrupoController(secondStage, grupo));
		}

		AnchorPane module;
		try {
			module = (AnchorPane) loader.load();

			Scene scene = new Scene(module);

			secondStage.setScene(scene);
			secondStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*Esse m�todo serve para carregar a tela que insere e atualiza usuario
	 * Ela recebe um objeto do tipo Usuario,
	 * caso o objeto seja nulo, o controller ir� inserir, caso contr�rio, ele ir� atualizar
	 * PS: � criada uma nova tela (Stage)
	 * */
	public void LoadUser(Usuario usuario){

		Stage secondStage = new Stage();

		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UsuarioCRUD.fxml"));

        if(usuario == null){

	        loader.setController(new UserController(secondStage));
		}else{

	        loader.setController(new UserController(secondStage, usuario));
		}

		AnchorPane module;
		try {
			module = (AnchorPane) loader.load();

			Scene scene = new Scene(module);

			secondStage.setScene(scene);
			secondStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	//Esse m�todo serve para carregar a tela inicial do m�dulo Controle de Usuario
			public void LoadMain(BorderPane border) throws IOException{

				FXMLLoader loader = new FXMLLoader();
		        loader.setLocation(getClass().getResource("UsuariosGrupos.fxml"));
		        loader.setController(new UsuariosGruposController());

				ScrollPane module= (ScrollPane) loader.load();
				border.setCenter(module);
			}

}
