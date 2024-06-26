import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.Group; 
import javafx.scene.layout.BorderPane; 
import javafx.scene.layout.HBox; 
import javafx.scene.paint.Color; 
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


/**
 * A graphical view of the simulation grid. The view displays a rectangle for
 * each location. Colors for each type of life form can be defined using the
 * setColor method.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2024.02.03
 * @edited by Rana Albedaiwi and Hatoon Fallatah
 */

public class SimulatorView extends Application {

    public static final int GRID_WIDTH = 100;
    public static final int GRID_HEIGHT = 80;    
    public static final int WIN_WIDTH = 650;
    public static final int WIN_HEIGHT = 650;
    
    private static final Color EMPTY_COLOR = Color.WHITE;

    private final String GENERATION_PREFIX = "Generation: ";
    private final String POPULATION_PREFIX = "Population: ";

    private Label genLabel, population, infoLabel;

    private FieldCanvas fieldCanvas;
    private FieldStats stats;
    private Simulator simulator;

  
    @Override
     public void start(Stage stage) 
    {
        choosingSimulator(stage);
    }

     /**
     * Display a window to let the user choose the wanted simulater.
     */
    public void  choosingSimulator(Stage stage)
    {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10); 
        
        //Layout for to choose simulator 
        Label patternLabelChoice = new Label("Write the pattern you want: 1] Bar , 2] Dimond , 3] SpaceShip , 4] Glider");
        grid.add(patternLabelChoice, 0, 0);
    
        Label patternNameLabel = new Label("Pattern Name:");
        grid.add(patternNameLabel, 0, 1);
        
        Label numberLabel = new Label("Number of pattern:");
        grid.add(numberLabel, 0, 2);
        
        TextField nameField = new TextField("Bar");
        grid.add(nameField, 1, 1);
        
        TextField numberField = new TextField("1");
        grid.add(numberField, 1, 2);
            
        //Creating the needed buttons 
         Button okButton = new Button("OK");
         
        okButton.setOnAction(e -> {
        try {
                simulator = new pattern( Integer.parseInt(numberField.getText()), nameField.getText());
                showSimulator( stage,simulator);  
                
            } catch (NumberFormatException ex) {
                System.out.println("Invalid number format");
            }
        });
        
        grid.add(okButton, 1, 3);
        
        Button SymbiosisButton = new Button("Symbiosis");
        
          SymbiosisButton.setOnAction(event -> 
          {  simulator = new Symbiosis();
             showSimulator(stage,simulator); 
          }); 
        
        grid.add(SymbiosisButton, 0, 4);
    
        Scene scene = new Scene(grid, 800, 200);
        stage.setScene(scene);
        stage.setTitle("Choose Simulator");
                
        stage.show();
    
    } 
    
    /**
     * Display a window of simulater that user chose.
     */
   public void showSimulator(Stage stage,Simulator simulator )
   { 
        stats = new FieldStats();
        fieldCanvas = new FieldCanvas(WIN_WIDTH - 50, WIN_HEIGHT - 50);
        fieldCanvas.setScale(GRID_HEIGHT, GRID_WIDTH); 

        Group root = new Group();
        
        genLabel = new Label(GENERATION_PREFIX);
        infoLabel = new Label("  ");
        population = new Label(POPULATION_PREFIX);

        BorderPane bPane = new BorderPane(); 
        HBox infoPane = new HBox();
        HBox popPane = new HBox();
        

        infoPane.setSpacing(10);
        infoPane.getChildren().addAll(genLabel, infoLabel);       
        popPane.getChildren().addAll(population); 
        
        bPane.setTop(infoPane);
        bPane.setCenter(fieldCanvas);
        bPane.setBottom(population);
        
        root.getChildren().add(bPane);
        Scene scene = new Scene(root, WIN_WIDTH, WIN_HEIGHT); 
        
        stage.setScene(scene);          
        stage.setTitle("Life Simulation");
        updateCanvas(simulator.getGeneration(), simulator.getField());
        
        stage.show();    
   
    }
    
    /**
     * Display a short information label at the top of the window.
     */
    public void setInfoText(String text) {
        infoLabel.setText(text);
    }

    /**
     * Show the current status of the field.
     * @param generation The current generation.
     * @param field The field whose status is to be displayed.
     */
    public void updateCanvas(int generation, Field field) {
        genLabel.setText(GENERATION_PREFIX + generation);
        stats.reset();
        
        for (int row = 0; row < field.getDepth(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                Cell cell = field.getObjectAt(row, col);
        
                if (cell != null && cell.isAlive()) {
                    stats.incrementCount(cell.getClass());
                    fieldCanvas.drawMark(col, row, cell.getColor());
                }
                else {
                    fieldCanvas.drawMark(col, row, EMPTY_COLOR);
                }
            }
        }
        
        stats.countFinished();
        population.setText(POPULATION_PREFIX + stats.getPopulationDetails(field));
    }

    /**
     * Determine whether the simulation should continue to run.
     * @return true If there is more than one species alive.
     */
    public boolean isViable(Field field) {
        return stats.isViable(field);
    }

    /**
     * Run the simulation from its current state for the given number of
     * generations.  Stop before the given number of generations if the
     * simulation ceases to be viable.
     * @param numGenerations The number of generations to run for.
     */
    public void simulate(int numGenerations) {
        new Thread(() -> {
           
            for (int gen = 1; gen <= numGenerations; gen++) {
                simulator.simOneGeneration();    
                simulator.delay(500);
                Platform.runLater(() -> {
                    updateCanvas(simulator.getGeneration(), simulator.getField());
                });
            }
            
        }).start();
    }

    /**
     * Reset the simulation to a starting position.
     */
    public void reset() {
        simulator.reset();
        updateCanvas(simulator.getGeneration(), simulator.getField());
    }
    
    public static void main(String args[]){           
        launch(args);      
   } 
}
