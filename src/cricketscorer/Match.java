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
public class Match {
    public String location;
    public Team home;
    public Team away;
    public int overs;

      
    
    public Match(String location, Team home, Team away, int overs){
        this.location = location;
        this.home=home;
        this.away=away;
        this.overs = overs;
    }
    
    
}
