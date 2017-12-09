/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.ragnarok.shield;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Arpan
 */
public class HomeController implements Initializable {
    /**
     * Initializes the controller class.
     */
    @FXML
    Button login_btn;
    @FXML
    Button signUp_btn;
    @FXML
    Button signOut_btn;
    @FXML
    Label user_label;
    
    private void toggleControls(){
        login_btn.setVisible(!login_btn.isVisible());
        signUp_btn.setVisible(!signUp_btn.isVisible());
        signOut_btn.setVisible(!signOut_btn.isVisible());
        user_label.setVisible(!user_label.isVisible());
    }
    
    private void createModal(String fxmlRes) throws IOException{
        Stage modalLogin = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/"+fxmlRes));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        modalLogin.setScene(scene);
        modalLogin.initStyle(StageStyle.UNDECORATED);
        modalLogin.initOwner(login_btn.getScene().getWindow());
        modalLogin.initModality(Modality.APPLICATION_MODAL);
        modalLogin.showAndWait();
        this.toggleControls();
    }
    
    @FXML
    private void loginNav() throws IOException{
        this.createModal("Login.fxml");
    }
    
     @FXML
    private void signUpNav() throws IOException{
        this.createModal("SignUp.fxml");
    }
    
     @FXML
    private void logout(){
       this.toggleControls();
    }
    
    @FXML
    private void refreshSongs(ActionEvent event){
        
    }
    
    @FXML
    private void refreshReco(ActionEvent event){
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO get user logged status and render home screen accordingly
    }    
    
}
