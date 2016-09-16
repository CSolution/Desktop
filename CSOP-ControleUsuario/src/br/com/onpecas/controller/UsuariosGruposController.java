package br.com.onpecas.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.com.onpecas.helper.*;
import br.com.onpecas.model.*;
import br.com.onpecas.view.CallScene;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

//classe de "controller" que associa o grupo e o usu�rio
public class UsuariosGruposController implements Initializable{

    @FXML Button btnUserInsert, btnUserDelete, btnUserEdit;
    @FXML Button btnGroupInsert, btnGroupDelete, btnGroupEdit, btnGroupPermission;
    @FXML Button btnFiltrar, btnPermissoes, btnLimparFiltro;


    @FXML TableView<Grupo> tblGroup;
    @FXML TableColumn<Grupo, String> clnGroupNome, clnGroupObservacao;

    @FXML ComboBox<Grupo> cboGrupo;
    @FXML TextField txtPesqUser;

    @FXML RadioButton radioNomeCompleto, radioLogin;

    @FXML TableView<Usuario> tblUser;
    @FXML TableColumn<Usuario, String> clnUserNome, clnUserLogin, clnUserGrupo;

    List<Grupo> lstGrupo;
    CallScene callscene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        lstGrupo = new ArrayList<>();
        callscene = new CallScene();

        AtribuirBotoes();
        AtualizarTblGroup();
        AtualizarTblUser();

        //Listener para atualizar os dados das table assim que o segundo scene se fecha
        //Antes de fechar o segundo Scene, o valor da variavel AUXGROUP � mudado, assim o listener ir� detectar e atualizar as tables
        Helper.AUXGROUP.addListener(new ChangeListener<Object>() {
              @Override
              public void changed(ObservableValue<?> observableValue, Object oldValue,
                  Object newValue) {
                  int newValuenovo =Integer.parseInt(newValue.toString());
                  if(newValuenovo == 1){
                      AtualizarTblGroup();
                      AtualizarTblUser();
                      Helper.AUXGROUP.setValue(0);
                  }
              }
            });

