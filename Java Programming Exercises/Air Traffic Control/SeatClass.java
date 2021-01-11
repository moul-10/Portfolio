
package airtrafficcontrol;

public class SeatClass {
    //data attributes
    private String className;
    private int numberPassengers;
    
    //constructors
    public SeatClass()
    {
        className = "Coach Class";
        numberPassengers = 100;
    }
    
    public SeatClass(String className)
    {
        this.className = className;
        numberPassengers = 100;
    }
    
    public SeatClass(int numberPassengers)
    {
        className = "Coach Class";
        this.numberPassengers = numberPassengers;
    }
    
    public SeatClass(String className, int numberPassengers)
    {
        this.className = className;
        this.numberPassengers = numberPassengers;
    }
    
//operations

    public String getClassName() {
        return className;
    }

    public int getNumberPassengers() {
        return numberPassengers;
    }
    
}//SeatClass
