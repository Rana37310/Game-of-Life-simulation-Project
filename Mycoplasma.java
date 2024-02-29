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
    * If it encounters a Spiral neighbor, it will die in next genration; 
    * otherwise, it will follow its own life principle.
    */
    public void act() {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        setNextState(false);// recheck its line position 
        
        
        for (Cell neighbor : neighbours)
        {
         
         if (neighbor instanceof Spiral) 
        {
            setNextState(true);
            
        }
        else if(neighbor instanceof Purple)
        {
            setNextState(false);
        }
        else
        {
            if (isAlive() && neighbours.size()==2||neighbours.size()==3) 
        {
                 setNextState(true);
                //setColor(Color.ORANGE);
        }
        
        if (!isAlive()&& neighbours.size()==3) 
            {
                 setNextState(true);
               // setColor(Color.ORANGE);
            }
        }
            
       }
    }
}
