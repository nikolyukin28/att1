package edu.csf.oop.java.solitaire.model;

import edu.csf.oop.java.solitaire.card.Card;
import edu.csf.oop.java.solitaire.card.Deck;

import java.util.Stack;

public class WorkingStackManager {

    public enum Workingstack implements Location {
        StackOne, StackTwo, StackThree, StackFour, StackFive, StackSix, StackSeven
    }

    private final WorkingStack[] workingStacks = new WorkingStack[Workingstack.values().length];

    public WorkingStackManager(Deck deck) {
        for (int i = 0; i < workingStacks.length; i++) {
            workingStacks[i] = new WorkingStack(deck, Workingstack.StackOne.ordinal() + 1 + i);
        }
    }

    public Stack<Card> getWorkingStack(Workingstack index) {
        Stack<Card> stack = new Stack<>();
        for (Card card : this.workingStacks[index.ordinal()]) {
            stack.push(card);
        }
        return stack;
    }

    public void add(Card card, Workingstack index) {
        workingStacks[index.ordinal()].push(card);
    }

    public boolean canAdd(Card card, Workingstack index) {
        assert card != null;
        if (workingStacks[index.ordinal()].isEmpty()) {
            return true;
        } else {
            if (card.getSuit().ordinal() + workingStacks[index.ordinal()].peek().getSuit().ordinal() % 2 != 0) {
                if (card.getRank().ordinal() == workingStacks[index.ordinal()].peek().getRank().ordinal() - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
