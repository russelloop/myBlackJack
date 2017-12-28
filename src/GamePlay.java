import java.util.Scanner;

public class GamePlay {
    public static void main (String[] args) {
        String name1 = null;//用户的名称
        String name2 = null;
        double p_end_chips = 2000;
        double d_end_chips = 2000;
        State status;//记录每场游戏后的胜负情况
        int restart_sel = 1;
        int pve_W_Round = 0;
        int pve_L_Round = 0;
        int pve_D_Round = 0;
        int pvp_W_Round = 0;
        int pvp_L_Round = 0;
        int pvp_D_Round = 0;
        int pvp_Round = 1;
        int pve_Round = 1;
        Scanner sc = new Scanner(System.in);
        int mode_sel = 0;
        System.out.println("Welcome to BlackJack Game.");

        while (true) {
            System.out.println("");
            System.out.println("_____________________________ GAME MENU _____________________________");
            System.out.println("        Please choose a mode(enter 1 or 2)(enter 0 to exit):");
            System.out.println(" ------------------------ 1.PVE(As Player) ------------------------- ");
            System.out.println(" ------------------------ 2.PVP(2 Players) ------------------------- ");
            System.out.println(" ------------------------ 0.Exit. ---------------------------------- ");
            System.out.println("_____________________________________________________________________");
            while(true) {
                try {
                    mode_sel = sc.nextInt();
                    if(mode_sel != 1 && mode_sel != 2 && mode_sel != 0){
                        System.out.println("Please enter number 1 or 2 or 0.");
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Please enter IntNumber");
                    sc = new Scanner(System.in);
                }
            }
            //PVE
            if (mode_sel == 1) {
                System.out.println("Please enter your name:");
                name1 = sc.next();
                do {
                    System.out.println("");
                    System.out.println("_____________________________ Round " + pve_Round + " in PVE _____________________________");
                    PVEGameFrame pve = new PVEGameFrame();
                    Player player1 = new Player(name1);
                    Player dealer1 = new Player("Computer");
                    player1.setChips(p_end_chips);
                    dealer1.setChips(d_end_chips);
                    status = pve.PVE(player1, dealer1);
                    p_end_chips = player1.getChips();
                    d_end_chips = dealer1.getChips();
                    if (status == State.WIN) {
                        pve_W_Round++;
                        pve_Round++;
                    } else if (status == State.LOST) {
                        pve_L_Round++;
                        pve_Round++;
                    } else if (status == State.DRAW) {
                        pve_D_Round++;
                        pve_Round++;
                    }

                    if(p_end_chips <= 0 || d_end_chips <= 0){
                        System.out.println("_____________________________ Game Over _____________________________");
                        if(p_end_chips <= 0){
                            System.out.println("You have lost all your chips.");
                            System.out.println("You have play " + (pve_Round - 1) + " rounds in PVE mode, and your record is: ");
                            System.out.println(pve_W_Round + " Win, " + pve_D_Round + " Draw, " + pve_L_Round + " Lose.");
                            System.out.println("");
                            System.out.println("Please enter number to select:");
                            System.out.println("1.Restart in PVE(refresh chips)");
                            System.out.println("2.ReSelect GameMode");
                            System.out.println("0.Exit Game.");
                            while(true) {
                                try {
                                    restart_sel = sc.nextInt();
                                    if(restart_sel != 1 && restart_sel != 2 && restart_sel != 0)
                                        System.out.println("Please enter number 1,2 or 0.");
                                    else
                                        break;
                                }catch (Exception sel){
                                    System.out.println("Incorrect form input! Please enter number 1,2 or 0.");
                                    sc = new Scanner(System.in);
                                }
                            }
                            if (restart_sel == 1) {
                                player1.setChips(2000);
                                p_end_chips = 2000;
                                d_end_chips = 2000;
                            } else if (restart_sel == 2) {
                                player1.setChips(2000);
                                dealer1.setChips(2000);
                                p_end_chips = 2000;
                                d_end_chips = 2000;
                                break;
                            } else if (restart_sel == 0)
                                System.exit(0);
                        }
                        else {
                            System.out.println("You have won all the chips, Congratulations!!");
                            System.out.println("You have play " + (pve_Round - 1) + " rounds in PVE mode, and your record is: ");
                            System.out.println(pve_W_Round + " Win, " + pve_D_Round + " Draw, " + pve_L_Round + " Lose.");
                            System.out.println("");
                            System.out.println("Please enter number to select:");
                            System.out.println("1.Restart in PVE(refresh chips)");
                            System.out.println("2.ReSelect GameMode");
                            System.out.println("0.Exit Game.");
                            restart_sel = sc.nextInt();
                            if (restart_sel == 1) {
                                player1.setChips(2000);
                                p_end_chips = 2000;
                                d_end_chips = 2000;
                            } else if (restart_sel == 2) {
                                player1.setChips(2000);
                                dealer1.setChips(2000);
                                p_end_chips = 2000;
                                d_end_chips = 2000;
                                break;
                            } else if (restart_sel == 0)
                                System.exit(0);
                        }
                    }
                    else {
                        System.out.println("_____________________________ Round " + (pve_Round - 1) + " in PVE is over. _____________________________");
                        System.out.println("");
                        System.out.println("You have play " + (pve_Round - 1) + " rounds in PVE mode, and your record is: ");
                        System.out.println(pve_W_Round + " Win, " + pve_D_Round + " Draw, " + pve_L_Round + " Lose.");
                        System.out.println("");
                        System.out.println("Please enter number to select:");
                        System.out.print("1.Next Round.");
                        System.out.println("            2.Restart in PVE(refresh chips)");
                        System.out.print("3.ReSelect GameMode");
                        System.out.println("    0.Exit Game.");
                        while(true) {
                            try {
                                restart_sel = sc.nextInt();
                                if(restart_sel != 1 && restart_sel != 2 && restart_sel != 0 && restart_sel != 3)
                                    System.out.println("Please enter number 1,2,3 or 0.");
                                else
                                    break;
                            }catch (Exception sel){
                                System.out.println("Incorrect form input! Please enter number 1,2 or 0.");
                                sc = new Scanner(System.in);
                            }
                        }

                        if (restart_sel == 2) {
                            player1.setChips(2000);
                            p_end_chips = 2000;
                            d_end_chips = 2000;
                            restart_sel = 1;
                        } else if (restart_sel == 3) {
                            player1.setChips(2000);
                            dealer1.setChips(2000);
                            p_end_chips = 2000;
                            d_end_chips = 2000;
                            break;
                        } else if (restart_sel == 0)
                            System.exit(0);
                    }

                }
                while (restart_sel == 1);
            }

            //PVP
            else if (mode_sel == 2) {
                System.out.println("Please Player1 (As Player) enter your name:");
                name1 = sc.next();
                System.out.println("Please Player2 (As Dealer) enter your name:");
                name2 = sc.next();
                do {
                    System.out.println("");
                    System.out.println("_____________________________ Round " + pvp_Round + " in PVP _____________________________");
                    PVPGameFrame pvp = new PVPGameFrame();
                    Player player1 = new Player(name1);
                    Player dealer1 = new Player(name2);
                    player1.setChips(p_end_chips);
                    dealer1.setChips(d_end_chips);
                    status = pvp.PVP(player1, dealer1);
                    p_end_chips = player1.getChips();
                    d_end_chips = dealer1.getChips();
                    if (status == State.WIN) {
                        pvp_W_Round++;
                        pvp_Round++;
                    } else if (status == State.LOST) {
                        pvp_L_Round++;
                        pvp_Round++;
                    } else if (status == State.DRAW) {
                        pvp_D_Round++;
                        pvp_Round++;
                    }

                    if(p_end_chips <= 0 || d_end_chips <= 0){
                        System.out.println("_____________________________ Game Over _____________________________");
                        if(p_end_chips <= 0){
                            System.out.println(player1.getName() + " have lost all his chips.");
                            System.out.println("You have play " + (pvp_Round - 1) + " rounds in PVP mode, and the record is: ");
                            System.out.print(player1.getName() + ": " + pvp_W_Round + " Win, ");
                            System.out.print(dealer1.getName() + ": " + (pvp_Round - pvp_W_Round - pvp_D_Round) + " Win, ");
                            System.out.println(pvp_D_Round + "Draw.");
                            System.out.println("");
                            System.out.println("Please enter number to select:");
                            System.out.println("1.Restart in PVP(refresh chips)");
                            System.out.println("2.ReSelect GameMode");
                            System.out.println("0.Exit Game.");
                            while(true) {
                                try {
                                    restart_sel = sc.nextInt();
                                    if(restart_sel != 1 && restart_sel != 2 && restart_sel != 0)
                                        System.out.println("Please enter number 1,2 or 0.");
                                    else
                                        break;
                                }catch (Exception sel){
                                    System.out.println("Incorrect form input! Please enter number 1,2 or 0.");
                                    sc = new Scanner(System.in);
                                }
                            }
                            if (restart_sel == 1) {
                                player1.setChips(2000);
                                p_end_chips = 2000;
                                d_end_chips = 2000;
                            } else if (restart_sel == 2) {
                                player1.setChips(2000);
                                dealer1.setChips(2000);
                                p_end_chips = 2000;
                                d_end_chips = 2000;
                                break;
                            } else if (restart_sel == 0)
                                System.exit(0);
                        }
                        else {
                            System.out.println(dealer1.getName() + " have lost all his chips.");
                            System.out.println("You have play " + (pvp_Round - 1) + " rounds in PVP mode, and the record is: ");
                            System.out.print(player1.getName() + ": " + pvp_W_Round + " Win, ");
                            System.out.print(dealer1.getName() + ": " + (pvp_Round - pvp_W_Round - pvp_D_Round - 1) + " Win, ");
                            System.out.println(pvp_L_Round + "Draw.");
                            System.out.println("");
                            System.out.println("Please enter number to select:");
                            System.out.println("1.Restart in PVP(refresh chips)");
                            System.out.println("2.ReSelect GameMode");
                            System.out.println("0.Exit Game.");
                            while(true) {
                                try {
                                    restart_sel = sc.nextInt();
                                    if(restart_sel != 1 && restart_sel != 2 && restart_sel != 0)
                                        System.out.println("Please enter number 1,2 or 0.");
                                    else
                                        break;
                                }catch (Exception sel){
                                    System.out.println("Incorrect form input! Please enter number 1,2 or 0.");
                                    sc = new Scanner(System.in);
                                }
                            }
                            if (restart_sel == 1) {
                                player1.setChips(2000);
                                p_end_chips = 2000;
                                d_end_chips = 2000;
                            } else if (restart_sel == 2) {
                                player1.setChips(2000);
                                dealer1.setChips(2000);
                                p_end_chips = 2000;
                                d_end_chips = 2000;
                                break;
                            } else if (restart_sel == 0)
                                System.exit(0);
                        }
                    }
                    System.out.println("_____________________________ Round " + (pvp_Round - 1) + " in PVP is over. _____________________________");
                    System.out.println("");
                    System.out.println("You have play " + (pvp_Round - 1) + " rounds in PVP mode, and the record is: ");
                    System.out.print(player1.getName() + ": " + pvp_W_Round + " Win, ");
                    System.out.print(dealer1.getName() + ": " + (pvp_Round - pvp_W_Round - pvp_D_Round - 1) + " Win, ");
                    System.out.println(pvp_D_Round + " Draw.");
                    System.out.println("");
                    System.out.println("Please enter number to select:");
                    System.out.print("1.Next Round.");
                    System.out.println("            2.Restart in PVP(refresh chips)");
                    System.out.print("3.ReSelect GameMode");
                    System.out.println("    0.Exit Game.");
                    while(true) {
                        try {
                            restart_sel = sc.nextInt();
                            if(restart_sel != 1 && restart_sel != 2 && restart_sel != 0 && restart_sel != 3)
                                System.out.println("Please enter number 1,2,3 or 0.");
                            else
                                break;
                        }catch (Exception sel){
                            System.out.println("Incorrect form input! Please enter number 1,2 or 0.");
                            sc = new Scanner(System.in);
                        }
                    }

                    if (restart_sel == 2) {
                        player1.setChips(2000);
                        dealer1.setChips(2000);
                        p_end_chips = 2000;
                        d_end_chips = 2000;
                        restart_sel = 1;
                    } else if (restart_sel == 3) {
                        player1.setChips(2000);
                        dealer1.setChips(2000);
                        p_end_chips = 2000;
                        d_end_chips = 2000;
                        break;
                    }
                    else if (restart_sel == 0)
                        System.exit(0);

                }
                while (restart_sel == 1);
            }

            //Exit
            else if(mode_sel == 0){
                System.exit(0);
            }
        }
    }


}
