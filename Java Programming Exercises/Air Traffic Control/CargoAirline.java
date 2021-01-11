
package airtrafficcontrol;

import java.util.ArrayList;

public class CargoAirline extends Airline {
    //data attributes
    
    private ArrayList<CargoAircraft> aircraft;
    private int numberAircraft;
    
    //Constructors
    public CargoAirline(){
        fullName = "[UNKOWN] Airline";
        code = "??";
        type = "Cargo Airline";
        aircraft = new ArrayList<>();
    }
    
    public CargoAirline(String code){
        fullName = "[UNKOWN] Airline";
        this.code = code;
        type = "Cargo Airline";
        aircraft = new ArrayList<>();
    }
    
    public CargoAirline(String fullName, String code){
        this.fullName = fullName;
        this.code = code;
        type = "Cargo Airline";
        aircraft = new ArrayList<>();
    }
    
//Operations

    /*public ArrayList<CargoAircraft> getAircraft() {
        return aircraft;
    }

    public void setAircraft(ArrayList<CargoAircraft> aircraft) {
        this.aircraft = aircraft;
    }*/

    public int getNumberAircraft() {
        this.numberAircraft = aircraft.size();
        return numberAircraft;
    }

    /*public void setNumberAircraft(int numberAircraft) {
        this.numberAircraft = numberAircraft;
    }*///Internally Managed

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
    }*///Internally Managed
    
    public void addAircraft(CargoAircraft newAircraft){
        this.aircraft.add(newAircraft);
    }
    
    public void printAircraftWithInfo(){
        int idx=0;
        for(CargoAircraft cargoAircraft : aircraft){
              System.out.println("\t" + idx + ".\t" + cargoAircraft.name);
              cargoAircraft.printInfo();
              idx++;
            }
            
        }
}
