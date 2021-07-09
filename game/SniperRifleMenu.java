package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

/**
 * This class allows the user to access the sniper rifle menu, gives user access to snipe actions or a target to snipe
 * if the target is null.
 */
public class SniperRifleMenu extends Action {
    /**
     * Instances
     */
    private Menu subMenu = new Menu();
    private Display display = new Display();
    private Actor target;

    /**
     * The constructor for SniperRifle object
     * @param curTarget, current target that player aimed at.
     */
    public SniperRifleMenu(Actor curTarget){
        this.target = curTarget;
    }

    /**
     *This method loop through the entire map, and return a list of menu of actors that can be targeted by the SniperRifle instance,
     * if target is null, else, return SnipeAction and player can choose between aiming or sniping.
     *
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return submenu, a submenu showing all the actors if target is null or SnipeAction, another submenu
     * that allow user to select between aimAction or SnipeAction.
     */

    @Override
    public String execute(Actor actor, GameMap map) {
        // get boundary values.
        int minX = map.getXRange().min(),
                minY = map.getYRange().min(),
                maxX = map.getXRange().max(),
                maxY = map.getYRange().max();

        ArrayList<Actor> actors = new ArrayList<>();
        Actions actions = new Actions();


        if (target == null) { // if no target (haven't aim)
            // If no target: Scan for target
            for (int x = minX; x <= maxX; x++) {
                for (int y = minY; y <= maxY; y++) {
                    Location currLocation = map.at(x, y);

                    if (currLocation.containsAnActor()) {
                        Actor currActor = map.getActorAt(currLocation);
                        // add only if its Zombie/ Mambo marie
                        if (currActor.hasCapability(ZombieCapability.UNDEAD)) {
                            //actors.add(currActor);
                            actions.add(new SnipeAction(currActor));

                        }
                    }
                }
            }
            return subMenu.showMenu(actor, actions, display).execute(actor, map);
        }
        return new SnipeAction(target).execute(actor, map);
    }

    /**
     *This method returns the string description of current allowable action.
     *
     * @param actor The actor performing the action.
     * @return String description based on the status of the sniper rifle.
     */
    @Override
    public String menuDescription(Actor actor) {
        if(target == null){
            return actor + " fires sniper rifle (Sniper Menu)";
        }else{
            return actor + " attacks or continues to aim with sniper rifle";
        }
    }
}
