package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class DriveAction extends Action {
    /**
     * Class that allows actor to move to different maps
     */
    private GameMap targetMap;

    /**
     * constructor
     * @param moveTarget map actor will move to
     */
    public DriveAction(GameMap moveTarget) {
        targetMap = moveTarget;
    }

    /**
     * moves actor to the target map as listed in moveTarget
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a menudescriptor stating actor has moved to another map.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        targetMap.addActor(actor, targetMap.at(0,1));
        return menuDescription(actor);
    }


    /**
     * returns description for DriveAction.
     * @param actor The actor performing the action.
     * @return description for DriveAction.
     */
    @Override
    public String menuDescription(Actor actor) {
        return (actor + " moves to another map.");
    }
}
