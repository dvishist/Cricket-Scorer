/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cricketscorer;

import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 *
 * @author Vishist
 */
public class Editor {
    private ComboBox teamBox, playerBox;
    private TextField name, runsScored, ballsFaced, fours, sixes, ballsBowled, runsFor, 
            maidens, wickets, extras, teamBalls, teamRuns, teamExtras, teamFours, teamSixes, partnershipRuns, partnershipBalls, overProgress;
    private Button playerSave, teamSave;
    
    public Editor(){
        Scene scene = CricketScorer.getScene();
        teamBox = (ComboBox) scene.lookup("#teamEditorBox");
        Team[] teamList = {CricketScorer.batting, CricketScorer.bowling};
        ObservableList<Object> newList = FXCollections.observableArrayList((Object[]) teamList);
        teamBox.setItems(newList);
        teamBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                setPlayers();
            }
        });
        
        playerBox = (ComboBox) scene.lookup("#playerEditorBox");
        playerBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                loadPlayerValues();
            }
        });
        
        name = (TextField) scene.lookup("#nameEditor");
        runsScored = (TextField) scene.lookup("#runsScoredEditor");
        ballsFaced = (TextField) scene.lookup("#ballsFacedEditor");
        fours = (TextField) scene.lookup("#foursEditor");
        sixes = (TextField) scene.lookup("#sixesEditor");
        ballsBowled = (TextField) scene.lookup("#ballsBowledEditor");
        runsFor = (TextField) scene.lookup("#runsForEditor");
        maidens = (TextField) scene.lookup("#maidenEditor");
        wickets = (TextField) scene.lookup("#wicketsEditor");
        extras = (TextField) scene.lookup("#extrasEditor");
        teamBalls = (TextField) scene.lookup("#teamBallsEditor");
        teamRuns = (TextField) scene.lookup("#teamRunsEditor");
        teamExtras = (TextField) scene.lookup("#teamExtrasEditor");
        teamFours = (TextField) scene.lookup("#teamFoursEditor");
        teamSixes = (TextField) scene.lookup("#teamSixesEditor");
        partnershipRuns = (TextField) scene.lookup("#partnershipRunsEditor");
        partnershipBalls = (TextField) scene.lookup("#partnershipBallsEditor");
        overProgress = (TextField) scene.lookup("#overProgressEditor");
        playerSave = (Button) scene.lookup("#playerSaveButton");
        teamSave = (Button) scene.lookup("#teamSaveButton");
    }
    
    public void setPlayers(){
        Team team = (Team) teamBox.getValue();
        ArrayList<Player> players = team.lineup;
        Object[] playerList = (Object[]) players.toArray();
        ObservableList<Object> newList = FXCollections.observableArrayList((Object[]) playerList);
        playerBox.setItems(newList);
    }
    
    public void loadPlayerValues(){
        Player player = (Player) playerBox.getValue();
        if (playerBox.getValue() instanceof Player) {
            name.setText(player.name);
            runsScored.setText(Integer.toString(player.runsScored));
            ballsFaced.setText(Integer.toString(player.ballsFaced));
            fours.setText(Integer.toString(player.fours));
            sixes.setText(Integer.toString(player.sixes));
            ballsBowled.setText(Integer.toString(player.ballsBowled));
            runsFor.setText(Integer.toString(player.runsFor));
            maidens.setText(Integer.toString(player.maidens));
            wickets.setText(Integer.toString(player.wickets));
            extras.setText(Integer.toString(player.extras));
        }
    }
    
    public void loadTeamValues(int balls, int runs, int extras, int fours, int sixes, int partR, int partB, String overprogress){
        teamBalls.setText(Integer.toString(balls));
        teamRuns.setText(Integer.toString(runs));
        teamExtras.setText(Integer.toString(extras));
        teamFours.setText(Integer.toString(fours));
        teamSixes.setText(Integer.toString(sixes));
        partnershipRuns.setText(Integer.toString(partR));
        partnershipBalls.setText(Integer.toString(partB));
        overProgress.setText(overprogress);
    }
            
    public void savePlayerValues(){
        Player player = (Player) playerBox.getValue();
        player.name = name.getText();
        player.runsScored = Integer.parseInt(runsScored.getText());
        player.ballsFaced = Integer.parseInt(ballsFaced.getText());
        player.fours = Integer.parseInt(fours.getText());
        player.sixes = Integer.parseInt(sixes.getText());
        player.ballsBowled = Integer.parseInt(ballsBowled.getText());
        player.runsFor = Integer.parseInt(runsFor.getText());
        player.maidens = Integer.parseInt(maidens.getText());
        player.wickets = Integer.parseInt(wickets.getText());
        player.extras = Integer.parseInt(extras.getText());
    }
    
    public ArrayList saveTeamValues(){
        ArrayList teamValues = new ArrayList();
        teamValues.add(Integer.parseInt(teamBalls.getText()));
        teamValues.add(Integer.parseInt(teamRuns.getText()));
        teamValues.add(Integer.parseInt(teamExtras.getText()));
        teamValues.add(Integer.parseInt(teamFours.getText()));
        teamValues.add(Integer.parseInt(teamSixes.getText()));
        teamValues.add(Integer.parseInt(partnershipRuns.getText()));
        teamValues.add(Integer.parseInt(partnershipBalls.getText()));
        teamValues.add(overProgress.getText());
        loadPlayerValues();
        return(teamValues);
    }
    
    
}
