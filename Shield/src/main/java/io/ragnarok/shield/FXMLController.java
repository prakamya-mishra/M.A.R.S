package io.ragnarok.shield;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class FXMLController implements Initializable {
    
    @FXML
    private TextField usernameFieldL;
    @FXML
    private PasswordField passwordFieldL;
    @FXML
    private TextField usernameFieldS;
    @FXML
    private PasswordField passwordFieldS;
    @FXML
    private PasswordField confirmPasswordFieldS;
    
    //Sign Up Screen
    @FXML
    private void handleSignUpAction(ActionEvent event) {
        System.out.println(usernameFieldS.getText()+","+passwordFieldS.getText()+","+confirmPasswordFieldS.getText());
    }
    
    @FXML
    private void handleSignUpEnter(KeyEvent event){
       if(event.getCode() == KeyCode.ENTER)
        System.out.println(usernameFieldS.getText()+","+passwordFieldS.getText()+","+confirmPasswordFieldS.getText());
    }
    
    //Login Screen
    @FXML
    private void handleLoginAction(ActionEvent event) {
        System.out.println(usernameFieldL.getText()+" "+passwordFieldL.getText());
    }
    
    @FXML
    private void handleLoginEnter(KeyEvent event){
       if(event.getCode() == KeyCode.ENTER)
        System.out.println(usernameFieldL.getText()+" "+passwordFieldL.getText());
    }
    
    @FXML
    private void closeStage(ActionEvent event){
      Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
      stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
