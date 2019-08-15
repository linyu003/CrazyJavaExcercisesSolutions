import java.util.LinkedList;
import java.util.ArrayList;
/**
    相对于第六章的游戏，没有增加更多的功能，仅仅运用Collections进行了一些改写(cards 和 players 的存储)。
    @author linyu003
*/
public class FiveCardStud{
    public static final int PLAYER_NUM = 5;
    public static final String[] SHAPE = {"方片","红桃","黑桃","梅花"};
    public static final String[] VALUE = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    public static final int SHAPE_NUM = SHAPE.length;
    public static final int VALUE_NUM = VALUE.length;
    public static final int CARD_NUM = SHAPE_NUM*VALUE_NUM;
    private ArrayList<Player> players = new ArrayList<>();
    private LinkedList<Card> cards = new LinkedList<>();
    int dealNum;
    public boolean quit(int seatNumber){
        return players.get(seatNumber).quit();
    }
    public FiveCardStud(){
        dealNum = 0;
        for(int i=0;i<PLAYER_NUM;i++){
            players.add(new Player(i));
        }
        for(int i=0;i<SHAPE_NUM;i++){
            String shape = SHAPE[i];
            for(int j=0;j<VALUE_NUM;j++){
                String value = VALUE[j];
                cards.add(new Card(shape,value));
            }
        }
    }
    public void printStatus(){
        System.out.println("FiveCardStud Game Status:"+(isEnd()?"Game is already end.":""));
        System.out.println("剩余牌数："+cards.size());
        System.out.println("轮次： "+dealNum);
        players.forEach(player->player.printStatus());
    }
    public void dealCards(){
        dealNum++;
        players.forEach(player->{
            if(!player.isQuitCheck()){
                player.addCard(cards.get(0));
                cards.remove(0);
            }
        });
    }
    public boolean isEnd(){return dealNum>=5;}
    


    private static class Player{
        private final int seatNumber;
        boolean isQuit = false;
        private int cardNum = 0;
        public static final int MAX_CARD_NUM = 5;
        private LinkedList<Card> cards = new LinkedList<>();
        
        Player(int seatNumber){
            this.seatNumber = seatNumber;
        }
        boolean isQuitCheck(){return isQuit;}
        void printStatus(){
            System.out.print("Player#"+seatNumber+": ");
            if(isQuitCheck())System.out.print("已放弃比赛。");
            else{
                System.out.print("手牌"+cardNum+"张,");
                cards.forEach(card->System.out.print(card.getShape()+card.getValue()+" "));
            }
            System.out.print("\n");
        }
        boolean addCard(Card card){
            if(cardNum>=MAX_CARD_NUM){
                System.out.println("Player#"+seatNumber+"can't add more cards.");
                return false;
            }
            cards.add(card);
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
    
    public static void main(String[] args){
        FiveCardStud game = new FiveCardStud();
        while(!game.isEnd()){
            game.dealCards();
            game.quit(1);
            game.printStatus();
        }
    }
}
