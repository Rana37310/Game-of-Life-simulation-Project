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

public class Dialog {
    
    private final static String [] SHAPES_NAME= {"Bar", "Stair", "Diamond", "SpaceShip"};
    private static Shape createdShape;
    
    private String ShapeName;
    private int NumberOfShape;
    private boolean CheckOkClick;
    
    
    public Dialog()
    {
        ShapeName=SHAPES_NAME[0];
        NumberOfShape=1;
        CheckOkClick=false;
    }

    public void creatDialog(Stage stage) {
        
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
       shapeChoiceBox.getItems().addAll(SHAPES_NAME);
        shapeChoiceBox.setValue("Bar"); // Default selection
        grid.add(shapeChoiceBox, 1, 0);

        // Add a Label for Number of Shapes
        Label numberLabel = new Label("Number of Shapes:");
        grid.add(numberLabel, 0, 1);

        // Create a TextField for entering the number of shapes
        TextField numberField = new TextField("1");
        grid.add(numberField, 1, 1);
        
         //default 
        //createdShape =new Shape(1, "Bar"); 

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
                SimulatorView s = new SimulatorView();
                //setNameNumber(shapeName, number);
                setName(shapeName);
                setNumber(number);
               
               
                dialogStage.close();
                
                
            } catch (NumberFormatException ex) {
                // Handle invalid number format
                System.out.println("Invalid number format");
            }
        });
        

        
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
       
       // return new String [] {getName(),getNumber()};
    }
    
    public boolean IsOkClicked()
    {
        return CheckOkClick;
    }
    
    public void setName(String name)
    {
        ShapeName=name;
        
    }
    
     public void setNumber(int number)
    {
        NumberOfShape=number;
    }
    
     public String getName()
    {
        return ShapeName;
        
    }
    
     public int getNumber()
    {
        return NumberOfShape;
    }

}
