package application;



import javafx.scene.shape.Circle;

import java.util.*;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Genes {
	
    private  int x=0;

    private  int y=0;

    Circle circle=new Circle(5);

    private String name;

    Map<Genes, Double> adjacentNodes = new HashMap<>();

    private static int numOfAdjFor=0;

    Boolean visited = false ;



    Genes(String name , final int x,

            final int y) {

        this.x = x;

        this.y = y;

        this.name=name;
        this.circle=  new Circle (x,y,3);
    	this.circle.setUserData(name);
    

    }


    public Genes() {
        this.name="no more";

    }

    @Override

    public String toString() {

        return "(" + this.name + "," + this.x + ", " + this.y+ ")";

    }


    public void incrementNumOfAdjFor(){
        numOfAdjFor++;
    }

    int getX() {

        return this.x;

    }



    int getY() {

        return this.y;

    }

    public Circle getCircle() {
        return circle;
    }

    public void setVisited(){
        visited=true;
    }

    public void setUnVisited(){
        visited=false;
    }
    public Boolean getVisited(){
        return visited;
    }
    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Genes, Double> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Genes, Double> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public static int getNumOfAdjFor() {
        return numOfAdjFor;
    }

    public static void setNumOfAdjFor(int numOfAdjFor) {
    	Genes.numOfAdjFor = numOfAdjFor;
    }

    double distance(final Genes other) {

            return adjacentNodes.get(other);

    }


    void addAdjecnt(Genes gene , double distance){
        adjacentNodes.put(gene,distance);

    }
   Genes getAdjecnt(){
        for(Genes gene : adjacentNodes.keySet()){
            if(gene.getNumOfAdjFor()==1&&gene.getAdjacentNodes().size()==1){
                gene.setVisited();
                return gene;
            }else if(gene.getNumOfAdjFor()==1){
                gene.setVisited();
                return gene;
            }
        }
        int size=adjacentNodes.size();
        double random=Math.random();
        int index=(int)(random*size);
        ArrayList<Genes> genes=new ArrayList<>();
        for(Genes indexGene : adjacentNodes.keySet()){
            genes.add(indexGene);
        }
        //printAdjecnts();
        while (!isAdjecntsVisited()){
            genes.get(index);
            if(!genes.get(index).getVisited()){
                genes.get(index).setVisited();
                return genes.get(index);
            }else{
                index=(int)(random*size);
            }
            random=Math.random();
        }

        return new Genes();
    }

    public Boolean isAdjecntsVisited(){
        for(Genes gene : adjacentNodes.keySet()){
            if(!gene.getVisited()){
                //return false if there an adjecent not visited
                return false;
            }
        }
        //return true if there no adjecnt not vivited
        return true ;
    }    @Override

    public boolean equals(final Object o) {


        final Genes gene = (Genes) o;

        return

                this.name.equals(gene.name);


    }

    public void printAdjecnts(){
        for (Map.Entry< Genes, Double> adjacencyPair: adjacentNodes.entrySet()){
            System.out.print(" "+adjacencyPair.getKey().name+" distance is "+adjacencyPair.getValue());
        }
    }


}
