import javafx.scene.paint.Color; 
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * This class populate different life forms depending on their probability.
 *
 * @author Rana Albedaiwi and Hatoon Fallatah
 */
public class Symbiosis extends Simulator
{

    private static final double MYCOPLASMA_ALIVE_PROB = 0.2;
    private static final double PURPLE_ALIVE_PROB = 0.4;
    private static final double SPIRAL_ALIVE_PROB = 0.6;
    private static final double UNCERTAIN_ALIVE_PROB =0.8;

    /**
     * Constructor for objects of class Symbiosis
     */
    public Symbiosis()
    {
        super();
    }
    
    
     public void reset() {
        generation = 0;
        cells.clear();
        populate();
    }
    
     protected void populate() 
     {
      Random rand = Randomizer.getRandom();
      field.clear();
      
       for (int row = 0; row < field.getDepth(); row++) {
        for (int col = 0; col < field.getWidth(); col++) {
            
          Location location = new Location(row, col);
          Mycoplasma myco = new Mycoplasma(field, location, Color.ORANGE);
          
          location = field.randomAdjacentLocation(location);
          Spiral spiral = new Spiral(field, location, Color.GREEN);
          
          location = field.randomAdjacentLocation(location);
          Purple purple = new Purple(field, location, Color.DARKORCHID);
          
          location = field.randomAdjacentLocation(location);
          Uncertain uncrtain = new Uncertain(field, location, Color.BLUE);
          
          double random = rand.nextDouble();
          
    
          if (random <= MYCOPLASMA_ALIVE_PROB)
           addCell(myco);
           else 
           destoryCell(myco);

          if (random > MYCOPLASMA_ALIVE_PROB && random<=PURPLE_ALIVE_PROB) 
            addCell(spiral);
          else
          destoryCell(spiral);
       
         if(random > PURPLE_ALIVE_PROB && random<=SPIRAL_ALIVE_PROB)
            addCell(purple);
           else
          destoryCell(purple);
          
         if(random> SPIRAL_ALIVE_PROB && random<=UNCERTAIN_ALIVE_PROB)
            addCell(uncrtain);
            else 
            destoryCell(uncrtain);
            
        }
    }
    }
    

      private void addCell(Cell cell)
    {
       cells.add(cell);
        
    }
    
    private void destoryCell(Cell cell)
    {
        cell.setDead();
        cells.add(cell);
    }
    
      protected void defaultPopulat()
    {
    
     for (int row = 0; row < field.getDepth(); row++) {
        for (int col = 0; col < field.getWidth(); col++) {
          Location location = new Location(row, col);
          Mycoplasma myco = new Mycoplasma(field, location, Color.BEIGE);
            myco.setDead();
            cells.add(myco);
          }
        }
       
    }
} 
