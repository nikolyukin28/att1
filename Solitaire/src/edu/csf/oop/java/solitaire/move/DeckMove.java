package edu.csf.oop.java.solitaire.move;

import edu.csf.oop.java.solitaire.model.GameModel;

public class DeckMove implements Move {

    GameModel gameModel;

    public DeckMove(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public boolean move() {
        if(gameModel.discard()) {

        }
        return false;
    }
}
