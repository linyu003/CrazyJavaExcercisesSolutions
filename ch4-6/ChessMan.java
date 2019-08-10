public enum ChessMan{
    BLACK("¡ñ"),WHITE("¡ğ");
    String chessMark;
    private ChessMan(String chessMark){
        this.chessMark = chessMark;
    }
    public String getChessMark(){
        return chessMark;
    }
}