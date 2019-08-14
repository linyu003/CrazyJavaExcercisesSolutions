public class StringCut{
    public static void main(String[] args){
        StringBuilder sb = new StringBuilder("ABCDEFG");
        System.out.println(sb);
        String toDeleteStr = "CD";
        int toDeleteIndex = sb.indexOf(toDeleteStr);
        sb.delete(toDeleteIndex,toDeleteIndex+toDeleteStr.length());
        System.out.println(sb);
        toDeleteStr = "B";
        toDeleteIndex = sb.indexOf(toDeleteStr);
        sb.delete(toDeleteIndex,toDeleteIndex+toDeleteStr.length());
        System.out.println(sb);
        toDeleteStr = "F";
        toDeleteIndex = sb.indexOf(toDeleteStr);
        sb.delete(toDeleteIndex,toDeleteIndex+toDeleteStr.length());
        System.out.println(sb);
    }
}