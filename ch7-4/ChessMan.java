public enum ChessMan{
    BLACK("●"),WHITE("○");
    String chessMark;
    private ChessMan(String chessMark){
        this.chessMark = chessMark;
    }
    public String getChessMark(){
        return chessMark;
    }
}