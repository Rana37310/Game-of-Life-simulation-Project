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
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


/**
 * A graphical view of the simulation grid. The view displays a rectangle for
 * each location. Colors for each type of life form can be defined using the
 * setColor method.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2024.02.03
 */

public class SimulatorView extends Application {

    public static final int GRID_WIDTH = 20;
    public static final int GRID_HEIGHT = 20;    
    public static final int WIN_WIDTH = 650;
    public static final int WIN_HEIGHT = 650;
    
    private static final Color EMPTY_COLOR = Color.WHITE;

    private final String GENERATION_PREFIX = "Generation: ";
    private final String POPULATION_PREFIX = "Population: ";

    private Label genLabel, population, infoLabel;

    private FieldCanvas fieldCanvas;
    private FieldStats stats;
    private Simulator simulator;

    /**
     * Create a view of the given width and height.
     * @param height The simulation's height.
     * @param width  The simulation's width.
     */
    @Override
    public void start(Stage stage) {
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
  
    Button ShapeButton = new Button("Magic Shapes");
    Button SymbiosisButton = new Button("Symbiosis");
    
    ShapeButton.setOnAction(event -> {
      ShapeActionButton(stage);
    });

     SymbiosisButton.setOnAction(event -> {
     simulator = new Symbiosis();
                updateCanvas(simulator.getGeneration(), simulator.getField());
                stage.show();
    });
    
    VBox button2Box = new VBox();
    button2Box.getChildren().addAll(ShapeButton,SymbiosisButton);
    button2Box.setSpacing(10); // Set spacing between buttons

    // Create a new stage
    Stage newStage = new Stage();
    newStage.setTitle("Choose Simulator");

    // Create a scene with the VBox containing button2
    Scene button2Scene = new Scene(button2Box, 300, 100); // Set width and height as per your requirement

    // Set the scene to the new stage
    newStage.setScene(button2Scene);
    newStage.show();

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
   
}



   public void ShapeActionButton(Stage stage)
   {
         // Create a GridPane to organize the components of the dialog window
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Add a Label for Shape Name
        Label shapeLabel = new Label("Shape Name:");
        grid.add(shapeLabel, 0, 0);

        // Create a ChoiceBox for selecting the shape name
        ChoiceBox<String> shapeChoiceBox = new ChoiceBox<>();
        shapeChoiceBox.getItems().addAll("Bar", "Stair", "Diamond", "SpaceShip");
        shapeChoiceBox.setValue("Bar"); // Default selection
        grid.add(shapeChoiceBox, 1, 0);

        // Add a Label for Number of Shapes
        Label numberLabel = new Label("Number of Shapes:");
        grid.add(numberLabel, 0, 1);

        // Create a TextField for entering the number of shapes
        TextField numberField = new TextField("1");
        grid.add(numberField, 1, 1);

        // Create the dialog stage
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Input Dialog");

        // Add OK button to confirm the selection
        Button okButton = new Button("OK");
        okButton.setOnAction(e -> {
            // Parse the number of shapes from the TextField
            try {
                int number = Integer.parseInt(numberField.getText());
                String shapeName = shapeChoiceBox.getValue();

                // Create the shape with the provided number and shape name
                simulator = new Shape(number, shapeName);
                updateCanvas(simulator.getGeneration(), simulator.getField());
                stage.show();
                dialogStage.close();
            } catch (NumberFormatException ex) {
                // Handle invalid number format
                System.out.println("Invalid number format");
            }
        });

        // Add Cancel button to close the dialog without action
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> dialogStage.close());

        // Add buttons to a horizontal box
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(okButton, cancelButton);
        grid.add(buttonBox, 1, 2);

        // Create the scene and set it to the dialog stage
        Scene dialogScene = new Scene(grid, 400, 150);
        dialogStage.setScene(dialogScene);

        // Show the dialog stage
        dialogStage.show();

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
                simulator.delay(2000);
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
