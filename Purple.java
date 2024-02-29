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
    * If it encounters a Spiral neighbor, it will die in next genration; 
    * otherwise, it will follow its own life principle.
    */
    public void act()
    {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        setNextState(false);// recheck its line position 
        
        for (Cell neighbor : neighbours)
        {
         
            
        if (neighbor instanceof Spiral) 
        {
            setNextState(false);
            neighbor.setNextState(false);
            
        }
        else
             
        
        { 
        /*
         * if the cell has 2 or less 
         * neighbours it live next generation
         */
            if (isAlive() && neighbours.size()<4) 
          {
                 setNextState(true);
          }
        /*
         * if a dead cell has exactly 2 
         * neighbours it lives next generation
         */
          if (!isAlive()&& neighbours.size()==4||neighbours.size()==3) 
            {
                 setNextState(true);
              
            }
        }
    }
  }
}