package WormHole;


        
public class Players {
    int no_of_players;
    public String name;
    public int auto_roll;
    public int position;
    public Players(){
        name = "";
        position = 1;
        auto_roll=0;
    }
    public String getPlayerName(){
        return this.name;
    }
    
}
