import java.util.Scanner;
public class PVPGameFrame {
    public State PVP(Player player1, Player dealer1) {
        Scanner reader = new Scanner(System.in);
        //variable declaration
        String pd_will = null;
        String pp_will = null;
        boolean pp_i_will = false;
        double pp_bet = 0;
        double pp_insurance = 0;
        //class declaration
        CardHost ch = new CardHost();
        //Game Start
        System.out.println("");
        System.out.println("You have selected PVP.");
        System.out.println("");
        System.out.print("In this Round, " + dealer1.getName() + " will be the Dealer,");
        System.out.println(" and " + player1.getName() + " will be the Player.");
        System.out.println(player1.getName() +", You have " + player1.getChips() + " chips now.");
        System.out.println(dealer1.getName() +" , have " + dealer1.getChips() + " chips now.");
        //chips in
        System.out.println("Please enter the amount of the bet: (lower than " + (player1.getChips()/2) + ")");
        while(true) {
            try {
                pp_bet = reader.nextInt();
                if (player1.getChips() - pp_bet < 0) {
                    System.out.println("You dont have that much chips left!");
                    System.out.println("Please re-enter the amount of the bet:");
                } else if (pp_bet > player1.getChips() / 2) {
                    System.out.println("Your pp_bet should not be more than half of your chips.");
                    System.out.println("Please re-enter the amount of the bet:");
                } else if (pp_bet <= 0) {
                    System.out.println("You cant bet in 0 or minus number!");
                    System.out.println("Please re-enter the amount of the bet:");
                }
                else
                    break;
            } catch(Exception b){
                System.out.println("Please enter Number within 0 ~ " + (player1.getChips()/2) + ".");
                reader = new Scanner(System.in);
            }
        }
        System.out.println("_____________________________ Game Start _____________________________");
        System.out.print("   Dealer: " + dealer1.getName() + "(" + dealer1.getChips() + ")");
        System.out.println("  Player: " + player1.getName() + "(" + player1.getChips() + ")(bet in " + pp_bet + ")");
        System.out.println("______________________________________________________________________");
        System.out.println("");
        //Init Deck
        ch.CardInit();//Deck Init
        ch.Shuffle();//Shuffle Deck
        //pPlayer Draw
        player1.Hit(ch.dealer());
        player1.Hit(ch.dealer());
        player1.show();
        //dPlayer Draw
        dealer1.Hit(ch.dealer());
        dealer1.show();
        if(dealer1.calculator() == 10){
            dealer1.Hit(ch.dealer());//dealer dark draw
            if(dealer1.HaveBJ()){
                if(player1.HaveBJ()){
                    System.out.println("--------------------- CheckPoint! ---------------------");
                    dealer1.show();
                    player1.show();
                    System.out.println("Dealer and Player both get BlackJack!");
                    System.out.println("DRAW!");
                    System.out.println("");
                    dealer1.setState(State.DRAW);
                    player1.setState(State.DRAW);
                    System.out.println(player1.getName() + "(player) got " + player1.getChips() + " chips left.");
                    System.out.println(dealer1.getName() + "(dealer) got " + dealer1.getChips() + " chips left.");
                    pp_bet = 0;
                    return State.DRAW;
                }
                else {
                    System.out.println("--------------------- CheckPoint! ---------------------");
                    dealer1.show();
                    System.out.println("Dealer got BlackJack!");
                    System.out.println("Dealer WIN!");
                    System.out.println("");
                    dealer1.setState(State.WIN);
                    player1.setState(State.LOST);
                    System.out.println(player1.getName() + " lose " + pp_bet*2 + " chips.");
                    player1.minusChips(pp_bet * 2.0);
                    dealer1.addChips(pp_bet * 2.0);
                    System.out.println(player1.getName() + "(player) got " + player1.getChips() + " chips left.");
                    System.out.println(dealer1.getName() + "(dealer) got " + dealer1.getChips() + " chips left.");
                    pp_bet = 0;
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
                pp_i_will = reader.nextBoolean();
                if (pp_i_will) {
                    pp_insurance = pp_bet / 2;
                    System.out.println(player1.getName() + " buy a insurance (cost " + pp_insurance + " chips.");
                    if (dealer1.HaveBJ()) {
                        System.out.println("--------------------- CheckPoint! ---------------------");
                        dealer1.show();
                        System.out.println("Dealer got BlackJack!");
                        System.out.println("And Player have Insurance.");
                        System.out.println("");
                        dealer1.setState(State.WIN);
                        player1.setState(State.LOST);
                        System.out.println(player1.getName() + " win " + pp_bet+pp_insurance + " chips.");
                        player1.addChips(pp_bet + pp_insurance);
                        dealer1.minusChips(pp_insurance);
                        System.out.println(player1.getName() + "(player) got " + player1.getChips() + " chips left.");
                        System.out.println(dealer1.getName() + "(dealer) got " + dealer1.getChips() + " chips left.");
                        pp_insurance = 0;
                        pp_bet = 0;
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
                        System.out.println(player1.getName() + " lose " + pp_bet * 2 + " chips.");
                        player1.minusChips(pp_bet * 2);
                        dealer1.addChips(pp_bet * 2);
                        System.out.println(player1.getName() + "(player) got " + player1.getChips() + " chips left.");
                        System.out.println(dealer1.getName() + "(dealer) got " + dealer1.getChips() + " chips left.");
                        pp_bet = 0;
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
        System.out.println("--------------------- Player Stage ---------------------");
        while(player1.getHandnum() < 5) {
            System.out.println("");
            System.out.println("You have " + player1.getHandnum() + " cards in hand now.");
            System.out.println("Hit or Stand?");
            while(true) {
                try {
                    pp_will = reader.next();
                    if(!pp_will.equalsIgnoreCase("hit") && !pp_will.equalsIgnoreCase("stand"))
                        System.out.println("Wrong command, Please enter \"Hit\" or \"Stand.\"");
                    else
                        break;
                }catch (Exception s){
                    System.out.println("Incorrect form input! Please enter \"Hit\" or \"Stand.\"");
                    reader = new Scanner(System.in);
                }
            }
            if (HitJudge(pp_will)) {
                player1.Hit(ch.dealer());//the dealer returns a card to player, and the card join player's hand
                player1.show();
                if (player1.Have21()){
                    System.out.println("--------------------- CheckPoint! ---------------------");
                    System.out.println(player1.getName() + " got " + player1.calculator() + " point");
                    System.out.println("Player1 WIN!");
                    System.out.println("");
                    dealer1.setState(State.LOST);
                    player1.setState(State.WIN);
                    System.out.println(player1.getName() + " win " + pp_bet + " chips.");
                    player1.addChips(pp_bet);
                    dealer1.minusChips(pp_bet);
                    System.out.println(player1.getName() + "(player) got " + player1.getChips() + " chips left.");
                    System.out.println(dealer1.getName() + "(dealer) got " + dealer1.getChips() + " chips left.");
                    pp_bet = 0;
                    return State.WIN;
                }
            }
            else
                break;
            if (player1.calculator() > 21) {
                System.out.println("--------------------- CheckPoint! ---------------------");
                player1.show();
                System.out.println(player1.getName() + " got " + player1.calculator() + " point");
                System.out.println("Player's Hand is Burst. You lose.");
                System.out.println("Dealer win.");
                System.out.println("");
                dealer1.setState(State.WIN);
                player1.setState(State.LOST);
                System.out.println(player1.getName() + " lose " + pp_bet + " chips.");
                player1.minusChips(pp_bet);
                dealer1.addChips(pp_bet);
                System.out.println(player1.getName() + "(player) got " + player1.getChips() + " chips left.");
                System.out.println(dealer1.getName() + "(dealer) got " + dealer1.getChips() + " chips left.");
                pp_bet = 0;
                return State.LOST;
            }
        }

        if(player1.getHandnum() == 5) {
            System.out.println("--------------------- CheckPoint! ---------------------");
            player1.show();
            System.out.println(player1.getName() + " got " + player1.calculator() + " point and not burst within 5 cards!");
            System.out.println("Player1 WIN!");
            System.out.println("");
            dealer1.setState(State.LOST);
            player1.setState(State.WIN);
            System.out.println(player1.getName() + " win " + pp_bet + " chips.");
            player1.addChips(pp_bet);
            dealer1.minusChips(pp_bet);
            System.out.println(player1.getName() + "(player) got " + player1.getChips() + " chips left.");
            System.out.println(dealer1.getName() + "(dealer) got " + dealer1.getChips() + " chips left.");
            pp_bet = 0;
            return State.WIN;
        }

        //Dealer Stage
        System.out.println("--------------------- Dealer Stage ---------------------");
        dealer1.show();//dealer show his hand after player's draw

        while (dealer1.calculator() <= 17){
            dealer1.Hit(ch.dealer());
            dealer1.show();
            if(dealer1.calculator() > 21) {
                System.out.println("--------------------- CheckPoint! ---------------------");
                dealer1.show();
                System.out.println(dealer1.getName() + " got " + dealer1.calculator() + " point.");
                System.out.println("Dealer's Hand is Burst. Player win.");
                System.out.println("");
                dealer1.setState(State.LOST);
                player1.setState(State.WIN);
                System.out.println(player1.getName() + " win " + pp_bet + " chips.");
                player1.addChips(pp_bet);
                dealer1.minusChips(pp_bet);
                System.out.println(player1.getName() + "(player) got " + player1.getChips() + " chips left.");
                System.out.println(dealer1.getName() + "(dealer) got " + dealer1.getChips() + " chips left.");
                pp_bet = 0;
                return State.WIN;
            }
            else if(dealer1.calculator() == 21) {
                System.out.println("--------------------- CheckPoint! ---------------------");
                dealer1.show();
                System.out.println(dealer1.getName() + " got " + dealer1.calculator() + " point.");
                System.out.println("Dealer WIN!");
                System.out.println("");
                dealer1.setState(State.WIN);
                player1.setState(State.LOST);
                System.out.println(player1.getName() + " lose " + pp_bet*2 + " chips.");
                player1.minusChips(pp_bet * 2);
                dealer1.addChips(pp_bet*2);
                System.out.println(player1.getName() + "(player) got " + player1.getChips() + " chips left.");
                System.out.println(dealer1.getName() + "(dealer) got " + dealer1.getChips() + " chips left.");
                pp_bet = 0;
                return State.LOST;
            }
        }


        System.out.println("Hit or Stand?");
        while(true) {
            try {
                pd_will = reader.next();
                if(!pp_will.equalsIgnoreCase("hit") && !pd_will.equalsIgnoreCase("stand"))
                    System.out.println("Wrong command, Please enter \"Hit\" or \"Stand\".");
                else
                    break;
            }catch (Exception s){
                System.out.println("Incorrect form input! Please enter \"Hit\" or \"Stand.\"");
                reader = new Scanner(System.in);
            }
        }
        if (HitJudge(pp_will)) {
            dealer1.Hit(ch.dealer());
            dealer1.show();
            if(dealer1.calculator() > 21) {
                System.out.println("--------------------- CheckPoint! ---------------------");
                dealer1.show();
                System.out.println(dealer1.getName() + " got " + dealer1.calculator() + " point.");
                System.out.println("Dealer's Hand is Burst. Player win.");
                System.out.println("");
                dealer1.setState(State.LOST);
                player1.setState(State.WIN);
                System.out.println(player1.getName() + " win " + pp_bet + " chips.");
                player1.addChips(pp_bet);
                dealer1.minusChips(pp_bet);
                System.out.println(player1.getName() + "(player) got " + player1.getChips() + " chips left.");
                System.out.println(dealer1.getName() + "(dealer) got " + dealer1.getChips() + " chips left.");
                pp_bet = 0;
                return State.WIN;
            }
            else if(dealer1.calculator() == 21) {
                System.out.println("--------------------- CheckPoint! ---------------------");
                dealer1.show();
                System.out.println(dealer1.getName() + " got " + dealer1.calculator() + " point.");
                System.out.println("Dealer WIN!");
                System.out.println("");
                dealer1.setState(State.WIN);
                player1.setState(State.LOST);
                System.out.println(player1.getName() + " lose " + pp_bet*2 + " chips.");
                player1.minusChips(pp_bet * 2);
                dealer1.addChips(pp_bet*2);
                System.out.println(player1.getName() + "(player) got " + player1.getChips() + " chips left.");
                System.out.println(dealer1.getName() + "(dealer) got " + dealer1.getChips() + " chips left.");
                pp_bet = 0;
                return State.LOST;
            }
            System.out.println("Hit or Stand?");
            while(true) {
                try {
                    pd_will = reader.next();
                    if(!pp_will.equalsIgnoreCase("hit") && !pd_will.equalsIgnoreCase("stand"))
                        System.out.println("Wrong command, Please enter \"Hit\" or \"Stand\".");
                    else
                        break;
                }catch (Exception s){
                    System.out.println("Incorrect form input! Please enter \"Hit\" or \"Stand.\"");
                    reader = new Scanner(System.in);
                }
            }
        }

        //ValueJudge
        System.out.println("--------------------- Final Judgement ---------------------");
        if(dealer1.calculator() > player1.calculator()){
            player1.show();
            dealer1.show();
            System.out.println(player1.getName() + " got " + player1.calculator() + " point.");
            System.out.println(dealer1.getName() + " got " + dealer1.calculator() + " point.");
            System.out.println("Dealer WIN!");
            System.out.println("");
            dealer1.setState(State.WIN);
            player1.setState(State.LOST);
            System.out.println(player1.getName() + " lose " + pp_bet * 2 + " chips.");
            player1.minusChips(pp_bet * 2);
            dealer1.addChips(pp_bet * 2);
            System.out.println(player1.getName() + "(player) got " + player1.getChips() + " chips left.");
            System.out.println(dealer1.getName() + "(dealer) got " + dealer1.getChips() + " chips left.");
            pp_bet = 0;
            return State.LOST;
        }
        else if(dealer1.calculator() < player1.calculator()){
            player1.show();
            dealer1.show();
            System.out.println(player1.getName() + " got " + player1.calculator() + " chips point.");
            System.out.println(dealer1.getName() + " got " + dealer1.calculator() + " chips point.");
            System.out.println("Player WIN!");
            System.out.println("");
            dealer1.setState(State.LOST);
            player1.setState(State.WIN);
            System.out.println(player1.getName() + " win " + pp_bet + " chips.");
            player1.addChips(pp_bet);
            dealer1.minusChips(pp_bet);
            System.out.println(player1.getName() + "(player) got " + player1.getChips() + " chips left.");
            System.out.println(dealer1.getName() + "(dealer) got " + dealer1.getChips() + " chips left.");
            pp_bet = 0;
            return State.WIN;
        }
        else {
            player1.show();
            dealer1.show();
            System.out.println(player1.getName() + " got " + player1.calculator() + " point.");
            System.out.println(dealer1.getName() + " got " + dealer1.calculator() + " point.");
            System.out.println("DRAW!");
            System.out.println("");
            dealer1.setState(State.DRAW);
            player1.setState(State.DRAW);
            System.out.println(player1.getName() + "(player) got " + player1.getChips() + " chips left.");
            System.out.println(dealer1.getName() + "(dealer) got " + dealer1.getChips() + " chips left.");
            pp_bet = 0;
            return State.DRAW;
        }
    }

    public boolean HitJudge(String select){
        if(select.equalsIgnoreCase("hit"))
            return true;
        else if(select.equalsIgnoreCase("stand"))
            return false;
        else
            return false;
    }


}
