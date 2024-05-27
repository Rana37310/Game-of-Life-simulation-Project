import javafx.scene.paint.Color; 
import java.util.List;

/**
 * 
 *  @author Rana Albedaiwi and Hatoon Fallatah
 *  
 */
public class Spiral extends Cell
{
    /**
     * Create a new Spiral.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Spiral(Field field, Location location, Color col) {
        super(field, location, col);
    }
    
    
    /**
    * This is how the Spiral decides if it's alive or not
    * If it encounters a Purple neighbor, it will die in next genration; 
    * otherwise, if the cell has even number of neibougrs and the size
    * is less than 7 it will live on next genration, if dead cell
    * has odd number of neibougrs and the size
    * is less than 7 it will live again on next genration
    */
    public void act() 
    {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        int size =  neighbours.size();
        setNextState(false);
        
        if(!PurpleNeighborExist(neighbours))
           {
            if (isAlive() && size < 7 && size %2==0) 
                 setNextState(true);
           else if (!isAlive() && size < 7 && size %2==1)
                 setNextState(true);
            }
         
      }
      
    /**
     * Check if there is a Purple neighbour or not 
     * if found, set their next states
     * @return true if found, false otherwise.
     */
      private boolean PurpleNeighborExist(List<Cell> neighbours )
      { 
           for (Cell neighbor : neighbours)
        {
         if (neighbor instanceof Purple)
         {
             setAll(false,false,neighbor);
             return true;
         }
        }
         return false;
        }
        
    }
        
