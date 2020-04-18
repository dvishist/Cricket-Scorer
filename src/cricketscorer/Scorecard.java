/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cricketscorer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Vishist
 */
public class Scorecard {
        private static Scene scoreScene;
    
        public static Label fBat, fBowl, sBat,sBowl, fBatTotal,sBatTotal,fBowlExtras,sBowlExtras, teamNames, locationDate, wicketsLabel;
        public static ListView fBatScore, fBowlScore, sBatScore, sBowlScore;
        public static ObservableList fBatList = FXCollections.observableArrayList(); 
        public static ObservableList fBowlList = FXCollections.observableArrayList(); 

    static void setFallOfWickets(boolean chase, int balls) {
        if(!chase){
            wicketsLabel = (Label) scoreScene.lookup("#1stBatWickets");
        }else{
            wicketsLabel = (Label) scoreScene.lookup("#2ndBatWickets");
        }
        wicketsLabel.setText(wicketsLabel.getText() + CricketScorer.batting.runsScored + "/" + CricketScorer.batting.wickets + "(" + (balls / 6) + "." + (balls % 6) + ") - ");
        
    }
        
        public void setup(String names, String location){
            Stage scoreStage = new Stage();
            try {
                Pane root = FXMLLoader.load(getClass().getResource("scorecardFXML.fxml"));
                scoreScene = new Scene(root);
                scoreStage.setScene(scoreScene);
                scoreStage.setTitle("Scorecards");
                scoreStage.getIcons().add(new Image("/cricketscorer/app/icon.png"));
                scoreStage.show();
            } catch (IOException ex) {
                Logger.getLogger(Scorecard.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            teamNames = (Label) scoreScene.lookup("#teamNames");
            locationDate = (Label) scoreScene.lookup("#locationDate");
            teamNames.setText(names);
            locationDate.setText(location);
            
            
            fBat=(Label) scoreScene.lookup("#1stBat");
            fBat.setText(CricketScorer.batting.name);
            fBowl=(Label) scoreScene.lookup("#1stBowl");
            fBowl.setText(CricketScorer.bowling.name);
            sBat=(Label) scoreScene.lookup("#2ndBat");
            sBat.setText(CricketScorer.bowling.name);
            sBowl=(Label) scoreScene.lookup("#2ndBowl");
            sBowl.setText(CricketScorer.batting.name);
            fBatScore = (ListView) scoreScene.lookup("#1stBatScore");
            fBowlScore = (ListView) scoreScene.lookup("#1stBowlScore");
            sBatScore = (ListView) scoreScene.lookup("#2ndBatScore");
            sBowlScore = (ListView) scoreScene.lookup("#2ndBowlScore");
            fBatTotal = (Label) scoreScene.lookup("#1stBatTotal");
            sBatTotal = (Label) scoreScene.lookup("#2ndBatTotal");
            fBowlExtras = (Label) scoreScene.lookup("#1stBowlExtras");
            sBowlExtras = (Label) scoreScene.lookup("#2ndBowlExtras");
        }
        
       public static void updateBattingCard(Team team, boolean chase, int balls) {
        fBatList.clear();
        ArrayList<Player> remaining = (ArrayList<Player>) team.lineup.clone();
        remaining.removeAll(team.batOrder);
        for (Player p : team.batOrder) {
            String line = "";
            double sr = 0;
            String format = "%-18s %-17s %-17s %7s %3s %3s %4s";
            if (p.out.equals("notout")) {
                if(p.ballsFaced>0){
                    sr = (p.runsScored * 1.0 / p.ballsFaced) * 100;
                }
                String sr1 = " "+String.format("%.1f",sr);
                line = String.format(format, p.name.toUpperCase(), "NOT OUT","", (p.runsScored + "(" + p.ballsFaced + ")"), p.fours, p.sixes, sr1);
            } else {
                if(p.ballsFaced>0) sr = (p.runsScored * 1.0 / p.ballsFaced) * 100;
                String sr1 = " " + String.format("%.1f",sr);
                if (p.outMethod.equals("b")) {
                    line = String.format(format, p.name.toUpperCase(),"", (p.outMethod + "."+p.outBy), (((p.runsScored>0) ? p.runsScored : "🦆") + "(" + p.ballsFaced + ")"), p.fours, p.sixes, sr1);
                } else if (p.outMethod.equals("")) {
                    line = String.format(format, p.name.toUpperCase(),"", ("retired hurt"), (p.runsScored + "(" + p.ballsFaced + ")"), p.fours, p.sixes, sr1);
                } 
                
                else if (p.outMethod.equals("lb")) {
                    line = String.format(format, p.name.toUpperCase(),"lbw", ("b."+p.outBy), (((p.runsScored>0) ? p.runsScored : "🦆") + "(" + p.ballsFaced + ")"), p.fours, p.sixes, sr1);
                } 
                
                else if (p.outMethod.equals("runout")) {
                    line = String.format(format, p.name.toUpperCase(), (p.outMethod + "."+ p.outByFielder),"", (((p.runsScored>0) ? p.runsScored : "🦆") + "(" + p.ballsFaced + ")"), p.fours, p.sixes, sr1);
                }else if(p.outMethod.equals("c") && p.outByFielder.equals(p.outBy)) {
                    line = String.format(format, p.name.toUpperCase(), "",("c&b." +p.outBy), (((p.runsScored>0) ? p.runsScored : "🦆") + "(" + p.ballsFaced + ")"), p.fours, p.sixes, sr1);
                } 
                else {
                    line = String.format(format, p.name.toUpperCase(), (p.outMethod +"."+ p.outByFielder),("b." + p.outBy), (((p.runsScored>0) ? p.runsScored : "🦆") + "(" + p.ballsFaced + ")"), p.fours, p.sixes, sr1);
                }
            }

            if (line.length() > 0) {
                fBatList.add(line);
            }
        }
        
        
        
        if(team.wickets==10){
            fBatList.remove(11);
        }
        
        for(Player p : remaining){
            fBatList.add(p.name.toUpperCase());
        }
        String overs = String.valueOf(balls/6)+"."+String.valueOf(balls%6);
        if (!chase) {
            fBatScore.getItems().clear();
            fBatScore.getItems().addAll(fBatList);
            fBatTotal.setText("Total "+team.runsScored+"/"+team.wickets+"    Overs: "+overs);
        } else {
            sBatScore.getItems().clear();
            sBatScore.getItems().addAll(fBatList);
            sBatTotal.setText("Total "+team.runsScored+"/"+team.wickets+"    Overs: "+overs);
        }
        
        
    }
       
    public static void updateBowlingCard(Team team, boolean chase, int extras){
        fBowlList.clear();
        String line="";
        for(Player p: team.lineup){
            if(p.ballsBowled>0){
                String overs = String.valueOf(p.ballsBowled/6)+"."+String.valueOf(p.ballsBowled%6);
                double eco = (p.runsFor*1.0/p.ballsBowled)*6;
                eco = Double.valueOf(String.format("%1.2f",eco));
                line = String.format("%-22s %4s %4s %4s %5s %7s %4s",p.name.toUpperCase(),overs,p.maidens,p.runsFor,p.wickets,eco,p.extras);
            fBowlList.add(line);
            }
            
        }
        if (!chase) {
            fBowlScore.getItems().clear();
            fBowlScore.getItems().addAll(fBowlList);
            fBowlExtras.setText("Extras "+extras);
        } else {
            sBowlScore.getItems().clear();
            sBowlScore.getItems().addAll(fBowlList);
            sBowlExtras.setText("Extras "+extras);
        }
    }
       
}
