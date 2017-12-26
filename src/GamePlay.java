import java.util.Scanner;

public class GamePlay {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = null;//用户的名称
        State status;//记录每场游戏后的胜负情况
        int W_Round = 0;
        int L_Round = 0;
        int D_Round = 0;
        int Round = 0;

        System.out.println("Welcome to BlackJack Game.");
        System.out.println("Please enter your name:");
        name = sc.next();
        while (true) {
            Round++;
            System.out.println("");
            System.out.println("_____________________________ Round " + Round + " _____________________________");
            System.out.println("Please choose a mode(enter 1 or 2):");
            System.out.println("1.PVE  2.PVP(2 players)");
            int mode_sel = sc.nextInt();
            System.out.println("you want to be Dealer or Player(enter 1 or 2)");
            System.out.println("1.Dealer 2.Player");
            int role_sel = sc.nextInt();
            if(mode_sel == 1){//PVE
                if(role_sel == 2){//P1VE
                    PVEGameFrame pve = new PVEGameFrame();
                    status = pve.PVE(name);
                    if(status == State.WIN)
                        W_Round++;
                    else if(status == State.LOST)
                        L_Round++;
                    else
                        D_Round++;
                }
            }
        }
    }
}
