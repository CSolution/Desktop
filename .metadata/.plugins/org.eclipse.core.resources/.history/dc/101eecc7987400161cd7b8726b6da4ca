package br.com.onpecas.view;

import java.io.IOException;

import br.com.onpecas.controller.GrupoController;
import br.com.onpecas.controller.UsuariosGruposController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.*;

public class CSOPControllerUsers extends Application {

	BorderPane border;
	Stage primaryStage;
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;

		LoadBorder();
		LoadMain();
	}

	public void LoadBorder() throws IOException{
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PainelPrincipal.fxml"));

		border = (BorderPane) loader.load();
        Scene scene = new Scene(border);

        primaryStage.setScene(scene);
        primaryStage.show();

	}
	public void LoadMain() throws IOException{
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UsuariosGrupos.fxml"));
        loader.setController(new UsuariosGruposController());

		ScrollPane module= (ScrollPane) loader.load();
		border.setCenter(module);
	}
	public void LoadUser(){
		Stage secondStage = new Stage();

		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UsuarioCRUD.fxml"));
        
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
	public void LoadGroup(){
		Stage secondStage = new Stage();
		
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("GrupoCRUD.fxml"));
        loader.setController(new GrupoController(secondStage));
        
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
	public static void main(String[] args) {
		launch(args);
	}
}
