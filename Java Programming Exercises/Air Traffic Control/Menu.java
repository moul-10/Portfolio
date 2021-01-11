
package airtrafficcontrol;

public class Menu {
   
    // data attributes
   public static final int Set_Clock = 1;
   public static final int Clear_Schedule = 2;
   public static final int Add_Airline = 3;
   public static final int Add_Flight = 4;
   public static final int Cancel_Flight = 5;
   public static final int Show_Flight_Info = 6;
   public static final int Show_Departures = 7;
   public static final int Show_Arrivals = 8;
   public static final int Find_Flights_Between_Airports = 9;
   public static final int Show_Airline_Aircraft_Info = 10;
   public static final int Exit = 0;


public void displayGraphic(){
       System.out.println("                **   ");
       System.out.println("                ||  *");
       System.out.println("                ||  |                       __ _");
       System.out.println("             *  ||  |                        _| \\");
       System.out.println("             |  ||  |                     |\\__|  \\_____ ");
       System.out.println("=============|==||==|=====================|-__    _____) ");
       System.out.println("          ___|__||__|___                  |/ _|  /");
       System.out.println("          \\            /                    __|_/");
       System.out.println("       ____\\__________/____    ");
       System.out.println("     _/                    \\_ ");
       System.out.println("    (________________________)");
       System.out.println("     |_|__|___|____|___|__|_|");
       System.out.println("     |_|__|___|____|___|__|_|");
       System.out.println("     |_|__|___|____|___|__|_|");
       System.out.println("     |_|__|___|____|___|__|_|");
       System.out.println("    (________________________)");
       System.out.println("       \\                  /        ____    _____________    _________");
       System.out.println("        \\________________/        /    \\  |             |  /         |");
       System.out.println("         \\______________/        /      \\ |____     ____| /    ______|");
       System.out.println("          |            |        /   /\\   \\     |   |     /    /");
       System.out.println("          |            |       |   /__\\   |    |   |    |    |");
       System.out.println("          |            |       |    __    |    |   |     \\    \\______ ");
       System.out.println("          |            |       |   |  |   |    |   |      \\          |");
       System.out.println("__________|____________|_______|___|  |___|ir__|___|raffic_\\_________|ontrol");
       System.out.println("");
       System.out.println("Hello, Controller");
       System.out.println("");
       System.out.println("What would you like to do?");
       System.out.println("");
}

public void displayOptions(){
    System.out.println(" _________________________");
    System.out.println("|                         |");
    System.out.println("|--------MAIN MENU--------|");
    System.out.println("|_________________________|");
    System.out.println();
    System.out.println(
         "1.  Set Clock\n"
       + "2.  Clear Schedule\n"
       + "3.  Add Airline\n"
       + "4.  Add Flight\n"
       + "5.  Cancel Flight\n"
       + "6.  Show Flight Information\n"
       + "7.  Show Departures\n"
       + "8.  Show Arrivals\n"
       + "9.  Find Flights Between Airports\n"
       + "10. Show All Airline and Aircraft Info\n"          
       + "0.  Exit\n");

}

public int getUserChoice(){
return Validator.getIntMenu(">>>> ", Exit, Show_Airline_Aircraft_Info);
}

}//Menu