package io.ragnarok.shield;

import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
    private void handleSignUp(Event event) {
        String user = usernameFieldS.getText();
        String pass = passwordFieldS.getText();
        if(confirmPasswordFieldS.getText().equals(pass)){
            if(event.getEventType().toString().equals("KEY_PRESSED")){
                if(((KeyEvent) event).getCode().equals(KeyCode.ENTER))
                    this.onCloseAddUser(event, user, pass);
            }        
            else if(event.getEventType().toString().equals("ACTION"))
                this.onCloseAddUser(event,user,pass);
        }
    }
    
    
    //Login Screen
    @FXML
    private void handleLogin(Event event) {
       String user = usernameFieldL.getText();
        String pass = passwordFieldL.getText();
        if(event.getEventType().toString().equals("KEY_PRESSED")){
            if(((KeyEvent) event).getCode().equals(KeyCode.ENTER))
                this.onCloseAuthenticate(event, user, pass);
        }        
        else if(event.getEventType().toString().equals("ACTION"))
            this.onCloseAuthenticate(event,user,pass);
    }
    
    @FXML
    private void closeStage(Event event){
      Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
      stage.close();
    }
    
    private void onCloseAuthenticate(Event event, String user, String pass){
        String hashPass = this.MD5(pass);
        closeStage(event);
    }
    
    private void onCloseAddUser(Event event, String user, String pass){
        String hashPass = this.MD5(pass);
        closeStage(event);
    }
    
    private String MD5(String password) {
        try {
        	//Given code creates a MD5 hash result for the given password string
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            //Exception raised when cryptographic algo not available
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
