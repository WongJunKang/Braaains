package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

public class ShotgunAction extends Action {
    /**
     * final constant
     */
    private static final int  SHOOT_RANGE = 3; // the shoot range

    /**
     * instances
     */
    private String direction;
    private String msg = "";
    private String hotkey;

    /**
     *Constructor
     * @param direction the direction in String to perform ShotgunAction at.e.g(North, South)
     * @param hotkey hotkey to display/ to trigger the action
     */
    public ShotgunAction(String direction, String hotkey){
        this.direction = direction;
        this.hotkey = hotkey;
    }

    /**
     * Override hotkey from a-z convention to number.
     * @return hotket of the direction
     */
    @Override
    public String hotkey() {
        return hotkey;
    }

    /**
     * Execute ShotgunAction
     * performs AttackAction on all actors within the shotgun range (in the cone)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return menu description of the snipe action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        processShotgun(actor, map, direction);
        ((Player)actor).getShotgun().minusAmmo(); // reduce ammo count of the shotgun.
        return menuDescription(actor);
    }

    /**
     * This method loads all actors within the shooting range of the shotgun of the selected direction into an array list.
     *
     * @param actor, the actor(player) who perform ShotgunAction.
     * @param map, the map that the
     * @param direction, a string indicating the direction to fire the shotgun at.
     */
    private void processShotgun(Actor actor, GameMap map, String direction){
        int x = map.locationOf(actor).x(); // current X coordinate of actor
        int y = map.locationOf(actor).y(); // current Y coordinate of actor

        int k = 1;  int b = 1; int a = 0;
        // even directions of hotkeys (North, East, South, West)
        int[]array = new int[2];
        if (Integer.parseInt(hotkey) % 2 == 0) {
            if(direction.equals("North") || direction.equals("West")){k = -1;} // By default South or East
            if(direction.equals("West") || direction.equals("East")){ a = 1; b = 0; }// By default North or South

            // loop from 1 to 4 (range of 3)
            for(int i=1; i <= SHOOT_RANGE; i++){
                for(int j=-i; j < i+1; j++){
                    array[0] = j;
                    array[1] = i * k;

                    // by default if direction is North or South a is 0 and b is 1, else swap.
                    fire(x + array[a], y + array[b], actor, map);
                }
            }
        }else{ // diagonal attacks (NorthEast, SouthEast, NorthWest, SouthWest)
            if(direction.equals("South-West") || direction.equals("North-West")){k = -1;}
            if(direction.equals("North-West") || direction.equals("North-East")){b = -1;}
            for(int i=0; i <= SHOOT_RANGE; i++){
                for(int j=0; j <= SHOOT_RANGE; j++){
                    fire(x + (i * k), y + (j * b), actor, map);
                }
            }
        }
    }

    /**
     *
     *  This method applies AttackAction on target actor if the location is within the map, and current location contains
     *  a valid target actor to be attacked.
     *
     * @param x x coordinate of target actor.
     * @param y y coordinate of target actor.
     * @param actor actor(player) who shoots sniper rifle.
     * @param map the map that actor is currently on.
     */
    private void fire(int x, int y, Actor actor, GameMap map){
        if(x >= map.getXRange().min() && x <= map.getXRange().max()
                && y >= map.getYRange().min() && y <= map.getYRange().max()){
            Location currLocation = map.at(x, y);
            if(currLocation.containsAnActor() && !currLocation.getActor().hasCapability(ZombieCapability.PLAYER)){
                Actor target = map.getActorAt(currLocation);
                msg +=  "\n" + new AttackAction(target, ItemCapability.SHOTGUN).execute(actor, map);
            }
        }
    }

    /**
     * Method that display a string description of a ShotgunAction.
     *
     * @param actor The actor performing the action.
     * @return a string description of ShotgunAction.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " shoots " + this.direction + " with a shotgun." + msg;
    }

}
