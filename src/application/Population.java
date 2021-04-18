package application;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Population {
	
	
	
	
    private static List<Chromosome> population;

    private final int initialSize;

   static Genes start;
   static Genes end;


    Population(final Genes[] points,

                  final int initialSize) {

        this.population = init(points, initialSize);

        this.initialSize = initialSize;
        
        for(int i=0;i<points.length;i++){
            if(points[i].equals(start)){
                start=points[i];
            }if(points[i].equals(end)){
                end=points[i];
            }

        }
    


    }


    List<Chromosome> getPopulation() {

        return this.population;

    }



   static Chromosome getAlpha() {

        return population.get(0);

    }



    private List<Chromosome> init(final Genes[] points, final int initialSize) {

        final List<Chromosome> eden = new ArrayList<>();

        for(int i = 0; i < initialSize; i++) {


            final Chromosome chromosome = Chromosome.create(points,start,end);

            eden.add(chromosome);

        }

     


        return eden;

    }


    public void generateCromosoms(Genes startGene, Genes endGene , Genes[] data){
        Genes startNode ;
        for(Genes gene : data){
            if(startGene.equals(gene)){
                startNode=gene;
                break;
            }
        }


    }

    void update() {

        doCrossOver();

        doMutation();

        doSpawn();

        doSelection();

    }



    private void doSelection() {

        this.population.sort(Comparator.comparingDouble(Chromosome::getDistance));

        this.population = this.population.stream().limit(this.initialSize).collect(Collectors.toList());

    }



    private void doSpawn() {

        IntStream.range(0, 1000).forEach(e -> this.population.add(Chromosome.create(GenerateData.CITIES,start,end)));

    }



    private void doMutation() {

//        final List<TSPChromosome> newPopulation = new ArrayList<>();
//
//        for(int i = 0; i < this.population.size()/10; i++) {
//
//            final TSPChromosome mutation = this.population.get(TSPUtils.randomIndex(this.population.size())).mutate();
//
//            newPopulation.add(mutation);
//
//        }
//
//        this.population.addAll(newPopulation);

    }



    private void doCrossOver() {



        final List<Chromosome> newPopulation = new ArrayList<>();

        for(final Chromosome chromosome : this.population) {

            final Chromosome partner = getCrossOverPartner(chromosome);

            newPopulation.addAll(Arrays.asList(chromosome.crossOver(partner)));

        }

        this.population.addAll(newPopulation);

    }



    private Chromosome getCrossOverPartner(final Chromosome chromosome) {

        Chromosome partner = this.population.get(GenerateData.randomIndex(this.population.size()));

        while(chromosome == partner) {

            partner = this.population.get(GenerateData.randomIndex(this.population.size()));

        }

        return partner;

    }


}
