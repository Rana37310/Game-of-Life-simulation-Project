import javafx.scene.paint.Color; 
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * This class creats different patterns depending on user choice.
 * @author Rana Albedaiwi and Hatoon Fallatah
 */
public class pattern extends Simulator
{
    private final String[] PATTERN_NAMES = {"Bar","Dimond", "SpaceShip", "Glider"};
    private int numberPattern;
    private String patternName;
    private int count;
    private Random random ;

    /**
     * Constructor for objects of class pattern
     */
    public pattern(int number, String name)
    {
        super();
        numberPattern=number;
        patternName=name; 
        random =  new Random();
        reset();
        
    }
    
    /**
     * @overriding reset from Simulator. 
     **/
     public void reset() 
     {
        generation = 0;
        cells.clear();
        populate();
     }

    /**
     * @overriding populate from Simulator. 
     * This method populate the different pattrens depending on user input.
     */
    
      protected void populate() 
    {
        field.clear();
        defaultPopulat();
            
      if(patternName !=null )
     {
       if(patternName.equals(PATTERN_NAMES[0]))
        populateBar(numberPattern);
        else if(patternName.equals(PATTERN_NAMES[1]))
        populateDimond(numberPattern);
        else if (patternName.equals(PATTERN_NAMES[2]))
        populateSpaceShip(numberPattern);
        else if(patternName.equals(PATTERN_NAMES[3]))
        populateGilder(numberPattern);
      }
      
    }
    
    /**
     *returns a random row in from min to max 
     */
    private int getRandomRow(int min, int max)
    {   return random.nextInt(field.getDepth() - max - min + 1) + min; }  
    
    
    /**
     *returns a random column in from min to max 
     */
    private int getRandomCol(int min, int max)
    {return random.nextInt(field.getWidth() - max - min + 1) + min;}
    
    
       protected void defaultPopulat()
    {
    
     for (int row = 0; row < field.getDepth(); row++) {
        for (int col = 0; col < field.getWidth(); col++) {
          Location location = new Location(row, col);
          Mycoplasma myco = new Mycoplasma(field, location, Color.ORANGE);
            myco.setDead();
            cells.add(myco);
          }
        }
    }

  /**
    * Populate Mycoplasma object in a specific location to create Bar pattren. 
    */
  private void  populateBar(int num)
   {
      count=0;
     int randomRow = getRandomRow(1,1);
     int randomCol = getRandomCol(1,4);
    for(int i =0 ; i<4*num ; i++)
    {  Location  location = new Location(randomRow, randomCol+count);
        cells.add( new Mycoplasma(field, location , Color.ORANGE)) ;
        count++;
        
        if(count==4)
        {
        randomRow = getRandomRow(1,1);
        randomCol =getRandomCol(1,4);
        count=0;
        }
    }
   
   }

   
    /**
    * Populate Mycoplasma object in a specific location to create Dimond pattren. 
    */
  private void  populateDimond(int num)
   {
    count=0;

     int randomRow = getRandomRow(2,2); 
     int randomCol = getRandomCol(1,3);
     

    for(int i =0 ; i<3*num ; i++)
    {  Location  location = new Location(randomRow, randomCol+count);
        cells.add( new Mycoplasma(field, location , Color.ORANGE)) ;
       
        count++;
        
         if(count==1)
        {   
            Location  middle = new Location(randomRow-1, randomCol+count);
            cells.add( new Mycoplasma(field, middle , Color.ORANGE)) ;
        }
        if(count==3)
        {
        randomRow = getRandomRow(2,2); 
        randomCol = getRandomCol(1,3);
        count=0;
        }
    }
   }
   
    /**
    * Populate Mycoplasma object in a specific location to create SpaceShip pattren. 
    */
    private void populateSpaceShip(int num)
   {
       int count=0;
    Random r = new Random();
     int randomRow = getRandomRow(3,3);
     int randomCol =getRandomCol(3,3);
     
    List<Cell>  mycoList= new ArrayList<>();
    for(int i =0 ; i<3*num ; i++)
    {  Location  horizontalLocation = new Location(randomRow+count, randomCol);
        cells.add( new Mycoplasma(field, horizontalLocation , Color.ORANGE)) ;
        count++;
        if(count==3)
        {
            
          Location  a = new Location(randomRow+1, randomCol-2);
           Location  b = new Location(randomRow+2, randomCol-1);
           cells.add( new Mycoplasma(field, a , Color.ORANGE)) ;
           cells.add( new Mycoplasma(field, b , Color.ORANGE)) ;
          
        randomRow =getRandomRow(3,3);
        randomCol =getRandomCol(3,3);
        count=0;
        }
    }
  }
      
   /**
    * Populate Mycoplasma object in a specific location to create Gilder pattren. 
    */
  private void populateGilder(int num)
   {
      count=0;
     int randomRow =getRandomRow(3,3); 
     int randomCol =getRandomCol(3,3);
     
    List<Cell>  mycoList= new ArrayList<>();
    for(int i =0 ; i<3*num ; i++)
    {  Location  horizontalLocation = new Location(randomRow, randomCol+count);
        mycoList.add( new Mycoplasma(field, horizontalLocation , Color.ORANGE)) ;
        count++;
        if(count==3)
        {   
           Location  locationA = new Location(randomRow-2, randomCol+1);
           Location  locationB = new Location(randomRow-1, randomCol+2);
           cells.add( new Mycoplasma(field, locationA , Color.ORANGE)) ;
           cells.add( new Mycoplasma(field, locationB , Color.ORANGE)) ;
           randomRow =getRandomRow(3,3); 
           randomCol =getRandomCol(3,3);
           count=0;
        }
    }
      
   }
  

}
