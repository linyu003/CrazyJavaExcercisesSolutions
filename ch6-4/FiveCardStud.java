public class FiveCardStud{
    public static final int PLAYER_NUM = 5;
    public static final int CARD_NUM = 52;
    public static final String[] SHAPE = {"方片","红桃","黑桃","梅花"};
    public static final String[] VALUE = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    private Player[] players;
    private Card[] cards;
    int leftCardsNum;
    int dealNum;
    public boolean quit(int seatNumber){
        return players[seatNumber].quit();
    }
    public FiveCardStud(){
        leftCardsNum = CARD_NUM;
        dealNum = 0;
        players = new Player[PLAYER_NUM];
        for(int i=0;i<PLAYER_NUM;i++){
            players[i]=new Player(i);
        }
        cards = new Card[CARD_NUM];
        for(int i=0;i<SHAPE.length;i++){
            String shape = SHAPE[i];
            for(int j=0;j<VALUE.length;j++){
                String value = VALUE[j];
                cards[i*VALUE.length+j] = new Card(shape,value);
            }
        }
    }
    public void printStatus(){
        System.out.println("FiveCardStud Game Status:"+(isEnd()?"Game is already end.":""));
        System.out.println("leftCardsNum = "+leftCardsNum);
        System.out.println("dealNum = "+dealNum);
        for(Player player : players){
            player.printStatus();
        }
    }
    public void dealCards(){
        dealNum++;
        for(Player player : players){
            if(!player.isQuitCheck())player.addCard(cards[--leftCardsNum]);
        }
    }
    public boolean isEnd(){return dealNum>=5;}
    
    public static void main(String[] args){
        FiveCardStud game = new FiveCardStud();
        while(!game.isEnd()){
            game.dealCards();
            game.quit(1);
            game.printStatus();
        }
    }

    private static class Player{
        private final int seatNumber;
        boolean isQuit = false;
        private int cardNum = 0;
        public static final int MAX_CARD_NUM = 5;
        Card[] cards = new Card[MAX_CARD_NUM];
        
        Player(int seatNumber){
            this.seatNumber = seatNumber;
        }
        boolean isQuitCheck(){return isQuit;}
        void printStatus(){
            System.out.print("Player#"+seatNumber+": ");
            if(isQuitCheck())System.out.print("已放弃比赛。");
            else{
                System.out.print("手牌"+cardNum+"张,");
                for(int i=0;i<cardNum;i++){
                    Card card = cards[i];
                    System.out.print(card.getShape()+card.getValue()+" ");
                }
            }
            System.out.print("\n");
        }
        boolean addCard(Card card){
            if(cardNum>=MAX_CARD_NUM){
                System.out.println("Player#"+seatNumber+"can't add more cards.");
                return false;
            }
            cards[cardNum++]=card;
            return true;
        }
        boolean quit(){
            if(isQuitCheck()){
                System.out.println("Player#"+seatNumber+" is already quited, can't quit again.");
                return false;
            }
            isQuit=true;
            System.out.println("Player#"+seatNumber+" quits.");
            return true;
        }
    }
    private static class Card{
        private final String value;
        private final String shape;
        Card(String shape,String value){
            this.value = value;
            this.shape = shape;
        }
        String getValue(){return this.value;}
        String getShape(){return this.shape;}
    }
}
