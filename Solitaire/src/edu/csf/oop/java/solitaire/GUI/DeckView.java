package edu.csf.oop.java.solitaire.GUI;

import edu.csf.oop.java.solitaire.model.GameModel;
import edu.csf.oop.java.solitaire.model.GameModelListener;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class DeckView extends HBox implements GameModelListener {
    private static final String BUTTON_STYLE_NORMAL = "-fx-background-color: transparent; -fx-padding: 5, 5, 5, 5;";
    private static final String BUTTON_STYLE_PRESSED = "-fx-background-color: transparent; -fx-padding: 6, 4, 4, 6;";
    private static final int IMAGE_FONT_SIZE = 12;

    public DeckView() {
        GameModel.getInstance().reset();
        Button button = new Button();
        button.setGraphic(new ImageView(CardImages.getBack()));
        button.setStyle(BUTTON_STYLE_NORMAL);
        button.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ((Button) event.getSource()).setStyle(BUTTON_STYLE_PRESSED);
            }
        });
        button.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ((Button) event.getSource()).setStyle(BUTTON_STYLE_NORMAL);
                if(!GameModel.getInstance().canDraw(GameModel.CardDeck.DECK)) {
                    GameModel.getInstance().reset();
                } else {
                    GameModel.getInstance().getDeckMove().move();
                }
            }
        });

        getChildren().add(button);
        GameModel.getInstance().addListener(this);
    }

    @Override
    public void gameStateChanged() {
        if(!GameModel.getInstance().canDraw(GameModel.CardDeck.DECK)) {
            ((Button) getChildren().get(0)).setGraphic(createNewGameImage());
        } else {
            ((Button) getChildren().get(0)).setGraphic(new ImageView(CardImages.getBack()));
        }

    }

    private Canvas createNewGameImage() {
        double width = CardImages.getBack().getWidth();
        double height = CardImages.getBack().getHeight();
        Canvas canvas = new Canvas(width, height);
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.setTextAlign(TextAlignment.CENTER);
        context.setFill(Color.DARKKHAKI);
        context.setFont(Font.font(Font.getDefault().getName(), IMAGE_FONT_SIZE));
        context.fillText("Start again?", Math.round(width/2),IMAGE_FONT_SIZE);
        context.setStroke(Color.DARKGREEN);
        context.setLineWidth(10);
        context.strokeOval(width / 4, height / 2 - width / 4 + IMAGE_FONT_SIZE, width / 2, width / 2);
        return canvas;
    }
}
