public class CardHost {
    private Card[] deck = new Card[52];//牌库使用去除大小Joker外的52张牌

    /*初始化牌库*/
    public CardHost() {
        int i, j;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 14; j++) {
                deck[(i - 1) * 13 + j - 1] = new Card(j, i);
            }
        }
    }

    /*洗牌*/
    public void Shuffle() {
        Card temp = null;
        int p, q;
        int i;
        p = (int) (Math.random() * 52);//利用Math.random()函数生成一个[0, 52)的整数
        q = (int) (Math.random() * 52);
        for (i = 0; i < 52; i++) {
            temp = deck[p];
            deck[p] = deck[q];
            deck[q] = temp;
        }
    }

    /*发牌*/

}
