import java.util.Scanner;
public class PVEGameFrame {
    public int PVE(){
        Scanner reader = new Scanner(System.in);
        //variable declaration
        boolean will = true;
        String pname = null;
        String dname = null;
        int bet = 0;
        int insurance = 0;
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
                System.out.println("Dealer got BlackJack!");
                System.out.println("Dealer WIN!");
                dealer1.setState(State.WIN);
                player1.setState(State.LOST);
                bet *= 2;
                player1.minusChips(bet);
                dealer1.addChips(bet);
                bet = 0;
                return 1;
            }
        }
        else if(dealer1.calculator() == 11) {
            dealer1.Hit(ch.dealer());//dealer dark draw
            System.out.println("Do you want Insurance?(Enter \"true\" or \"false\")");
            will = reader.nextBoolean();
            if (will) {
                insurance = bet / 2;
                dealer1.show();
                if (dealer1.HaveBJ()) {
                    System.out.println("Dealer got BlackJack!");
                    System.out.println("Dealer WIN!");
                    dealer1.setState(State.WIN);
                    player1.setState(State.LOST);
                    dealer1.minusChips(insurance);
                    dealer1.addChips(bet * 2);
                    player1.addChips(insurance);
                    player1.minusChips(bet * 2);
                    insurance = 0;
                    bet = 0;
                    return 1;
                }
            } else {
                if (dealer1.HaveBJ()) {
                    System.out.println("Dealer got BlackJack!");
                    System.out.println("Dealer WIN!");
                    dealer1.setState(State.WIN);
                    player1.setState(State.LOST);
                    dealer1.addChips(bet * 3);
                    bet = 0;
                    return 1;
                }
            }
        }

        //Player Stage
        //if Player.Hit
        while(player1.getHandnum() < 5) {
            System.out.println("Do you want to Hit?(Enter \"true\" or \"false\")");
            will = reader.nextBoolean();
            if (will) {
                player1.Hit(ch.dealer());//the dealer returns a card to player, and the card join player's hand
                player1.show();
            }
            else
                break;
            if (player1.calculator() > 21) {
                player1.show();
                System.out.println(pname + "got" + player1.calculator() + "point");
                System.out.println("Player1's Hand is Burst. You lose.");
                System.out.println("Dealer win.");
                dealer1.setState(State.WIN);
                player1.setState(State.LOST);
                return 1;
            }
        }

        if(player1.getHandnum() == 5) {
            System.out.println(pname + "got" + player1.calculator() + "point and not burst within 5 cards!");
            System.out.println("Player1 WIN!");
            dealer1.setState(State.LOST);
            player1.setState(State.WIN);
            return 0;
        }
        else if(player1.calculator() == 21) {
            System.out.println(pname + "got" + player1.calculator() + "point");
            System.out.println("Player1 WIN!");
            dealer1.setState(State.LOST);
            player1.setState(State.WIN);
            return 0;
        }

        //Host Stage
        dealer1.show();//dealer show his hand after player's draw

        while (dealer1.calculator() <= 17){
            dealer1.Hit(ch.dealer());
            if(dealer1.calculator() > 21) {
                dealer1.show();
                System.out.println(dname + "got" + dealer1.calculator() + "point");
                System.out.println("Dealer's Hand is Burst. Player win.");
                dealer1.setState(State.LOST);
                player1.setState(State.WIN);
                return 0;
            }
            else if(dealer1.calculator() == 21) {
                dealer1.show();
                System.out.println("Dealer got " + dealer1.calculator() + "point");
                System.out.println("Dealer WIN!");
                dealer1.setState(State.WIN);
                player1.setState(State.LOST);
                return 1;
            }
        }

        //ValueJudge
        if(dealer1.calculator() > player1.calculator()){
            player1.show();
            System.out.println(pname + "got" + player1.calculator() + "point");
            dealer1.show();
            System.out.println(dname + "got" + dealer1.calculator() + "point");
            System.out.println("Dealer WIN!");
            dealer1.setState(State.WIN);
            player1.setState(State.LOST);
            return 1;
        }
        else if(dealer1.calculator() < player1.calculator()){
            player1.show();
            System.out.println(pname + "got" + player1.calculator() + "point");
            dealer1.show();
            System.out.println(dname + "got" + dealer1.calculator() + "point");
            System.out.println("Player WIN!");
            dealer1.setState(State.LOST);
            player1.setState(State.WIN);
            return 0;
        }
        //chip settle
        return 0;
    }
}

