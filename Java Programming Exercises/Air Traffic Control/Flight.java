package airtrafficcontrol;

public class Flight implements Comparable<Flight> {
//data attributes
    public static final int ORDER_BY_DEPARTURE_TIME = 1;
    public static final int ORDER_BY_ARRIVAL_TIME = 2;
    public static int comparisonType = ORDER_BY_DEPARTURE_TIME;
    
    private Airline airline; //"American Airlines"
    private int number; // "1341"
    private String flightNumber; //"AA-1341"
    private Aircraft aircraft;
    private String status; //"OT: On Time, X: Cancelled, B: Boarding
    
    private DepartureArrivalInfo departureInfo;
    private DepartureArrivalInfo arrivalInfo;
    

//constructors

    public Flight(Airline airline,  int number) {
        this.airline = airline;
        this.number = number;
        this.flightNumber = airline.getCode() + "-" + number;
        status = "OT";
        departureInfo = null;
        arrivalInfo = null;
        aircraft = null; //to be assigned through populating method
    }
    
    

//operations
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {    
        this.status = status;
    }

    
    
    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
        this.flightNumber = airline.getCode() + number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        this.flightNumber = airline.getCode() + number;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    
    
    /*public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }*///UNNECESSARY: flightNumber is internally managed and cannot
    //                 be changed without changing airline and #

    public DepartureArrivalInfo getDepartureInfo() {
        return departureInfo;
    }

    public void setDepartureInfo(DepartureArrivalInfo departureInfo) {
        this.departureInfo = departureInfo;
    }

    public DepartureArrivalInfo getArrivalInfo() {
        return arrivalInfo;
    }

    public void setArrivalInfo(DepartureArrivalInfo arrivalInfo) {
        this.arrivalInfo = arrivalInfo;
    }
    
    @Override
    public int compareTo( Flight another ) {
        // if this < another -- return -1
        // if this == another -- return 0
        // if this > another -- return +1
        if ( comparisonType == ORDER_BY_DEPARTURE_TIME) {
            if ( this.getDepartureInfo().getTime() < another.getDepartureInfo().getTime() ) {
                return -1;
            } else if ( this.getDepartureInfo().getTime() > another.getDepartureInfo().getTime() ) {
                return +1;
            }
            return 0;
            
        } else if ( comparisonType == ORDER_BY_ARRIVAL_TIME ) {
            if ( this.getArrivalInfo().getTime() < another.getArrivalInfo().getTime() ) {
                return -1;
            } else if ( this.getArrivalInfo().getTime() > another.getArrivalInfo().getTime() ) {
                return +1;
            }
            return 0;
            
        } else {
            return 0;
        }
    }
}//class Flight
