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

    private static final double MYCOPLASMA_ALIVE_PROB = 0.25;
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
    private void populate() {
      Random rand = Randomizer.getRandom();
      field.clear();
      for (int row = 0; row < field.getDepth(); row++) {
        for (int col = 0; col < field.getWidth(); col++) {
            
          Location location = new Location(row, col);
          Mycoplasma myco = new Mycoplasma(field, location, Color.ORANGE);
          
          location = field.randomAdjacentLocation(location);
          Spiral spir = new Spiral(field, location, Color.GREEN);
          
          location = field.randomAdjacentLocation(location);
          Purple myPur = new Purple(field, location, Color.DARKORCHID);
          

          
          double random = rand.nextDouble();
          
          if (random <= MYCOPLASMA_ALIVE_PROB) { // r< 0.25
            cells.add(myco);
          }
          else {
            myco.setDead();
            cells.add(myco);
          }
          
          if (random > 0.25 && random <=0.5 )  { 

                cells.add(myPur);
            }
             else {
              
                myPur.setDead();
                 cells.add(myPur);
                 
            }
          
          if(random > .5 && random < .75)   // 0.5< rand <= 0.75
          {
             cells.add(spir); 
          }
          else {
            spir.setDead();
            cells.add(spir);
          }
        }
        
        /*
         * if (row > 10 && rand.nextDouble() <= MYCOPLASMA_ALIVE_PROB) {
                Mycoplasma myco = new Mycoplasma(field, location, Color.ORANGE);
                cells.add(myco);
            } else if (rand.nextDouble() <= 0.75 && rand.nextDouble() > 0.5) {
                Spir spir = new Spir(field, location, Color.GREEN);
                cells.add(spir);
            } else {
                Spir spir = new Spir(field, location, Color.BLUE);
                spir.setDead();
                cells.add(spir);
            }
        }
         */
        
    }
    }

    /**
     * Pause for a given time.
     * @param millisec  The time to pause for, in milliseconds
     */
    public void delay(int millisec) {
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
