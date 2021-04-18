package application;



import java.util.*;

public class Chromosome {
    private final List<Genes> chromosome;

    private final double distance;



    public double getDistance() {

        return this.distance;

    }



    private Chromosome(final List<Genes> chromosome) {

        this.chromosome = Collections.unmodifiableList(chromosome);

        this.distance = calculateDistance();

    }



    static Chromosome create(final Genes[] g , Genes source , Genes target) {

        List<Genes> solution = new ArrayList<>();
       
         solution.add(source);
       if(source.getAdjecnt().equals(target)){
    	   solution.add(target);
           
       }else {
    	   
    	   Genes s=source;

           for (int i=0;i<30 ;i++)
           { if(s!=target || !solution.contains(s))
               for(int j=0;j<g.length;j++){
                   g[j].setUnVisited();

               }
               solution=new ArrayList<>();
               source.setVisited();
               while (!s.isAdjecntsVisited()) {
            	   solution.add(s);
                   s = s.getAdjecnt();
                   if (s.equals(target)) {
                      
                       solution.add(target);
                       return new Chromosome(solution);
                   } }
              
               s=source;
           }
       }
        return new Chromosome(solution);
    }


  
    

    @Override

    public String toString() {

        final StringBuilder builder = new StringBuilder();

        for(final Genes gene : this.chromosome) {

            builder.append(gene.toString()).append((" : "));

        }

        return builder.toString();

    }



    List<Genes> getChromosome() {

        return this.chromosome;

    }



    double calculateDistance() {
        double total = 0.0f;
        for(int i = 0; i < this.chromosome.size() - 1; i++) {
            total += this.chromosome.get(i).distance(this.chromosome.get(i+1));
        }
        return total;
    }

    int RandomIndex(){
        int size = chromosome.size()-1;
        		
        int random=(int)(Math.random()*size)+1;
        return random;

    }


    Chromosome[] crossOver(final Chromosome other) {
        int indexA;
        int indexB;
        List<Genes> firstCrossOver=null;
        List<Genes> secondCrossOver=null;
        List<Genes> first=null;
        List<Genes> second=null;
        Chromosome smaller ;
        Chromosome largest ;

        if (this.chromosome.size()<other.getChromosome().size()) {
        	 smaller=this;
             largest=other;
            }
            else {
                smaller = other;
                largest=this;
            }
            indexA=smaller.RandomIndex();

        Chromosome thisCr=new Chromosome(smaller.chromosome);
        Chromosome otherCrom=new Chromosome(largest.chromosome);

        int i=0;
        int numOfIterations=5*largest.chromosome.size();
        while ((!largest.getChromosome().contains(smaller.chromosome.get(indexA)))&&i<(numOfIterations)){
            indexA=smaller.RandomIndex();
            i++;
        }
        if(i>=numOfIterations-1){
            return null;
        }
        indexB=largest.chromosome.indexOf(smaller.chromosome.get(indexA));


        firstCrossOver = new ArrayList<>(thisCr.chromosome.subList(0, indexA));
        for (int j=indexB;j<otherCrom.getChromosome().size();j++)
                firstCrossOver.add(otherCrom.getChromosome().get(j));

        secondCrossOver = new ArrayList<>(otherCrom.getChromosome().subList(0, indexB));
        for (int j=indexA;j<thisCr.chromosome.size();j++)
                secondCrossOver.add(thisCr.chromosome.get(j));


        return new Chromosome[] {
                new Chromosome(firstCrossOver),
                new Chromosome(secondCrossOver)
        };
    }



    void mutate() {

  

    }




}
