package InnerClassNotes;

public class InnerClassNotes {
    public static void main(String[] args) {

        Person person1 = new Person();
        person1.age = 22;
        person1.card = new Card(1,100);

        Person.CreditCard creditCard1 = person1.new CreditCard(2, 0,-100);
        Person.CreditCard creditCard2 = person1.new CreditCard(3, 0,-500);
        System.out.println(creditCard1);//CreditCard{limit=-100, cardNum=2, cardBalance=0}
        System.out.println(creditCard2);//CreditCard{limit=-500, cardNum=3, cardBalance=0}
    }
}
class Person{

    int age = 0;
    Card card;//每一个Person实例的每一个card变量只能指向一个Card实例，即每一个person只能拥有一张Card

    class CreditCard extends Card{
        //每一个Person实例可以创建无数CreditCard实例，即每一个person可以拥有无数张卡
        //注意：通过Person实例创建出来的内部类CreditCard实例不是该Person实例的成员变量
        int limit = 0;//信用卡额度

        public CreditCard(int cardNum, int cardMoney,int limit) {
            super(cardNum, cardMoney);
            this.limit = limit;
        }

        @Override
        public String toString() {
            return "CreditCard{" +
                    "limit=" + limit +
                    ", cardNum=" + cardNum +
                    ", cardBalance=" + cardBalance +
                    '}';
        }
    }

}

class Card {
    int cardNum = 0;
    int cardBalance = 0;

    public Card(int cardNum, int cardMoney) {
        this.cardNum = cardNum;
        this.cardBalance = cardMoney;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardNum=" + cardNum +
                ", cardBalance=" + cardBalance +
                '}';
    }
}
