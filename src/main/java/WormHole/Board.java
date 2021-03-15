package WormHole;

import java.util.ArrayList;
import java.util.Random;

public class Board {

    int dimension_of_board = 0;
    ArrayList<Integer> board_layout = new ArrayList<Integer>();
    ArrayList<Integer> check_position = new ArrayList<Integer>();

    int wormhole[];
    int escape[];

    public Board(int dimension_of_board) {
        this.dimension_of_board = dimension_of_board;
        for (int index = 0; index < (this.dimension_of_board * this.dimension_of_board); index++) {
            board_layout.add(index + 1);
        }
    }

    public ArrayList<Integer> getBoardLayout() {
        return board_layout;
    }

    public int[] autoRollTheDice() {
        Random rand = new Random();
        int rand_int1 = rand.nextInt(6);
        int rand_int2 = rand.nextInt(6);
        if (rand_int1 == 0) {
            rand_int1++;
        }
        if (rand_int2 == 0) {
            rand_int2++;
        }
        int[] dice_value = new int[2];
        dice_value[0] = rand_int1;
        dice_value[1] = rand_int2;
        return dice_value;
    }

    public void generateWormholes(int width) {
        Random rand = new Random();
        int temp = 0;
        wormhole = new int[width];
        for (int i = 0; i < width; i++) {
            temp = 0;
            
            while (temp == 0 || temp == 1 || check_position.contains(temp)) {
                temp = rand.nextInt(width * width);
            }
            wormhole[i] = temp;
            check_position.add(temp);
        }
    }

    public void generateEscape(int width) {
        Random rand = new Random();
        int temp = 0;
        escape = new int[width];
        for (int i = 0; i < width; i++) {
            temp = 0;
            while (temp == 0 || temp == 1 || check_position.contains(temp)) {
                temp = rand.nextInt(width * width);
            }
            escape[i] = temp;
            check_position.add(temp);
        }
       
    }
    public void getWormholes(){
        System.out.print("Wormholes: ");
        for (int i = 0; i < dimension_of_board; i++) {
            System.out.print(wormhole[i] + " ");
        }
        System.out.println("");
    }
    public void getEscape(){
        System.out.print("Escape: ");
        for (int i = 0; i < dimension_of_board; i++) {
            System.out.print(escape[i] + " ");
        }
        System.out.println("");
    }
    
    public int check_wormhole(int pos){
        for(int i : wormhole){
            if(pos == i){
                //System.out.println(name+"");
                Random rand = new Random();
                int index = rand.nextInt(wormhole.length);
                System.out.println("wormhole at "+i);
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> exit at "+escape[index]);
                return escape[index];
            }
        }
        return pos;
    }
    
    

}
