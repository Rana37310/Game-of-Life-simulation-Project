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
   
     
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
       // neighbours=getPurpleNeighbours(neighbours);
        setNextState(false);// recheck its line position 
        if(isAlive() && isBrown(getColor()))
        {
           setNextState(false);
        }
        if (isAlive()) {
            if (neighbours.size()==1||neighbours.size()==2)
                setNextState(true);
        }
        
        if (!isAlive()&& neighbours.size()==2) {
                setNextState(true);
        }
        
       for (int i=0 ;i<neighbours.size(); i++)
        {
            Cell c = neighbours.get(i);
            if(c instanceof Mycoplasma)
            {
                setNextState(true);
                c.setNextState(false);
            }
            
            if(c instanceof Spiral)
            {
               //setColor(Color.RED);
                setNextState(false);
                c.setNextState(false);
            }
         } 
    }
    
    public List<Cell> getPurpleNeighbours(  List<Cell> neighbours)
    {
        
        for( int i=0; i<neighbours.size() ;i++)
        {
           Cell cell = neighbours.get(i);
            if (!(cell instanceof Mycoplasma)) 
            {
            neighbours.remove(cell);
        }
            
        }
        return neighbours;
        
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