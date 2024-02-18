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
    public Spiral(Field field, Location location, Color col) {
        super(field, location, col);
    }

    public void act() {
        
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        setNextState(false);// recheck its line position 
        
        
        if(isAlive() && isBrown(getColor()))
        {
           setNextState(false);
        }
        
        /*
         * 
         */
        if (isAlive()) {
            if (neighbours.size() < 7 && neighbours.size()%2==0)   //even
                setNextState(true);
                setColor(Color.GREEN);
        }
        
        if (!isAlive() && neighbours.size() < 7 && neighbours.size()%2==1) {  //odd and dead
                setNextState(true);
                setColor(Color.GREEN);
        }
        
        /*
         * 
         */
        
        for (int i=0 ;i<neighbours.size(); i++)
        {
            Cell c = neighbours.get(i);
            if(c instanceof Mycoplasma)
            {
                setNextState(true);
                c.setNextState(true);
            }
            
            if(c instanceof Purple)
            {
               setColor(Color.RED);
                setNextState(false);
                c.setNextState(false);
            }
            
            
        }
        
        
    }
}
