
package assmt5_dice;

import java.util.Random;

public class Die {
    private static Random randFValue = new Random();//declared to prevent repetition
    
// data
    private int numberSides;
    private int faceValue;
    public final int DEFAULT_SIDES = 6;
    private int shakes;
    
    // constructors
    public Die (){
        this.numberSides = DEFAULT_SIDES; 
        roll();
    }
    
   
    public Die ( int numberSides ){
        this.numberSides = numberSides;
        roll();
    }
    
    
    /*
     * The below constructor is included for 
     * testing of return values and added 
     * functionality of the Die object. Else-if 
     * ladder validates values and, if necessary,
     * forces values to be within die value range.
     */
    public Die ( int numberSides, int faceValue ){
        this.numberSides = numberSides;
        if ( faceValue <= numberSides && faceValue >= 1){
            this.faceValue = faceValue;
        }
        else if (faceValue < 1){
            this.faceValue = 1;
        }
        else if (faceValue > numberSides){
            this.faceValue = numberSides;
        }
        roll();
    }
    
    // operations
    public int roll() {
        faceValue = (1 + randFValue.nextInt( numberSides ));
        return faceValue;
    }
    
    public int roll(int shakes) {
        for ( ; shakes>1; --shakes ) {
            System.out.println("Shake!");
            faceValue = 1 + randFValue.nextInt( numberSides );
        }
        System.out.println("Shake!");
        faceValue = (1 + randFValue.nextInt( numberSides ));
        return faceValue;
    }
    
    public int getNumberSides() {
        return numberSides;
    }

    public int getFaceValue() {
        return faceValue;
    }



}//Class Die