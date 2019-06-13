/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import service.UsuarioService;


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
    private TextField textField_atributoLivro;

    @FXML
    private TextArea textArea_resultadoBusca;

    @FXML
    private Button buttton_pesquisar;

    @FXML
    private Button button_limpar;

    @FXML
    private MenuButton menuButton_atributoLivro;

    @FXML
    private Label label_descricaoBusca;

    private Alert alert;
    
    private UsuarioService usuarioService;
    
    /// MÉTODOS **********************************************************************************
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    void acaoAutenticarLogin() {
        /// REALIZA-SE A CHAMADA DE AUTENTICACAO PARA SERVICO
        
        if( textField_login.getText().equals("admin") && passwordField_senha.getText().equals("legal") )
             System.out.println("CORRETO");
        else{
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro na autenticação de usuário!");
            alert.setContentText("Senha incorreta!");
            alert.show();
        }    
        
    }

    @FXML
    void limpaCaixaDeTexto() {
        textField_atributoLivro.setText("");
    }

    @FXML
    void pesquisaNoBancoEMostra() {

    }
    
    
}
