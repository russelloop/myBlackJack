public class Hand {
    private Card[] hand = new Card[5];
    private int handSum;
    private int handindex;

    public Hand(){
        this.handSum = 0;
        this.handindex = 0;
    }

    public void AddCard(Card newHandCard){
        int v,t;
        v = newHandCard.getValue();
        t = newHandCard.getType();
        hand[handindex] = new Card(v, t);   //add hand card
        handindex++;                        //increase hand cards number
    }

    public int getHandindex(){
        return handindex;
    }

    public int getSum(){
        int i;
        for(i = 0; i <= handindex; i++){
            this.handSum += hand[handindex].getValue();
        }
        return handSum;
    }

    public Card getCard(int i){
        return hand[i-1];
    }

    public int getCardValue(int i){
        int value;
        value = hand[i].getValue();
        return value;
    }

    public int getCardType(int i){
        int type;
        type = hand[i].getType();
        return type;
    }

    public void ShowHand(String name){
        System.out.println(name + "'s hand is:");
        for (int i = 0; i < handindex; i++) {
            if(hand[handindex].getType() == 1)
                System.out.print("黑桃");
            else if(hand[handindex].getType() == 2)
                System.out.print("红桃");
            else if(hand[handindex].getType() == 3)
                System.out.print("梅花");
            else if(hand[handindex].getType() == 4)
                System.out.print("方块");
            System.out.print(hand[handindex].getValue());
            System.out.print("、");
        }
        System.out.println("");
    }
}
