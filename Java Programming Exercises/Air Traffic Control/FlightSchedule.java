package airtrafficcontrol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FlightSchedule {

    //data attributes  
    private int hours;
    private int minutes;
    private int currentTime;
    private char currentDay;// U, M, T, W, R, F, S
    public ArrayList<Airline> airlines;//!!!!!!!!!!!!!!!!!! MAKE PRIVATE

    private ArrayList<PassengerAirline> passengerAirlines;
    private ArrayList<CargoAirline> cargoAirlines;
    public HashMap< String/*flightNumber, ex: "AA-1341"*/, Flight> flightMap; //!!!!!!!!!!!!!!!!!! MAKE PRIVATE
    private HashMap< Flight, Aircraft> flightToAircraftMap;
    final public ArrayList<CargoAircraft> cargoConfigurations;
    final public ArrayList<PassengerAircraft> passengerConfigurations;
    private ArrayList<String> airportCodes = new ArrayList<>();

    public int integerTime(int hours, int minutes) {

        if (hours >= 24) {
            hours = hours - 24;
        }
        if (minutes >= 60) {
            hours++;
            minutes = minutes - 60;
        }

        int time = 100 * hours + minutes;
        return time;
    }//integerTime        

    public String stringTime(int time) {
        String strTime;
        if (time < 10) {
            strTime = "000" + Integer.toString(time);
        } else if (time >= 10 && time < 100) {
            strTime = "00" + Integer.toString(time);
        } else if (time >= 100 && time < 1000) {
            strTime = "0" + Integer.toString(time);
        } else {
            strTime = Integer.toString(time);
        }
        return strTime;
    }//stringTime

    public String stringDay(char charDay) {

        String strDay = null;
        if (charDay == 'u' || charDay == 'U') {
            strDay = "Sunday";
        } else if (charDay == 'm' || charDay == 'M') {
            strDay = "Monday";
        } else if (charDay == 't' || charDay == 'T') {
            strDay = "Tuesday";
        } else if (charDay == 'w' || charDay == 'W') {
            strDay = "Wednesday";
        } else if (charDay == 'r' || charDay == 'R') {
            strDay = "Thursday";
        } else if (charDay == 'f' || charDay == 'F') {
            strDay = "Friday";
        } else if (charDay == 's' || charDay == 'S') {
            strDay = "Saturday";
        } else {
            strDay = charDay + "";
        }

        return strDay;
    }//stringDay

    public int getCurrentTime() {
        return currentTime;
    }

    public char getCurrentDay() {
        return currentDay;
    }

    public void showDepartures() {
        System.out.println(" __________________________");
        System.out.println("|                          |");
        System.out.println("|--------Departures--------|");
        System.out.println("|__________________________|");
        System.out.println(
                "\n________________________________________________________________\n"
                + "\n                            AIRPORT CODES"
                + "\n________________________________________________________________");
        int codeIdx = 0;
        for (String airportCode : airportCodes) {
            System.out.println(1 + codeIdx + ".\t" + airportCode.toUpperCase() + "\t" + airportCodeToString(airportCode));
            codeIdx++;
        }

        System.out.println();
        codeIdx = Validator.getInt("Enter number that corresponds to the departure airport:  >", 1, airportCodes.size());
        codeIdx--;
        String departureDayOfWeek = null;
        for (;;) {
            departureDayOfWeek = Validator.getString("Enter U, M, T, W, R, F, or S for day of departure: ");
            if (departureDayOfWeek.equalsIgnoreCase("u")
                    || departureDayOfWeek.equalsIgnoreCase("m")
                    || departureDayOfWeek.equalsIgnoreCase("t")
                    || departureDayOfWeek.equalsIgnoreCase("w")
                    || departureDayOfWeek.equalsIgnoreCase("r")
                    || departureDayOfWeek.equalsIgnoreCase("f")
                    || departureDayOfWeek.equalsIgnoreCase("s")) {
                break;
            } else {
                System.out.println("ERROR: Invalid entry. Please try again.");
            }
        }
        ArrayList<Flight> matchingFlights = new ArrayList<>();
        for (Map.Entry<String, Flight> entry : flightMap.entrySet()) {
            Flight flight = entry.getValue();
            if (airportCodes.get(codeIdx).equalsIgnoreCase(flight.getDepartureInfo().getAirportCode())
                    && (departureDayOfWeek.equalsIgnoreCase(flight.getDepartureInfo().getDayOfWeek()))) {
                matchingFlights.add(flight);
            }
        }
        Collections.sort(matchingFlights);
        printFlights(matchingFlights);
        System.out.println();
        Validator.pause();
    }

    public void showArrivals() {
        System.out.println(" __________________________");
        System.out.println("|                          |");
        System.out.println("|---------Arrivals---------|");
        System.out.println("|__________________________|");
        System.out.println(
                "\n________________________________________________________________\n"
                + "\n                            AIRPORT CODES"
                + "\n________________________________________________________________");
        int codeIdx = 0;
        for (String airportCode : airportCodes) {
            System.out.println(1 + codeIdx + ".\t" + airportCode.toUpperCase() + "\t" + airportCodeToString(airportCode));
            codeIdx++;
        }

        System.out.println();
        codeIdx = Validator.getInt("Enter number that corresponds to the destination airport:  >", 1, airportCodes.size());
        codeIdx--;
        String arrivalDayOfWeek = null;
        for (;;) {
            arrivalDayOfWeek = Validator.getString("Enter U, M, T, W, R, F, or S for day of departure: ");
            if (arrivalDayOfWeek.equalsIgnoreCase("u")
                    || arrivalDayOfWeek.equalsIgnoreCase("m")
                    || arrivalDayOfWeek.equalsIgnoreCase("t")
                    || arrivalDayOfWeek.equalsIgnoreCase("w")
                    || arrivalDayOfWeek.equalsIgnoreCase("r")
                    || arrivalDayOfWeek.equalsIgnoreCase("f")
                    || arrivalDayOfWeek.equalsIgnoreCase("s")) {
                break;
            } else {
                System.out.println("ERROR: Invalid entry. Please try again.");
            }
        }
        ArrayList<Flight> matchingFlights = new ArrayList<>();
        for (Map.Entry<String, Flight> entry : flightMap.entrySet()) {
            Flight flight = entry.getValue();
            if (airportCodes.get(codeIdx).equalsIgnoreCase(flight.getArrivalInfo().getAirportCode())
                    && (arrivalDayOfWeek.equalsIgnoreCase(flight.getArrivalInfo().getDayOfWeek()))) {
                matchingFlights.add(flight);
            }
        }
        Collections.sort(matchingFlights);
        printFlights(matchingFlights);
        System.out.println();
        Validator.pause();
    }

    public ArrayList<Flight> sortFlights(ArrayList<Flight> flightList) {

        return flightList;
    }
//constructors

    public FlightSchedule() {
        hours = 0;
        minutes = 0;
        stringTime(integerTime(hours, minutes));
        currentDay = 'M';
        airlines = new ArrayList<>();
        passengerAirlines = new ArrayList<>();
        cargoAirlines = new ArrayList<>();
        flightMap = new HashMap<>();
        cargoConfigurations = new ArrayList<>();
        passengerConfigurations = new ArrayList<>();
        populateAirportCodes();
        //configureAircraftPrototypes();
        PassengerAirline alaska = new PassengerAirline("Alaska Airlines", "AS");
        PassengerAirline american = new PassengerAirline("American Airlines", "AA");
        PassengerAirline delta = new PassengerAirline("Delta Airlines", "DL");
        PassengerAirline hawaiian = new PassengerAirline("Hawaiian Airlines", "HA");
        PassengerAirline jetblue = new PassengerAirline("JetBlue Airways", "JB");
        PassengerAirline southwest = new PassengerAirline("Southwest Airlines", "WN");
        PassengerAirline united = new PassengerAirline("United Airlines", "UA");
        PassengerAirline airForce1 = new PassengerAirline("Air Force One", "US");

        //                       737, 747, 767, 777, 787, A321, A330, A380
        populatePassengerAirline(alaska, 16, 0, 0, 0, 0, 7, 0, 0);
        populatePassengerAirline(american, 30, 0, 2, 7, 4, 22, 3, 0);
        populatePassengerAirline(delta, 22, 2, 13, 5, 0, 17, 5, 1);
        populatePassengerAirline(hawaiian, 8, 0, 0, 0, 0, 17, 4, 0);
        populatePassengerAirline(jetblue, 25, 4, 8, 5, 2, 24, 7, 3);
        populatePassengerAirline(southwest, 43, 0, 0, 8, 0, 13, 0, 0);
        populatePassengerAirline(united, 22, 2, 13, 5, 0, 17, 5, 1);
        airForce1.addAircraft(passengerConfigurations.get(8));
        passengerAirlines.add(alaska);
        passengerAirlines.add(american);
        passengerAirlines.add(delta);
        passengerAirlines.add(hawaiian);
        passengerAirlines.add(jetblue);
        passengerAirlines.add(southwest);
        passengerAirlines.add(united);
        passengerAirlines.add(airForce1);
        CargoAirline fedex = new CargoAirline("FedEx", "FX");
        CargoAirline ups = new CargoAirline("United Parcel Service", "UP");
        CargoAirline dhl = new CargoAirline("DHL SPECIAL AIRLIFT", "DC");
        CargoAirline lufthansa = new CargoAirline("Lufthansa Cargo", "LC");

        //                  747, 757, 767, 777, A300, A330, An-124, An-225
        populateCargoAirline(fedex, 34, 12, 4, 0, 12, 1, 0, 0);
        populateCargoAirline(ups, 19, 2, 9, 1, 5, 5, 0, 0);
        populateCargoAirline(dhl, 49, 9, 4, 0, 0, 8, 4, 2);
        populateCargoAirline(lufthansa, 27, 17, 9, 1, 5, 0, 1, 1);
        cargoAirlines.add(fedex);
        cargoAirlines.add(ups);
        cargoAirlines.add(dhl);
        cargoAirlines.add(lufthansa);
        airlines.addAll(cargoAirlines);
        airlines.addAll(passengerAirlines);

    }

    public ArrayList<String> getAirportCodes() {
        Collections.sort(airportCodes);
        return airportCodes;
    }

    public String airportCodeToString(String airportCode) {
        String airportName = null;
        if (airportCode.equalsIgnoreCase("ATL")) {
            airportName = "Hartsfield–Jackson Atlanta International Airport";
        } else if (airportCode.equalsIgnoreCase("BOS")) {
            airportName = "Logan International Airport";
        } else if (airportCode.equalsIgnoreCase("BWI")) {
            airportName = "Baltimore–Washington International Airport";
        } else if (airportCode.equalsIgnoreCase("CLT")) {
            airportName = "Charlotte Douglas International Airport";
        } else if (airportCode.equalsIgnoreCase("DCA")) {
            airportName = "Ronald Reagan Washington National Airport";
        } else if (airportCode.equalsIgnoreCase("DEN")) {
            airportName = "Denver International Airport";
        } else if (airportCode.equalsIgnoreCase("DFW")) {
            airportName = "Dallas/Fort Worth International Airport";
        } else if (airportCode.equalsIgnoreCase("DTW")) {
            airportName = "Detroit Metropolitan Airport";
        } else if (airportCode.equalsIgnoreCase("EWR")) {
            airportName = "Newark Liberty International Airport";
        } else if (airportCode.equalsIgnoreCase("JFK")) {
            airportName = "John F. Kennedy International Airport";
        } else if (airportCode.equalsIgnoreCase("LAS")) {
            airportName = "McCarran International Airport";
        } else if (airportCode.equalsIgnoreCase("LAX")) {
            airportName = "Los Angeles International Airport";
        } else if (airportCode.equalsIgnoreCase("MSP")) {
            airportName = "Minneapolis–Saint Paul International Airport";
        } else if (airportCode.equalsIgnoreCase("ORD")) {
            airportName = "O'Hare International Airport";
        } else if (airportCode.equalsIgnoreCase("PHL")) {
            airportName = "Philadelphia International Airport";
        } else if (airportCode.equalsIgnoreCase("PHX")) {
            airportName = "Phoenix Sky Harbor International Airport";
        } else if (airportCode.equalsIgnoreCase("SEA")) {
            airportName = "Seattle–Tacoma International Airport";
        } else if (airportCode.equalsIgnoreCase("SFO")) {
            airportName = "San Francisco International Airport";
        } else if (airportCode.equalsIgnoreCase("SLC")) {
            airportName = "Salt Lake City International Airport";
        } else if (airportCode.equalsIgnoreCase("TPA")) {
            airportName = "Tampa International Airport";
        }
        return airportName;
    }

    public ArrayList<String> populateAirportCodes() {
        airportCodes.add("ATL"); //Hartsfield-Jackson Atlanta
        airportCodes.add("BOS"); //Boston Logan
        airportCodes.add("BWI"); //Baltimore
        airportCodes.add("CLT"); //Charlotte
        airportCodes.add("LAX"); //LA
        airportCodes.add("SEA"); //Settle        
        airportCodes.add("ORD"); //O'Hare Chicago
        airportCodes.add("DFW"); //Dallas Fort Worth
        airportCodes.add("DEN"); //Denver
        airportCodes.add("DTW"); //Detroit
        airportCodes.add("DCA"); //Washington DC
        airportCodes.add("LAS"); //McCarran
        airportCodes.add("JFK"); //JFK
        airportCodes.add("EWR"); //Newark
        airportCodes.add("SFO"); //San Francisco
        airportCodes.add("PHL"); //Philadelphia
        airportCodes.add("PHX"); //Phoenix
        airportCodes.add("TPA"); //Tampa
        airportCodes.add("SLC"); //Salt Lake City
        airportCodes.add("MSP"); //Minneapolis
        Collections.sort(airportCodes);
        return airportCodes;
    }

    public boolean isOpenAircraft(Aircraft aircraftCheck) {
        for (Airline airline : airlines) {
            for (Aircraft aircraft : airline.getAircraft()) {
                if (aircraft.flight != null) {
                    return false;
                }
            }

        }
        return true;
    }//prototype: unimplemented

    public void populateFlights(int numberNewFlights) {
        /*
        Create a defined nummber of flights using random indexes in 
        ArrayList airlines, then a specific aircraft in ArrayList aircraft
         */

        Random random = new Random();
        for (int num = numberNewFlights; num > 0; num--) {
            ArrayList<String> days = new ArrayList<>();
            days.add("U");
            days.add("M");
            days.add("T");
            days.add("W");
            days.add("R");
            days.add("F");
            days.add("S");

            ArrayList<String> gates = new ArrayList<>();
            gates.add("A");
            gates.add("B");
            gates.add("C");
            gates.add("D");
            gates.add("E");
            gates.add("F");
            gates.add("G");
            gates.add("H");
            gates.add("I");
            gates.add("J");
            gates.add("K");
            gates.add("L");
            gates.add("M");
            gates.add("N");
            gates.add("O");
            gates.add("P");
            gates.add("Q");
            gates.add("R");
            gates.add("S");
            gates.add("T");
            gates.add("U");
            gates.add("V");
            gates.add("W");
            gates.add("X");
            gates.add("Y");
            gates.add("Z");
            int randAirline = random.nextInt(airlines.size() - 1);

            Flight flight = new Flight(airlines.get(randAirline), 1 + random.nextInt(9999));

            //assign departure and arrival data
            int dHours = random.nextInt(24);
            int dMinutes = random.nextInt(60);
            int dTime = integerTime(dHours, dMinutes);
            String dGate = gates.get(random.nextInt(gates.size())) + Integer.toString(1 + random.nextInt(20));

            int randDayIdx = random.nextInt(days.size());
            String dDay = days.get(randDayIdx);

            flight.setDepartureInfo(new DepartureArrivalInfo(dDay, dTime, airportCodes.get(random.nextInt(airportCodes.size())), dGate));

            int aHours = dHours + (1 + random.nextInt(8));
            if (aHours >= 24) {
                aHours = aHours - 24;
            }
            int aMinutes = random.nextInt(60);
            int aTime = integerTime(aHours, aMinutes);
            String aDay = null;
            if (aTime < dTime) {
                if (randDayIdx == 6) {
                    randDayIdx = 0;
                } else {
                    randDayIdx++;
                }
                aDay = days.get(randDayIdx);
            } else {
                aDay = dDay;
            }
            String aGate = gates.get(random.nextInt(gates.size())) + Integer.toString(1 + random.nextInt(20));
            flight.setArrivalInfo(new DepartureArrivalInfo(aDay, aTime, airportCodes.get(random.nextInt(airportCodes.size())), aGate));
            // assign random aircraft
            flightMap.put(flight.getFlightNumber(), flight);

        }

    }

    public void populatePassengerAirline(PassengerAirline passengerAirline, int numberIdx0, int numberIdx1, int numberIdx2, int numberIdx3, int numberIdx4, int numberIdx5, int numberIdx6, int numberIdx7) {

        /*
      INDEX     PASSENGER PROTOTYPE
        0.	737-900ER
        1.	747-8
        2.	767-400ER
        3.	777-300
        4.	787-10 Dreamliner
        5.	A321
        6.	A330-900neo
        7.	A380-800
         */
        configureAircraftPrototypes();
        while (numberIdx0 > 0) {
            passengerAirline.addAircraft(new PassengerAircraft(passengerConfigurations.get(0)));
            numberIdx0--;
        }
        while (numberIdx1 > 0) {
            passengerAirline.addAircraft(new PassengerAircraft(passengerConfigurations.get(1)));
            numberIdx1--;
        }
        while (numberIdx2 > 0) {
            passengerAirline.addAircraft(new PassengerAircraft(passengerConfigurations.get(2)));
            numberIdx2--;
        }
        while (numberIdx3 > 0) {
            passengerAirline.addAircraft(new PassengerAircraft(passengerConfigurations.get(3)));
            numberIdx3--;
        }
        while (numberIdx4 > 0) {
            passengerAirline.addAircraft(new PassengerAircraft(passengerConfigurations.get(4)));
            numberIdx4--;
        }
        while (numberIdx5 > 0) {
            passengerAirline.addAircraft(new PassengerAircraft(passengerConfigurations.get(5)));
            numberIdx5--;
        }
        while (numberIdx6 > 0) {
            passengerAirline.addAircraft(new PassengerAircraft(passengerConfigurations.get(6)));
            numberIdx6--;
        }
        while (numberIdx7 > 0) {
            passengerAirline.addAircraft(new PassengerAircraft(passengerConfigurations.get(7)));
            numberIdx7--;
        }
    }

    public void populateCargoAirline(CargoAirline cargoAirline, int numberIdx0, int numberIdx1, int numberIdx2, int numberIdx3, int numberIdx4, int numberIdx5, int numberIdx6, int numberIdx7) {
        /*CARGO PRESETS
        0.	747-8F
        1.	757-200SF
        2.	767-300ER/F
        3.	777F
        4.	A300-600F
        5.	A330-200F
        6.	An-124 Ruslan
        7.	An-225 Mriya
         */
        configureAircraftPrototypes();
        while (numberIdx0 > 0) {
            cargoAirline.addAircraft(new CargoAircraft(cargoConfigurations.get(0)));
            numberIdx0--;
        }
        while (numberIdx1 > 0) {
            cargoAirline.addAircraft(new CargoAircraft(cargoConfigurations.get(1)));
            numberIdx1--;
        }
        while (numberIdx0 > 2) {
            cargoAirline.addAircraft(new CargoAircraft(cargoConfigurations.get(2)));
            numberIdx2--;
        }
        while (numberIdx3 > 0) {
            cargoAirline.addAircraft(new CargoAircraft(cargoConfigurations.get(3)));
            numberIdx3--;
        }
        while (numberIdx4 > 0) {
            cargoAirline.addAircraft(new CargoAircraft(cargoConfigurations.get(4)));
            numberIdx4--;
        }
        while (numberIdx5 > 0) {
            cargoAirline.addAircraft(new CargoAircraft(cargoConfigurations.get(5)));
            numberIdx5--;
        }
        while (numberIdx6 > 0) {
            cargoAirline.addAircraft(new CargoAircraft(cargoConfigurations.get(6)));
            numberIdx6--;
        }
        while (numberIdx7 > 0) {
            cargoAirline.addAircraft(new CargoAircraft(cargoConfigurations.get(7)));
            numberIdx7--;
        }
    }

    public void configureAircraftPrototypes() {
//CARGO PROTOTYPES   
        //Boeing Cargo Presets
        CargoAircraft cargo747 = new CargoAircraft(
                "Boeing", //String manufacturer
                "747-8F", //String model
                30288, //int cargoCapacity
                516, //int maxVelocity
                250, //int length
                224, //int wingSpan
                434600 //int emptyWeight
        );

        CargoAircraft cargo757 = new CargoAircraft(
                "Boeing", //String manufacturer
                "757-200SF", //String model
                6600, //int cargoCapacity
                496, //int maxVelocity
                155, //int length
                124, //int wingSpan
                115580 //int emptyWeight
        );

        CargoAircraft cargo767 = new CargoAircraft(
                "Boeing", //String manufacturer
                "767-300ER/F", //String model
                4030, //int cargoCapacity
                486, //int maxVelocity
                159, //int length
                156, //int wingSpan
                198440 //int emptyWeight
        );

        CargoAircraft cargo777 = new CargoAircraft(
                "Boeing", //String manufacturer
                "777F", //String model
                23051, //int cargoCapacity
                510, //int maxVelocity
                209, //int length
                212, //int wingSpan
                318300 //int emptyWeight
        );

        //Airbus Models
        CargoAircraft cargoA300 = new CargoAircraft(
                "Airbus", //String manufacturer
                "A300-600F", //String model
                10000, //int cargoCapacity
                546, //int maxVelocity
                177, //int length
                147, //int wingSpan
                180133 //int emptyWeight
        );

        CargoAircraft cargoA330 = new CargoAircraft(
                "Airbus", //String manufacturer
                "A330-200F", //String model
                16567, //int cargoCapacity
                470, //int maxVelocity
                193, //int length
                198, //int wingSpan
                241200 //int emptyWeight
        );

        //Antonov Models
        CargoAircraft cargoAn124 = new CargoAircraft(
                "Antonov", //String manufacturer
                "An-124 Ruslan", //String model
                34692, //int cargoCapacity
                470, //int maxVelocity
                226, //int length
                240, //int wingSpan
                385000 //int emptyWeight
        );

        CargoAircraft cargoAn225 = new CargoAircraft(
                "Antonov", //String manufacturer
                "An-225 Mriya", //String model
                46000, //int cargoCapacity
                459, //int maxVelocity
                275, //int length
                290, //int wingSpan
                628317 //int emptyWeight
        );

        //add all to Configurations ArrayLists
        cargoConfigurations.add(cargo747);
        cargoConfigurations.add(cargo757);
        cargoConfigurations.add(cargo767);
        cargoConfigurations.add(cargo777);
        cargoConfigurations.add(cargoA300);
        cargoConfigurations.add(cargoA330);
        cargoConfigurations.add(cargoAn124);
        cargoConfigurations.add(cargoAn225);
//END CARGO PROTOTYPES

//PASSENGER PROTOTYPES
        //Boeing Passenger Prototypes
        PassengerAircraft passenger737 = new PassengerAircraft(
                "Boeing", //manufacturer 
                "737-900ER", //model 
                455, //maxVelocity 
                138, //length 
                117, //wingSpan 
                98495, //emptyWeight 
                "Business Class", //String class1Name 
                12, //int class1NumberPassengers 

                "Coach Class", //String class2Name, 
                165 //int class2NumberPassengers, 

        //String class3Name, NOT USED
        //int class3NumberPassengers, NOT USED

        //String class4Name, NOT USED
        //int class4NumberPassengers USED
        );

        PassengerAircraft passenger747 = new PassengerAircraft(
                "Boeing", //manufacturer 
                "747-8", //model 
                516, //maxVelocity 
                250, //length 
                225, //wingSpan 
                485300, //emptyWeight 
                "First Class", //String class1Name 
                8, //int class1NumberPassengers 

                "Business Class", //String class2Name, 
                48, //int class2NumberPassengers, 

                "Premium Economy Class", //String class3Name, NOT USED
                32, //int class3NumberPassengers, NOT USED

                "Economy Class", //String class4Name, NOT USED
                244 //int class4NumberPassengers USED
        );

        PassengerAircraft passenger767 = new PassengerAircraft(
                "Boeing", //manufacturer 
                "767-400ER", //model 
                486, //maxVelocity 
                201, //length 
                170, //wingSpan 
                229000, //emptyWeight 
                "First Class", //String class1Name 
                16, //int class1NumberPassengers 

                "Business Class", //String class2Name, 
                36, //int class2NumberPassengers, 

                "Coach Class", //String class3Name, 
                189 //int class3NumberPassengers, 

        //String class4Name, NOT USED
        //int class4NumberPassengers USED
        );

        PassengerAircraft passenger777 = new PassengerAircraft(
                "Boeing", //manufacturer 
                "777-300", //model 
                593, //maxVelocity 
                242, //length 
                200, //wingSpan 
                353800, //emptyWeight 
                "First Class", //String class1Name 
                30, //int class1NumberPassengers 

                "Business Class", //String class2Name, 
                84, //int class2NumberPassengers, 

                "Coach Class", //String class3Name, 
                254 //int class3NumberPassengers, 

        //String class4Name, NOT USED
        //int class4NumberPassengers USED
        );

        PassengerAircraft passenger787 = new PassengerAircraft(
                "Boeing", //manufacturer 
                "787-10 Dreamliner", //model 
                593, //maxVelocity 
                242, //length 
                200, //wingSpan 
                353800, //emptyWeight 
                "Business Class", //String class1Name 
                32, //int class1NumberPassengers 

                "Coach Class", //String class2Name, 
                298 //int class2NumberPassengers, 

        //String class3Name, NOT USED
        //int class3NumberPassengers, 

        //String class4Name, NOT USED
        //int class4NumberPassengers USED
        );

        //AIRBUS PROTOTYPES
        PassengerAircraft passengerA320 = new PassengerAircraft(
                "Airbus", //manufacturer 
                "A321", //model 
                470, //maxVelocity 
                146, //length 
                117, //wingSpan 
                107000, //emptyWeight 
                "Business Class", //String class1Name 
                16, //int class1NumberPassengers 

                "Coach Class", //String class2Name, 
                169 //int class2NumberPassengers, 

        //String class3Name, NOT USED
        //int class3NumberPassengers, NOT USED

        //String class4Name, NOT USED
        //int class4NumberPassengers USED
        );

        PassengerAircraft passengerA330 = new PassengerAircraft(
                "Airbus", //manufacturer 
                "A330-900neo", //model 
                496, //maxVelocity 
                209, //length 
                210, //wingSpan 
                302000, //emptyWeight 
                "Executive Class", //String class1Name 
                32, //int class1NumberPassengers 

                "Economy Plus Class", //String class2Name, 
                96, //int class2NumberPassengers, 

                "Economy Class", //String class3Name, 
                176 //int class3NumberPassengers, 

        //String class4Name, NOT USED
        //int class4NumberPassengers USED
        );

        PassengerAircraft passengerA380 = new PassengerAircraft(
                "Airbus", //manufacturer 
                "A380-800", //model 
                511, //maxVelocity 
                239, //length 
                262, //wingSpan 
                611000, //emptyWeight 
                "First Class", //String class1Name 
                14, //int class1NumberPassengers 

                "Executive Class", //String class2Name, 
                97, //int class2NumberPassengers, 

                "Economy Plus Class", //String class3Name, 
                55, //int class3NumberPassengers, 

                "Economy Class", //String class4Name, NOT USED
                303 //int class4NumberPassengers USED
        );

        PassengerAircraft airForceOne = new PassengerAircraft(
                "Boeing", //manufacturer 
                "VC-25A", //model 
                613, //maxVelocity 
                231, //length 
                196, //wingSpan 
                526500, //emptyWeight 
                "Presidential Party", //String class1Name 
                14, //int class1NumberPassengers 
                "Secret Service",
                62,
                "Crew",
                10
        );

        //add all to Configurations ArrayLists
        passengerConfigurations.add(passenger737);
        passengerConfigurations.add(passenger747);
        passengerConfigurations.add(passenger767);
        passengerConfigurations.add(passenger777);
        passengerConfigurations.add(passenger787);
        passengerConfigurations.add(passengerA320);
        passengerConfigurations.add(passengerA330);
        passengerConfigurations.add(passengerA380);
        passengerConfigurations.add(airForceOne);
    }//configureAircraftPrototypes

    public void setDateTime() {
        System.out.println(" ___________________________");
        System.out.println("|                           |");
        System.out.println("|---------Set Clock---------|");
        System.out.println("|___________________________|");
        System.out.println(" Current Day: \t[" + stringDay(currentDay) + "]\n \tTime:\t[" + stringTime(integerTime(hours, minutes)) + "]");

        System.out.println();
        String day = null;
        for (;;) {
            day = Validator.getString("Enter U, M, T, W, R, F, or S for corresponding day of the week: ");
            if (day.equalsIgnoreCase("u") || day.equalsIgnoreCase("m") || day.equalsIgnoreCase("t") || day.equalsIgnoreCase("w") || day.equalsIgnoreCase("r") || day.equalsIgnoreCase("f") || day.equalsIgnoreCase("s")) {
                break;
            } else {
                System.out.println("ERROR: Invalid entry. Please try again.");
            }
        }
        day = day.toUpperCase();
        currentDay = day.charAt(0);

        System.out.println();
        hours = Validator.getInt("Please enter HOURS in military notation[0-23]: ", 0, 23);
        minutes = Validator.getInt("Please enter MINUTES in military notation[0-59]: ", 0, 59);
        System.out.println("Day and time set!");
        System.out.println(" Current Day: \t[" + stringDay(currentDay) + "]\n \tTime:\t[" + stringTime(integerTime(hours, minutes)) + "]");
        Validator.pause();
    }

    public void clearSchedule() {

        System.out.println(" ____________________________");
        System.out.println("|                            |");
        System.out.println("|--!!!--CLEAR SCHEDULE--!!!--|");
        System.out.println("|____________________________|");

        String clearChoice = Validator.getString("WARNING!!! ALL CONTENTS WILL BE DELETED!!! \nDO YOU STILL WANT TO CONTINUE?? Y/N  \n>");

        while (clearChoice != "y" && clearChoice != "n") {

            if (clearChoice.equalsIgnoreCase("y")) {
                resetSchedule();
                return;
            } else if (clearChoice.equalsIgnoreCase("n")) {
                return;
            } else {
                System.out.println();
                clearChoice = Validator.getString("PLEASE ENTER 'Y' or 'N'!  \n>");
            }
        }

    }//clearSchedule

    private void resetSchedule() {
        //TODO ask user for confirmation...
        currentTime = 0;
        currentDay = 'M';
        airlines = new ArrayList<>();
        flightMap = new HashMap<>();
    }

    public boolean isNewAirlineName(String name) {
        for (Airline oneAirline : airlines) {
            if (name.equalsIgnoreCase(oneAirline.fullName) || name.toUpperCase().startsWith(oneAirline.fullName.toUpperCase()) || oneAirline.fullName.toUpperCase().startsWith(name.toUpperCase())) {
                return false;
            }
        }
        return true;
    }

    public boolean isNewAirlineCode(String code) {
        for (Airline oneAirline : airlines) {
            if (code.equalsIgnoreCase(oneAirline.fullName)) {
                return false;
            }
        }
        return true;
    }

    public void addAirline() {//TODO: CREATE SUB-MENU TO SELECT CARGO/PASSENGER, IMPLEMENT PARAMETERS, ADD AIRCRAFT

        System.out.println(" ___________________________");
        System.out.println("|                           |");
        System.out.println("|--------Add Airline--------|");
        System.out.println("|___________________________|");
        //TO DO: recreate Validator usage without "sc"
        String fullName;
        String code;
        for (;;) {
            fullName = Validator.getString("Enter Full Name of Airline: >");
            if (isNewAirlineName(fullName) == false) {
                System.out.println("Name [" + fullName + "] already exists. Please choose another.");
            }//if Name Already Exists
            else {
                break;
            }
        }
        for (;;) {
            code = Validator.getString("Enter 2-digit Airline Code: >");
            code = code.toUpperCase();//convert code to uppercase
            if (isNewAirlineCode(code) == false) {
                System.out.println("Airline Code [" + fullName + "] already exists. Please choose another.");
            }//if Code Already Exists
            else {
                break;
            }
        }

        Airline airline = new Airline(fullName, code);
        airlines.add(airline);
        System.out.println("Airline, [" + fullName + "], Code [" + code + "], successfully added!");
        Validator.pause();
    }//TODO: CREATE SUB-MENU TO SELECT CARGO/PASSENGER, IMPLEMENT PARAMETERS, ADD AIRCRAFT

    public void addFlight() {
        //TODO: ASSIGN AIRCRAFT TO FLIGHT/ FLIGHT TO AIRCRAFT
        System.out.println(" ___________________________");
        System.out.println("|                           |");
        System.out.println("|--------Add Flight---------|");
        System.out.println("|___________________________|");
        System.out.println(
                "\n__________________________________\n"
                + "\n         AVAILABLE AIRLINES"
                + "\n__________________________________"
                + "\n0.  FedEx, \t\t\tFX"
                + "\n1.  United Parcel Service, \tUP"
                + "\n2.  DHL SPECIAL AIRLIFT, \tDC"
                + "\n3.  Lufthansa Cargo, \t\tLC"
                + "\n4.  Alaska Airlines, \t\tAS"
                + "\n5.  American Airlines, \t\tAA"
                + "\n6.  Delta Airlines, \t\tDL"
                + "\n7.  Hawaiian Airlines, \t\tHA"
                + "\n8.  JetBlue Airways, \t\tJB"
                + "\n9.  Southwest Airlines, \tWN"
                + "\n10. United Airlines, \t\tUA"
                + "\n11. Air Force One,	 \tUS\n__________________________________\n");
        int airlinesIndex = Validator.getInt("Enter corresponding number for Airline of new flight:  >", 0, 11);
        int flightNumber;
        for (;;) {
            flightNumber = Validator.getInt("Flight Number:  >");
            if (flightMap.containsKey(airlines.get(airlinesIndex).code + "-" + flightNumber)) {
                System.out.println("ERROR: A Flight with this number already exists! Please try again...");
            } else {
                break;
            }
        }
        Flight flight = new Flight(airlines.get(airlinesIndex), flightNumber);
        //TODO: Check if this flight already on file,
        // if so tell the user and exist...
        if (isFlightOnFile(flight.getFlightNumber())) {
            System.out.println("[" + flight.getFlightNumber() + "] is already on file! ");
            Validator.pause();
            return;
        }
        flightMap.put(flight.getFlightNumber(), flight);
        String departureDayOfWeek;
        for (;;) {
            departureDayOfWeek = Validator.getString("Enter U, M, T, W, R, F, or S for day of departure: ");
            if (departureDayOfWeek.equalsIgnoreCase("u") || departureDayOfWeek.equalsIgnoreCase("m") || departureDayOfWeek.equalsIgnoreCase("t") || departureDayOfWeek.equalsIgnoreCase("w") || departureDayOfWeek.equalsIgnoreCase("r") || departureDayOfWeek.equalsIgnoreCase("f") || departureDayOfWeek.equalsIgnoreCase("s")) {
                break;
            } else {
                System.out.println("ERROR: Invalid entry. Please try again.");
            }
        }
        stringDay(departureDayOfWeek.charAt(0));
        int departureHours = Validator.getInt("Please enter HOURS in military notation[0-23]: ", 0, 23);
        int departureMinutes = Validator.getInt("Please enter MINUTES in military notation[0-59]: ", 0, 59);
        int departureTime = integerTime(departureHours, departureMinutes);
        String departureAirportCode = Validator.getString("Departure Airport (XXX): > ");
        char departureGateLetter = Validator.getLetterChar("Departure Gate Letter: > ");
        int departureGateNumber = Validator.getInt("Departure Gate Number (1-20): > ");
        String departureGate = Character.toString(departureGateLetter) + departureGateNumber;
        flight.setDepartureInfo(new DepartureArrivalInfo(departureDayOfWeek, departureTime, departureAirportCode, departureGate));

        String arrivalDayOfWeek;
        for (;;) {
            arrivalDayOfWeek = Validator.getString("Enter U, M, T, W, R, F, or S for day of arrival: ");
            if (arrivalDayOfWeek.equalsIgnoreCase("u") || arrivalDayOfWeek.equalsIgnoreCase("m") || arrivalDayOfWeek.equalsIgnoreCase("t") || arrivalDayOfWeek.equalsIgnoreCase("w") || arrivalDayOfWeek.equalsIgnoreCase("r") || arrivalDayOfWeek.equalsIgnoreCase("f") || arrivalDayOfWeek.equalsIgnoreCase("s")) {
                break;
            } else {
                System.out.println("ERROR: Invalid entry. Please try again.");
            }
        }
        stringDay(arrivalDayOfWeek.charAt(0));
        int arrivalHours = Validator.getInt("Please enter HOURS of Arrival Time in military notation[0-23]: ", 0, 23);
        int arrivalMinutes = Validator.getInt("Please enter MINUTES of Arrival Time in military notation[0-59]: ", 0, 59);
        int arrivalTime = integerTime(arrivalHours, arrivalMinutes);
        String arrivalAirportCode = Validator.getString("Arrival Airport (XXX): > ");
        char arrivalGateLetter = Validator.getLetterChar("Arrival Gate Letter: > ");
        int arrivalGateNumber = Validator.getInt("Arrival Gate Number (1-20): > ", 1, 20);
        String arrivalGate = Character.toString(arrivalGateLetter) + arrivalGateNumber;
        flight.setArrivalInfo(new DepartureArrivalInfo(arrivalDayOfWeek, arrivalTime, arrivalAirportCode, arrivalGate));

        printFlightInfo(flight);
        Validator.pause();
    }

    public void showFlightInfo() {
        System.out.println(" ____________________________");
        System.out.println("|                            |");
        System.out.println("|------SHOW FLIGHT INFO------|");
        System.out.println("|____________________________|");
        printFlightInfo(flightSelection());
        Validator.pause();
    }

    public void cancelFlight() {
        System.out.println(" ___________________________");
        System.out.println("|                           |");
        System.out.println("|-------CANCEL FLIGHT-------|");
        System.out.println("|___________________________|");
        Flight flight = flightSelection();
        flight.setStatus("CX");
        System.out.println("Flight " + flight.getFlightNumber() + " cancelled!");
        Validator.pause();
    }

    public Flight flightSelection() {

        System.out.println(
                "\n__________________________________\n"
                + "\n         AVAILABLE AIRLINES"
                + "\n__________________________________"
                + "\n0.  FedEx, \t\t\tFX"
                + "\n1.  United Parcel Service, \tUP"
                + "\n2.  DHL SPECIAL AIRLIFT, \tDC"
                + "\n3.  Lufthansa Cargo, \t\tLC"
                + "\n4.  Alaska Airlines, \t\tAS"
                + "\n5.  American Airlines, \t\tAA"
                + "\n6.  Delta Airlines, \t\tDL"
                + "\n7.  Hawaiian Airlines, \t\tHA"
                + "\n8.  JetBlue Airways, \t\tJB"
                + "\n9.  Southwest Airlines, \tWN"
                + "\n10. United Airlines, \t\tUA"
                + "\n11. Air Force One,	 \tUS"
                + "\n12. EXIT\n__________________________________\n");
        ArrayList<String> flightCall = new ArrayList<>();
        for (;;) {
            int airlinesIndex = Validator.getInt("Enter corresponding number for Airline of flight:  >", 0, 12);
            if (airlinesIndex == 12) {
                ;
            }
            String airlineCode = airlines.get(airlinesIndex).code;
            System.out.println("\n__________________________________\n"
                    + "\n              FLIGHTS"
                    + "\n__________________________________");

            for (String flightNumber : flightMap.keySet()) {
                if (flightNumber.contains(airlineCode)) {
                    flightCall.add(flightNumber);
                }

            }
            if (flightCall.isEmpty()) {
                System.out.println("[NO FLIGHTS SCHEDULED FOR SPECIFIED AIRLINE]\n\nEnter \"12\" to return to Main Menu.\n");
            } else {
                break;
            }
        }
        Collections.sort(flightCall);
        int idx = 0;
        for (String flightNumber : flightCall) {
            System.out.println((idx + 1) + ".\t" + flightNumber);
            idx++;
        }
        System.out.println();
        int flightIndex = Validator.getInt("Enter corresponding number for flight:  >", 0, flightMap.keySet().size());
        return flightMap.get(flightCall.get(flightIndex - 1));
    }

    public void printHeading() {
        System.out.println(" _____________________________________________________________________________________________________________________________________________________________________");
        System.out.println("|                       |                 |            |                |        |               |                |             |        |             |              |");
        System.out.println(
                "|\tAirline"
                + "\t\t|  Flight Number"
                + "  |   Status   |"
                + " Departing From |"
                + "  Gate  |"
                + " Departure Day |"
                + " Departure Time |"
                + " Arriving In |"
                + "  Gate  |"
                + " Arrival Day |"
                + " Arrival Time |");

        System.out.println("|_______________________|_________________|____________|________________|________|_______________|________________|_____________|________|_____________|______________|\n");
    }

    public void printFlightNoHeading(Flight flight) {
        System.out.print("  " + flight.getAirline().fullName);
        int spaceNum = 0;
        if (flight.getAirline().fullName.length() < 21) {
            spaceNum = 21 - flight.getAirline().fullName.length();
        }

        for (spaceNum = spaceNum; spaceNum > 0; spaceNum--) {
            System.out.print(" ");
        }
        System.out.println(
                "\t      " + flight.getFlightNumber()
                + "\t\t" + flight.getStatus()
                + "\t      " + flight.getDepartureInfo().getAirportCode()
                + "\t    " + flight.getDepartureInfo().getGate()
                + "\t      " + stringDay(flight.getDepartureInfo().getDayOfWeek().charAt(0))
                + "\t\t" + stringTime(flight.getDepartureInfo().getTime())
                + "\t\t" + flight.getArrivalInfo().getAirportCode()
                + "\t    " + flight.getArrivalInfo().getGate()
                + "\t     " + stringDay(flight.getArrivalInfo().getDayOfWeek().charAt(0))
                + "\t     " + stringTime(flight.getArrivalInfo().getTime())
        );
    }

    public void printFlightInfo(Flight flight) {
        printHeading();
        System.out.print("  " + flight.getAirline().fullName);
        int spaceNum = 0;
        if (flight.getAirline().fullName.length() < 21) {
            spaceNum = 21 - flight.getAirline().fullName.length();
        }

        for (spaceNum = spaceNum; spaceNum > 0; spaceNum--) {
            System.out.print(" ");
        }
        System.out.println(
                "\t      " + flight.getFlightNumber()
                + "\t\t" + flight.getStatus()
                + "\t      " + flight.getDepartureInfo().getAirportCode()
                + "\t    " + flight.getDepartureInfo().getGate()
                + "\t      " + stringDay(flight.getDepartureInfo().getDayOfWeek().charAt(0))
                + "\t\t" + stringTime(flight.getDepartureInfo().getTime())
                + "\t\t" + flight.getArrivalInfo().getAirportCode()
                + "\t    " + flight.getArrivalInfo().getGate()
                + "\t     " + stringDay(flight.getArrivalInfo().getDayOfWeek().charAt(0))
                + "\t     " + stringTime(flight.getArrivalInfo().getTime())
        );
    }

    public void printFlights(ArrayList<Flight> flightList) {
        System.out.println(" _____________________________________________________________________________________________________________________________________________________________________");
        System.out.println("|                       |                 |            |                |        |               |                |             |        |             |              |");
        System.out.println(
                "|\tAirline"
                + "\t\t|  Flight Number"
                + "  |   Status   |"
                + " Departing From |"
                + "  Gate  |"
                + " Departure Day |"
                + " Departure Time |"
                + " Arriving In |"
                + "  Gate  |"
                + " Arrival Day |"
                + " Arrival Time |");

        System.out.println("|_______________________|_________________|____________|________________|________|_______________|________________|_____________|________|_____________|______________|\n");

        for (Flight flight : flightList) {
            System.out.print("  " + flight.getAirline().fullName);
            int spaceNum = 0;
            if (flight.getAirline().fullName.length() < 21) {
                spaceNum = 21 - flight.getAirline().fullName.length();
            }

            for (spaceNum = spaceNum; spaceNum > 0; spaceNum--) {
                System.out.print(" ");
            }
            System.out.println(
                    "\t      " + flight.getFlightNumber()
                    + "\t\t" + flight.getStatus()
                    + "\t      " + flight.getDepartureInfo().getAirportCode()
                    + "\t    " + flight.getDepartureInfo().getGate()
                    + "\t      " + stringDay(flight.getDepartureInfo().getDayOfWeek().charAt(0))
                    + "\t\t" + stringTime(flight.getDepartureInfo().getTime())
                    + "\t\t" + flight.getArrivalInfo().getAirportCode()
                    + "\t    " + flight.getArrivalInfo().getGate()
                    + "\t     " + stringDay(flight.getArrivalInfo().getDayOfWeek().charAt(0))
                    + "\t     " + stringTime(flight.getArrivalInfo().getTime())
            );
        }
    }//printFlights


    public void findFlightsBetweenAirports() {
        System.out.println(" ___________________________________");
        System.out.println("|                                   |");
        System.out.println("|---Find Flights Between Airlines---|");
        System.out.println("|___________________________________|");
        System.out.println(
                "\n________________________________________________________________\n"
                + "\n                            AIRPORT CODES"
                + "\n________________________________________________________________");
        int dCodeIdx = 0;
        int aCodeIdx = 0;
        for (String airportCode : airportCodes) {
            System.out.println(1 + dCodeIdx + ".\t" + airportCode.toUpperCase() + "\t" + airportCodeToString(airportCode));
            dCodeIdx++;
        }

        System.out.println();
        dCodeIdx = Validator.getInt("Enter number that corresponds to the departure airport:  >", 1, airportCodes.size());
        dCodeIdx--;
        aCodeIdx = Validator.getInt("Enter number that corresponds to the destination airport:  >", 1, airportCodes.size());
        aCodeIdx--;
        String departureDayOfWeek = null;
        for (;;) {
            departureDayOfWeek = Validator.getString("Enter U, M, T, W, R, F, or S for day of departure: ");
            if (departureDayOfWeek.equalsIgnoreCase("u")
                    || departureDayOfWeek.equalsIgnoreCase("m")
                    || departureDayOfWeek.equalsIgnoreCase("t")
                    || departureDayOfWeek.equalsIgnoreCase("w")
                    || departureDayOfWeek.equalsIgnoreCase("r")
                    || departureDayOfWeek.equalsIgnoreCase("f")
                    || departureDayOfWeek.equalsIgnoreCase("s")) {
                break;
            } else {
                System.out.println("ERROR: Invalid entry. Please try again.");
            }
        }
        ArrayList<Flight> firstFlights = new ArrayList<>();
        ArrayList<Flight> secondFlights = new ArrayList<>();
        for (Map.Entry<String, Flight> entry : flightMap.entrySet()) {
            Flight flight = entry.getValue();
            if (airportCodes.get(dCodeIdx).equalsIgnoreCase(flight.getDepartureInfo().getAirportCode())
                    && (departureDayOfWeek.equalsIgnoreCase(flight.getDepartureInfo().getDayOfWeek()))
                    && (departureDayOfWeek.equalsIgnoreCase(flight.getArrivalInfo().getDayOfWeek()))
                    && (!flight.getArrivalInfo().getAirportCode().equalsIgnoreCase(airportCodes.get(aCodeIdx)))) {
                firstFlights.add(flight);
            } else if (!airportCodes.get(dCodeIdx).equalsIgnoreCase(flight.getDepartureInfo().getAirportCode())
                    &&//if not departing airport code and arrival code matches destination airport code, add to second flight list
                    airportCodes.get(aCodeIdx).equalsIgnoreCase(flight.getArrivalInfo().getAirportCode())
                    && (departureDayOfWeek.equalsIgnoreCase(flight.getDepartureInfo().getDayOfWeek()))) {
                secondFlights.add(flight);
            }
        }

        
        Collections.sort(firstFlights);
        Collections.sort(secondFlights);
        /*printFlights(firstFlights);
        printFlights(secondFlights);*/
        printMatchingFlights(firstFlights, secondFlights);
        System.out.println();
        Validator.pause();

    }

     public void printMatchingFlights(ArrayList<Flight> firstFlights, ArrayList<Flight> secondFlights) {
        int layover;
        Collections.sort(firstFlights);
        Collections.sort(secondFlights);
        printHeading();

        //for (int firstIdx = 0; firstIdx == firstFlights.size() - 1; firstIdx++) {
        for( Flight firstFlight: firstFlights ){ 
        for (int secondIdx = 0; secondIdx < secondFlights.size(); secondIdx++) {
                if( secondFlights.get(secondIdx).getDepartureInfo().getAirportCode().equalsIgnoreCase(firstFlight.getArrivalInfo().getAirportCode())
                    &&
                    (secondFlights.get(secondIdx).getDepartureInfo().getTime() > firstFlight.getArrivalInfo().getTime())) {
                    
                    layover = (secondFlights.get(secondIdx).getDepartureInfo().getTime()) - (firstFlight.getArrivalInfo().getTime());
                    int layoverHours = layover /100;
                    int layoverMins = layover % 100;
                    if (layoverMins >= 60){
                        layoverMins = layoverMins - 40;
                    }
                    printFlightNoHeading(firstFlight);
                    printFlightNoHeading(secondFlights.get(secondIdx));
                    System.out.println("  Layover:  " + layoverHours + " Hour(s), " + layoverMins + " Minute(s)");
                    System.out.println("\n\n");
                }
            }
        }
    }
    
    public void printALLAirlineAndAircraftInfo() {

        int idx = 0;
        System.out.println(" ____________________________");
        System.out.println("|                            |");
        System.out.println("|-----PASSENGER AIRLINES-----|");
        System.out.println("|____________________________|");
        Validator.pause();

        for (PassengerAirline airline : passengerAirlines) {
            System.out.println(idx + ".  " + airline.fullName + ",\t " + airline.code);
            airline.printAircraftWithInfo();
            idx++;
        }

        idx = 0;
        System.out.println(" ____________________________");
        System.out.println("|                            |");
        System.out.println("|-------CARGO AIRLINES-------|");
        System.out.println("|____________________________|");
        Validator.pause();

        for (CargoAirline airline : cargoAirlines) {
            System.out.println(idx + ".  " + airline.fullName + ",\t " + airline.code);
            airline.printAircraftWithInfo();
            idx++;
        }
    }

    public boolean isValidAirlineCode(String code) {
        for (Airline oneAirline : airlines) {
            if (code.equals(oneAirline.getCode())) {
                return true;
            }
        }
        return false;
    }

    public boolean isFlightOnFile(String flightNumber) {
        return flightMap.containsKey(flightNumber);
    }

}//class FlightSchedule    
