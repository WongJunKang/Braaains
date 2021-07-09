package game;

import edu.monash.fit2099.engine.*;

import java.util.List;

/**
 * This class allows Human Object to pick up Food and Zombie to pick up WeaponItem.
 *
 */
public class PickUpItemBehaviour implements Behaviour {
    /**
     * Returns an PickUpItemAction that allows actor to pick up item directly below them.
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return PickUpItemAction(item) if there is an item to pick up, else return null.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        List<Item> itemOnGround = map.locationOf(actor).getItems();
        boolean isZombie = actor.hasCapability(ZombieCapability.UNDEAD);

        if (isZombie) {
            Zombie thisZombie = (Zombie) actor;
            // If zombie has no arm or already has a weapon then cannot pick up item
            if (thisZombie.getNoOfArm() == 0 || thisZombie.hasWeapon()) {
                return null;
            } else { // check if there are any weapon on ground
                for (Item item : itemOnGround) {
                    if (item.hasCapability(ItemCapability.MELEEWEAPON)) {
                        thisZombie.setHasWeapon(true);
                        return new PickUpItemAction(item);
                    }
                }
            }
        } else {  // human
            for (Item item : itemOnGround) {
                if (item.hasCapability(ItemCapability.FOOD)) {
                    return new PickUpItemAction(item);
                }
            }
        }
        return null;
    }

}
