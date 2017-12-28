import java.util.Vector;

public class Player {

    private String name;
    private double chips;//筹码
    private Hand hand = new Hand();//手牌
    private State state;//状态

    /*Player 构造函数*/
    public Player(String playername) {
        this.name = playername;
        this.chips = 2000; //每名玩家初始化拥有2000筹码
    }

    public String getName(){
        return name;
    }

    /*增加筹码*/
    public void addChips(double money) {
        this.chips += money;
    }

    /*减少筹码*/
    public void minusChips(double money) {
        this.chips -= money;
    }

    /*获取余额筹码*/
    public double getChips() {
        if(chips >= 0)
            return chips;
        else
            return 0.0;
    }

    public void setChips(double v){
        this.chips = v;
    }

    /*拿牌*/
    public void Hit(Card newPlayerCard) {
        System.out.println(this.name + " has draw a card.");
        hand.AddCard(newPlayerCard);
    }

    /*获取手牌数量*/
    public int getHandnum() {
        return hand.gethandSum();
    }

    //设置玩家胜负状态
    public void setState(State state) {
        this.state = state;
    }

    public void show() {
        hand.ShowHand(this.name);
    }

    //BJ判断器
    public boolean HaveBJ() {
        boolean haveA = false;
        boolean haveT = false;
        int i;
        //判断是否有A
        for (i = 0; i < hand.gethandSum(); i++) {
            Card temp = (Card) hand.getCard(i);
            if (temp.getValue() == 1) {
                haveA = true;
                break;
            }
        }
        //判断是否有T（J、K或Q）
        for (i = 0; i < hand.gethandSum(); i++) {
            Card temp = (Card) hand.getCard(i);
            if ((int) temp.getValue() >= 11) {
                haveT = true;
                break;
            }
        }
        return (haveA && haveT);
    }

    //21点判断器
    public boolean Have21() {
        if (calculator() == 21)
            return true;
        else
            return false;
    }

    //牌面加法器
    public int calculator() {
        int total = 0;
        int i;
        for (i = 0; i < hand.gethandSum(); i++) {//计算前先将手牌中的所有A移动到数组末尾方便抉择取值
            Card temp = new Card(hand.getCard(i).getValue(),hand.getCard(i).getType());//new
            if (temp.getValue() == 1) {   //若有A,和当前手牌中最后一张交换位置
                hand.setCard(hand.getCard(hand.gethandSum() - 1).getValue(), hand.getCard(hand.gethandSum() - 1).getType(), i);
                hand.setCard(temp.getValue(), temp.getType(), hand.gethandSum() - 1);
            }
        }
        for (i = 0; i < hand.gethandSum(); i++) {
            Card temp = hand.getCard(i);
            if (temp.getValue() == 1) {
                if (total > 10)
                    total += 1;//如果当前总数大于10，则将A视作1计算
                else
                    total += 11;//否则当前总数小于等于10，则将A视作1计算
            }
            //小于10的手牌按照点数计算
            else if (temp.getValue() <= 10)
                total += temp.getValue();
                //手里的花牌按10计算
            else if (temp.getValue() > 10)
                total += 10;
        }
        return total;
    }

}

