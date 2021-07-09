package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Class allow for action that reload ammo for ranged weapon
 */
public class ReloadAction extends Action {
    /**
     * instances
     */
    private int ammoCount;
    private Item ammo;
    private Enum<ItemCapability> weaponType;
    private String weaponName;

    /**
     * Constructor
     * @param ammoCount number of ammo to reload.
     * @param ammo the ammo object
     * @param thisWeaponType the type of weapon to reload ammo at.
     * @param weaponName the name of the weapon to be reloaded.
     */
    public ReloadAction(int ammoCount, Item ammo, Enum<ItemCapability> thisWeaponType, String weaponName){
        this.ammoCount = ammoCount;
        this.ammo = ammo;
        this.weaponType = thisWeaponType;
        this.weaponName = weaponName;
    }

    /**
     * Execute ReloadAction
     * This method reloads ammo for a specific weapon based on provided weapon type.
     * get weapon and add ammo based on ammoCount.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String description of ReloadAction.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // only player can reload
        Player player = (Player)actor;
        if(weaponType == ItemCapability.SHOTGUN){
            player.getShotgun().addAmmo(ammoCount);
        }else{ // sniper rifle
            player.getSniperRifle().addAmmo(ammoCount);
        }
        actor.removeItemFromInventory(ammo);// remove reloaded ammo from inventory
        return menuDescription(actor);


    }

    /**
     * Method that display a string description of a ReloadAction.
     *
     * @param actor The actor performing the action.
     * @return a string description of ReloadAction
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " loads " + ammoCount + " ammo into " + weaponName;
    }

}
