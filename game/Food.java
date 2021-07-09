package game;

import edu.monash.fit2099.engine.Action;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for food, an item that restores hitpoints.
 */
public class Food extends PortableItem {

    private int healAmount;

    /**
     * Constructor
     */
    public Food() {
        super("Banana", 'b');
        healAmount = 10;
        addCapability(ItemCapability.FOOD);
    }

    /**
     * returns allowable actions for this item.
     * @return allowable actions
     */
    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<>(allowableActions.getUnmodifiableActionList());
        if (this.hasCapability(ItemCapability.INVENTORY)){
            actions.add(new EatAction());
        }
        return actions;
    }

    /**
     * getter for heal amount
     * @return heal amount
     */
    public int getHealAmount(){
        return healAmount;
    }
}
