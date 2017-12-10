import java.util.Scanner;
public class GameFrame {
    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        //variable declaration
        boolean will = true;
        String name = null;
        int bet = 0;
        //welcome
        System.out.println("Welcome to my_BlackJack game.");
        System.out.println("Please set a Player name:");
        name = reader.nextLine();
        //class declaration
        CardHost ch = new CardHost();
        Player player1 = new Player(name);
        //Game Start
        System.out.println("okay " + name +" You have" + player1.getChips() + " chips now.");
        System.out.println("*************************** Game Start ***************************");
        //chip in
        System.out.println("Please enter the amount of the bet.");
        bet = reader.nextInt();
        player1.minusChips(bet);
        //CardHost
        ch.CardInit();//Deck Init
        ch.Shuffle();//Shuffle Deck
        //Player Draw
        player1.Hit(ch.dealer());
        //Player Stage
        //if Player.Hit
        while(true) {
            player1.show();
            System.out.println("Do you want a Hit?(Enter \"true\" or \"false\")");
            will = reader.nextBoolean();
            if (player1.getHandnum() <= 5 && will) {
                player1.Hit(ch.dealer());//the dealer returns a card to player, and the card join player's hand
            }
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

        }
    }
}
