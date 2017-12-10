/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.ragnarok.shield;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Arpan
 */
public class CellController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @ FXML
    Label titleText;
    @FXML
    Label artistText;
    @FXML
    ImageView thumb;
    @FXML
    VBox cell;
    
    public void setCell(String[] data){
            titleText.setText(data[0]);
            artistText.setText(data[1]);
            if(data[2]!=null){
                Image image = new Image(getClass().getResource("/images/"+data[2]).toString());
                thumb.setImage(image);
            }
        }
    @FXML
    private void hover(){
        cell.setStyle(" -fx-background-color: #cccccc;");
    }
    
    @FXML
    private void leave(){
        cell.setStyle(" -fx-background-color: transparent;");
    }

    @FXML
    private void Like(){
        String songName = titleText.getText();
        String artistName = artistText.getText();
        String[] metaData = {songName,artistName};
        Functions.Like(metaData);
    }

    @FXML
    private void disLike(){
        String songName = titleText.getText();
        String artistName = artistText.getText();
        String[] metaData = {songName,artistName};
        Functions.disLike(metaData);
    }

    @FXML
    private void addToMySongs(){
        String songName = titleText.getText();
        String artistName = artistText.getText();
        String[] metaData = {songName,artistName,HomeController.getUser()};
        Functions.songAdd(metaData);
        HomeController.refreshMySong();
    }

    @FXML
    private void removeFromMySongs(){
        String songName = titleText.getText();
        String artistName = artistText.getText();
        String[] metaData = {songName,artistName,HomeController.getUser()};
        Functions.songDelete(metaData);
        HomeController.refreshMySong();
    }
    
    @FXML
    public Node getVBox(){
        return cell;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
}
