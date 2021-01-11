
package assmt5_dice;


public class TwoDice {
    
    //data
    private Die dieOne;
    private Die dieTwo;
    
    // constructors
    public TwoDice (){ //DEFAULT_SIDES = 6 (standard dice)
        dieOne = new Die();
        dieTwo = new Die();
    }
    
    public TwoDice ( int numberSides ){ // dice with same # sides
        dieOne = new Die( numberSides );
        dieTwo = new Die( numberSides );
    }
    
    public TwoDice ( int numberSides1, int numberSides2 ){ // dice with different # sides
        dieOne = new Die( numberSides1 );
        dieTwo = new Die( numberSides2 );
    }
    
    // operations
    public int roll() {
        int sumDice = dieOne.roll() + dieTwo.roll();
        return sumDice;
    }
    
    public int roll(int shakes) {
        int sumDice;
        if (shakes%2 == 1){
            System.out.println("Shake!");
        }
        sumDice = dieOne.roll(shakes/2) + dieTwo.roll(shakes/2);
        return sumDice;
    }
    
    public int getFaceValue1(){
        return dieOne.getFaceValue();
    }
    
    public int getFaceValue2(){
        return dieTwo.getFaceValue();
    }

    public int Sum(){
        return dieOne.getFaceValue() + dieTwo.getFaceValue();
    }
}

