package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

import java.util.List;

public class LeaveAction extends Action {
    /**
     * Execurte the leave action of MamboMarie
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a menu description of the Action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        List<Item> items = map.at(0,0).getItems();
        for (Item item: items){
            if (item.hasCapability(ItemCapability.TOTEM)){
                MamboTotem totem = (MamboTotem)item;
                totem.resetSpawn();
            }
        }
        map.removeActor(actor);
        return menuDescription(actor);
    }

    /**
     * Return a String description of leave action.
     * @param actor The actor performing the action.
     * @return a String description of leave action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return (actor + " leaves the area.");
    }
}
