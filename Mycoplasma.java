
import javafx.scene.paint.Color; 
import java.util.List;

/**
 * Simplest form of life.
 * Fun Fact: Mycoplasma are one of the simplest forms of life.  A type of
 * bacteria, they only have 500-1000 genes! For comparison, fruit flies have
 * about 14,000 genes.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * edited by Rana and Hatoon
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
    * If it encounters a Spiral neighbor, they will both live in next genration; 
    * if it encounters a Purple neighbor it will die and Purple will survive
    * otherwise, it will follow its own life principle.
    */
    public void act() 
    {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        int size = neighbours.size();
        setNextState(false);
        

          if(!spiralOrPurpleNeighborExist(neighbours))
          {
               if (isAlive() && size==2||size==3) 
                     setNextState(true);
               else if (!isAlive()&& size==3)
                     setNextState(true);   
          }
                   
                  
       }
    
     /**
     * Check if there is a Spiral or Purple neighbour or not 
     * if found, set their next state as follows 
     * @return true if found, false otherwise.
     */
    private boolean spiralOrPurpleNeighborExist(List<Cell> neighbours)
    {
         for (Cell neighbor : neighbours)
             { 
             if (neighbor instanceof Spiral) 
                {
                    setAll(true,true,neighbor);
                    return true;  
                }    
             else if(neighbor instanceof Purple)
             {
                 setAll(false,true,neighbor);
                 return true;
             }
               
            }
            return false;
        
    }
}
