import java.io.*;
import java.util.regex.*;
import java.util.ResourceBundle;
import java.util.Locale;
import java.text.MessageFormat;
public class FiveChessGame{
    //获取jvm默认区域 Locale.getDefault(Locale.Category.FORMAT);
    //英语：Locale.ENGLISH
    //这里写死了中文，因为笔者jvm默认英语，正常情况下应该用默认设置。
    //资源文件只有中文和英文，没有写其他语言的资源文件。
    private static final Locale locale = Locale.CHINESE;
    private static ResourceBundle bundle;
    private static final int WIN_COUNT = 5;
    private ChessBoard chessBoard;
    private int posX;
    private int posY;
    private BufferedReader br;
    //实例初始化块。
    {
        //这里bundle只能在实例中初始化，因为使用了getClass().getName()来获取类名(资源文件是根据类名取的名字)，需要用到this对象。bundle作为类变量，只初始化一次，节约时空资源。
        if(this.bundle == null) this.bundle = ResourceBundle.getBundle(this.getClass().getName(),this.locale);
    }
    //main函数
    public static void main(String[] args)throws Exception{
        FiveChessGame fg = new FiveChessGame(new ChessBoard());
        fg.start();
    }
    //构造器
    public FiveChessGame(ChessBoard chessBoard){
        this.chessBoard=chessBoard;
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }
    /**
        游戏运行的主体函数。
    */
    public int run()throws Exception{
        chessBoard.init();
        while(true){
            chessBoard.print();
            System.out.println(bundle.getString("InputHint"));
            String inputStr;
            if((inputStr = br.readLine()) == null)break;
            if(!isValid(inputStr)){
                System.out.println(bundle.getString("InvalidInputHint"));
                continue;
            }
            String chessMark = ChessMan.BLACK.getChessMark();
            chessBoard.placePiece(posX,posY,chessMark);
            if(isWon(posX,posY)){
                chessBoard.print();
                System.out.println(bundle.getString("YouWin"));
                break;
            }
            int[] computerPosArr = getComputerNextPos();
            chessMark = ChessMan.WHITE.getChessMark();
            chessBoard.placePiece(computerPosArr[0],computerPosArr[1],chessMark);
            if(isWon(computerPosArr[0],computerPosArr[1])){
                chessBoard.print();
                System.out.println(bundle.getString("YouLose"));
                break;
            }
        }
        return 0;
    }
    /**
        随机获取下一个电脑的坐标。
        时间复杂度O(n2)。
        先遍历，找出所有空位置，然后随机选一个；而不是先随机选，再判断是否为空。因为后者的末期性能恶化严重。
    */
    private int[] getComputerNextPos(){
        int[][] availablePosArr = new int[ChessBoard.BOARDSIZE*ChessBoard.BOARDSIZE][2];
        int availablePosNum = 0;
        for(int x=1;x<=ChessBoard.BOARDSIZE;x++){
            for(int y=1;y<=ChessBoard.BOARDSIZE;y++){
                if(chessBoard.getPos(x,y).equals(ChessBoard.EMPTY_MARK)){
                    availablePosArr[availablePosNum][0]=x;
                    availablePosArr[availablePosNum][1]=y;
                    availablePosNum++;
                }
            }
        }
        int selectedPosIndex = (int)((Math.random())*(availablePosNum));
        return availablePosArr[selectedPosIndex];
    }
    /**
        判断是否胜负已分。
        笨办法，搜索八个方向。
    */
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

            return true;
        }
        return false;
    }
    /**
        开始游戏。
    */
    public int start()throws Exception{
        while(true){
            run();
            if(!isReplay())break;
        }
        return 0;
    }
    /**
        是否再来一局。
    */
    private boolean isReplay()throws IOException{
        System.out.println(bundle.getString("AskReplay"));
        if(br.readLine().strip().equals("y"))return true;
        return false;
    }
    /**
        判断输入是否合法。
        根据题目要求进行了修改，使用了正则表达式。
    */
    private boolean isValid(String inputStr){
        String inputStrs = inputStr.strip();
        Matcher matcher = Pattern.compile("^(\\d+),(\\d+)$").matcher(inputStr);
        try{
            if(matcher.find()){
                posX = Integer.parseInt(matcher.group(1));
                posY = Integer.parseInt(matcher.group(2));
            }else{
                throw new Exception();
            }
        }catch(Exception e){
            //System.out.println("Fail:Input form not Valid.");
            return false;
        }
        if(posX<1||posX>ChessBoard.BOARDSIZE ||
            posY<1||posY>ChessBoard.BOARDSIZE
          ){
            System.out.println(
                MessageFormat.format(
                    bundle.getString("InputOutOfRangeHint"),ChessBoard.BOARDSIZE
                )
            );
            return false;
        }
        if(!chessBoard.getPos(posX,posY).equals(ChessBoard.EMPTY_MARK)){
            System.out.println(bundle.getString("InputPositionOccupiedHint"));
            return false;
        }
        return true;
    }
}