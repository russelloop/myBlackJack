import java.util.Scanner;
public class PVEGameFrame {
    public State PVE(String pname){
        Scanner reader = new Scanner(System.in);
        //variable declaration
        boolean will = true;
        String dname = "computer";
        int bet = 0;
        int insurance = 0;
        //class declaration
        CardHost ch = new CardHost();
        System.out.println("");
        System.out.println("You have selected PVE as a Player.");
        Player player1 = new Player(pname);
        Player dealer1 = new Player(dname);
        //Game Start
        System.out.println("okay, " + pname +", You have " + player1.getChips() + " chips now.");
        //chips in
        System.out.println("Please enter the amount of the bet.");
        bet = reader.nextInt();
        System.out.println("_____________________________ Game Start _____________________________");
        System.out.print("   Dealer: " + dname + "(" + dealer1.getChips() + ")");
        System.out.println("  Player: " + pname + "(" + player1.getChips() + ")(bet in " + bet + " )");
        System.out.println("______________________________________________________________________");
        System.out.println("");

        //InitDeck
        ch.CardInit();//Deck Init
        ch.Shuffle();//Shuffle Deck
        //Player Draw
        player1.Hit(ch.dealer());//player draw
        player1.Hit(ch.dealer());
        player1.show();
        //Dealer Draw
        dealer1.Hit(ch.dealer());//dealer draw
        dealer1.show();          //show card 1
        if(dealer1.calculator() == 10){
            dealer1.Hit(ch.dealer());//dealer dark draw
            if(dealer1.HaveBJ()){
                if(player1.HaveBJ()){
                    System.out.println("--------------------- CheckPoint! ---------------------");
                    System.out.println("Dealer and Player both get BlackJack!");
                    System.out.println("DRAW!");
                    System.out.println("");
                    dealer1.setState(State.DRAW);
                    player1.setState(State.DRAW);
                    System.out.println(pname + " got " + player1.getChips() + " left.");
                    bet = 0;
                    return State.DRAW;
                }
                else {
                    System.out.println("--------------------- CheckPoint! ---------------------");
                    System.out.println("Dealer got BlackJack!");
                    System.out.println("Dealer WIN!");
                    System.out.println("");
                    dealer1.setState(State.WIN);
                    player1.setState(State.LOST);
                    System.out.println(pname + " lose " + bet*2 + " chips.");
                    player1.minusChips(bet * 2);
                    dealer1.addChips(bet * 2);
                    System.out.println(pname + " got " + player1.getChips() + " left.");
                    bet = 0;
                    return State.LOST;
                }
            }
        }
        else if(dealer1.calculator() == 1) {
            dealer1.Hit(ch.dealer());//dealer dark draw
            if (player1.HaveBJ()) {

            }
            else {
                System.out.println("Do you want Insurance?(Enter \"true\" or \"false\")");
                will = reader.nextBoolean();
                if (will) {
                    insurance = bet / 2;
                    System.out.println(pname + " buy a insurance (cost " + insurance + " chips.");
                    if (dealer1.HaveBJ()) {
                        System.out.println("--------------------- CheckPoint! ---------------------");
                        dealer1.show();
                        System.out.println("Dealer got BlackJack!");
                        System.out.println("And Player have Insurance.");
                        System.out.println("");
                        dealer1.setState(State.WIN);
                        player1.setState(State.LOST);
                        System.out.println(pname + " win " + bet+insurance + " chips.");
                        player1.minusChips(bet+insurance);
                        dealer1.addChips(bet+insurance);
                        System.out.println(pname + " got " + player1.getChips() + " left.");
                        insurance = 0;
                        bet = 0;
                        return State.LOST;
                    }
                }
                else {
                    if (dealer1.HaveBJ()) {
                        System.out.println("--------------------- CheckPoint! ---------------------");
                        System.out.println("Dealer got BlackJack!");
                        System.out.println("But Player dont have Insurance.");
                        System.out.println("Dealer WIN!");
                        System.out.println("");
                        dealer1.setState(State.WIN);
                        player1.setState(State.LOST);
                        System.out.println(pname + " lose " + bet*2 + " chips.");
                        player1.minusChips(bet * 2);
                        dealer1.addChips(bet*2);
                        System.out.println(pname + " got " + player1.getChips() + " left.");
                        bet = 0;
                        return State.LOST;
                    }
                }
            }
        }
        else {
            dealer1.Hit(ch.dealer());//dealer dark draw
            System.out.print("Dealer have " + dealer1.getHandnum() + " cards in hand now.");
            System.out.println("(One of them are in dark)");
        }

        //Player Stage
        //if Player.Hit
        while(player1.getHandnum() < 5) {
            System.out.println("");
            System.out.println("You have " + player1.getHandnum() + " cards in hand now.");
            System.out.println("Do you want to Hit?(Enter \"true\" or \"false\")");
            will = reader.nextBoolean();
            if (will) {
                player1.Hit(ch.dealer());//the dealer returns a card to player, and the card join player's hand
                player1.show();
                if (player1.Have21()){
                    System.out.println("--------------------- CheckPoint! ---------------------");
                    System.out.println(pname + " got " + player1.calculator() + " point");
                    System.out.println("Player1 WIN!");
                    System.out.println("");
                    dealer1.setState(State.LOST);
                    player1.setState(State.WIN);
                    System.out.println(pname + " win " + bet + " chips.");
                    player1.addChips(bet);
                    dealer1.minusChips(bet);
                    System.out.println(pname + " got " + player1.getChips() + " left.");
                    bet = 0;
                    return State.WIN;
                }
            }
            else
                break;
            if (player1.calculator() > 21) {
                System.out.println("--------------------- CheckPoint! ---------------------");
                player1.show();
                System.out.println(pname + " got " + player1.calculator() + " point");
                System.out.println("Player's Hand is Burst. You lose.");
                System.out.println("Dealer win.");
                System.out.println("");
                dealer1.setState(State.WIN);
                player1.setState(State.LOST);
                System.out.println(pname + " lose " + bet + " chips.");
                player1.minusChips(bet);
                dealer1.addChips(bet);
                System.out.println(pname + " got " + player1.getChips() + " left.");
                bet = 0;
                return State.LOST;
            }
        }

        if(player1.getHandnum() == 5) {
            System.out.println("--------------------- CheckPoint! ---------------------");
            player1.show();
            System.out.println(pname + " got " + player1.calculator() + " point and not burst within 5 cards!");
            System.out.println("Player1 WIN!");
            System.out.println("");
            dealer1.setState(State.LOST);
            player1.setState(State.WIN);
            System.out.println(pname + " win " + bet + " chips.");
            player1.addChips(bet);
            dealer1.minusChips(bet);
            System.out.println(pname + " got " + player1.getChips() + " left.");
            bet = 0;
            return State.WIN;
        }

        //Dealer Stage
        dealer1.show();//dealer show his hand after player's draw

        while (dealer1.calculator() <= 17){
            dealer1.Hit(ch.dealer());
            dealer1.show();
            if(dealer1.calculator() > 21) {
                System.out.println("--------------------- CheckPoint! ---------------------");
                dealer1.show();
                System.out.println(dname + " got " + dealer1.calculator() + " point.");
                System.out.println("Dealer's Hand is Burst. Player win.");
                System.out.println("");
                dealer1.setState(State.LOST);
                player1.setState(State.WIN);
                System.out.println(pname + " win " + bet + " chips.");
                player1.addChips(bet);
                dealer1.minusChips(bet);
                System.out.println(pname + " got " + player1.getChips() + " left.");
                bet = 0;
                return State.WIN;
            }
            else if(dealer1.calculator() == 21) {
                System.out.println("--------------------- CheckPoint! ---------------------");
                dealer1.show();
                System.out.println(dname + " got " + dealer1.calculator() + " point.");
                System.out.println("Dealer WIN!");
                System.out.println("");
                dealer1.setState(State.WIN);
                player1.setState(State.LOST);
                System.out.println(pname + " lose " + bet*2 + " chips.");
                player1.minusChips(bet * 2);
                dealer1.addChips(bet*2);
                System.out.println(pname + " got " + player1.getChips() + " left.");
                bet = 0;
                return State.LOST;
            }
        }

        //ValueJudge
        if(dealer1.calculator() > player1.calculator()){
            System.out.println("--------------------- CheckPoint! ---------------------");
            player1.show();
            dealer1.show();
            System.out.println(pname + " got " + player1.calculator() + " point.");
            System.out.println(dname + " got " + dealer1.calculator() + " point.");
            System.out.println("Dealer WIN!");
            System.out.println("");
            dealer1.setState(State.WIN);
            player1.setState(State.LOST);
            System.out.println(pname + " lose " + bet*2 + " chips.");
            player1.minusChips(bet * 2);
            dealer1.addChips(bet*2);
            System.out.println(pname + " got " + player1.getChips() + " left.");
            bet = 0;
            return State.LOST;
        }
        else if(dealer1.calculator() < player1.calculator()){
            System.out.println("--------------------- CheckPoint! ---------------------");
            player1.show();
            dealer1.show();
            System.out.println(pname + " got " + player1.calculator() + " point.");
            System.out.println(dname + " got " + dealer1.calculator() + " point.");
            System.out.println("Player WIN!");
            System.out.println("");
            dealer1.setState(State.LOST);
            player1.setState(State.WIN);
            System.out.println(pname + " win " + bet + " chips.");
            player1.addChips(bet);
            dealer1.minusChips(bet);
            System.out.println(pname + " got " + player1.getChips() + " left.");
            bet = 0;
            return State.WIN;
        }
        else {
            System.out.println("--------------------- CheckPoint! ---------------------");
            player1.show();
            dealer1.show();
            System.out.println(pname + " got " + player1.calculator() + " point.");
            System.out.println(dname + " got " + dealer1.calculator() + " point.");
            System.out.println("DRAW!");
            System.out.println("");
            dealer1.setState(State.DRAW);
            player1.setState(State.DRAW);
            System.out.println(pname + " got " + player1.getChips() + " left.");
            bet = 0;
            return State.DRAW;
        }
    }
}

