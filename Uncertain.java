import javafx.scene.paint.Color; 
import java.util.List;
import java.util.Random;
/**
 * 
 * * @author Rana Albedaiwi and Hatoon Fallatah
 * 
 */
public class Uncertain extends Cell
{
    /**
     * Create a new Uncertain.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    
    // initializing the probability of each rule
    private static final double  RULE1_PROB = 0.25;
    private static final double RULE2_PROB = 0.5;
    private static final double RULE3_PROB = 0.75;

    public Uncertain(Field field, Location location, Color col) {
        super(field, location, col);
    }

    /**
    * In this method, the Uncertain cell decides if it's alive or not depending on its probability and rule
    */
    public void act()
    {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        setNextState(false);
        
        Random rand = Randomizer.getRandom();
        double random = rand.nextDouble();
 
          if (random <= RULE1_PROB)  
           rule1(neighbours);
 
          if (random > RULE1_PROB && random <=RULE2_PROB)  
           rule2(neighbours);
       
          if(random > RULE2_PROB && random <=RULE3_PROB)
          rule3(neighbours);
 
    }
    
    private void rule1(List<Cell> neighbours)
    {
        int size = neighbours.size();
         if (isAlive() && size==1||size==4) 
          setNextState(true);
        if (!isAlive()&& size==5 || size==7) 
           setNextState(true);
        
    }
    
    private void rule2(List<Cell> neighbours)
    {
        int size = neighbours.size();
            if (isAlive() && size%3==0) 
              setNextState(true);
            if (!isAlive()&& size<=3 ) 
              setNextState(true);
            
    }
    
    private void rule3(List<Cell> neighbours)
    {
         int size = neighbours.size();
          if (isAlive() && size%2==0) 
           setNextState(true);
          if (!isAlive()&& size%2==1) 
           setNextState(true);
            
    }
    
   }