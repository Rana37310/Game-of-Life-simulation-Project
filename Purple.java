import javafx.scene.paint.Color; 
import java.util.List;
/**
 * Write a description of class Purple here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
        setNextState(false);
        
        for (Cell neighbor : neighbours)
        {   
        if (neighbor instanceof Spiral) 
        {
            setNextState(false);
            neighbor.setNextState(false);
            
        }
        else
        { 
            if (isAlive() && neighbours.size()<4) 
          {
                 setNextState(true);
          }
          
          else if (!isAlive()&& neighbours.size()==4||neighbours.size()==3) 
            {
                 setNextState(true);
              
            }
        }
    }
  }
}