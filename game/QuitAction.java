package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class QuitAction extends Action {
    /**
     * Quit action. does what it says and allows player to quit the game.
     */

    /**
     * actor(player) quits the game.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a menu descriptor stating actor quits the game.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return menuDescription(actor);
    }

    /**
     * actor(player) quits the game.
     * @param actor The actor performing the action.
     * @return actor(player) quits the game.
     */
    @Override
    public String menuDescription(Actor actor) {
        return (actor + " quits the game.");
    }
}
