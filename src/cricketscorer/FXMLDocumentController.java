/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cricketscorer;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Vishist
 */
public class FXMLDocumentController implements Initializable {
   
    private boolean chase = false;
    private int target;
    private int balls = 0;
    private double runRate=0;
    private int extras=0;
    private double projected=0;
    private ArrayList<Player> battingLineup;
    private Player striker;
    private Player nonStriker;
    private int partnershipRuns = 0;
    private int partnershipBalls = 0;
    private int fours = 0;
    private int sixes = 0;
    
    
    private Label scoreLabel,batsman1Name,batsman2Name,batsman1Score,batsman2Score ;
    private TextField oversLabel;
    private TextField runRateLabel;
    private TextField projectedLabel;
    private TextField extrasLabel ;
    private ComboBox bowlerBox;
    private Object[] bowlersList;
    private TextArea bowlerScore;
    private RadioButton batsman1Radio,batsman2Radio,ballRadio,noBallRadio,wideRadio;
    private TextField runsInput;
    public Label targetLabel,runChaseLabel,reqRunRateLabel, overProgress, boundary, partnership;
    private ComboBox bowlerBox2;
    private ComboBox bowlerBox1, batsmenSelector, newBatsmanBox;
    private TextField awaySubField;
    private TextField homeSubField;
    private Player awaySubFielder;
    private Player homeSubFielder;
    private Button newBatsmanButton;
    private ArrayList<Player> battingLineup1;
    private Editor editor;
    private Label locationDate;
    private Label namesLabel;
    private Player allOutPlayer;
    private int oldBalls = 0;
    
    
    @FXML
    private void saveLineups(ActionEvent event){
        scoreLabel = (Label) CricketScorer.getScene().lookup("#score");
        oversLabel = (TextField) CricketScorer.getScene().lookup("#scoresFieldOvers");
        runRateLabel= (TextField) CricketScorer.getScene().lookup("#scoresFieldRR");
        projectedLabel= (TextField) CricketScorer.getScene().lookup("#scoresFieldProjected");
        extrasLabel = (TextField) CricketScorer.getScene().lookup("#scoresFieldExtras");
        batsman1Radio = (RadioButton) CricketScorer.getScene().lookup("#batsman1Radio");
        batsman2Radio = (RadioButton) CricketScorer.getScene().lookup("#batsman2Radio");
        ballRadio = (RadioButton) CricketScorer.getScene().lookup("#ballRadio");
        noBallRadio = (RadioButton) CricketScorer.getScene().lookup("#noBallRadio");
        wideRadio = (RadioButton) CricketScorer.getScene().lookup("#wideRadio");
        batsman1Name = (Label) CricketScorer.getScene().lookup("#batsman1");
        batsman2Name = (Label) CricketScorer.getScene().lookup("#batsman2");
        batsman1Score = (Label) CricketScorer.getScene().lookup("#batsman1Score");
        batsman2Score = (Label) CricketScorer.getScene().lookup("#batsman2Score");
        targetLabel = (Label) CricketScorer.getScene().lookup("#targetLabel");
        runChaseLabel = (Label) CricketScorer.getScene().lookup("#runChase");
        reqRunRateLabel = (Label) CricketScorer.getScene().lookup("#reqRun");
        overProgress =  (Label) CricketScorer.getScene().lookup("#overProgress");
        boundary = (Label) CricketScorer.getScene().lookup("#boundary");
        partnership = (Label) CricketScorer.getScene().lookup("#partnership");
        
        bowlerBox = (ComboBox) CricketScorer.getScene().lookup("#bowlerBox");
        bowlerBox1 = (ComboBox) CricketScorer.getScene().lookup("#bowlerBox1");
        bowlerBox2= (ComboBox) CricketScorer.getScene().lookup("#bowlerBox2");
        batsmenSelector = (ComboBox) CricketScorer.getScene().lookup("#batsmenSelector");
        newBatsmanBox = (ComboBox) CricketScorer.getScene().lookup("#newBatsmanBox");
        newBatsmanButton = (Button) CricketScorer.getScene().lookup("#newBatsmanButton");
        bowlerScore = (TextArea) CricketScorer.getScene().lookup("#bowlerScore");
        
        homeSubField = (TextField) CricketScorer.getScene().lookup("#homeSub");
        awaySubField = (TextField) CricketScorer.getScene().lookup("#awaySub");
        
        createTeams();
        createPlayers();
        setupData();
        startGame();
    }
    
