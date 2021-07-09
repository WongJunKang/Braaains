package game;

import edu.monash.fit2099.engine.*;


/**
 * Shotgun Menu that allows player to choose which direction to fire the Shotgun.
 */
public class ShotgunMenu extends Action {
    /**
     * Constructor
     */
    Menu shotgunMenu = new Menu();
    Display shotgunMenuDisplay = new Display();

    /**
     *  Execute ShotgunMenu, iterate through all exits of actor(player) and add in ShotgunAction for all available directions.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a menu of available directions to fire Shotgun at (to perform a ShotgunAction).
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location curLocation = map.locationOf(actor);
        Actions actions = new Actions();
        for(Exit exit: curLocation.getExits()){
            actions.add(new ShotgunAction(exit.getName(), exit.getHotKey()));
        }
        return shotgunMenu.showMenu(actor, actions, shotgunMenuDisplay).execute(actor, map);
    }

    /**
     * This method return a String description of ShotgunMenu.
     *
     * @param actor The actor performing the action.
     * @return  a String description of ShotgunMenu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " fires Shotgun (Shotgun Menu)";
    }
}
