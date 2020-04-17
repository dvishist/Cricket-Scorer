/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cricketscorer;

import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Vishist
 */
public class CricketScorer extends Application {

    private static Scene scene;
    private ChoiceBox toss, decision;
    static Match match;
    static Team batting;
    static Team bowling;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        scene = new Scene(root);
        stage.getIcons().add(new Image("/cricketscorer/app/icon.png"));
        
        stage.setScene(scene);
        stage.setTitle("ScoreMate by Vishist Singh Dura");
        setOptions(scene);
        stage.show();
        
        
        
    }
    
    public static Scene getScene(){
        return CricketScorer.scene;
    }
       
    
    private void setOptions(Scene scene){
        toss=(ChoiceBox) scene.lookup("#tossBox");
        String st[] = {"Home","Away"}; 
        ObservableList<Object> newList = FXCollections.observableArrayList((Object[]) st);
        toss.setItems(newList);
        decision=(ChoiceBox) scene.lookup("#decisionBox");
        String st1[] = {"Batting","Bowling"}; 
        ObservableList<Object> newList1 = FXCollections.observableArrayList((Object[]) st1);
        decision.setItems(newList1);
        
        ObservableList<String> batNumbers1 = FXCollections.observableArrayList();
        ObservableList<String> batNumbers2 = FXCollections.observableArrayList();
        for(int i =1; i<=6  ; i++){
            batNumbers1.add(Integer.toString(i)+".");
        }
        for(int i =7; i<=12  ; i++){
            batNumbers2.add(Integer.toString(i)+".");
        }
        
        ListView battingNumbers = (ListView) scene.lookup("#battingNumbers");
        ListView battingNumbers2 = (ListView) scene.lookup("#battingNumbers2");
        ListView battingNumbers3 = (ListView) scene.lookup("#battingNumbers3");
        ListView battingNumbers4 = (ListView) scene.lookup("#battingNumbers4");
        battingNumbers.getItems().addAll(batNumbers1);
        battingNumbers2.getItems().addAll(batNumbers2);
        battingNumbers3.getItems().addAll(batNumbers1);
        battingNumbers4.getItems().addAll(batNumbers2);
        
    }
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        launch(args);
    }
    
}
