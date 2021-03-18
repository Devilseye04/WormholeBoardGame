package WormHole;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to Wormhole.");

        //int width_of_board =5;
        Scanner sc = new Scanner(System.in).useDelimiter("\n");;
        while (true) {
            int width_of_board;

            //Enter the dimension
            while (true) {

                System.out.println("Please enter the width dimension of your board.[5-10]");
                try {

                    width_of_board = Integer.parseInt(sc.nextLine());
                    if (width_of_board >= 5 && width_of_board <= 10) {

                        break;
                    } else {
                        System.out.println("**********Width of board needs to be between 5 and 10**********");
                    }
                } catch (Exception e) {

                    System.out.println("--------------------------------------------------");
                    System.out.println("Please enter an Interger number");
                    System.out.println("Example:-");
                    System.out.println("5");
                    System.out.println("7");
                    System.out.println("--------------------------------------------------");
                    sc.nextLine();
                }

            }

            System.out.println("Thank You!!!");

            //Creation on Board
            Board b = new Board(width_of_board);

            //Generate Wormhole
            b.generateWormholes(width_of_board);

            //Generate Escape
            b.generateEscape(width_of_board);
            System.out.println("Board has " + width_of_board * width_of_board + " squares.");

            b.getWormholes();
            b.getEscape();
            //Enter number of Players
            int nop;
            while (true) {
                try {
                    System.out.println("Please enter the number of players.[2-6]");

                    nop = Integer.parseInt(sc.nextLine());
                    if (nop >= 2 && nop <= 6) {
                        break;
                    } else {
                        System.out.println("**********Number of players needs to be between 2 and 6**********");
                    }

                } catch (Exception e) {
                    System.out.println("--------------------------------------------------");
                    System.out.println("Please enter an Integer number");
                    System.out.println("Example:-");
                    System.out.println("2");
                    System.out.println("6");
                    System.out.println("--------------------------------------------------");
                    //sc.next();
                }
            }

            //Enter name of Players
            Players players[] = new Players[nop];
            for (int i = 0;
                    i < nop;
                    i++) {
                while (true) {
                    try {

                        System.out.println("Please enter the name of player " + (i + 1));
                        players[i] = new Players();
                        players[i].name = sc.nextLine();
                        /*
                    if (players[i].name.matches(".*\\d.*")) {
                        System.out.println("--------------------------------------------------");
                        System.out.println("Name should not contain any numbers or special characters");
                        System.out.println("--------------------------------------------------");

                    }
                         */
                        Pattern my_pattern = Pattern.compile("[^a-z]", Pattern.CASE_INSENSITIVE);
                        Matcher my_match = my_pattern.matcher(players[i].name);
                        boolean check = my_match.find();
                        if (check) {
                            System.out.println("--------------------------------------------------");
                            System.out.println("Name should not contain any numbers or special characters or spaces");
                            System.out.println("--------------------------------------------------");
                            continue;
                        }
                        int taken = 0;
                        for (int j = 0; j < i; j++) {
                            if (players[j].name.equals(players[i].name)) {
                                System.out.println("Name is already taken");
                                taken = 1;
                            }
                        }
                        if (taken == 1) {
                            continue;
                        }
                        if (players[i].name.length() > 20) {
                            System.out.println("Player name should not exceed 20 letters");
                            continue;

                        }
                        break;

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
            for (int i = 0;
                    i < nop;
                    i++) {
                System.out.println(players[i].name);
            }

            String auto_check = new String();

            for (int i = 0;
                    i < nop;
                    i++) {

                //Auto and Manual Dice Roll
                while (true) {

                    System.out.println(players[i].name + "  - do you want to roll the dice, or shall I do it for you? \n Type \"Y\" to roll yourself or \"N\" to let me do it.");
                    auto_check = sc.nextLine();
                    if (auto_check.equals("y") || auto_check.equals("Y")) {
                        players[i].auto_roll = 0;
                        break;
                    } else if (auto_check.equals("n") || auto_check.equals("N")) {
                        players[i].auto_roll = 1;
                        break;
                    } else {
                        System.out.println("**********Please enter correct option**********");
                    }
                }
            }
            //Lets Play

            System.out.println(
                    "Let's Play");

            String again = "y";
            int break_while = 1;

            for (int i = 0; i < nop; i++) {
                players[i].position = 1;
            }
            break_while = 1;
            while (true) {

                //Dice roll
                int[] dice_roll = new int[2];
                int brk = 1;

                for (int i = 0; i < nop; i++) {
                    while (true) {
                        try {
                            System.out.println(players[i].name + " is on square " + players[i].position);
                            if (players[i].auto_roll == 1) {
                                dice_roll = b.autoRollTheDice();
                                System.out.println(dice_roll[0] + "," + dice_roll[1]);
                                players[i].position = players[i].position + dice_roll[0] + dice_roll[1];
                                System.out.println(players[i].name + " jumped to square " + players[i].position);
                            } else {
                                while (true) {
                                    System.out.println(players[i].name + " - please roll the dice");
                                    String temp = sc.nextLine();

                                    Pattern my_pattern = Pattern.compile("^[0-6]{1},[0-6]{1}$", Pattern.CASE_INSENSITIVE);
                                    Matcher my_match = my_pattern.matcher(temp);
                                    boolean check = my_match.find();
                                    if (check) {
                                        String[] tmp = temp.split(",");
                                        dice_roll[0] = Integer.parseInt(tmp[0]);
                                        dice_roll[1] = Integer.parseInt(tmp[1]);
                                        if (dice_roll[0] >= 1 && dice_roll[0] <= 6 && dice_roll[1] >= 1 && dice_roll[1] <= 6) {
                                            break;
                                        } else {
                                            System.out.println("**********Dice values can only be between 1 to 6**********");
                                        }
                                    } else {
                                        System.out.println("--------------------------------------------------");
                                        System.out.println("Please enter dice values in proper format");
                                        System.out.println("Example:-");
                                        System.out.println("2,3");
                                        System.out.println("1,6");
                                        System.out.println("4,5");
                                        System.out.println("--------------------------------------------------");

                                    }
                                    /*
                                    if (sc.hasNext("[0-9]{1,}" + "," + "[0-9]{1,}")) {
                                        String temp = sc.next();
                                        String[] tmp = temp.split(",");
                                        dice_roll[0] = Integer.parseInt(tmp[0]);
                                        dice_roll[1] = Integer.parseInt(tmp[1]);
                                        if (dice_roll[0] >= 1 && dice_roll[0] <= 6 && dice_roll[1] >= 1 && dice_roll[1] <= 6) {
                                            break;
                                        } else {
                                            System.out.println("**********Dice values can only be between 1 to 6**********");
                                        }
                                    } else {
                                        System.out.println("--------------------------------------------------");
                                        System.out.println("Please enter dice values in proper format");
                                        System.out.println("Example:-");
                                        System.out.println("2,3");
                                        System.out.println("1,6");
                                        System.out.println("4,5");
                                        System.out.println("--------------------------------------------------");
                                        sc.nextLine();
                                    }*/
                                }
                                System.out.println(dice_roll[0] + " " + dice_roll[1]);
                                players[i].position = players[i].position + dice_roll[0] + dice_roll[1];
                                System.out.println(players[i].name + " jumped to square " + players[i].position);
                            }
                            brk = 0;
                        } catch (Exception e) {

                        }
                        if (brk == 0) {
                            break;
                        }
                    }

                    //Check Wormhole
                    players[i].position = b.check_wormhole(players[i].position);

                    //Deciding Winner
                    if (players[i].position >= width_of_board * width_of_board) {
                        System.out.println("**********" + players[i].name + " is the Winner**********");
                        break_while = 0;
                        break;
                    }

                }
                if (break_while == 0) {
                    break;
                }
            }
            while (true) {

                System.out.println("Would you like to play the game again?");
                System.out.println("Type [Y] to play again or type [N] to stop the game.");
                again = sc.nextLine();
                if (again.contains("y") || again.contains("Y")) {
                    break;
                } else if (again.contains("n") || again.contains("N")) {
                    break;
                } else {
                    System.out.println("Please enter correct option.");
                }
            }
            if (again.contains("n") || again.contains("N")) {
                break;
            }
        }

        System.out.println(
                "");

    }
}
