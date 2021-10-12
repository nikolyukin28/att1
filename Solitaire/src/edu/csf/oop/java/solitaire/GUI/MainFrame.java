package edu.csf.oop.java.solitaire.GUI;

import edu.csf.oop.java.solitaire.card.Suit;
import edu.csf.oop.java.solitaire.model.SuitStackManager;
import edu.csf.oop.java.solitaire.model.WorkingStackManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainFrame extends Application {

    private static final int WIDTH = 680;
    private static final int HEIGHT = 500;
    private static final String TITLE = "Solitaire";

    private DeckView deckView = new DeckView();
    private WasteView wasteView = new WasteView();
    private SuitStackView[] suitStacks = new SuitStackView[Suit.values().length];
    private WorkingStackView[] stacks = new WorkingStackView[WorkingStackManager.Workingstack.values().length];

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(TITLE);

        GridPane root = new GridPane();
        root.setStyle("-fx-background-color: green");
        root.add(deckView,0,0);
        root.add(wasteView,1,0);

        for(SuitStackManager.SuitStack index : SuitStackManager.SuitStack.values()) {
            suitStacks[index.ordinal()] = new SuitStackView();
            root.add(suitStacks[index.ordinal()], 3 + index.ordinal(), 0);

        }

        for(WorkingStackManager.Workingstack index : WorkingStackManager.Workingstack.values()) {
            stacks[index.ordinal()] = new WorkingStackView(index);
            root.add(stacks[index.ordinal()], index.ordinal(), 1);
        }

        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
    }
}
