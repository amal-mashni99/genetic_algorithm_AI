package application;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicInteger;

public class Main extends JPanel {
  


static int count =0;
    static final int WIDTH = 800;

    static final int HEIGHT = 1000;
   
    GenerateData utils;
    static JButton run = new JButton("Run");
    static JTextField source = new JTextField("Source");
    
    static JTextField distnation = new JTextField("Distnatin");
   
  static JComboBox s=new JComboBox();
  static JComboBox t=new JComboBox();
   static JPanel p = new JPanel(); 
    private Main() {
    	//source.setLocation(500, 25);
    	
    	//final Population population = null;
		try {
    		  utils=new GenerateData();
    	}
    	catch(FileNotFoundException e)
    	{
    		
    	}
		for (int i=0;i<GenerateData .CITIES.length;i++)
		{s.addItem(GenerateData .CITIES[i].getName());
		t.addItem(GenerateData .CITIES[i].getName());
		
		}

		setPreferredSize(new Dimension(WIDTH, HEIGHT));

       setBackground(Color.WHITE);
	 
   	
       run.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
            	Population.start=GenerateData.getGene(source.getText());
            		
                Population.end=GenerateData.getGene(distnation.getText());
                		
                
                Population  population = new Population(GenerateData.CITIES, GenerateData.CITIES.length*2);	 
            	
                 AtomicInteger generation = new AtomicInteger(0);
                 Timer timer = new Timer(500, (ActionEvent ) -> {

                   population.update();

                    repaint();

                });
            	
            	
                timer.start();
            }
        });
        add(run);


    

    }



    @Override

    public void paintComponent(final Graphics graphics) {

        super.paintComponent(graphics);

        final Graphics2D g = (Graphics2D) graphics;

        g.setColor(Color.BLACK);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

     

        g.drawString("shortest path : "

                +String.format("%.2f", Population.getAlpha().getDistance()), 500, 15);

        drawBestChromosome(g);

    }



    private void drawBestChromosome(final Graphics2D g) {

        final java.util.List<Genes> chromosome =Population.getAlpha().getChromosome();

        g.setColor(Color.BLACK);

        for(int i = 0; i < chromosome.size() - 1; i++) {

           Genes gene = chromosome.get(i);

            Genes neighbor = chromosome.get(i + 1);

            g.drawLine(gene.getX(), gene.getY(), neighbor.getX(), neighbor.getY());

        }

        g.setColor(Color.RED);

       for(int i=0;i<utils.CITIES.length;i++) {
    	  // JLabel lab1 = new JLabel(utils.CITIES[i].getName(), JLabel.LEFT);
    	   g.drawString(utils.CITIES[i].getName(), utils.CITIES[i].getX(),utils.CITIES[i].getY()-2);
    	  
         g.fillOval(utils.CITIES[i].getX(),utils.CITIES[i].getY(), 5, 5);
        //  g.setPaint(g.fillOval(utils.CITIES[i].getX(),utils.CITIES[i].getY(), 5, 5));

        }

    }

   
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
        	
            final JFrame frame = new JFrame();
          
         

    		// set Box Layout 
    		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS)); 
    		 
    		
    		p.add(run,BorderLayout.WEST);
    		p.add(source);
    		p.add(distnation ,BorderLayout.WEST);
    		
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            frame.setTitle("Genetic Algorithms");

            frame.setResizable(false);

         
                p.add(new Main(), BorderLayout.CENTER);
                frame.add(p);
      

            frame.pack();

            frame.setLocationRelativeTo(null);

            frame.setVisible(true);

        });

    }
}