    @FXML
    private void createTeams(){
        TextField home = (TextField) CricketScorer.getScene().lookup("#homeField");
        TextField away = (TextField) CricketScorer.getScene().lookup("#awayField");
        String homeName = home.getText();
        String awayName = away.getText();
        TextField homeA = (TextField) CricketScorer.getScene().lookup("#homeAbb");
        TextField awayA = (TextField) CricketScorer.getScene().lookup("#awayAbb");
        String homeAbb = homeA.getText();
        String awayAbb = awayA.getText();
        TextField locationA = (TextField) CricketScorer.getScene().lookup("#locationField");
        String location = locationA.getText();
        TextField oversA = (TextField) CricketScorer.getScene().lookup("#oversField");
        String over = oversA.getText();
        int overs = Integer.parseInt(over); 
        CricketScorer.match = new Match(location,new Team(homeName,homeAbb),new Team(awayName,awayAbb), overs);
        
    }
    
    
    @FXML
    private void createPlayers(){
        String homeid = "#home";
        String awayid = "#away";
        for (int i = 1; i<=11; i++){
            TextField homeField = (TextField) CricketScorer.getScene().lookup(homeid+i);
            String homeName = homeField.getText();
            TextField awayField = (TextField) CricketScorer.getScene().lookup(awayid+i);
            
            homeSubFielder = new Player(homeSubField.getText());
            awaySubFielder = new Player(awaySubField.getText());        
            
            String awayName = awayField.getText();
            CricketScorer.match.home.addPlayer(new Player(homeName));
            CricketScorer.match.away.addPlayer(new Player(awayName));
        }
        
    }
    
    @FXML
    private void setupData(){
        namesLabel = (Label) CricketScorer.getScene().lookup("#teamNames");
        locationDate = (Label) CricketScorer.getScene().lookup("#locationDate");
        namesLabel.setText(CricketScorer.match.home.name.toUpperCase()+"  -VS-  "+CricketScorer.match.away.name.toUpperCase());
        locationDate.setText(CricketScorer.match.overs +"overs     "+ CricketScorer.match.location+"      "+ new SimpleDateFormat("dd/MM/yyyy").format(new Date()));        
        
    }
    
    @FXML
    private void startGame(){
        ChoiceBox toss = (ChoiceBox) CricketScorer.getScene().lookup("#tossBox");
        ChoiceBox decision = (ChoiceBox) CricketScorer.getScene().lookup("#decisionBox");
        if(toss.getValue()=="Home"){
            if(decision.getValue()=="Batting"){
                CricketScorer.batting = CricketScorer.match.home;
                CricketScorer.bowling = CricketScorer.match.away;
            }
            else{
                CricketScorer.batting = CricketScorer.match.away;
                CricketScorer.bowling = CricketScorer.match.home;
            }
        }
        else if (toss.getValue()=="Away"){
            if(decision.getValue()=="Batting"){
                CricketScorer.batting = CricketScorer.match.away;
                CricketScorer.bowling = CricketScorer.match.home;
            }
            else{
                CricketScorer.batting = CricketScorer.match.home;
                CricketScorer.bowling = CricketScorer.match.away;
            }
        }
        
        Scorecard scorecard = new Scorecard();
        scorecard.setup(namesLabel.getText(),locationDate.getText());
        setSides();  
        updateScore();
        setBatsmen();
        
    }
    
