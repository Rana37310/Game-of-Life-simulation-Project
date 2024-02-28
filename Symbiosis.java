import javafx.scene.paint.Color; 
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Write a description of class Symbiosis here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Symbiosis extends Simulator
{
    // instance variables - replace the example below with your own
    private int x;

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
   /*   for (int row = 0; row < field.getDepth(); row++) {
        for (int col = 0; col < field.getWidth(); col++) {
          Location location = new Location(row, col);
          Mycoplasma myco = new Mycoplasma(field, location, Color.ORANGE);
          if (rand.nextDouble() <= MYCOPLASMA_ALIVE_PROB) {
            cells.add(myco);
          }
          else {
            myco.setDead();
            cells.add(myco);
          }
         
      */
        
  for (int row = 0; row < field.getDepth(); row++) {
    for (int col = 0; col < field.getWidth(); col++) {
        Location location = new Location(row, col);
        
            
            double random = rand.nextDouble();
            Purple myPur = new Purple(field, location, Color.DARKORCHID);
            
            location=field.randomAdjacentLocation(location);
            
            Mycoplasma myco = new Mycoplasma(field, location, Color.ORANGE);
           
            
            if (random <= 0.25) {
                cells.add(myco);
            } 
            else
            {
                  myco.setDead();
                cells.add(myco);
            }
            
            
          if (random > 0.25 && random <=0.5 )  {

                cells.add(myPur);
            }
             else {
              
                myPur.setDead();
                 cells.add(myPur);
                 
            }
            
        }
    }
}
}
