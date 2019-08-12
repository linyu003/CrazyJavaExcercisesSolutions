@FunctionalInterface
public interface Movable{
    void move();
    default boolean isMovable(){
        return true;
    }
    public static void main(String[] args){
        Movable mv = ()->System.out.println("I am moving...");
        if(mv.isMovable())mv.move();
    }
}