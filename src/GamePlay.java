import java.util.Scanner;

public class GamePlay {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int status;//记录每场游戏后的胜负情况

        while (true) {
            System.out.println("Welcome to BlackJack Game.");
            System.out.println("Please choose a mode(enter 1 or 2):");
            System.out.println("1.PVE  2.PVP(2 players)");
            int mode_sel = sc.nextInt();
            System.out.println("you want to be Dealer or Player(enter 1 or 2)");
            System.out.println("1.Dealer 2.Player");
            int role_sel = sc.nextInt();
            if(mode_sel == 1){
                if(role_sel == 1){//PVE and being Player
                    PVEGameFrame pve = new PVEGameFrame();
                    status = pve.PVE();
                }
            }
        }
    }
}
