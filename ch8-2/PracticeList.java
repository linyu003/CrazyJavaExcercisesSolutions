import java.util.ArrayList;
public class PracticeList{
    public static void main(String[] args){
        var books = new ArrayList();
        books.add("book0");
        books.add("book1");
        books.add("book2");
        books.add("book3");
        books.add("book4");
        books.add("book5");
        books.add("book6");
        books.add("book7");
        books.add("book8");
        books.add("book9");
        System.out.println(books.get(5));
        System.out.println(books.indexOf(new String("book1")));
        System.out.println(books.indexOf(new String("book9")));
        books.remove(3);
        books.forEach(book->System.out.println(book));
        
        
    }
}