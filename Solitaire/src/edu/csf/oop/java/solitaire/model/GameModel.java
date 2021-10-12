package edu.csf.oop.java.solitaire.model;

import edu.csf.oop.java.solitaire.card.Card;
import edu.csf.oop.java.solitaire.card.Deck;
import edu.csf.oop.java.solitaire.move.Move;
import edu.csf.oop.java.solitaire.move.DeckMove;
import edu.csf.oop.java.solitaire.move.OneCardMove;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GameModel {
    private static final GameModel INSTANCE = new GameModel();
    private Deck deck = new Deck();
    private Stack<Card> discard;
    private List<GameModelListener> listenerList = new ArrayList<>();
    private WorkingStackManager workingStackManager;

    public enum CardDeck implements Location {
        DECK, DISCARD
    }

    public GameModel() {

    }

    public static GameModel getInstance() {
        return INSTANCE;
    }

    public void addListener(GameModelListener listener) {
        listenerList.add(listener);
    }

    private void notifyListener() {
        for (GameModelListener listener : listenerList) {
            listener.gameStateChanged();
        }
    }

    public Move getDeckMove() {
        return new DeckMove(getInstance());
    }

    public void reset() {
        deck.reset();
        discard = new Stack<Card>();
        workingStackManager = new WorkingStackManager(deck);
        notifyListener();
    }

    public boolean discard() {
        if (!this.deck.isEmpty()){
            discard.add(deck.draw());
            notifyListener();
            return true;
        }
        return false;
    }

    public Card peekDiscard() {
        if(discard.isEmpty()) {
            return null;
        }
        return discard.peek();
    }

    public boolean canDraw(Location source) {
        if (source.equals(CardDeck.DECK)) {
            if (!deck.isEmpty()) {
                return true;
            }
        }
        if (source.equals(CardDeck.DISCARD)) {
            if (!discard.isEmpty()) {
                return true;
            }

        }
        return false;
    }

    public Card[] getStack(WorkingStackManager.Workingstack index) {
        Card[] cards = new Card[workingStackManager.getWorkingStack(index).size()];
        for (int i = 0; i < workingStackManager.getWorkingStack(index).size(); i++) {
            cards[i] = workingStackManager.getWorkingStack(index).get(i);

        }
        return cards;
    }

    public boolean move(Location source, Location destination) {
        if (source.equals(CardDeck.DISCARD) && destination instanceof WorkingStackManager.Workingstack) {
            workingStackManager.add(discard.pop(), (WorkingStackManager.Workingstack) destination);
            notifyListener();
            return true;
        }

        return false;
    }

    public Move getCardMove(Card top, Location destination) {

        if (top.equals(peekDiscard())) {
            return new OneCardMove(CardDeck.DISCARD, destination, getInstance());
        }


        return null;
    }

    public boolean canAdd(Card top, Location destination) {
        if (destination instanceof WorkingStackManager.Workingstack) {
            if (workingStackManager.canAdd(top, (WorkingStackManager.Workingstack) destination)) {
                return true;
            }
        }
        return false;
    }
}
