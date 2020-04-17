/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cricketscorer;

import java.util.ArrayList;

/**
 *
 * @author Vishist
 */
public class Team {
    public String name;
    public String abbreviation;
    public ArrayList<Player> lineup;
    public Player sub;
    public ArrayList<Player> batOrder;
    
    public int runsScored=0;
    public int wickets=0;
    
    public Team(String name, String abbreviation){
        this.lineup = new ArrayList<>();
        this.batOrder = new ArrayList<>();
        this.name = name;
        this.abbreviation = abbreviation;
    }
    
    public void addPlayer(Player player){
        this.lineup.add(player);
    }
    
    public void setSub(Player player){
        this.sub = player;
    }
    
    public String toString(){
        return this.abbreviation;
    }
        
}
