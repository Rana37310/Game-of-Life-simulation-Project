 import javafx.scene.paint.Color; 
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Write a description of class Shapes here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Shape extends Simulator
{
    // instance variables - replace the example below with your own
    private int numberShape=1;
    private String shapeName;

    /**
     * Constructor for objects of class Shapes
     */
    public Shape(String name, int number)
    {
        super();
        numberShape=number;
        shapeName=name; 
        //change??
        reset();
    }
    
    public int getShapeNumber()
    {
        return numberShape;
    }
    
     public void reset() {
        generation = 0;
        cells.clear();
        populate();
    }

      protected void populate() 
    {
        field.clear();
         Random r = new Random();
     int randomRow = r.nextInt(field.getDepth());
     int randomCol = r.nextInt(field.getWidth()-3);
     
      // Location location = new Location(randomRow, randomCol);
      //  Virus myVir = new Virus(field, location, Color.RED);
       // cells.add(myVir);
       //creating cells to avoid null pointer exception
            for (int row = 0; row < field.getDepth(); row++) {
    for (int col = 0; col < field.getWidth(); col++) {
        Location location = new Location(row, col);
        Mycoplasma myco = new Mycoplasma(field, location, Color.ORANGE);
         myco.setDead();
          cells.add(myco);
        
        
    }
}

      List<Cell>  mycoList=GenerateMycoplasma(numberShape);
     // List<Cell>  mycoList=GenerateSpaceShip(numberShape);
    

          for (int i = 0; i < mycoList.size() ; i++) {
           
           
            cells.add(mycoList.get(i));
          
        }
        
   
    }
    


public List<Cell>  GenerateMycoplasma(int num)
{
    int count=0;
    Random r = new Random();
     int randomRow = r.nextInt(field.getDepth());
     int randomCol = r.nextInt(field.getWidth()-3);
     
    List<Cell>  mycoList= new ArrayList<>();
    for(int i =0 ; i<4*num ; i++)
    {  Location  location = new Location(randomRow, randomCol+count);
        mycoList.add( new Mycoplasma(field, location , Color.AQUA)) ;
        count++;
        if(count==4)
        {
        randomRow = r.nextInt(field.getDepth());
        randomCol = r.nextInt(field.getWidth()-3);
        count=0;
        }
    }
     return mycoList;
}


public List<Cell>  GenerateSpaceShip(int num)
{
    int count=0;
    Random r = new Random();
     int randomRow =3+r.nextInt(field.getDepth()-6+1);
     int randomCol =3+r.nextInt(field.getDepth()-6+1);
     
    List<Cell>  mycoList= new ArrayList<>();
    for(int i =0 ; i<3*num ; i++)
    {  Location  horizontalLocation = new Location(randomRow+count, randomCol);
        mycoList.add( new Mycoplasma(field, horizontalLocation , Color.AQUA)) ;
        count++;
        if(count==3)
        {
            
          Location  a = new Location(randomRow+1, randomCol-2);
           Location  b = new Location(randomRow+2, randomCol-1);
           mycoList.add( new Mycoplasma(field, a , Color.AQUA)) ;
           mycoList.add( new Mycoplasma(field, b , Color.AQUA)) ;
          
        randomRow =3+r.nextInt(field.getDepth()-6+1);
        randomCol =3+r.nextInt(field.getDepth()-6+1);
        count=0;
        }
    }
     return mycoList;
}







}
