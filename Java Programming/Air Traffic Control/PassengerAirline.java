
package airtrafficcontrol;

import java.util.ArrayList;

public class PassengerAirline extends Airline {
    //data attributes
    
    private ArrayList<PassengerAircraft> aircraft;
    private int numberAircraft;
    
    //Constructors
    public PassengerAirline(){
        fullName = "[UNKOWN] Airline";
        code = "??";
        type = "Passenger Airline";
        aircraft = new ArrayList<>();
    }
    
    public PassengerAirline(String code){
        fullName = "[UNKOWN] Airline";
        this.code = code;
        type = "Passenger Airline";
        aircraft = new ArrayList<>();
    }
    
    public PassengerAirline(String fullName, String code){
        this.fullName = fullName;
        this.code = code;
        type = "Passenger Airline";
        aircraft = new ArrayList<>();
    }
    
    //operations

    /*public ArrayList<PassengerAircraft> getAircraft() {
        return aircraft;
    }

    public void setAircraft(ArrayList<PassengerAircraft> aircraft) {
        this.aircraft = aircraft;// copies existing ArrayList
    }*/

    public int getNumberAircraft() {
        this.numberAircraft = aircraft.size();
        return numberAircraft;
    }

    /*public void setNumberAircraft(int numberAircraft) {
        this.numberAircraft = numberAircraft;
    }*///internally managed

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    /*public void setType(String type) {
        this.type = type;
    }*///Internally managed
    
    public void addAircraft(PassengerAircraft newAircraft){
        this.aircraft.add(newAircraft);
        
    }
    
    public void printAircraft(){
        int idx=0;
        for(PassengerAircraft passengerAircraft : aircraft){
              System.out.println("\t" + idx + ".\t" + passengerAircraft.name);
              idx++;
            }
            
        }
    
    public void printAircraftWithInfo(){
        int idx=0;
        for(PassengerAircraft passengerAircraft : aircraft){
              System.out.println("\t" + idx + ".\t" + passengerAircraft.name);
              passengerAircraft.printInfo();
              idx++;
            }
            
        }

}//PassengerAirline