/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.ragnarok.shield;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

/**
 *
 * @author Arpan
 */
public class CustomCell extends ListCell<String[]>{
   FXMLLoader fxloader ;
    CellController controller;
    CustomCell(String fxmlUrl){
        fxloader = new FXMLLoader(getClass().getResource(fxmlUrl));
           try{
               fxloader.load();
            } catch (IOException ex) {
                Logger.getLogger(CustomCell.class.getName()).log(Level.SEVERE, null, ex);
            }
        controller = fxloader.getController();
    }
    @Override
    public void updateItem(String[] data, boolean empty){
        super.updateItem(data,empty);
        if(data!=null){
            controller.setCell(data);
            setGraphic(controller.getVBox());
        }
        else if(empty){
            setGraphic(null);
            setText(null);
        }
        }
    }
    

