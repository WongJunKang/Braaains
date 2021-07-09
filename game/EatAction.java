package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Allows humans to eat food and restore their hitpoints.
 * Requires food to be in inventory. (checks actor inventory for food only.)
 */

public class EatAction extends Action {
    /**
     *Searches for food object in actor's inventory. Removes food object and restores actor by a set amount
     * dictated within food object (healAmount).
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a descriptor for EatAction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        for (Item item : actor.getInventory()){
            if (item.hasCapability(ItemCapability.FOOD)){
                actor.removeItemFromInventory(item);
                actor.heal(((Food)item).getHealAmount());
                return menuDescription(actor);
            }
        }
        return null;
    }

    /**
     * descriptor for EatAction
     * @param actor The actor performing the action.
     * @return string stating actor restored health.
     */
    @Override
    public String menuDescription(Actor actor) {
        return (actor + " restored some health.");
    }
}
