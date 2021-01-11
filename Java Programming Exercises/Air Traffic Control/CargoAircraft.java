package airtrafficcontrol;

public class CargoAircraft extends Aircraft {

    //data attributes
    private int cargoCapacity; // in cubic feet
  
//constructors
    public CargoAircraft() {
        super();
        type = "Cargo";
        cargoCapacity = 650;
    }

    public CargoAircraft(int cargoCapacity) {
        super();
        type = "Cargo";
        this.cargoCapacity = cargoCapacity;
    }
    
    public CargoAircraft(int cargoCapacity , int maxVelocity) {
        super(maxVelocity);
        type = "Cargo";
        this.cargoCapacity = cargoCapacity;
    }
    
    public CargoAircraft(int cargoCapacity , int maxVelocity, int length) {
        super(maxVelocity, length);
        type = "Cargo";
        this.cargoCapacity = cargoCapacity;
    }
    
    public CargoAircraft(int cargoCapacity, int maxVelocity, int length, int wingSpan){
        super(maxVelocity, length, wingSpan);
        type = "Cargo";
        this.cargoCapacity = cargoCapacity;
    }
    
    public CargoAircraft(int cargoCapacity, int maxVelocity, int length, int wingSpan, int emptyWeight){
        super(maxVelocity, length, wingSpan, emptyWeight);
        type = "Cargo";
        this.cargoCapacity = cargoCapacity;
    }
    
    public CargoAircraft(String manufacturer, int cargoCapacity, int maxVelocity, int length, int wingSpan, int emptyWeight){
        super(manufacturer, maxVelocity, length, wingSpan, emptyWeight);
        type = "Cargo";
        this.cargoCapacity = cargoCapacity;
    }
    
    public CargoAircraft(String manufacturer, String model, int cargoCapacity, int maxVelocity, int length, int wingSpan, int emptyWeight){
        super(manufacturer, model, maxVelocity, length, wingSpan, emptyWeight);
        type = "Cargo";
        this.cargoCapacity = cargoCapacity;
    }
    
    //copy constructor
    public CargoAircraft(CargoAircraft cargoAircraft){
        super(cargoAircraft.manufacturer, cargoAircraft.model, cargoAircraft.maxVelocity, cargoAircraft.length, cargoAircraft.wingSpan, cargoAircraft.emptyWeight);
        this.type = cargoAircraft.type;
        this.cargoCapacity = cargoAircraft.cargoCapacity;
    }//copy constructor

    //operations
    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWingSpan() {
        return wingSpan;
    }

    public void setWingSpan(int wingSpan) {
        this.wingSpan = wingSpan;
    }

    public int getEmptyWeight() {
        return emptyWeight;
    }

    public void setEmptyWeight(int emptyWeight) {
        this.emptyWeight = emptyWeight;
    }

    public int getMaxVelocity() {
        return maxVelocity;
    }

    public void setMaxVelocity(int maxVelocity) {
        this.maxVelocity = maxVelocity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void printInfo(){
        System.out.println(
            "\t\t\tManufacturer:\t" + manufacturer
            +"\n\t\t\tModel:\t" + model
            +"\n\t\t\tType:\t" + type
            +"\n\t\t\tLength:\t" + length
            +"\n\t\t\tWing Span:\t" + wingSpan
            +"\n\t\t\tEmpty Weight:\t" + emptyWeight
            +"\n\t\t\tMax Velocity:\t" + maxVelocity
            +"\n\t\t\tCargo Capacity:\t" + cargoCapacity
            );
    }
    
    
    /*public void setName(String name) {
        this.name = name;
    }*/ //isolated as it is calculated internally
    

}
