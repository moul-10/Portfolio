
package airtrafficcontrol;

public class DepartureArrivalInfo {
    //data attributes
    private String dayOfWeek;
    private int time;
    private String airportCode;
    private String gate;
    
    //constructors
    DepartureArrivalInfo(String dayOfWeek, int time, String airportCode, String gate){
        this.dayOfWeek = dayOfWeek;
        this.time = time;
        this.airportCode = airportCode;
        this.gate = gate;
    }
    
    
    //operations

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }
     
   
}//DepartureArrivalInfo
