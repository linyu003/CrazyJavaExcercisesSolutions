import java.util.Arrays;
public class ChessBoard{
    public static final String EMPTY_MARK = "十";
    public static final int BOARDSIZE = 22;
    private String[][] board;
    public ChessBoard(){
        board = new String[BOARDSIZE][BOARDSIZE];
    }
    public static boolean isWithinRange(int x,int y){
        return 1<=x && x<=BOARDSIZE && 1<=y && y<=BOARDSIZE;
    }
    public void init(){
        for(int i=0;i<BOARDSIZE;i++){
            Arrays.fill(board[i],EMPTY_MARK);
        }
    }
    public void placePiece(int xPos, int yPos, String chessMark){
        board[xPos-1][yPos-1]=chessMark;
    }
    public void print(){
        for(int i=0;i<BOARDSIZE;i++){
            for(int j=0;j<BOARDSIZE;j++){
                System.out.print(board[i][j]);
            }
            System.out.print("\n");
        }
    }
    public String getPos(int xPos,int yPos){
        return board[xPos-1][yPos-1];
    }
    
}