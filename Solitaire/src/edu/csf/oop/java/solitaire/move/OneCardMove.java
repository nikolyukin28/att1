package edu.csf.oop.java.solitaire.move;

import edu.csf.oop.java.solitaire.model.GameModel;
import edu.csf.oop.java.solitaire.model.Location;

public class OneCardMove implements Move{

    private Location source;
    private Location destination;
    GameModel gameModel;

    public OneCardMove(Location source, Location destination, GameModel gameModel) {
        this.source = source;
        this.destination = destination;
        this.gameModel = gameModel;
    }

    @Override
    public boolean move() {
        boolean success = gameModel.move(source, destination);
        if (success) {
            return true;
        }
        return false;
    }
}
