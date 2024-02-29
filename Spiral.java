import javafx.scene.paint.Color; 
import java.util.List;

/**
 * Write a description of class Spiral here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
    * otherwise, it will follow its own life principle.
    */
    public void act() {
        
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        setNextState(false);// recheck its line position 
        
        for (Cell neighbor : neighbours)
        {
        
        
         if (neighbor instanceof Purple) 
        {
            setNextState(false);
            neighbor.setNextState(false);
        }
        
        
        else
        {
            /*
             * if the cell has even number of neibougrs and the size
             * is less than 7 it will live on next genration
             */
           if (isAlive() && neighbours.size() < 7 && neighbours.size()%2==0) 
           {
                 setNextState(true);
               // setColor(Color.GREEN);
        }
        
            /*
             * if dead cell has odd number of neibougrs and the size
             * is less than 7 it will live again on next genration
             */
        if (!isAlive() && neighbours.size() < 7 && neighbours.size()%2==1) //odd
            {
                 setNextState(true);
               // setColor(Color.GREEN);
            } 
        }
            
      }
    }  
    }

