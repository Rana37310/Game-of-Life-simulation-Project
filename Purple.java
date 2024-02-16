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
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Purple
     */
    public Purple(Field field, Location location, Color col) {
        super(field, location, col);
    }

    
    public void act()
    {
        if(getField().getLivingNeighbours(getLocation()) !=null)
        {
           List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        setNextState(false);// recheck its line position 
    
        if (isAlive()) {
            if (neighbours.size()<=1||neighbours.size()==5)
                setNextState(true);
        }
        
        if (!isAlive()&& neighbours.size()==2) {
                setNextState(true);
                //setColor(Color.GREEN);
        }
    }
        
    }
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
}
