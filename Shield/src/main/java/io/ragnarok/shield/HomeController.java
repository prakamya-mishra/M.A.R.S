/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.ragnarok.shield;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

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
    @FXML
    WebView ytPlayer;
    @FXML
    private ListView songList;
    @FXML
    private ListView recoList;
    @FXML
    private ListView myList;
    @FXML
    Label titleText;
    @FXML
    Label artistText;

    static String user;
    static boolean toggle = false;
    static WebEngine engine;
    private void toggleControls(){
        login_btn.setVisible(!login_btn.isVisible());
        signUp_btn.setVisible(!signUp_btn.isVisible());
        signOut_btn.setVisible(!signOut_btn.isVisible());
        user_label.setText(HomeController.user);
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
        if(toggle)
            this.toggleControls();
        toggle = false;
    }

    public void show(final String[] data){
        String query = data[0]+" - "+data[1];
        titleText.setText("Title: "+data[0]);
        artistText.setText("Artist: "+data[1]);
        String url = Search.search(query);
        String content_Url = "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube-nocookie.com/embed/"+url+"?rel=0&amp;controls=0&amp;showinfo=0\" frameborder=\"0\" gesture=\"media\" allow=\"encrypted-media\" allowfullscreen></iframe>";
        engine.loadContent(content_Url);
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
        Functions.recommendRefresh();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO get user logged status and render home screen accordingly
        engine = ytPlayer.getEngine();
        Callback<ListView<String[]>, javafx.scene.control.ListCell<String[]>>  callback= new Callback<ListView<String[]>, javafx.scene.control.ListCell<String[]>>(){
        @Override
        public ListCell<String[]> call (ListView<String[]> listview){
            return new CustomCell();
        }};
        ChangeListener<String[]> songSelector = new ChangeListener<String[]>() {
            @Override
          public void changed(ObservableValue<? extends String[]> observable,
              String[] oldValue, String[] newValue) {
                        show(newValue);
          }
        };

        String[] data = {"Pink World","Planet P Project",null};
        ObservableList<String[]> dataobv = FXCollections.observableArrayList();
        dataobv.add(data);
        songList.setItems(dataobv);
        songList.setCellFactory(callback);
        songList.getSelectionModel().selectedItemProperty().addListener(songSelector);
        recoList.setCellFactory(callback);
        recoList.getSelectionModel().selectedItemProperty().addListener(songSelector);
        myList.setCellFactory(callback);
        myList.getSelectionModel().selectedItemProperty().addListener(songSelector);
    } 
    
    
    
    
    
    
}
