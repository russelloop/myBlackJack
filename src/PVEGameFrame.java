import java.util.Scanner;
public class PVEGameFrame {
    public int PVE(){
        Scanner reader = new Scanner(System.in);
        //variable declaration
        boolean will = true;
        String pname = null;
        String dname = null;
        int bet = 0;
        //class declaration
        CardHost ch = new CardHost();
        pname = reader.next();
        dname = "computer";
        Player player1 = new Player(pname);
        Player dealer1 = new Player(dname);
        //Game Start
        System.out.println("okay " + pname +" You have" + player1.getChips() + " chips now.");
        System.out.println("*************************** Game Start ***************************");
        //chip in
        System.out.println("Please enter the amount of the bet.");
        bet = reader.nextInt();
        player1.minusChips(bet);
        //InitDeck
        ch.CardInit();//Deck Init
        ch.Shuffle();//Shuffle Deck
        //Player Draw
        player1.Hit(ch.dealer());
        player1.Hit(ch.dealer());
        //Dealer Draw
        dealer1.Hit(ch.dealer());
        dealer1.Hit(ch.dealer());
        dealer1.show();
        //Player Stage
        //if Player.Hit
        while(player1.getHandnum() < 5) {
            player1.show();
            System.out.println("Do you want to Hit?(Enter \"true\" or \"false\")");
            will = reader.nextBoolean();
            if (will)
                player1.Hit(ch.dealer());//the dealer returns a card to player, and the card join player's hand
            else
                break;
            if (player1.calculator() > 21) {
                player1.setState(State.LOST);
                System.out.println("Player1's Hand is Burst. You lose.");
                System.out.println("Dealer win.");
                return 0;
            }
        }

        if(player1.getHandnum() == 5) {
            player1.setState(State.WIN);
            System.out.println("Player1 WIN!");
        }
        else if(player1.HaveBJ()) {
            player1.setState(State.WIN);
            System.out.println("Player1 got BlackJack!");
            System.out.println("Player1 WIN!");
        }

            //Host Stage
            //if Host hand < 18
            //Host.Hit
            //if Host HaveBJ;
            //Player LOSE; Host WIN;(1:3)
            //else if Host Burst
            //Player WIN; Host LOSE;(1:1)

            //ValueJudge
            //chip settle
        return 0;
    }
}
