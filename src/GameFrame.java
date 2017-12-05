import java.util.Scanner;
public class GameFrame {
    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        String name = null;
        System.out.println("Welcome to my_BlackJack game.");
        System.out.println("Please set a Player name:");
        name = reader.nextLine();
        System.out.println("****************okay " + name +" ,Game Start ****************");
        //chip in

        //Player Stage
        //if Player.Hit
            //else if Player HaveBJ
                //Player WIN; Host LOSE;(3:1)
            //else if Player Burst
                //Player LOSE; Host WIN;(1:1)
            //else if Player have md5 cards
                //Player WIN; Host LOSE;(3:1)

        //Host Stage
        //if Host hand < 18
            //Host.Hit
            //if Host HaveBJ;
                //Player LOSE; Host WIN;(1:3)
            //else if Host Burst
                //Player WIN; Host LOSE;(1:1)

        //ValueJudge
        //chip settle
        //next round
    }
}
