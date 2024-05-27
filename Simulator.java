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
 * edited by Rana Albedaiwi and Hatoon Fallatah
 */

public  abstract class  Simulator {

    protected List<Cell> cells;
    protected Field field;
    protected int generation;


    /**
     * Construct a simulation field with default size.
     */
    
      public Simulator(int depth, int width) {
        cells = new ArrayList<>();
        field = new Field(depth, width);
        ///change
        reset();
    }

    public Simulator() {
        this(SimulatorView.GRID_HEIGHT, SimulatorView.GRID_WIDTH);
    }

    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
  
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
     * Reset implemented in subclasses. 
     */
    public abstract void reset() ;

    /**
     * populate implemented in subclasses. 
     */
    protected  abstract void populate() ;
    
    /**
    * Populate each location in the field with a dead Mycoplasma object.To avoid any null pointer exception.
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
