/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.tiposMaterial.Livro;

import service.UsuarioService;
import view.TelaInicial;
import view.TelaSecundaria;


/**
 * FXML Controller class
 */
public class FXMLTelaInicialController implements Initializable {
    
    /// ATRIBUTOS ********************************************************************************
    @FXML
    private Label label_login;

    @FXML
    private TextField textField_login;

    @FXML
    private Label label_senha;

    @FXML
    private Button button_Entrar;

    @FXML
    private Separator separator_loginEbusca;

    @FXML
    private PasswordField passwordField_senha;

    @FXML
    private TextField textField_busca;

    @FXML
    private Button buttton_pesquisar;

    @FXML
    private Button button_limpar;

    @FXML
    private MenuButton menuButton_tipoDeMaterial;

    @FXML
    private MenuItem menuItem_livro;

    @FXML
    private Label label_descricaoBusca;

    @FXML
    private MenuButton menuButton_atributo;

    @FXML
    private MenuItem menuItem_titulo;

    @FXML
    private MenuItem menuItem_autor;

    @FXML
    private MenuItem menuItem_editora;

    @FXML
    private Label label_busca;

    @FXML
    private TableView<Livro> tableView_busca;

    @FXML
    private TableColumn<?, ?> atributo_1;

    @FXML
    private TableColumn<?, ?> atributo_2;

    @FXML
    private TableColumn<?, ?> atributo_3;

    @FXML
    private TableColumn<?, ?> atributo_4;

    @FXML
    private TableColumn<?, ?> atributo_5;

    @FXML
    private TableColumn<?, ?> atributo_6;
    
    private Alert alert;
    private UsuarioService usuarioService;
    
    /// MÉTODOS **********************************************************************************
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuButton_atributo.getItems().clear();
        tableView_busca.getColumns().clear();
        
    }    
    
    @FXML
    void selecionarMaterial(ActionEvent event) {
    
    }
    

    @FXML
    void desAtivadoAutor(ActionEvent event) {
        menuButton_atributo.setText(menuItem_autor.getText());
    }

    @FXML
    void desAtivadoEditora(ActionEvent event) {
        menuButton_atributo.setText(menuItem_editora.getText());

    }
    
    @FXML
    void desAtivadoTitulo(ActionEvent event) {
        menuButton_atributo.setText(menuItem_titulo.getText());

    }
    @FXML
    void limpaCaixaDeTexto() {
        textField_busca.setText("");
        menuButton_atributo.setText("Atributo:");
        menuButton_tipoDeMaterial.setText("Tipo de material:");
    }

    @FXML
    void pesquisaNoBancoEMostra(ActionEvent event) {

    }
    
    @FXML
    void buscaEmLivro(ActionEvent event) {       
        menuButton_tipoDeMaterial.setText(menuItem_livro.getText());
        menuButton_atributo.getItems().clear();
        menuButton_atributo.getItems().add(menuItem_titulo);
        menuButton_atributo.getItems().add(menuItem_autor);
        menuButton_atributo.getItems().add(menuItem_editora);
        
        
        for(int i = 0; i < 8; i++){
            TableColumn<Livro, String> atributo = new TableColumn<>("");
            atributo.setResizable(true);
            tableView_busca.getColumns().add(atributo);
        }
        
    }

    @FXML
    void acaoAutenticarLogin(ActionEvent event) {
        
        if( textField_login.getText().equals("admin") && passwordField_senha.getText().equals("legal") ){
            TelaSecundaria telaSecundaria = new TelaSecundaria();
            fechar();
            try {
                telaSecundaria.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(FXMLTelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro na autenticação de usuário!");
            alert.setContentText("Senha incorreta!");
            alert.show();
        }    

    }


    void pesquisaNoBancoEMostra() {

    }
    
    private void fechar(){
        TelaInicial.getStage().close();
    }
}
