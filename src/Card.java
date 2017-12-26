public class Card {
    /**
     * @param value: 牌的面值，1~10，花牌（J、K、Q）计作10
     * @param type: 花色，分别用“1,2,3,4”代表“黑桃，红桃，梅花，方片”
     */
    private int value;//面值
    private int type;//花色
    public Card(int v, int t){
        this.value = v;
        this.type = t;
    }
    public int getValue(){
        return value;
    }
    public int getType(){
        return type;
    }
    public void setValue(int cv){
        this.value = cv;
    }
    public void setType(int ct){
        this.type = ct;
    }
}
