public class Hand {
    private Card[] hand = new Card[5];
    private int handSum;
    private int handIndex;

    public Hand(){
        this.handSum = 0;
        this.handIndex = 0;
    }

    public void AddCard(Card newHandCard){
        int v,t;
        v = newHandCard.getValue();
        t = newHandCard.getType();
        hand[handIndex] = new Card(v, t);   //add hand card
        handIndex++;                        //increase hand cards number
        handSum++;
    }

    public void setCard(int v, int t, int i){
        hand[i].setValue(v);
        hand[i].setType(t);
    }

    public int gethandSum(){
        return handSum;
    }


    public Card getCard(int i){
        return hand[i];
    }


    //显示当前手牌
    public void ShowHand(String name){
        System.out.println("");
        System.out.println(name + "'s hand is:");
        for (int i = 0; i < handSum; i++) {
            //输出牌面花色
            if(hand[i].getType() == 1)
                System.out.print("黑桃");
            else if(hand[i].getType() == 2)
                System.out.print("红桃");
            else if(hand[i].getType() == 3)
                System.out.print("梅花");
            else if(hand[i].getType() == 4)
                System.out.print("方块");
            //输出牌面点数
            if (hand[i].getValue() == 1)
                System.out.print("A");
            else if(hand[i].getValue() == 11)
                System.out.print("J");
            else if(hand[i].getValue() == 12)
                System.out.print("K");
            else if(hand[i].getValue() == 13)
                System.out.print("Q");
            else
            System.out.print(hand[i].getValue());
            System.out.print("、");
        }
        System.out.println("");
        System.out.println("");
    }
}
