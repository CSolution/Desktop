package br.com.onpecas.view;

import java.io.IOException;

import br.com.onpecas.controller.*;
import br.com.onpecas.model.Grupo;
import br.com.onpecas.model.Usuario;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;

//Classe que irá carregar as telas 

public class CallScene {
	
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
	
}
