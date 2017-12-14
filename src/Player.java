import java.util.Vector;

public class Player {

    private String name;
    private double chips;//筹码
    private Hand hand = new Hand();//手牌
    private State state;//状态

    /*Player 构造函数*/
    public Player(String playername){
        this.name = playername;
        this.chips = 2000; //每名玩家初始化拥有2000筹码
    }
    /*增加筹码*/
    public void addChips(int money){
        this.chips += money;
    }
    /*减少筹码*/
    public void minusChips(int money){
        this.chips -= money;
    }
    /*获取余额筹码*/
    public double getChips(){
        return chips;
    }
    /*拿牌*/
    public void Hit(Card newPlayerCard){
        hand.AddCard(newPlayerCard);
    }
    /*获取手牌数量*/
    public int getHandnum(){
        return hand.getHandindex();
    }
    //设置玩家胜负状态
    public void setState(State state){
        this.state = state;
    }
    public void show(){
        hand.ShowHand(this.name);
    }
    //BJ判断器
    public boolean HaveBJ(){
        boolean haveA = false;
        boolean haveT = false;
        int i;
        //判断是否有A
        for(i = 0; i <= 5; i++){
            Card temp = hand.getCard(i);
            if(temp.getValue() == 1) {
                haveA = true;
                break;
            }
        }
        //判断是否有T（J、K或Q）
        for(i = 0; i<= 5; i++){
            Card temp = hand.getCard(i);
            if((int)temp.getValue() >= 11){
                haveT = true;
                break;
            }
        }
        return (haveA && haveT);
    }
    //牌面加法器
    public int calculator(){
        int total = 0;
        int i;
        for(i = 0; i <= 5; i++){
            Card temp = hand.getCard(i);
            //小于10的手牌按照点数计算
            if(temp.getValue() <= 10)
                total += temp.getValue();
            //手里的花牌按10计算
            else if(temp.getValue() > 10)
                total += 10;
            //手里有A时，若其他牌面综合小于12
            if(HaveBJ() && total < 12)
                total += 11;//则将黑桃A看做11点
        }
        return total;
    }
}
