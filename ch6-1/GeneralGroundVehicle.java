public abstract class GeneralGroundVehicle{
    private int currentPersonsNum;
    private int personsCapacity;
    public abstract void move();
    public GeneralGroundVehicle(){
        currentPersonsNum=0;
        personsCapacity=0;
    }
    public GeneralGroundVehicle(int personsCapacity){
        this.personsCapacity=personsCapacity;
    }
    public int getPersonsCapacity(){return personsCapacity;};
    public int getCurrentPersonsNum(){
        return currentPersonsNum;
    }
    public boolean loadPerson(){
        if(currentPersonsNum<personsCapacity){
            currentPersonsNum++;
            System.out.println("loadPerson Success, currentPersonsNum = "+currentPersonsNum+" personsCapacity = "+personsCapacity);
            return true;
        }
        System.out.println("loadPerson Failed, currentPersonsNum = "+currentPersonsNum+" personsCapacity = "+personsCapacity);
        return false;
    }
    public static void main(String[] args){
        GeneralGroundVehicle car = new Car(5);
        GeneralGroundVehicle truck = new Truck(2);
        GeneralGroundVehicle tractor = new Tractor(1);
        car.move();truck.move();tractor.move();
        car.loadPerson();car.loadPerson();
        truck.loadPerson();truck.loadPerson();
        tractor.loadPerson();tractor.loadPerson();
    }
}