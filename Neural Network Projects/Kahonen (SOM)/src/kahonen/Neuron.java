package kahonen;


public class Neuron {
    private double[] weights;
    
    public Neuron(){
        this.weights = new double[2];
    }
    
    public Neuron(double[] weights){
        this.weights = weights;
    }
    
    public double[] getWeights(){
        return this.weights;
    }
    
    public void setWeights(double[] otherWeights){
        this.weights = otherWeights;
    }
    
    public double getSingleWeight(int index){
        return this.weights[index];
    }
    
    public void setSingleWeight(int index, double weight){
        this.weights[index] = weight;
    }
    
    public double getDistance(double[] point){
        return Math.sqrt((Math.pow(point[0]-this.getSingleWeight(0), 2)) + (Math.pow(point[1]-this.getSingleWeight(1), 2)));
    }
    
    
    
}