    private void setSides(){
        
        Label inningsLabel = (Label) CricketScorer.getScene().lookup("#innings");
        if(!chase){
            inningsLabel.setText("1st Innings");
        }else{
            inningsLabel.setText("2nd Innings");
        }
        this.battingLineup=(ArrayList<Player>) CricketScorer.batting.lineup.clone();
        allOutPlayer = new Player("ALL OUT");
        this.battingLineup.add(allOutPlayer);
        
        this.striker=this.battingLineup.get(1);
        this.nonStriker=this.battingLineup.get(0);
        this.battingLineup.remove(this.striker);
        this.battingLineup.remove(this.nonStriker);
        CricketScorer.batting.batOrder.add(striker);
        CricketScorer.batting.batOrder.add(nonStriker);
        this.striker.out="notout";
        this.nonStriker.out="notout";
        
        setBatsmenList();
               
        Player subFielder;
        if (CricketScorer.bowling==CricketScorer.match.away){
            subFielder = awaySubFielder;
        } else {
            subFielder = homeSubFielder;
        }
        
        CricketScorer.bowling.lineup.add(subFielder);
        setBowlerBoxes();
        CricketScorer.bowling.lineup.remove(subFielder);
        
        bowlersList = (Object[]) CricketScorer.bowling.lineup.toArray();
        ObservableList<Object> newList1 = FXCollections.observableArrayList((Object[]) bowlersList);
        bowlerBox.setItems(newList1);
        
        bowlerBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                updateBowlerScore();
                overProgress.setText("");
            }
        });
        editor = new Editor();
    }

    public void setBowlerBoxes() {
        bowlersList = (Object[]) CricketScorer.bowling.lineup.toArray();
        ObservableList<Object> newList = FXCollections.observableArrayList((Object[]) bowlersList);
        bowlerBox.setItems(newList);
        bowlerBox1.setItems(newList);
        bowlerBox2.setItems(newList);
    }
    
    @FXML
    private void updateScore(){
        //Scores
        scoreLabel.setText(CricketScorer.batting.abbreviation.toUpperCase()+" "+CricketScorer.batting.runsScored+"-"+CricketScorer.batting.wickets);
        oversLabel.setText((this.balls/6)+"."+(this.balls%6));
        if(this.balls>0){
            runRate = ((CricketScorer.batting.runsScored*1.0)/this.balls)*6;
            String runRate1 = String.format("%.2f", runRate);
            runRateLabel.setText(runRate1);
            projected = (runRate*CricketScorer.match.overs);
            int projected1 = (int) Math.round(projected);
            projectedLabel.setText(String.valueOf(projected1));
        }
            extrasLabel.setText(String.valueOf(extras));
        
        //batsmen
        updateBatsmenScore();
        
        //bowler
        updateBowlerScore();
        
        boundary.setText("Boundaries: "+fours+" Fours    "+sixes+" Sixes");
        partnership.setText("Partnership: "+partnershipRuns+" Runs From "+partnershipBalls+" Balls");
        
        if(this.balls%6==0){
            changeStrike();
            boolean found = false;
            for(int i = 0; i<overProgress.getText().length();i++){
                char ch = overProgress.getText().charAt(i);
                if(ch=='W' || ch == '.' || ch==' '){
                    
                }else{
                    found = true;
                }
            }
            if(!found && checkBowler()!=null && checkBowler().ballsBowled>0){
                checkBowler().maidens++;
            }
            
        }
       
        
        
        
        if(chase){
            if(CricketScorer.batting.runsScored>=target){endOfInnings();}
            targetLabel.setVisible(true);
            targetLabel.setText("TARGET "+String.valueOf(target));
            int reqRuns = target-CricketScorer.batting.runsScored;
            int remBalls = ((CricketScorer.match.overs*6)-balls);
            runChaseLabel.setText("Need "+(reqRuns)+" Runs From "+remBalls+" Balls");
            double reqRR = (reqRuns*1.0/remBalls)*6;
            String reqRR1 = String.format("%.2f", reqRR);
            reqRunRateLabel.setText("Req. Run-Rate "+reqRR1);
            ProgressBar chaseBar = (ProgressBar) CricketScorer.getScene().lookup("#chaseBar");
            double progress = (CricketScorer.batting.runsScored*1.0)/target;
            chaseBar.setProgress(progress);      
        } else{
            runChaseLabel.setText("Will Be Unlocked In");
            reqRunRateLabel.setText("Second Innings");
            
        }
        Scorecard.updateBattingCard(CricketScorer.batting,chase,balls);
        Scorecard.updateBowlingCard(CricketScorer.bowling,chase,extras);
        editor.loadTeamValues(balls, CricketScorer.batting.runsScored, extras, fours, sixes, this.partnershipRuns, this.partnershipBalls, overProgress.getText());
        
        
        int totalBalls =CricketScorer.match.overs*6; 
        if(balls == totalBalls){
            endOfInnings();
        }
        
        if(CricketScorer.batting.wickets==10) endOfInnings();
    }
    
    

    public void updateBatsmenScore(){    
        batsman1Name.setText(striker.name.toUpperCase());
        batsman2Name.setText(nonStriker.name.toUpperCase());
        double strikeRate1,strikeRate2;
        String strikeRate11, strikeRate22;
        if(striker.ballsFaced>0) {
            strikeRate1 = (striker.runsScored*100.0)/striker.ballsFaced;
            strikeRate11 = String.format("%.2f", strikeRate1);
        }else{strikeRate11="0";}
        batsman1Score.setText(striker.runsScored+"("+striker.ballsFaced+")"+"        4s: "+striker.fours+"  6s: "+striker.sixes+"  SR:"+strikeRate11);
        if(nonStriker.ballsFaced>0) {
            strikeRate2 = (nonStriker.runsScored*100.0)/nonStriker.ballsFaced;
            strikeRate22 = String.format("%.2f", strikeRate2);
        }else{strikeRate22="0";}
        batsman2Score.setText(nonStriker.runsScored+"("+nonStriker.ballsFaced+")"+"        4s: "+nonStriker.fours+"  6s: "+nonStriker.sixes+"  SR:"+strikeRate22);
    }
    
    public void updateBowlerScore(){
        Player selectedBowler = (Player) bowlerBox.getValue();
        if (selectedBowler instanceof Player){
            String oversBowled = String.valueOf((selectedBowler.ballsBowled/6)+"."+(selectedBowler.ballsBowled%6));
            double economy;
            String economy1;
            if(selectedBowler.ballsBowled>0){
                economy = ((selectedBowler.runsFor*1.0)/selectedBowler.ballsBowled)*6;
                economy1 = String.format("%.2f", economy);
            }
            else{economy1 = "0.0";}
            Label bowlerName = (Label) CricketScorer.getScene().lookup("#BowlerName");
            bowlerName.setText(selectedBowler.name.toUpperCase());
            bowlerScore.setText(selectedBowler.wickets+"/"+selectedBowler.runsFor+"\nOvers: "+oversBowled+"\nEconomy: "+economy1+"\nExtras: "+selectedBowler.extras);
        }
    }
    
    
    public void changeStrike(){
        if(balls != oldBalls){
            Player temp = striker;
            striker = nonStriker;
            nonStriker = temp;
            updateBatsmenScore();
        }
        oldBalls = balls;
    }
    
    public void changeStrikeButton() {
        Player temp = striker;
        striker = nonStriker;
        nonStriker = temp;
        updateBatsmenScore();

    }
    
    public Player checkBowler(){
        return (Player) bowlerBox.getValue();
    }
    
    public void overProgress(String runs){
        String text = overProgress.getText();
        overProgress.setText(text+runs+" ");
    }
    
    public void playBall(){
        runsInput = (TextField) CricketScorer.getScene().lookup("#runsInput");
        if(ballRadio.isSelected()){
            balls++;
            this.striker.ballsFaced++;
            CricketScorer.batting.runsScored+=Integer.parseInt(runsInput.getText());               
            partnershipBalls++;
            partnershipRuns+=Integer.parseInt(runsInput.getText());               
            this.striker.runsScored+=Integer.parseInt(runsInput.getText());
            Player bowler = checkBowler();
            bowler.ballsBowled++;
            bowler.runsFor+=Integer.parseInt(runsInput.getText());
            overProgress(String.valueOf(runsInput.getText()));
        }
        else if(noBallRadio.isSelected()){
            CricketScorer.batting.runsScored+=Integer.parseInt(runsInput.getText());
            CricketScorer.batting.runsScored++;
            partnershipRuns++;
            partnershipRuns+=Integer.parseInt(runsInput.getText());               
            extras++;
            this.striker.runsScored+=Integer.parseInt(runsInput.getText());
            Player bowler = checkBowler();
            bowler.runsFor+=Integer.parseInt(runsInput.getText());
            bowler.runsFor++;
            bowler.extras++;
            overProgress(String.valueOf(runsInput.getText()+"nb"));
        }
        else if(wideRadio.isSelected()){
            CricketScorer.batting.runsScored+=Integer.parseInt(runsInput.getText());               
            CricketScorer.batting.runsScored++;
            extras+=Integer.parseInt(runsInput.getText());
            extras++;
            partnershipRuns++;
            partnershipRuns+=Integer.parseInt(runsInput.getText());               
            Player bowler = checkBowler();
            bowler.runsFor+=Integer.parseInt(runsInput.getText());
            bowler.runsFor++;
            bowler.extras+=Integer.parseInt(runsInput.getText());
            bowler.extras++;
            overProgress(String.valueOf(runsInput.getText()+"wd"));
        }
        if(Integer.parseInt(runsInput.getText())%2!=0){
            changeStrike();
        }
        reloadPage();
    }
    
    public void dotBall(){
        if(ballRadio.isSelected()){
            balls++;
            partnershipBalls++;
            this.striker.ballsFaced++;              
            Player bowler = checkBowler();
            bowler.ballsBowled++;
            overProgress(".");
        }
        else if(noBallRadio.isSelected()){
            CricketScorer.batting.runsScored++;
            extras++;
            partnershipRuns++;
            Player bowler = checkBowler();
            bowler.runsFor++;
            bowler.extras++;
            overProgress("nb");
        }
        else if(wideRadio.isSelected()){              
            CricketScorer.batting.runsScored++;
            extras++;
            partnershipRuns++;
            Player bowler = checkBowler();
            bowler.runsFor++;
            bowler.extras++;
            overProgress("wd");
        }
        reloadPage();
    }
    
    public void single(){
        if(ballRadio.isSelected()){
            balls++;
            this.striker.ballsFaced++;
            CricketScorer.batting.runsScored++;               
            this.striker.runsScored++;
            Player bowler = checkBowler();
            bowler.ballsBowled++;
            bowler.runsFor++;
            overProgress("1");
            partnershipRuns++;
            partnershipBalls++;
        }
        else if(noBallRadio.isSelected()){
            CricketScorer.batting.runsScored+=2;               
            this.striker.runsScored++;
            Player bowler = checkBowler();
            bowler.runsFor+=2;
            extras++;
            bowler.extras++;
            overProgress("1nb");
            partnershipRuns+=2;
            
        }
        else if(wideRadio.isSelected()){
            CricketScorer.batting.runsScored+=2;               
            Player bowler = checkBowler();
            bowler.runsFor+=2;
            bowler.extras+=2;
            overProgress("1wd");
            extras+=2;
            partnershipRuns+=2;
        }
        if(balls%6==0)oldBalls = balls;
        changeStrike();
        reloadPage();
    }
    
    public void doubleRun(){
        if(ballRadio.isSelected()){
            balls++;
            this.striker.ballsFaced++;
            CricketScorer.batting.runsScored+=2;               
            this.striker.runsScored+=2;
            Player bowler = checkBowler();
            bowler.ballsBowled++;
            bowler.runsFor+=2;
            overProgress("2");
            partnershipRuns+=2;
            partnershipBalls++;
        }
        else if(noBallRadio.isSelected()){
            CricketScorer.batting.runsScored+=3;               
            this.striker.runsScored+=2;
            partnershipRuns+=3;
            extras++;
            Player bowler = checkBowler();
            bowler.runsFor+=3;
            bowler.extras++;
            overProgress("2nb");
        }
        else if(wideRadio.isSelected()){
            CricketScorer.batting.runsScored+=3;               
            partnershipRuns+=3;
            Player bowler = checkBowler();
            bowler.runsFor+=3;
            extras+=3;
            bowler.extras+=3;
            overProgress("2wd");
        }
        reloadPage();
    }
    
    public void fourRun(){
        if(ballRadio.isSelected()){
            balls++;
            this.striker.ballsFaced++;
            CricketScorer.batting.runsScored+=4;               
            this.striker.runsScored+=4;
            partnershipRuns+=4;
            partnershipBalls++;
            fours++;
            Player bowler = checkBowler();
            bowler.ballsBowled++;
            bowler.runsFor+=4;
            overProgress("4");
        }
        else if(noBallRadio.isSelected()){
            CricketScorer.batting.runsScored+=5;               
            this.striker.runsScored+=4;
            partnershipRuns+=5;
            fours++;
            extras++;
            Player bowler = checkBowler();
            bowler.runsFor+=5;
            bowler.extras++;
            overProgress("4nb");
        }
        else if(wideRadio.isSelected()){
            CricketScorer.batting.runsScored+=5;               
            Player bowler = checkBowler();
            partnershipRuns+=5;
            extras+=5;
            partnershipRuns+=5;
            bowler.runsFor+=5;
            bowler.extras+=5;
            overProgress("4wd");
        }
        this.striker.fours++;
        reloadPage();
    }
    
    public void sixRun(){
        if(ballRadio.isSelected()){
            balls++;
            this.striker.ballsFaced++;
            CricketScorer.batting.runsScored+=6;               
            this.striker.runsScored+=6;
            partnershipRuns+=6;
            partnershipBalls++;
            Player bowler = checkBowler();
            bowler.ballsBowled++;
            bowler.runsFor+=6;
            overProgress("6");
            sixes++;
        }
        else if(noBallRadio.isSelected()){
            CricketScorer.batting.runsScored+=7;               
            this.striker.runsScored+=6;
            partnershipRuns+=7;
            sixes++;
            extras++;
            Player bowler = checkBowler();
            bowler.runsFor+=7;
            bowler.extras++;
            overProgress("6nb");
            sixes++;
        }
        else if(wideRadio.isSelected()){
            CricketScorer.batting.runsScored+=7;               
            Player bowler = checkBowler();
            partnershipRuns+=7;
            extras+=7;
            bowler.runsFor+=7;
            bowler.extras+=7;
            overProgress("6wd");
        }
        this.striker.sixes++;
        reloadPage();
    }
    
    public void tripleRun(){
        if(ballRadio.isSelected()){
            balls++;
            this.striker.ballsFaced++;
            CricketScorer.batting.runsScored+=3;               
            partnershipRuns+=3;
            partnershipBalls++;
            this.striker.runsScored+=3;
            Player bowler = checkBowler();
            bowler.ballsBowled++;
            bowler.runsFor+=3;
            overProgress("3");
        }
        else if(noBallRadio.isSelected()){
            CricketScorer.batting.runsScored+=4;               
            this.striker.runsScored+=3;
            partnershipRuns+=4;
            Player bowler = checkBowler();
            extras++;
            bowler.runsFor+=4;
            bowler.extras++;
            overProgress("3nb");
        }
        else if(wideRadio.isSelected()){
            CricketScorer.batting.runsScored+=4;               
            partnershipRuns+=4;
            Player bowler = checkBowler();
            bowler.runsFor+=4;
            extras+=4;
            bowler.extras+=4;
            overProgress("3wd");
        }
        if(balls%6==0)oldBalls = balls;
        changeStrike();
        reloadPage();
    }
    
    public void byRuns(){
        runsInput = (TextField) CricketScorer.getScene().lookup("#runsInput");
        if(ballRadio.isSelected()){
            balls++;
            this.striker.ballsFaced++;
            CricketScorer.batting.runsScored+=Integer.parseInt(runsInput.getText());               
            partnershipRuns+=Integer.parseInt(runsInput.getText());
            partnershipBalls++;
            Player bowler = checkBowler();
            bowler.ballsBowled++;
            extras+=Integer.parseInt(runsInput.getText());
            overProgress(runsInput.getText()+"b");
        }
        else if(noBallRadio.isSelected()){
            CricketScorer.batting.runsScored+=Integer.parseInt(runsInput.getText());
            CricketScorer.batting.runsScored++;
            this.striker.ballsFaced++;
            partnershipRuns++;
            partnershipRuns+=Integer.parseInt(runsInput.getText());
            extras++;
            extras+=Integer.parseInt(runsInput.getText());
            Player bowler = checkBowler();
            bowler.runsFor++;
            bowler.extras++;
            overProgress(runsInput.getText()+"b.nb");
        }
        else if(wideRadio.isSelected()){
            CricketScorer.batting.runsScored+=Integer.parseInt(runsInput.getText());               
            CricketScorer.batting.runsScored++;
            extras+=Integer.parseInt(runsInput.getText());
            extras++;
            partnershipRuns++;
            partnershipRuns+=Integer.parseInt(runsInput.getText());
            Player bowler = checkBowler();
            bowler.runsFor+=Integer.parseInt(runsInput.getText());
            bowler.extras+=Integer.parseInt(runsInput.getText());               
            extras+=Integer.parseInt(runsInput.getText());
            bowler.runsFor++;
            bowler.extras++;
            overProgress(runsInput.getText()+"wd");
        }
        if(Integer.parseInt(runsInput.getText())%2!=0){
            changeStrike();
        }
        reloadPage();
    }
     
    public void legByRuns(){
        runsInput = (TextField) CricketScorer.getScene().lookup("#runsInput");
        if(ballRadio.isSelected()){
            balls++;
            this.striker.ballsFaced++;
            CricketScorer.batting.runsScored+=Integer.parseInt(runsInput.getText());               
            partnershipRuns+=Integer.parseInt(runsInput.getText());
            partnershipBalls++;
            Player bowler = checkBowler();
            bowler.ballsBowled++;
            extras+=Integer.parseInt(runsInput.getText());
            overProgress(runsInput.getText()+"lb");
        }
        else if(noBallRadio.isSelected()){
            CricketScorer.batting.runsScored+=Integer.parseInt(runsInput.getText());
            CricketScorer.batting.runsScored++;
            this.striker.ballsFaced++;
            partnershipRuns++;
            partnershipRuns+=Integer.parseInt(runsInput.getText());
            extras++;
            extras+=Integer.parseInt(runsInput.getText());
            Player bowler = checkBowler();
            bowler.runsFor++;
            bowler.extras++;
            overProgress(runsInput.getText()+"lb.nb");
        }
        else if(wideRadio.isSelected()){
            CricketScorer.batting.runsScored+=Integer.parseInt(runsInput.getText());               
            CricketScorer.batting.runsScored++;
            extras+=Integer.parseInt(runsInput.getText());
            extras++;
            partnershipRuns++;
            partnershipRuns+=Integer.parseInt(runsInput.getText());
            Player bowler = checkBowler();
            bowler.runsFor+=Integer.parseInt(runsInput.getText());
            bowler.extras+=Integer.parseInt(runsInput.getText());               
            extras+=Integer.parseInt(runsInput.getText());
            bowler.runsFor++;
            bowler.extras++;
            overProgress(runsInput.getText()+"wd");
        }
        if(Integer.parseInt(runsInput.getText())%2!=0){
            changeStrike();
        }
        reloadPage();
    }

    private void reloadPage() {
        ballRadio.setSelected(true);
        runsInput = (TextField) CricketScorer.getScene().lookup("#runsInput");
        runsInput.setText("");
        updateScore();
    }
    
    private void setBatsmen(){
        String[] batsmenName = {batsman1Name.getText(),batsman2Name.getText()};
        ObservableList<Object> newList = FXCollections.observableArrayList((Object[]) batsmenName);
        batsmenSelector.setItems(newList);
    }

    public void lbwOut(){
        striker.outMethod="lb";
        batsman1Name.setText("");
        batsman1Score.setText("");
        wicket();
    }
    
    public void bowledOut(){
        striker.outMethod="b";
        batsman1Name.setText("");
        batsman1Score.setText("");
        wicket();
    }
    
    public void caughtOut(){
        striker.outMethod="c";
        striker.outByFielder = bowlerBox1.getValue().toString();
        batsman1Name.setText("");
        batsman1Score.setText("");
        wicket();
    }
    
    public void stumpOut(){
        striker.outMethod="st";
        striker.outByFielder = bowlerBox1.getValue().toString();
        batsman1Name.setText("");
        batsman1Score.setText("");
        wicket();
    }
    
    public void retired(){
        
        if(!noBallRadio.isSelected() && !wideRadio.isSelected()){
            this.balls++;
            checkBowler().ballsBowled++;
        }
        setBatsmen();
        Player batsman = (Player) newBatsmanBox.getValue();
        if (striker != batsman){
            changeStrike();
        }
        striker.outMethod="retired hurt";
        striker.outByFielder = (String) bowlerBox1.getValue();
        striker.out="OUT";
        striker.ballsFaced++;
        batsman1Name.setText("");
        batsman1Score.setText("");
        if(CricketScorer.batting.wickets==9) endOfInnings();
        overProgress.setText(overProgress.getText()+".");
        partnershipRuns = 0;
        partnershipBalls = 0;
    }
    
    public void runOut(){
        this.battingLineup.remove(striker);
        CricketScorer.batting.wickets++;
        if(!noBallRadio.isSelected() && !wideRadio.isSelected()){
            this.balls++;
            checkBowler().ballsBowled++;
            striker.ballsFaced++;
        }
        partnershipRuns = 0;
        partnershipBalls = 0;
        overProgress.setText(overProgress.getText()+runsInput.getText()+"W");
        if (!runsInput.getText().equals("")) {
            CricketScorer.batting.runsScored += Integer.parseInt(runsInput.getText());
            this.striker.runsScored+=Integer.parseInt(runsInput.getText());
        }
        
        Scorecard.setFallOfWickets(chase, balls);
        if(CricketScorer.batting.wickets<11){
            setBatsmenList();
            String batsman = (String) batsmenSelector.getValue();
            if (!striker.name.toUpperCase().equals(batsman)){
                changeStrike();
            }
            striker.outMethod="runout";
            striker.outByFielder = bowlerBox2.getValue().toString();
            striker.out="OUT";
            batsman1Name.setText("");
            batsman1Score.setText("");
            
        } else{
            endOfInnings();
        }    
        
    }
    
    public void wicket(){
        striker.out="OUT";
        striker.outBy=checkBowler().name;
        this.battingLineup.remove(striker);
        if(!noBallRadio.isSelected() && !wideRadio.isSelected()){
            this.balls++;
            checkBowler().ballsBowled++;
            striker.ballsFaced++;
        }
        partnershipRuns = 0;
        partnershipBalls = 0;
        checkBowler().wickets++;
        CricketScorer.batting.wickets++;
        overProgress.setText(overProgress.getText()+"W ");
        Scorecard.setFallOfWickets(chase,balls);
        if(CricketScorer.batting.wickets<11){
            setBatsmenList();
            batsman1Name.setText("");
            batsman1Score.setText("");
        }
        else{
            endOfInnings();
        }
        
    }
    
    public void endOfInnings1(){
        Label matchWin = (Label) CricketScorer.getScene().lookup("#matchWinNotice");
        overProgress.setText("");
        Scorecard.updateBattingCard(CricketScorer.batting, chase,balls);
        Scorecard.updateBowlingCard(CricketScorer.bowling,chase,extras);
        partnershipRuns = 0;
        partnershipBalls = 0;
        fours=0;
        sixes=0;
        extras = 0;
        runRateLabel.setText("0.0");
        projectedLabel.setText("0");
        extrasLabel.setText("0");
        if (!chase){
            this.target=CricketScorer.batting.runsScored+1;
            chase = true;
            Team temp = CricketScorer.batting;
            CricketScorer.batting = CricketScorer.bowling;
            CricketScorer.bowling=temp;
            setSides();
            this.balls=0;
            this.runRate=0;
            this.projected=0;
            updateScore();
            setBatsmen();
            setBatsmenList();
            Button next = (Button) CricketScorer.getScene().lookup("#nextInningsButton");
            next.setVisible(false);
            matchWin.setVisible(false);
        }
        else{
            matchWin.setVisible(true);
            String team1 = Scorecard.fBatTotal.getText();
            String team2 = Scorecard.sBatTotal.getText();
            team1 = team1.replace("Total", CricketScorer.bowling.abbreviation);
            team2 = team2.replace("Total", CricketScorer.batting.abbreviation);
            String win = team1 + ")      " + team2;
            win = win.replace("   Overs: ", "(") + ")\n";

            if (CricketScorer.batting.runsScored<this.target || CricketScorer.batting.wickets==10){
                if (CricketScorer.batting.runsScored==this.target-1) {
                    matchWin.setText(win+"\nMatch Tied");
                } else {
                    matchWin.setText(win + CricketScorer.bowling.name + "\nWins by " + (target - CricketScorer.batting.runsScored - 1) + " Runs");
                }
            }
            else if (CricketScorer.batting.runsScored>=this.target){
                matchWin.setText(win + CricketScorer.batting.name+"\nWins by "+(10-CricketScorer.batting.wickets)+" Wickets");
            }
        }
        
    }
    
    private void endOfInnings(){
        if(!chase){
            Button next = (Button) CricketScorer.getScene().lookup("#nextInningsButton");
            next.setVisible(true);
            Label matchWin = (Label) CricketScorer.getScene().lookup("#matchWinNotice");
            matchWin.setVisible(true);
        }else{
            endOfInnings1();
        }
    }
    
    public void selectNewBatsman() {
        Player newBatsman = (Player) newBatsmanBox.getValue();
        this.striker.out = "";
        if(this.striker.ballsFaced==0) {
            CricketScorer.batting.batOrder.remove(this.striker);
            if(!this.battingLineup.contains(striker)) this.battingLineup.add(0,striker);
            this.battingLineup.remove(newBatsman);
            setBatsmen();
        }
        this.striker = newBatsman;
        this.striker.out="notout";
        CricketScorer.batting.batOrder.add(striker);
        newBatsmanBox.setValue(null);
        updateScore();
        setBatsmen();
        setBatsmenList();
    }
    
       
    private void setBatsmenList() {
        Object[] battingList = (Object[]) this.battingLineup.toArray();
        ObservableList<Object> newBatList = FXCollections.observableArrayList((Object[]) battingList);        
        newBatsmanBox.setItems(newBatList);
    }
    
    public void savePlayerEdit(){
        editor.savePlayerValues();
        updateScore();
        setBowlerBoxes();
        setBatsmenList();
        
    }
    
    public void saveTeamEdit(){
        ArrayList returnList = editor.saveTeamValues();
        this.balls = (int) returnList.get(0);
        CricketScorer.batting.runsScored = (int) returnList.get(1);
        this.extras = (int) returnList.get(2);
        this.fours = (int) returnList.get(3);
        this.sixes = (int) returnList.get(4);
        this.partnershipRuns = (int) returnList.get(5);
        this.partnershipBalls = (int) returnList.get(6);
        this.overProgress.setText((String) returnList.get(7));
        updateScore();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   

   
    
   
}
