package edu.csf.oop.java.solitaire.model;

import edu.csf.oop.java.solitaire.card.Card;
import edu.csf.oop.java.solitaire.card.Deck;

import java.util.Iterator;
import java.util.Stack;

public class WorkingStack implements Iterable<Card> {
    private final Stack<Card> workingStack = new Stack<>();

    public WorkingStack(Deck deck, int num) {
        for(int i = 0; i < num; i++) {
            workingStack.add(deck.draw());
        }
    }

    public void push(Card card) {
        workingStack.push(card);
    }

    public boolean isEmpty() {
        return workingStack.isEmpty();
    }

    public Card peek() {
        assert !workingStack.isEmpty();
        return workingStack.peek();
    }

    @Override
    public Iterator<Card> iterator() {
        return workingStack.iterator();
    }
}
