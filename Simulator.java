import javafx.scene.paint.Color; 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


/**
 * A Life (Game of Life) simulator, first described by British mathematician
 * John Horton Conway in 1970.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2024.02.03
 */

public class Simulator {

    private static final double MYCOPLASMA_ALIVE_PROB = .25;
    private List<Cell> cells;
    private Field field;
    private int generation;
    //private List<Cell> shape 

    /**
     * Construct a simulation field with default size.
     */
    public Simulator() {
        this(SimulatorView.GRID_HEIGHT, SimulatorView.GRID_WIDTH);
    }

    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width) {
        cells = new ArrayList<>();
        field = new Field(depth, width);
        reset();
    }

    /**
     * Run the simulation from its current state for a single generation.
     * Iterate over the whole field updating the state of each life form.
     */
    public void simOneGeneration() {
        generation++;
        for (Iterator<Cell> it = cells.iterator(); it.hasNext(); ) {
            Cell cell = it.next();
            cell.act();
        }

        for (Cell cell : cells) {
          cell.updateState();
        }
    }

    /**
     * Reset the simulation to a starting position.
     */
    public void reset() {
        generation = 0;
        cells.clear();
        populate();
    }

    /**
     * Randomly populate the field live/dead life forms
     */
    private void populate() 
    {
      Random rand = Randomizer.getRandom();
      field.clear();
   /*   for (int row = 0; row < field.getDepth(); row++) {
        for (int col = 0; col < field.getWidth(); col++) {
          Location location = new Location(row, col);
          Mycoplasma myco = new Mycoplasma(field, location, Color.ORANGE);
          if (rand.nextDouble() <= MYCOPLASMA_ALIVE_PROB) {
            cells.add(myco);
          }
          else {
            myco.setDead();
            cells.add(myco);
          }
         
      //stable shapes
      /*
      //****
      shape.repated();
        for (int row = 0; row < field.getDepth(); row++) {
         for (int col = 0; col < field.getWidth(); col++) 
         {
          Location location = new Location(row, col);
          Mycoplasma myco = new Mycoplasma(field, location, Color.ORANGE);
          if (row==2 && (col==3 || col==4||col==5||col==6))
          {
            cells.add(myco);
          }
          else
          {
             myco.setDead();
            cells.add(myco); 
          }
        }
    }
        //Reapeted shape
        for (int row = 0; row < field.getDepth(); row++) 
        {
         for (int col = 0; col < field.getWidth(); col++) 
         {
          Location location = new Location(row, col);
          Mycoplasma myco = new Mycoplasma(field, location, Color.ORANGE);
          if (row==5 && col==4 || row==6 &&(col==3 || col==4||col==5))
          {
            cells.add(myco);
          }
          else
          {
             myco.setDead();
            cells.add(myco); 
          }
          }
        }
    
        for (int row = 0; row < field.getDepth(); row++) 
        {
         for (int col = 0; col < field.getWidth(); col++) 
         {
          Location location = new Location(row, col);
          Mycoplasma myco = new Mycoplasma(field, location, Color.ORANGE);
          if (col==16 && (row==1|| row==2 || row==3))
          {
            cells.add(myco);
          }
          else
          {
             myco.setDead();
            cells.add(myco); 
          }
          }
        }
        
        */
       
       //
       
    
        
          /*
           *
       for (int row = 0; row < field.getDepth(); row++) {
        for (int col = 0; col < field.getWidth(); col++) {
          Location location = new Location(row, col);
          Mycoplasma myco = new Mycoplasma(field, location, Color.ORANGE);
           Purple myPur = new Purple(field, location, Color.DARKORCHID);
           
           
           List <Cell>  a = new ArrayList<>();
             a.add(new Mycoplasma(field, location, Color.ORANGE));
             a.add(new Purple(field, location, Color.DARKORCHID));
          
          if (row >10 && rand.nextDouble() <= MYCOPLASMA_ALIVE_PROB) {
              
            cells.add(myco);
          } 
           else {
            myco.setDead();
            cells.add(myco);
          }
            
         if ( rand.nextDouble() > MYCOPLASMA_ALIVE_PROB && rand.nextDouble() > 0.5)
          {
             
            cells.add(myPur);
          }
           else {
            myPur.setDead();
            cells.add(myPur);
          }
          
        }
    }
    */
  for (int row = 0; row < field.getDepth(); row++) {
    for (int col = 0; col < field.getWidth(); col++) {
        Location location = new Location(row, col);
        
            
            double random = rand.nextDouble();
            Purple myPur = new Purple(field, location, Color.DARKORCHID);
            
            location=field.randomAdjacentLocation(location);
            
            Mycoplasma myco = new Mycoplasma(field, location, Color.ORANGE);
           
            
            if (random == 0.20) {
                cells.add(myPur);
            } else if (random >= 0.20 && random<0.30)  {

                cells.add(myco);
            }
             else {
                myco.setDead();
                cells.add(myco);
                myPur.setDead();
                 cells.add(myPur);
                 
            }
            
        }
    }


    
     Random r = new Random();
     int randomRow = r.nextInt(field.getDepth());
     int randomCol = r.nextInt(field.getWidth()-3);

      List<Cell>  mycoList=GenerateMycoplasma(0); //
     // Location location = new Location(0, 1);
        
     
    
        // stable train

          for (int i = 0; i < mycoList.size() ; i++) {
           
           
            cells.add(mycoList.get(i));
          
        
        
       
      /*    else {
            myco.setDead();
            cells.add(myco);
        }
        */
       
    }
    
      
}

public List<Cell>  GenerateMycoplasma(int num)
{
    int count=0;
    Random r = new Random();
     int randomRow = r.nextInt(field.getDepth());
     int randomCol = r.nextInt(field.getWidth()-3);
     
    List<Cell>  mycoList= new ArrayList<>();
    for(int i =0 ; i<4*num ; i++)
    {  Location  location = new Location(randomRow, randomCol+count);
        mycoList.add( new Mycoplasma(field, location , Color.AQUA)) ;
        count++;
        if(count==4)
        {
        randomRow = r.nextInt(field.getDepth());
        randomCol = r.nextInt(field.getWidth()-3);
        count=0;
        }
    }
     return mycoList;
}



/*
public  void rpeatedShape()
{
    Random r = new Random();
    int randomRow = r.nextInt(field.getDepth()+1);//1
    
    for (int col = 0; col < field.getWidth(); col++) 
    {
        Location location = new Location(row, col);
        Mycoplasma myco = new Mycoplasma(field, location, Color.ORANGE);

        // stable train
        if (row == 0 && (col == 3 || col == 4 || col == 5 || col == 6)) {
            cells.add(myco);
        }
    }
    
    
}

    /**
     * Pause for a given time.
     * @param millisec  The time to pause for, in milliseconds
     */
public void delay(int millisec) 
{
        try {
            Thread.sleep(millisec);
        }
        catch (InterruptedException ie) {
            // wake up
        }
    }
    
    public Field getField() {
        return field;
    }

    public int getGeneration() {
        return generation;
    }
}
