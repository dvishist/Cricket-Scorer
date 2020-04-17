/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cricketscorer;

/**
 *
 * @author Vishist
 */
class Player {

    public String name;
    public int runsScored;
    public int ballsFaced;
    public int fours;
    public int sixes;
    public String out;
    public String outMethod;
    public String outBy="";
    public String outByFielder="";
    
    
    public int ballsBowled;
    public int runsFor;
    public int maidens;
    public int wickets;
    public double economy;
    public int extras;
    
    

    public Player(String name){
        this.extras = 0;
        this.economy = 0;
        this.wickets = 0;
        this.maidens = 0;
        this.runsFor = 0;
        this.ballsBowled = 0;
        this.runsScored = 0;
        this.ballsFaced = 0;
        this.fours = 0;
        this.sixes = 0;
        this.out = "";
        this.outMethod = "";
        this.name = name;
    }
    
    @Override
    public String toString(){
        return this.name;
    }
}
