
package kahonen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SOM {
    private final static Random rand = new Random();
    private final ArrayList<Neuron> neurons = new ArrayList<>(); //Arraylist of neurons
    private final int size; //number of neurons
    private double lr = 0.1; //learning rate
    private double lr0 = 0.1; //alpha0-original learning rate
    private double nb = 1; //neighborhood proximity
    private double nb0=1;
    
    public SOM(int size){
        this.size = size;
        
        for(int i=0; i<this.size; i++){
            double[] weights = {8*rand.nextDouble(), 8*rand.nextDouble()};
            Neuron neuron = new Neuron(weights);
            neurons.add(neuron);
        }
        
    }
    
    
    public void train(double[][] trainingData, int epochs, double lr, double nb, boolean lrDecay, boolean nbDecay){
        this.lr0=lr;
        this.lr = this.lr0;
        this.nb0=nb;
        this.nb=this.nb0;
        double tau= epochs/Math.log(nb0);
        int e = 1;
        
        while(e <= epochs){
            ArrayList<Integer> indices = new ArrayList<>();
            for(int i=0; i<trainingData.length; i++){
                indices.add(i);
            }
//            System.out.println("=============================");
//            System.out.println("Epoch "+e+": Learning Rate: "+this.lr+", Neighborhood Radius: "+this.nb);
//            System.out.println("=============================");
            while(!indices.isEmpty()){
                
                // choose random row
                int next = rand.nextInt(indices.size());
//                System.out.println("indices.size: "+indices.size()+" next: "+next+", training data[next]: "+Arrays.toString(trainingData[indices.get(next)]));
                double[] dataPoint = trainingData[indices.remove(next)];
                
                //System.out.println(Arrays.toString(dataPoint));
              
                // get winner and update weights
                updateWeights(this.neurons.get(winning(dataPoint)), true, dataPoint, this.lr);
                
                //update learning rate and nb radius
                if(lrDecay){
                    this.lr = this.lr0*Math.exp((double)-e/epochs);
                }
                if(nbDecay){
                    this.nb=this.nb0*Math.exp((double)-e/tau);
                }
            }
//            m
            e++;
        }
    }
    
    //return the index of the winning neuron
    public int winning(double[] point){
        int winner = 0;
        double minDist = this.neurons.get(winner).getDistance(point);
//        System.out.println("Neuron 0 Distance: "+minDist);
        for(int i = 1; i<this.neurons.size(); i++){
            if(this.neurons.get(i).getDistance(point)< minDist){
                winner = i;
                minDist = this.neurons.get(i).getDistance(point);
            }
//            System.out.println("Neuron "+i+" Distance: "+this.neurons.get(i).getDistance(point));
        }
//        System.out.println("Point: "+Arrays.toString(point)+", Winning neuron: "+winner+", "+Arrays.toString(this.neurons.get(winner).getWeights())+", Distance: "+this.neurons.get(winner).getDistance(point));
        return winner;
    }
    
    public ArrayList<Neuron> getNeighbors(Neuron n){
        ArrayList<Neuron> neighbors = new ArrayList<>();
        int index = this.neurons.indexOf(n);
        //add left neighbors
        int i = 1;
        while(i<=this.nb && index-i>=0){
            neighbors.add(this.neurons.get(index-i));
            i++;
        }
        //add right neighbors
        i = 1;
        while(i<=this.nb && index+i<this.neurons.size()){
            neighbors.add(this.neurons.get(index+i));
            i++;
        }
        
        return neighbors;
    }
    
    public void updateWeights(Neuron neuron, boolean isWinner, double[] point, double lr){
        for(int i=0; i<point.length;i++){ //for each component of input vector, adjust weight of neuron
            neuron.setSingleWeight(i, neuron.getSingleWeight(i)+lr*(point[i]-neuron.getSingleWeight(i)));
        }
        if(isWinner){ //if this is BMU, drag neighbors along
            dragNeighbors(neuron, point);
        }
    }
    
    public void dragNeighbors(Neuron neuron, double[] point){
        ArrayList<Neuron> neighbors = getNeighbors(neuron);
        for(int i = 0; i<neighbors.size(); i++){
            updateWeights(neighbors.get(i), false, point, this.lr*Math.exp(-(Math.pow(this.neurons.indexOf(neuron)-this.neurons.indexOf(neighbors.get(i)),2)/(2*Math.pow(nb,2)))));
        }
    }
    
    public double[][] getWeights(){
        double[][] weights = new double[this.neurons.size()][this.neurons.get(0).getWeights().length];
        for(int i = 0; i<this.neurons.size(); i++){
            weights[i]=this.neurons.get(i).getWeights();
        }
        return weights;
    }
    
    public void printWeights(){
        for(Neuron n: this.neurons){
            System.out.println(Arrays.toString(n.getWeights()));
        }
    }
    
}
