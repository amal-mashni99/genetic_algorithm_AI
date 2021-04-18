package application;



import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.IntStream;

public class GenerateData {
    private final static Random R = new Random(10000);
    static  Genes[] CITIES ;




    static int numOfVertexs=0;
    private static HashMap<String,Genes> nodes = new HashMap<>();
    static HashMap <Genes,LinkedList<Genes>> edges = new HashMap<>();


    public GenerateData() throws FileNotFoundException {

        CITIES = getVertexes();
        getEdges();
      
        

    }

 


   
    static Genes getGene (String name )
    {
        for (int i=0;i<CITIES.length;i++)
        {if (CITIES[i].getName().equalsIgnoreCase(name))
            return CITIES[i];
        }
        return null;
    }


    public static Genes[] getVertexes() throws FileNotFoundException {
      

      File file=new File("COO.txt");
        Scanner scan =new Scanner(file);
        int i=0;
        ArrayList arrayData=new ArrayList<Genes>();
        while (scan.hasNextLine()){
            String []str=scan.nextLine().split(" ");
            Genes v=new Genes(str[0],Integer.parseInt(str[1]),Integer.parseInt(str[2]));
                numOfVertexs++;
                nodes.put(v.getName(), v);
                arrayData.add(v);
            i++;
        }
        int numDataPoints=i;
        final Genes[] data =new Genes[numDataPoints];
        for (int i1=0;i1<arrayData.size();i1++){
            data[i1]=(Genes)arrayData.get(i1);
			//edges.put(data[i1], new LinkedList <Genes>());

        }

        return data;
    }
    public void getEdges() throws FileNotFoundException {
        
        File file=new File("cities.txt");
        Scanner scan =new Scanner(file);
        int i=0;
        while (scan.hasNextLine()) {
            String[] str = scan.nextLine().split(" ");
           
            //nodes.put(str[0],nodes.get(str[1]));
            Genes adjecent=nodes.get(str[1]);
            adjecent.incrementNumOfAdjFor();
            nodes.get(str[0]).getAdjacentNodes().put(adjecent, Double.parseDouble(str[2]));
        }


    }

    static int randomIndex(final int limit) {

        return R.nextInt(limit);

    }





}