        //Listener para atualizar os dados da table de usuarios assim que o segundo scene se fecha
        //Antes de fechar o segundo Scene, o valor da variavel AUXUSER � mudado, assim o listener ir� detectar e atualizar a table
        Helper.AUXUSER.addListener(new ChangeListener<Object>() {
              @Override
              public void changed(ObservableValue<?> observableValue, Object oldValue,
                  Object newValue) {
                  int newValuenovo =Integer.parseInt(newValue.toString());
                  if(newValuenovo == 1){
                      AtualizarTblUser();
                      Helper.AUXUSER.setValue(0);
                  }
              }
            });
        cboGrupo.getItems().addAll(lstGrupo);
    }

    //M�todo para inserir um novo grupo
    public void InsertGroup(){
        callscene.LoadGroup(null);
    }

    //M�todo para apagar um grupo que foi selecionado na Table, caso o mesmo n�o seja nulo
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

    //M�todo que chama a tela de atualiza��o de grupo
    public void UpdateGroup(){
        Grupo grupo = tblGroup.getSelectionModel().getSelectedItem();

        if(grupo != null){
            callscene.LoadGroup(grupo);
        }else{
            Alerta.showError("Erro", "Selecione um usuario.");
        }
    }

    //M�todo para inserir um novo usuario
    public void InsertUser(){
        callscene.LoadUser(null);
    }

    //M�todo para apagar um usuario que foi selecionado na Table, caso o mesmo n�o seja nulo
    public void DeleteUser(){
        Usuario usuario = tblUser.getSelectionModel().getSelectedItem();

        //Esse � um optionpane que retorna 1 caso o bo�po sim tenha sido pressionado
        int result = JOptionPane.showConfirmDialog(null,"Deseja Excluir ? ","Excluir",JOptionPane.YES_NO_CANCEL_OPTION);

        if(result ==JOptionPane.YES_OPTION){
            if(usuario != null){
                Usuario.Delete(usuario);
                AtualizarTblUser();
            }else{
                Alerta.showError("Erro", "Nenhum usuario selecionado");
            }
        }
    }

    //M�todo para atualizar um usuario que foi selecionado na Table, caso o mesmo n�o seja nulo
    public void UpdateUser(){
        Usuario usuario = tblUser.getSelectionModel().getSelectedItem();

        if(usuario != null){
            callscene.LoadUser(usuario);
        }else{
            Alerta.showError("Erro", "Selecione um usuario.");
        }
    }

    //M�todo de associa��o de bot�es com suas respectivas funcionalidades
    public void AtribuirBotoes(){
        btnGroupInsert.setOnAction(l-> InsertGroup());
        btnGroupEdit.setOnAction(l-> UpdateGroup());
        btnGroupDelete.setOnAction(l-> DeleteGroup());
        btnGroupPermission.setOnAction(l-> Permission());

        btnUserInsert.setOnAction(l-> InsertUser());
        btnUserEdit.setOnAction(l-> UpdateUser());
        btnUserDelete.setOnAction(l-> DeleteUser());

        btnLimparFiltro.setOnAction(l-> Filtrar());
    }

    //Metodo que chama a tela de permiss�es passando o grupo que foi selecionado
    private void Permission() {
        Grupo grupo = tblGroup.getSelectionModel().getSelectedItem();

        if(grupo != null){
            callscene.LoadPermission(grupo);
        }else{
            Alerta.showError("Erro", "Nenhum grupo selecionado");
        }
    }

    public void Filtrar(){
        if(!txtPesqUser.getText().isEmpty()){
            if(cboGrupo.getSelectionModel().getSelectedItem()!= null){
                if(radioNomeCompleto.isSelected() && radioLogin.isSelected()){
                    /* TEM NOME DE USUARIO, GRUPO SELECIONADO, NOME E LOGIN */
                }else if(radioNomeCompleto.isSelected()){
                    /* TEM NOME DE USUARIO, GRUPO SELECIONADO E NOME */
                }else if(radioLogin.isSelected()){
                    /* TEM NOME DE USUARIO, GRUPO SELECIONADO E LOGIN */
                }else{
                    /* TEM NOME DE USUARIO E GRUPO SELECIONADO */
                }
            }else{
                /* TEM NOME DE USUARIO SELECIONADO */
            }
        } else if(cboGrupo.getSelectionModel().getSelectedItem()!= null){
            if(radioNomeCompleto.isSelected() && radioLogin.isSelected()){
                /* TEM GRUPO SELECIONADO, NOME E LOGIN */
            }else if(radioNomeCompleto.isSelected()){
                /* TEM GRUPO SELECIONADO, E NOME */
            }else if(radioLogin.isSelected()){
                /* TEM GRUPO SELECIONADO E LOGIN */
            }else{
                /* TEM GRUPO SELECIONADO */
            }
        }else {
            Alerta.showError("Erro", "Selecione o filtro desejado");
        }
    }

    //Metodo para preencher a Table de grupos com os dados do banco
    public void AtualizarTblGroup(){
        clnGroupNome.setCellValueFactory(new PropertyValueFactory<Grupo, String>("nome"));
        clnGroupObservacao.setCellValueFactory(new PropertyValueFactory<Grupo, String>("descricao"));

        lstGrupo = Grupo.Select();
        ObservableList<Grupo> data = FXCollections.observableList(lstGrupo);
        tblGroup.setItems(data);
    }

    //Metodo para preencher a Table de usuarios com os dados do banco
    public void AtualizarTblUser(){
        clnUserNome.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nomeCompleto"));
        clnUserLogin.setCellValueFactory(new PropertyValueFactory<Usuario, String>("login"));
        clnUserGrupo.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nomeGrupo"));

        List<Usuario> lstUsers = Usuario.Select();
        ObservableList<Usuario> data = FXCollections.observableList(lstUsers);

        tblUser.setItems(data);
    }

}
