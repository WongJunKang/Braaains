package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
/**
 * Allows Humans to eat food to restore hitpoints.
 * checks if actor's hitpoints is smaller than maximum hitpoints, along with actor's inventory
 * for food object.
 */
public class EatBehaviour implements Behaviour {
    /**
     * checks for food item in inventory and if actor's hitpoints is smaller than maximum hitpoints
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return an EatAction if actor fulfils requirements or null otherwise
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (((ZombieActor)actor).getHitPoints() < ((ZombieActor)actor).getMaxHitPoints()){
            for (Item item: actor.getInventory()){
                if (item.hasCapability(ItemCapability.FOOD)){
                    return (new EatAction());
                }
            }
        }
        return null;
    }
}
