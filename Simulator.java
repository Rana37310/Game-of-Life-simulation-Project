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
      /*for (int row = 0; row < field.getDepth(); row++) {
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
       */   
      //stable shapes
      /*
      //****
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
       
       
       for (int row = 0; row < field.getDepth(); row++) {
    for (int col = 0; col < field.getWidth(); col++) {
        Location location = new Location(row, col);
        Mycoplasma myco = new Mycoplasma(field, location, Color.ORANGE);

        // stable train
        if (row == 2 && (col == 3 || col == 4 || col == 5 || col == 6)) {
            cells.add(myco);
        }
        // repeated mashroom shape
      //  else if (row == 5 && col == 4 || row == 6 && (col == 3 || col == 4 || col == 5)) {
         //   cells.add(myco);
       // }
        // repeated tower shape
        else if (col == 16 && (row == 0 || row == 1 || row == 2)) {
            cells.add(myco);
        }
        //death shape
        else if ( (row == 5 && col==5) || (row==6 && col==6) || (row==7 && col==7) ) {
            cells.add(myco);
        }
        
        else if ( row >10 && rand.nextDouble() <= MYCOPLASMA_ALIVE_PROB) {
            cells.add(myco);
          }
        else {
            myco.setDead();
            cells.add(myco);
        }
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