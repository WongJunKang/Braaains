package game;

import edu.monash.fit2099.engine.*;

/**
 * Class to allow action for SniperRifle to perform aiming.
 */
public class AimAction extends Action {
    /**
     * Instances
     */
    private Actor target;
    private int aimCount;

    /**
     * Constructor
     * @param curTarget the target that player is currently aiming at.
     * @param currAimCount number of turn the player has been aiming the same target.
     */
    public AimAction(Actor curTarget, int currAimCount){
        this.target = curTarget;
        this.aimCount = currAimCount;
    }

    /**
     * Execute aim action.
     * The method increment the aim counter of SniperRifle and return a menu description of AimAction.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return menu description, a string description of AimAction.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        SniperRifle sniper = ((Player)actor).getSniperRifle();
        sniper.incrementAimCounter();
        return menuDescription(actor);
    }

    /**
     * This method returns a String description of AimAction.
     * @param actor The actor performing the action.
     * @return A string description of AimAction.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " aims sniper rifle at " + target + "(Aim Count: " + aimCount  + ")";
    }
}
