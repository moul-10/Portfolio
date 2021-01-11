package airtrafficcontrol;
/*
This project was a lot of fun and definitely a challenge at times.
It features a few methods that handle time in a practical manner,
a complex and effective method for populating random flights,
and a complete method for finding two connecting flights with a layover.

Additionally, airlines were given more usability and versatility with the 
addition of airline types. These are populated by either passenger or cargo
aircraft depending on the type of airline. Each aircraft is created from
generic prototype that has integer-based attributes gathered from
specifications gathered from wikipedia and respective airline and manufacturer
websites.

As always, I appreciate all feedback, and hope you enjoy this program.

This class has been a real pleasure, THANK YOU!
*/
public class AirTrafficControl {

    public static void main(String[] args) {

        Menu menu = new Menu();
        FlightSchedule schedule = new FlightSchedule();
        schedule.populateFlights(5000);// populate enough random flights to make "Find Flights Between Airports" work
        menu.displayGraphic();
        for (;;) {
            menu.displayOptions();
            switch (menu.getUserChoice()) {
                case Menu.Set_Clock: //FINISHED
                    schedule.setDateTime();
                    break;
                case Menu.Clear_Schedule://FINISHED
                    schedule.clearSchedule();
                    //FlightSchedule schedule = new FlightSchedule(); 
                    //^Destroys previous reference, makes new empty schedule
                    break;
                case Menu.Add_Airline: //WORKS BUT STILL TODO
                    schedule.addAirline();//TODO: CREATE SUB-MENU TO SELECT CARGO/PASSENGER, IMPLEMENT PARAMETERS, ADD AIRCRAFT
                    break;
                case Menu.Add_Flight: //WORKS BUT STILL TODO
                    schedule.addFlight();//TODO: CREATE SUB-MENU TO SELECT CARGO/PASSENGER, IMPLEMENT PARAMETERS, ADD AIRCRAFT
                    break;
                case Menu.Cancel_Flight://FINISHED
                    schedule.cancelFlight();
                    break;
                case Menu.Show_Flight_Info://FINISHED
                    schedule.showFlightInfo();
                    break;
                case Menu.Show_Departures:
                    schedule.showDepartures();
                    break;
                case Menu.Show_Arrivals:
                    schedule.showArrivals();
                    break;
                case Menu.Find_Flights_Between_Airports:
                    schedule.findFlightsBetweenAirports();
                    break;
                case Menu.Show_Airline_Aircraft_Info:
                    schedule.printALLAirlineAndAircraftInfo();
                    break;
                case Menu.Exit: //FINISHED
                    for (;;) {
                        String exitChoice = Validator.getString("Do you really want exit? Y/N  >");
                        if (exitChoice.equalsIgnoreCase("y")) {
                            return;
                        } else if (exitChoice.equalsIgnoreCase("n")) {
                            break;
                        }
                        System.out.println("PLEASE ENTER 'Y' or 'N'!");
                    }
                    break;
            }
        }
    }//main()

}//AirTrafficControl
