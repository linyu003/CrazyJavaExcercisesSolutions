import java.io.*;
public class FiveChessGame{
    private static final int WIN_COUNT = 5;
    private ChessBoard chessBoard;
    private int posX;
    private int posY;
    private BufferedReader br;
    public static void main(String[] args)throws Exception{
        FiveChessGame fg = new FiveChessGame(new ChessBoard());
        fg.start();
    }
    public FiveChessGame(ChessBoard chessBoard){
        this.chessBoard=chessBoard;
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }
    public int run()throws Exception{
        chessBoard.init();
        while(true){
            chessBoard.print();
            System.out.println("Input where you chess, form is x,y :");
            String inputStr;
            if((inputStr = br.readLine()) == null)break;
            if(!isValid(inputStr)){
                System.out.println("Input invalid.");
                continue;
            }
            String chessMark = ChessMan.BLACK.getChessMark();
            chessBoard.placePiece(posX,posY,chessMark);
            if(isWon(posX,posY)){
                break;
            }
            int[] computerPosArr = getComputerNextPos();
            chessMark = ChessMan.WHITE.getChessMark();
            chessBoard.placePiece(computerPosArr[0],computerPosArr[1],chessMark);
            if(isWon(computerPosArr[0],computerPosArr[1])){
                break;
            }
        }
        return 0;
    }
    private int[] getComputerNextPos(){
        int[][] availablePosArr = new int[ChessBoard.BOARDSIZE*ChessBoard.BOARDSIZE][2];
        int availablePosNum = 0;
        for(int x=1;x<=ChessBoard.BOARDSIZE;x++){
            for(int y=1;y<=ChessBoard.BOARDSIZE;y++){
                if(chessBoard.getPos(x,y).equals("ʮ")){
                    availablePosArr[availablePosNum][0]=x;
                    availablePosArr[availablePosNum][1]=y;
                    availablePosNum++;
                }
            }
        }
        int selectedPosIndex = (int)((Math.random())*(availablePosNum));
        return availablePosArr[selectedPosIndex];
    }
    private boolean isWon(int xPos,int yPos){
        String chessMark = chessBoard.getPos(xPos,yPos);
        int left=0,right=0,up=0,down=0,leftUp=0,leftDown=0,rightUp=0,rightDown=0;
        for(int x=xPos-1,y=yPos;ChessBoard.isWithinRange(x,y);x--){
            if(chessBoard.getPos(x,y).equals(chessMark))
                left++;
            else break;
        }
        for(int x=xPos+1,y=yPos;ChessBoard.isWithinRange(x,y);x++){
            if(chessBoard.getPos(x,y).equals(chessMark))
                right++;
            else break;
        }
        for(int x=xPos,y=yPos-1;ChessBoard.isWithinRange(x,y);y--){
            if(chessBoard.getPos(x,y).equals(chessMark))
                up++;
            else break;
        }
        for(int x=xPos,y=yPos+1;ChessBoard.isWithinRange(x,y);y++){
            if(chessBoard.getPos(x,y).equals(chessMark))
                down++;
            else break;
        }
        for(int x=xPos-1,y=yPos-1;ChessBoard.isWithinRange(x,y);x--,y--){
            if(chessBoard.getPos(x,y).equals(chessMark))
                leftUp++;
            else break;
        }
        for(int x=xPos-1,y=yPos+1;ChessBoard.isWithinRange(x,y);x--,y++){
            if(chessBoard.getPos(x,y).equals(chessMark))
                leftDown++;
            else break;
        }
        for(int x=xPos+1,y=yPos-1;ChessBoard.isWithinRange(x,y);x++,y--){
            if(chessBoard.getPos(x,y).equals(chessMark))
                rightUp++;
            else break;
        }
        for(int x=xPos+1,y=yPos+1;ChessBoard.isWithinRange(x,y);x++,y++){
            if(chessBoard.getPos(x,y).equals(chessMark))
                rightDown++;
            else break;
        }
        if(left+right+1>=WIN_COUNT ||
        up+down+1>=WIN_COUNT ||
        leftDown+rightUp+1>=WIN_COUNT ||
        leftUp+rightDown+1>=WIN_COUNT ){
            if(chessMark.equals(ChessMan.BLACK.getChessMark())){
                System.out.println("You win!");
            }else{
                System.out.println("You lose.");
            }
            return true;
        }
        return false;
    }
    public int start()throws Exception{
        while(true){
            run();
            if(!isReplay())break;
        }
        return 0;
    }
    private boolean isReplay()throws IOException{
        System.out.println("Replay?(y/n)");
        if(br.readLine().strip().equals("y"))return true;
        return false;
    }
    private boolean isValid(String inputStr){
        String[] inputStrs = inputStr.strip().split(",");
        try{
            posX = Integer.parseInt(inputStrs[0]);
            posY = Integer.parseInt(inputStrs[1]);
        }catch(Exception e){
            System.out.println("Fail:Input form not Valid.");
        }
        if(posX<1||posX>ChessBoard.BOARDSIZE ||
            posY<1||posY>ChessBoard.BOARDSIZE
          ){
            System.out.println("Fail:The range of coordinates must be between 1 and "+ChessBoard.BOARDSIZE+".");
            return false;
        }
        if(!chessBoard.getPos(posX,posY).equals("ʮ")){
            System.out.println("Fail:That location is already occupied.");
            return false;
        }
        return true;
    }
}