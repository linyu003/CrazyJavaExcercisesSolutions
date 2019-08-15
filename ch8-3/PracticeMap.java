import java.util.HashMap;
public class PracticeMap{
    public static void main(String[] args){
        String[] strs = {"a","b","a","b","c","a","b","c","b"};
        var map = new HashMap();
        for(String str:strs){
            if(!map.containsKey(str))map.put(str,1);
            else map.replace(str,(Integer)map.get(str)+1);
        }
        map.forEach((key,value)->System.out.println(key+":"+value));
    }
}