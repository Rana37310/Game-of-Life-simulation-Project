import javafx.scene.paint.Color; 
import java.util.List;

/**
 * Simplest form of life.
 * Fun Fact: Mycoplasma are one of the simplest forms of life.  A type of
 * bacteria, they only have 500-1000 genes! For comparison, fruit flies have
 * about 14,000 genes.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2022.01.06
 */

public class Mycoplasma extends Cell {

    /**
     * Create a new Mycoplasma.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    
    
    public Mycoplasma(Field field, Location location, Color col) {
        super(field, location, col);
    }

    /**
    * This is how the Mycoplasma decides if it's alive or not
    */
    public void act() {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        setNextState(false);// recheck its line position 
    
        if (isAlive()) {
            if (neighbours.size()==2||neighbours.size()==3)
                setNextState(true);
        }
        
        if (!isAlive()&& neighbours.size()==3) {
                setNextState(true);
                //setColor(Color.GREEN);
        }
        int rows= this.getField().getDepth();
        int cols= this.getField().getWidth();
        
        
        if( this.getLocation().getRow()==rows-1|| this.getLocation().getRow()==0)
            setColor(Color.RED);
            
         if( this.getLocation().getCol()==0 || this.getLocation().getCol()==cols-1)
            setColor(Color.DARKORCHID);
            
    
        
      //  if ( this.getLocation().getRow()<10)
      //  {
     //       setNextState(false);
      //  }
        
    }
}