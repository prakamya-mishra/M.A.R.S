package io.ragnarok.shield;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class MainApp extends Application {
    
    static ObservableList<Node> childs;

    @Override
    public void start(Stage stage) throws Exception {
        Parent home = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
        Parent launch = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        
        StackPane stack = new StackPane();
        stack.getChildren().add(home);
        stack.getChildren().add(launch);
        MainApp.childs = stack.getChildren();
        
        Scene scene = new Scene(stack,1366,768);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("Shield");
        stage.getIcons().add(new Image(getClass().getResourceAsStream( "/image/logo.png" ))); 
        stage.minHeightProperty().set(500);
        stage.minWidthProperty().set(650);
        stage.setScene(scene);
        stage.show();
        FadeTransition ft = new FadeTransition(Duration.millis(500),launch);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setDelay(Duration.seconds(1));
        ft.play();
        ft.onFinishedProperty().set(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                MainApp.childs.get(MainApp.childs.size()-1).toBack();
            }
        });
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
