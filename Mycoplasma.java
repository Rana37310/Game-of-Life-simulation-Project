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
    * This is how th e Mycoplasma decides if it's alive or not
    * If it encounters a Spiral neighbor, they will both live in next genration; 
    * if it encounters a Purple neighbor it will die and Purple will survive
    * otherwise, it will follow its own life principle.
    */
    public void act() {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        setNextState(false);
        
        for (Cell neighbor : neighbours)
        {
         
         if (neighbor instanceof Spiral) 
        {
            setNextState(true);
            neighbor.setNextState(true);
            
        }
        else if(neighbor instanceof Purple)
        {
            setNextState(false);
            neighbor.setNextState(true);
        }
        
        else
        {
            if (isAlive() && neighbours.size()==2||neighbours.size()==3) 
        {
                 setNextState(true);
        }
        
        else if (!isAlive()&& neighbours.size()==3) 
            {
                 setNextState(true);
            }
        }   
       }
    }
}
