package game;

import edu.monash.fit2099.engine.*;

/**
 * SnipeAction returns a menu for player to choose between AimAction and AttackAction(to shoot.)
 */
public class SnipeAction extends Action {
    /**
     * Instances
     */
    private Actor target;
    private Menu subMenu = new Menu();
    private Display display = new Display();

    /**
     *Constructor
     *
     * @param thisTarget, the target to perform SnipeAction on.
     */
    public SnipeAction(Actor thisTarget){
        this.target = thisTarget;
    }

    /**
     * This method execute the SnipeAction and return a menu description
     * This method add AttackAction and AimAction (if aimCount is smaller than 2) into a subMenu for player to make decision between
     * whether to shoot or to aim.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a sub menu containing AttackAction and AimAction(if aimCounter is smaller than  2) for player to select from.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        SniperRifle sniper = ((Player)actor).getSniperRifle();
        sniper.setTarget(target);
        Actions actions = new Actions();
        actions.add(new AttackAction(target, ItemCapability.SNIPERRIFLE));
        if(sniper.getAimCounter() < 2){ // allow player to perform aim action only if aim count < 2
            actions.add(new AimAction(target, sniper.getAimCounter()));
        }
        return subMenu.showMenu(actor, actions, display).execute(actor, map);
    }

    /**
     * This method return a String description of SnipeAction,
     * @param actor The actor performing the action.
     * @return a string description of which target to snipe.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " snipes " + target;
    }
}
