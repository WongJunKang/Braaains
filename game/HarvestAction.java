package game;

import edu.monash.fit2099.engine.*;

import javax.naming.spi.DirStateFactory;

/**
 * Class that allow actor to transform a crop into a food object.
 */
public class HarvestAction extends Action {
    private Location location;

    /**
     * Constructor
     *
     * @param newLocation, location to perform HarvestAction on.
     */
    public HarvestAction(Location newLocation){
        this.location = newLocation;
    }

    /**
     * Method that transforms a crop into food.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A String description of a HarvestAction.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // player adds food into inventory
        if(actor.hasCapability(ZombieCapability.PLAYER)){
            actor.addItemToInventory(new Food());
        }else{ // farmer adds food onto ground
            this.location.addItem(new Food());
        }
        this.location.setGround(new Dirt()); // set crop type ground, back into dirt().
        return menuDescription(actor);
    }


    /**
     * Method that return a String description of a harvest action.
     *
     * @param actor The actor performing the action.
     * @return A String description of a harvest action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " harvest a crop" ;
    }
}
