package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * This class just replaces an old item with a new item in the actor's inventory.
 */

public class CraftAction extends Action {
    protected Item oldItem; // item to be replaced by new item
    protected Item newItem; // item to replace the old item

    /**
     * Constructor
     * @param oldItem item to be replaced
     * @param newItem item to replace
     */
    public CraftAction(Item oldItem, Item newItem){
        this.oldItem = oldItem;
        this.newItem = newItem;
    }

    /**
     * This method removes the "oldItem" from the actor's (player) inventory and add in a "newItem".
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return menuDescription(actor), that shows a string description of the CraftAction performed.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeItemFromInventory(oldItem);
        actor.addItemToInventory(newItem);
        return menuDescription(actor);
    }

    /**
     * Method that display a string description of CraftAction.
     *
     * @param actor The actor performing the action.
     * @return a string description of CraftAction.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " crafts " + newItem.toString() + " from " + oldItem.toString();
    }
}
