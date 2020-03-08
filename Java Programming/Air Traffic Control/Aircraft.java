
package airtrafficcontrol;

public class Aircraft {

    //data attributes
    protected String manufacturer;
    protected String model;
    protected int maxVelocity; //knots
    protected int length;      //feet
    protected int wingSpan;    //feet
    protected int emptyWeight;      //pounds
    protected String type;
    protected String name;
    protected Flight flight;

    //constructors
    public Aircraft()
    {
    length = 0; //feet
    wingSpan = 0; //feet
    emptyWeight = 0; //pounds
    maxVelocity = 0; //knots
    manufacturer = "[UNKNOWN]";
    model = "[AIRCRAFT}";
    name = manufacturer + " " + model;
    this.flight = null;
    }
    
    public Aircraft(int maxVelocity)
    {
    length = 176; //feet
    wingSpan = 147; //feet
    emptyWeight = 195120; //pounds
    this.maxVelocity = maxVelocity; //knots
    manufacturer = "Airbus";
    model = "A300";
    name = manufacturer + " " + model;
    this.flight = null;
    }
    
    public Aircraft(int maxVelocity, int length)
    {
    this.maxVelocity = maxVelocity; //knots
    this.length = length; //feet
    wingSpan = 147; //feet
    emptyWeight = 195120; //pounds
    manufacturer = "Airbus";
    model = "A300";
    name = manufacturer + " " + model;
    this.flight = null;
    }
    
    public Aircraft(int maxVelocity, int length, int wingSpan)
    {
    this.maxVelocity = maxVelocity; //knots    
    this.length = length; //feet
    this.wingSpan = wingSpan; //feet
    emptyWeight = 195120; //pounds
    manufacturer = "Airbus";
    model = "A300";
    name = manufacturer + " " + model;
    this.flight = null;
    }
    
    public Aircraft(int maxVelocity, int length, int wingSpan, int emptyWeight)
    {
    this.maxVelocity = maxVelocity; //knots
    this.length = length; //feet
    this.wingSpan = wingSpan; //feet
    this.emptyWeight = emptyWeight; //pounds
    manufacturer = "Airbus";
    model = "A300";
    name = manufacturer + " " + model;
    this.flight = null;
    }
    
    public Aircraft(String manufacturer, int maxVelocity, int length, int wingSpan, int emptyWeight)
    {
    this.maxVelocity = maxVelocity; //knots
    this.length = length; //feet
    this.wingSpan = wingSpan; //feet
    this.emptyWeight = emptyWeight; //pounds
    this.manufacturer = manufacturer;
    model = "[aircraft]";
    name = manufacturer + " " + model;
    this.flight = null;
    }
    
    public Aircraft(String manufacturer, String model, int maxVelocity, int length, int wingSpan, int emptyWeight)
    {
    this.maxVelocity = maxVelocity; //knots
    this.length = length; //feet
    this.wingSpan = wingSpan; //feet
    this.emptyWeight = emptyWeight; //pounds
    this.manufacturer = manufacturer;
    this.model = model;
    name = manufacturer + " " + model;
    this.flight = null;
    }
    //operations
    
}
