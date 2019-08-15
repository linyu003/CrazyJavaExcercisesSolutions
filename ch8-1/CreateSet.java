import java.util.Scanner;
import java.util.HashSet;
/**
    @author linyu003
*/
public class CreateSet{
    public static void main(String[] args){
        // 这里如果不使用<String>，会产生Xlint警告，尽管不影响程序运行。
        // 根据书中提到的原则，我们应该在集合中只存储同一种类型的对象，
        // 因此这个类型是可以确定的。
        var set = new HashSet<String>();
        var sc = new Scanner(System.in).useDelimiter("\n");
        //输入20个字符串太麻烦，因此改成了3个。
        for(int i=1;i<=3;i++){
            set.add(sc.next());
        }
        // 应当清楚，hashset遍历是乱序的，如果没有看到乱序输出，
        // 可能是因为个数过少导致的偶然，可以考虑增加输入个数。
        set.forEach(str->System.out.println(str));
    }
}