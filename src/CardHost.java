public class CardHost {
    private Card[] deck = new Card[52];//牌库使用去除大小Joker外的52张牌
    int index = 0;//发牌器下标
    /*初始化牌库*/
    public void CardInit() {
        int i, j;
        for (i = 1; i < 5; i++) {
            for (j = 1; j < 14; j++) {
                deck[(i - 1) * 13 + (j - 1)] = new Card(j, i);
            }
        }
    }

    /*洗牌*/
    public void Shuffle() {
        Card temp = null;
        int p, q;
        int i;
        for (i = 0; i < 52; i++) {
            p = (int) (Math.random() * 52);//利用Math.random()函数生成一个[0, 52)的整数
            q = (int) (Math.random() * 52);
            temp = deck[p];
            deck[p] = deck[q];
            deck[q] = temp;
        }
    }

    /*发牌*/
    public Card dealer(){
        return deck[index++];
    }
}
