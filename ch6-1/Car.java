class Car extends GeneralGroundVehicle{
    public void move(){
        System.out.println("Car Moving...");
    }
    public Car(int personsCapacity){
        super(personsCapacity);
    }
}
class Truck extends GeneralGroundVehicle{
    public void move(){
        System.out.println("Truck Moving...");
    }
    public Truck(int personsCapacity){
        super(personsCapacity);
    }
}
class Tractor extends GeneralGroundVehicle{
    public void move(){
        System.out.println("Tractor Moving...");
    }
    public Tractor(int personsCapacity){
        super(personsCapacity);
    }
}
