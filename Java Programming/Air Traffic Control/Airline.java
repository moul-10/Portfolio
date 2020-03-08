
package airtrafficcontrol;

import java.util.ArrayList;


public class Airline {
    //data attributes
    protected String fullName;
    protected String code;
    protected String type;
    private ArrayList<Aircraft> aircraft; 
    
    //constructors
    public Airline(){
        fullName = "[UNKOWN] Airline";
        code = "??";
        this.aircraft = new ArrayList<>();
    }
    
    public Airline(String code){
        fullName = "[UNKOWN] Airline";
        this.code = code;
        aircraft = new ArrayList<>();
    }
    
    public Airline(String fullName, String code){
        this.fullName = fullName;
        this.code = code;
        aircraft = new ArrayList<>();
    }
    //operations
    
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public ArrayList<Aircraft> getAircraft() {
        return aircraft;
    }

    public void setAircraft(ArrayList<Aircraft> aircraft) {
        this.aircraft = aircraft;
    }
    
    public void addAircraft(Aircraft newAircraft){
        this.aircraft.add(newAircraft);
    }

    
}
