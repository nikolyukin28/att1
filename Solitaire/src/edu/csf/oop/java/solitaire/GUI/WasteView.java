package edu.csf.oop.java.solitaire.GUI;

import edu.csf.oop.java.solitaire.card.Card;
import edu.csf.oop.java.solitaire.model.GameModel;
import edu.csf.oop.java.solitaire.model.GameModelListener;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class WasteView extends HBox implements GameModelListener {

    private static final int PADDING = 5;

    public WasteView() {
        setPadding(new Insets(PADDING));
        ImageView image = new ImageView(CardImages.getBack());
        image.setVisible(false);
        getChildren().add(image);
        GameModel.getInstance().addListener(this);
    }

    @Override
    public void gameStateChanged() {
        if(!GameModel.getInstance().canDraw(GameModel.CardDeck.DISCARD)) {
            getChildren().get(0).setVisible(false);
        } else {
            getChildren().get(0).setVisible(true);
            Card topCard = GameModel.getInstance().peekDiscard();
            ImageView image = (ImageView) this.getChildren().get(0);
            image.setImage(CardImages.getImage(topCard));
        }
    }



}
