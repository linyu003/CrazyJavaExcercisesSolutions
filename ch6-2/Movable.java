public interface Movable{
    void move();
    default boolean isMovable(){
        return true;
    }
    public static void main(String[] args){
        Movable mv = new Movable(){
            public void move(){
                System.out.println("I am moving...");
            }
        };
        if(mv.isMovable())mv.move();
    }
}