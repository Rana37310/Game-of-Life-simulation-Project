import javafx.scene.paint.Color; 
import java.util.List;
import java.util.Random;
/**
 * Write a description of class Uncertain here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Uncertain extends Cell
{
    /**
     * Create a new Mycoplasma.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Uncertain(Field field, Location location, Color col) {
        super(field, location, col);
    }

    /**
    * This is how the Mycoplasma decides if it's alive or not
    */
    public void act()
    {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        setNextState(false);
        
        Random rand = Randomizer.getRandom();
        double random = rand.nextDouble();
 
          if (random <= 0.25)  //prple act
          { // r< 0.25
        /*
         * if the cell has 2 or less 
         * neighbours it live next generation
         */
            if (isAlive() && neighbours.size()==1||neighbours.size()==4) 
          {
                 setNextState(true);
          }
        /*
         * if a dead cell has exactly 2 
         * neighbours it lives next generation
         */
          if (!isAlive()&& neighbours.size()==5 ||  neighbours.size()==7) 
            {
                 setNextState(true);
              
            }
        }

    
            
          
          if (random > 0.25 && random <=0.5 )  //myco act
          { 
              
             
            if (isAlive() && neighbours.size()%3==0) 
        {
                 setNextState(true);
                //setColor(Color.ORANGE);
        }
        
        if (!isAlive()&& neighbours.size()==1||neighbours.size()==2||neighbours.size()==3  ) 
            {
                 setNextState(true);
               // setColor(Color.ORANGE);
            }
        }
             
              if(random > .5 && random < .75)   // 0.5< rand <= 0.75 spiral
          {
        {
            /*
             * if the cell has even number of neibougrs and the size
             * is less than 7 it will live on next genration
             */
           if (isAlive() && neighbours.size()%2==0) 
           {
                 setNextState(true);
               // setColor(Color.GREEN);
        }
        
            /*
             * if bdead cell has odd number of neibougrs and the size
             * is less than 7 it will live again on next genration
             */
        if (!isAlive()&& neighbours.size()%2==1) //odd
            {
                 setNextState(true);
               // setColor(Color.GREEN);
            } 
        }
            
      
        }
      
        setColor(Color.BLUE);
    }
    
   }


