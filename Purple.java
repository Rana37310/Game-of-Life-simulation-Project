import javafx.scene.paint.Color; 
import java.util.List;
/**
 *
 * @author Rana Albedaiwi and Hatoon Fallatah
 * 
 */
public class Purple extends Cell
{
    
    /**
     * Create a new Purple.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Purple(Field field, Location location, Color col) {
        super(field, location, col);
    }

    
    /**
    * This is how the Purple decides if it's alive or not
    * If it encounters a Spiral neighbor, both will die in next genration; 
    * otherwise, if the cell has 3 or less 
    * neighbours it live next generation
    * If a dead cell has exactly 3 or 4 
    * neighbours it lives next generation
    */
    public void act()
    {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        int size = neighbours.size();
        setNextState(false);
    
              if(!SpiralNeighborExist(neighbours))
              {
                if (isAlive() && size<4) 
                 setNextState(true);
                 else if (!isAlive()&& size==4||size==3) 
                 setNextState(true);
                  
              }       
    }

    /**
     * Check if there is a Spiral neighbour or not 
     * if found, set their next states
     * @return true if found, false otherwise.
     */
    private boolean SpiralNeighborExist(List<Cell> neighbours)
    {
         for (Cell neighbour : neighbours)
        {   
        if (neighbour instanceof Spiral)
        {
            setAll(false, false, neighbour);
            return true;
            
        }
    }
    return false;  
    }

}