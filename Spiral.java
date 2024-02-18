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
        
        if (isAlive()) {
            if (neighbours.size() < 7 && neighbours.size()%2==0)   //even
                setNextState(true);
        }
        
        if (!isAlive()&& neighbours.size() < 7 && neighbours.size()%2==1) {  //odd and dead
                setNextState(true);
        }
        
        
        
    }
}
