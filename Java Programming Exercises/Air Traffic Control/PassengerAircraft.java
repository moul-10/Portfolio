package airtrafficcontrol;

import java.util.ArrayList;

public class PassengerAircraft extends Aircraft {

    //data attributes
    private int classes;
    private ArrayList<SeatClass> classlist;
    private SeatClass seatClass1;
    private SeatClass seatClass2;
    private SeatClass seatClass3;
    private SeatClass seatClass4;
    private int passengerCapacity;
    
//constructors
    public PassengerAircraft() 
    {
        super();
        type = "Passenger";
        classes = 1;
        seatClass1 = new SeatClass();
        passengerCapacity = seatClass1.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
    }
    

    public PassengerAircraft(int class1NumberPassengers) 
    {
        super();
        type = "Passenger";
        classes = 1;
        seatClass1 = new SeatClass(class1NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
    }
    
    public PassengerAircraft(String class1Name, int class1NumberPassengers) 
    {
        super();
        type = "Passenger";
        classes = 1;
        seatClass1 = new SeatClass(class1Name,class1NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
    }
    
    public PassengerAircraft(String class1Name, int class1NumberPassengers, String class2Name, int class2NumberPassengers) 
    {
        super();
        type = "Passenger";
        classes = 2;
        seatClass1 = new SeatClass(class1Name,class1NumberPassengers);
        seatClass2 = new SeatClass(class2Name,class2NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers() + seatClass2.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
        classlist.add(seatClass2);
    }
    
    public PassengerAircraft(String class1Name, int class1NumberPassengers, String class2Name, int class2NumberPassengers, String class3Name, int class3NumberPassengers) 
    {
        super();
        type = "Passenger";
        classes = 3;
        seatClass1 = new SeatClass(class1Name,class1NumberPassengers);
        seatClass2 = new SeatClass(class2Name,class2NumberPassengers);
        seatClass3 = new SeatClass(class3Name,class3NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers() + seatClass2.getNumberPassengers()  + seatClass3.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
        classlist.add(seatClass2);
        classlist.add(seatClass3);
    }
   
    public PassengerAircraft(String class1Name, int class1NumberPassengers, String class2Name, int class2NumberPassengers, String class3Name, int class3NumberPassengers, String class4Name, int class4NumberPassengers) 
    {
        super();
        type = "Passenger";
        classes = 4;
        seatClass1 = new SeatClass(class1Name,class1NumberPassengers);
        seatClass2 = new SeatClass(class2Name,class2NumberPassengers);
        seatClass3 = new SeatClass(class3Name,class3NumberPassengers);
        seatClass4 = new SeatClass(class4Name,class4NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers() + seatClass2.getNumberPassengers() + seatClass3.getNumberPassengers() + seatClass4.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
        classlist.add(seatClass2);
        classlist.add(seatClass3);
        classlist.add(seatClass4);
    }
    
    public PassengerAircraft(int maxVelocity, int class1NumberPassengers) 
    {
        super(maxVelocity);
        type = "Passenger";
        classes = 1;
        seatClass1 = new SeatClass(class1NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
    }
    
    public PassengerAircraft(int maxVelocity, String class1Name, int class1NumberPassengers) 
    {
        super(maxVelocity);
        type = "Passenger";
        classes = 1;
        seatClass1 = new SeatClass(class1Name,class1NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
    }
    
    public PassengerAircraft(int maxVelocity, String class1Name, int class1NumberPassengers, String class2Name, int class2NumberPassengers) 
    {
        super(maxVelocity);
        type = "Passenger";
        classes = 2;
        seatClass1 = new SeatClass(class1Name,class1NumberPassengers);
        seatClass2 = new SeatClass(class2Name,class2NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers() + seatClass2.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
        classlist.add(seatClass2);
    }
    
    public PassengerAircraft(int maxVelocity, String class1Name, int class1NumberPassengers, String class2Name, int class2NumberPassengers, String class3Name, int class3NumberPassengers) 
    {
        super(maxVelocity);
        type = "Passenger";
        classes = 3;
        seatClass1 = new SeatClass(class1Name,class1NumberPassengers);
        seatClass2 = new SeatClass(class2Name,class2NumberPassengers);
        seatClass3 = new SeatClass(class3Name,class3NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers() + seatClass2.getNumberPassengers() + seatClass3.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
        classlist.add(seatClass2);
        classlist.add(seatClass3);
    }
   
    public PassengerAircraft(int maxVelocity, String class1Name, int class1NumberPassengers, String class2Name, int class2NumberPassengers, String class3Name, int class3NumberPassengers, String class4Name, int class4NumberPassengers) 
    {
        super(maxVelocity);
        type = "Passenger";
        classes = 4;
        seatClass1 = new SeatClass(class1Name,class1NumberPassengers);
        seatClass2 = new SeatClass(class2Name,class2NumberPassengers);
        seatClass3 = new SeatClass(class3Name,class3NumberPassengers);
        seatClass4 = new SeatClass(class4Name,class4NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers() + seatClass2.getNumberPassengers() + seatClass3.getNumberPassengers() + seatClass4.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
        classlist.add(seatClass2);
        classlist.add(seatClass3);
        classlist.add(seatClass4);
    }
    
    public PassengerAircraft(int maxVelocity, int length, int class1NumberPassengers) 
    {
        super(maxVelocity, length);
        type = "Passenger";
        classes = 1;
        seatClass1 = new SeatClass(class1NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
    }
    
    public PassengerAircraft(int maxVelocity, int length, String class1Name, int class1NumberPassengers) 
    {
        super(maxVelocity, length);
        type = "Passenger";
        classes = 1;
        seatClass1 = new SeatClass(class1Name,class1NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
    }
    
    public PassengerAircraft(int maxVelocity, int length, String class1Name, int class1NumberPassengers, String class2Name, int class2NumberPassengers) 
    {
        super(maxVelocity, length);
        type = "Passenger";
        classes = 2;
        seatClass1 = new SeatClass(class1Name,class1NumberPassengers);
        seatClass2 = new SeatClass(class2Name,class2NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers() + seatClass2.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
        classlist.add(seatClass2);
    }
    
    public PassengerAircraft(int maxVelocity, int length, String class1Name, int class1NumberPassengers, String class2Name, int class2NumberPassengers, String class3Name, int class3NumberPassengers) 
    {
        super(maxVelocity, length);
        type = "Passenger";
        classes = 3;
        seatClass1 = new SeatClass(class1Name,class1NumberPassengers);
        seatClass2 = new SeatClass(class2Name,class2NumberPassengers);
        seatClass3 = new SeatClass(class3Name,class3NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers() + seatClass2.getNumberPassengers() + seatClass3.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
        classlist.add(seatClass2);
        classlist.add(seatClass3);
    }
   
    public PassengerAircraft(int maxVelocity, int length, String class1Name, int class1NumberPassengers, String class2Name, int class2NumberPassengers, String class3Name, int class3NumberPassengers, String class4Name, int class4NumberPassengers) 
    {
        super(maxVelocity, length);
        type = "Passenger";
        classes = 4;
        seatClass1 = new SeatClass(class1Name,class1NumberPassengers);
        seatClass2 = new SeatClass(class2Name,class2NumberPassengers);
        seatClass3 = new SeatClass(class3Name,class3NumberPassengers);
        seatClass4 = new SeatClass(class4Name,class4NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers() + seatClass2.getNumberPassengers() + seatClass3.getNumberPassengers() + seatClass4.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
        classlist.add(seatClass2);
        classlist.add(seatClass3);
        classlist.add(seatClass4);
    }
    
    public PassengerAircraft(int maxVelocity, int length, int wingSpan, int class1NumberPassengers) 
    {
        super(maxVelocity, length, wingSpan);
        type = "Passenger";
        classes = 1;
        seatClass1 = new SeatClass(class1NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
    }
    
    public PassengerAircraft(int maxVelocity, int length, int wingSpan, String class1Name, int class1NumberPassengers) 
    {
        super(maxVelocity, length, wingSpan);
        type = "Passenger";
        classes = 1;
        seatClass1 = new SeatClass(class1Name,class1NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
    }
    
    public PassengerAircraft(int maxVelocity, int length, int wingSpan, String class1Name, int class1NumberPassengers, String class2Name, int class2NumberPassengers) 
    {
        super(maxVelocity, length, wingSpan);
        type = "Passenger";
        classes = 2;
        seatClass1 = new SeatClass(class1Name,class1NumberPassengers);
        seatClass2 = new SeatClass(class2Name,class2NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers() + seatClass2.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
        classlist.add(seatClass2);
    }
    
    public PassengerAircraft(int maxVelocity, int length, int wingSpan, String class1Name, int class1NumberPassengers, String class2Name, int class2NumberPassengers, String class3Name, int class3NumberPassengers) 
    {
        super(maxVelocity, length, wingSpan);
        type = "Passenger";
        classes = 3;
        seatClass1 = new SeatClass(class1Name,class1NumberPassengers);
        seatClass2 = new SeatClass(class2Name,class2NumberPassengers);
        seatClass3 = new SeatClass(class3Name,class3NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers() + seatClass2.getNumberPassengers() + seatClass3.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
        classlist.add(seatClass2);
        classlist.add(seatClass3);
    }
   
    public PassengerAircraft(int maxVelocity, int length, int wingSpan, String class1Name, int class1NumberPassengers, String class2Name, int class2NumberPassengers, String class3Name, int class3NumberPassengers, String class4Name, int class4NumberPassengers) 
    {
        super(maxVelocity, length, wingSpan);
        type = "Passenger";
        classes = 4;
        seatClass1 = new SeatClass(class1Name,class1NumberPassengers);
        seatClass2 = new SeatClass(class2Name,class2NumberPassengers);
        seatClass3 = new SeatClass(class3Name,class3NumberPassengers);
        seatClass4 = new SeatClass(class4Name,class4NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers() + seatClass2.getNumberPassengers() + seatClass3.getNumberPassengers() + seatClass4.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
        classlist.add(seatClass2);
        classlist.add(seatClass3);
        classlist.add(seatClass4);
    }
    
    public PassengerAircraft(int maxVelocity, int length, int wingSpan, int emptyWeight, int class1NumberPassengers) 
    {
        super(maxVelocity, length, wingSpan, emptyWeight);
        type = "Passenger";
        classes = 1;
        seatClass1 = new SeatClass(class1NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
    }
    
    public PassengerAircraft(int maxVelocity, int length, int wingSpan, int emptyWeight, String class1Name, int class1NumberPassengers) 
    {
        super(maxVelocity, length, wingSpan, emptyWeight);
        type = "Passenger";
        classes = 1;
        seatClass1 = new SeatClass(class1Name,class1NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
    }
    
    public PassengerAircraft(int maxVelocity, int length, int wingSpan, int emptyWeight, String class1Name, int class1NumberPassengers, String class2Name, int class2NumberPassengers) 
    {
        super(maxVelocity, length, wingSpan, emptyWeight);
        type = "Passenger";
        classes = 2;
        seatClass1 = new SeatClass(class1Name,class1NumberPassengers);
        seatClass2 = new SeatClass(class2Name,class2NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers() + seatClass2.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
        classlist.add(seatClass2);
    }
    
    public PassengerAircraft(int maxVelocity, int length, int wingSpan, int emptyWeight, String class1Name, int class1NumberPassengers, String class2Name, int class2NumberPassengers, String class3Name, int class3NumberPassengers) 
    {
        super(maxVelocity, length, wingSpan, emptyWeight);
        type = "Passenger";
        classes = 3;
        seatClass1 = new SeatClass(class1Name,class1NumberPassengers);
        seatClass2 = new SeatClass(class2Name,class2NumberPassengers);
        seatClass3 = new SeatClass(class3Name,class3NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers() + seatClass2.getNumberPassengers() + seatClass3.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
        classlist.add(seatClass2);
        classlist.add(seatClass3);
    }
   
    public PassengerAircraft(int maxVelocity, int length, int wingSpan, int emptyWeight, String class1Name, int class1NumberPassengers, String class2Name, int class2NumberPassengers, String class3Name, int class3NumberPassengers, String class4Name, int class4NumberPassengers) 
    {
        super(maxVelocity, length, wingSpan, emptyWeight);
        type = "Passenger";
        classes = 4;
        seatClass1 = new SeatClass(class1Name,class1NumberPassengers);
        seatClass2 = new SeatClass(class2Name,class2NumberPassengers);
        seatClass3 = new SeatClass(class3Name,class3NumberPassengers);
        seatClass4 = new SeatClass(class4Name,class4NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers() + seatClass2.getNumberPassengers() + seatClass3.getNumberPassengers() + seatClass4.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
        classlist.add(seatClass2);
        classlist.add(seatClass3);
        classlist.add(seatClass4);
    }
    
    public PassengerAircraft(String manufacturer, int maxVelocity, int length, int wingSpan, int emptyWeight, int class1NumberPassengers) 
    {
        super(manufacturer, maxVelocity, length, wingSpan, emptyWeight);
        type = "Passenger";
        classes = 1;
        seatClass1 = new SeatClass(class1NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
    }
    
    public PassengerAircraft(String manufacturer, int maxVelocity, int length, int wingSpan, int emptyWeight, String class1Name, int class1NumberPassengers) 
    {
        super(manufacturer, maxVelocity, length, wingSpan, emptyWeight);
        type = "Passenger";
        classes = 1;
        seatClass1 = new SeatClass(class1Name,class1NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
    }
    
    public PassengerAircraft(String manufacturer, int maxVelocity, int length, int wingSpan, int emptyWeight, String class1Name, int class1NumberPassengers, String class2Name, int class2NumberPassengers) 
    {
        super(manufacturer, maxVelocity, length, wingSpan, emptyWeight);
        type = "Passenger";
        classes = 2;
        seatClass1 = new SeatClass(class1Name,class1NumberPassengers);
        seatClass2 = new SeatClass(class2Name,class2NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers() + seatClass2.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
        classlist.add(seatClass2);
    }
    
    public PassengerAircraft(String manufacturer, int maxVelocity, int length, int wingSpan, int emptyWeight, String class1Name, int class1NumberPassengers, String class2Name, int class2NumberPassengers, String class3Name, int class3NumberPassengers) 
    {
        super(manufacturer, maxVelocity, length, wingSpan, emptyWeight);
        type = "Passenger";
        classes = 3;
        seatClass1 = new SeatClass(class1Name,class1NumberPassengers);
        seatClass2 = new SeatClass(class2Name,class2NumberPassengers);
        seatClass3 = new SeatClass(class3Name,class3NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers() + seatClass2.getNumberPassengers() + seatClass3.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
        classlist.add(seatClass2);
        classlist.add(seatClass3);
    }
   
    public PassengerAircraft(String manufacturer, int maxVelocity, int length, int wingSpan, int emptyWeight, String class1Name, int class1NumberPassengers, String class2Name, int class2NumberPassengers, String class3Name, int class3NumberPassengers, String class4Name, int class4NumberPassengers) 
    {
        super(manufacturer, maxVelocity, length, wingSpan, emptyWeight);
        type = "Passenger";
        classes = 4;
        seatClass1 = new SeatClass(class1Name,class1NumberPassengers);
        seatClass2 = new SeatClass(class2Name,class2NumberPassengers);
        seatClass3 = new SeatClass(class3Name,class3NumberPassengers);
        seatClass4 = new SeatClass(class4Name,class4NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers() + seatClass2.getNumberPassengers() + seatClass3.getNumberPassengers() + seatClass4.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
        classlist.add(seatClass2);
        classlist.add(seatClass3);
        classlist.add(seatClass4);
    }
    
    public PassengerAircraft(String manufacturer, String model, int maxVelocity, int length, int wingSpan, int emptyWeight, int class1NumberPassengers) 
    {
        super(manufacturer, model, maxVelocity, length, wingSpan, emptyWeight);
        type = "Passenger";
        classes = 1;
        seatClass1 = new SeatClass(class1NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
    }
    
    public PassengerAircraft(String manufacturer, String model, int maxVelocity, int length, int wingSpan, int emptyWeight, String class1Name, int class1NumberPassengers) 
    {
        super(manufacturer, model, maxVelocity, length, wingSpan, emptyWeight);
        type = "Passenger";
        classes = 1;
        seatClass1 = new SeatClass(class1Name,class1NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
    }
    
    public PassengerAircraft(String manufacturer, String model, int maxVelocity, int length, int wingSpan, int emptyWeight, String class1Name, int class1NumberPassengers, String class2Name, int class2NumberPassengers) 
    {
        super(manufacturer, model, maxVelocity, length, wingSpan, emptyWeight);
        type = "Passenger";
        classes = 2;
        seatClass1 = new SeatClass(class1Name,class1NumberPassengers);
        seatClass2 = new SeatClass(class2Name,class2NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers() + seatClass2.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
        classlist.add(seatClass2);
    }
    
    public PassengerAircraft(String manufacturer, String model, int maxVelocity, int length, int wingSpan, int emptyWeight, String class1Name, int class1NumberPassengers, String class2Name, int class2NumberPassengers, String class3Name, int class3NumberPassengers) 
    {
        super(manufacturer, model, maxVelocity, length, wingSpan, emptyWeight);
        type = "Passenger";
        classes = 3;
        seatClass1 = new SeatClass(class1Name,class1NumberPassengers);
        seatClass2 = new SeatClass(class2Name,class2NumberPassengers);
        seatClass3 = new SeatClass(class3Name,class3NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers() + seatClass2.getNumberPassengers() + seatClass3.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
        classlist.add(seatClass2);
        classlist.add(seatClass3);
    }
   
    public PassengerAircraft(String manufacturer, String model, int maxVelocity, int length, int wingSpan, int emptyWeight, String class1Name, int class1NumberPassengers, String class2Name, int class2NumberPassengers, String class3Name, int class3NumberPassengers, String class4Name, int class4NumberPassengers) 
    {
        super(manufacturer, model, maxVelocity, length, wingSpan, emptyWeight);
        type = "Passenger";
        classes = 4;
        seatClass1 = new SeatClass(class1Name,class1NumberPassengers);
        seatClass2 = new SeatClass(class2Name,class2NumberPassengers);
        seatClass3 = new SeatClass(class3Name,class3NumberPassengers);
        seatClass4 = new SeatClass(class4Name,class4NumberPassengers);
        passengerCapacity = seatClass1.getNumberPassengers() + seatClass2.getNumberPassengers() + seatClass3.getNumberPassengers() + seatClass4.getNumberPassengers();
        classlist = new ArrayList<>();
        classlist.add(seatClass1);
        classlist.add(seatClass2);
        classlist.add(seatClass3);
        classlist.add(seatClass4);
    }

    //copy constructor
    public PassengerAircraft(PassengerAircraft passengerAircraft) 
    {
        super(passengerAircraft.manufacturer, passengerAircraft.model, passengerAircraft.maxVelocity, passengerAircraft.length, passengerAircraft.wingSpan, passengerAircraft.emptyWeight);
        this.type = passengerAircraft.type;
        this.classes = passengerAircraft.classes;
        this.seatClass1 = passengerAircraft.seatClass1;
        this.seatClass2 = passengerAircraft.seatClass2;
        this.seatClass3 = passengerAircraft.seatClass3;
        this.seatClass4 = passengerAircraft.seatClass4;
        this.passengerCapacity = passengerAircraft.passengerCapacity;
        this.classlist = passengerAircraft.classlist;
    
    }//copy constructor
    
//operations

    public String getType() {
        return type;
    }

    /*public void setType(String type) {
        this.type = type;
    }*/ //internally managed

    public int getClasses() {
        return classes;
    }

    /*public void setClasses(int classes) {
        this.classes = classes;
    }*/ //internally managed

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    /*public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }*/ // internally managed

    public SeatClass getSeatClass1() {
        return seatClass1;
    }

    public SeatClass getSeatClass2() {
        return seatClass2;
    }

    public SeatClass getSeatClass3() {
        return seatClass3;
    }

    public SeatClass getSeatClass4() {
        return seatClass4;
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

    public int getMaxVelocity() {
        return maxVelocity;
    }

    public void setMaxVelocity(int maxVelocity) {
        this.maxVelocity = maxVelocity;
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

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    /*public void setName(String name) {
        this.name = name;
    }*/ //internally managed
    
    public void printInfo(){
        System.out.println(
            "\t\t\tManufacturer:\t" + manufacturer
            +"\n\t\t\tModel:\t" + model
            +"\n\t\t\tType:\t" + type
            +"\n\t\t\tLength:\t" + length
            +"\n\t\t\tWing Span:\t" + wingSpan
            +"\n\t\t\tEmpty Weight:\t" + emptyWeight
            +"\n\t\t\tMax Velocity:\t" + maxVelocity
            +"\n\t\t\tTotal Capacity:\t" + passengerCapacity
            +"\n\t\t\tNumber of Seat Classes:\t" + classes);
        
        for( SeatClass seatclass : classlist){
            System.out.println(
            "\t\t\t\t"+ seatclass.getClassName() + ": " + seatclass.getNumberPassengers()
            );
        }
    }//printInfo
    
}//Class PassengerAircraft