package game;

import edu.monash.fit2099.engine.*;

/**
 * Class representing the Player.
 */
public class Player extends Human {

	private Menu menu = new Menu();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(ZombieCapability.PLAYER);
	}

	/**Execute player play turn
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return a menu that shows all allowable actions of player
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		actions.add(new QuitAction());
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Override to add enum ItemCapability.INVENTORY
	 * @param item The Item to add.
	 */
	@Override
	public void addItemToInventory(Item item) {
		item.addCapability(ItemCapability.INVENTORY);
		inventory.add(item);
	}

	/**
	 *  Override to remove enum ItemCapability.INVENTORY
	 * @param item The Item to remove.
	 */
	@Override
	public void removeItemFromInventory(Item item) {
		item.removeCapability(ItemCapability.INVENTORY);
		inventory.remove(item);
	}

	/**
	 * getter for weapon
	 * @return intrinsic weapon or weapon
	 */
	@Override
	public Weapon getWeapon() {
		for(Item item: inventory){
			// getWeapon only gets melee weapon (without ammo), so player can still attack with weapon like ZombieMace
			// or plank after the gun has no ammo.
			if(item.asWeapon() != null && (item.hasCapability(ItemCapability.MELEEWEAPON))){
				return item.asWeapon();
			}
		}
		return getIntrinsicWeapon();
	}

	/**
	 * getter for shotgun
	 * @return a shotgun or null if dont have a shotgun
	 */
	public Shotgun getShotgun(){
		for(Item item: inventory){
			if(item.hasCapability(ItemCapability.SHOTGUN)){
				return (Shotgun)item;
			}
		}
		return null;
	}

	/**
	 * getter of SniperRifle
	 * @return  a SniperRifle or null if dont have a SniperRifle
	 */
	public SniperRifle getSniperRifle(){
		for(Item item: inventory){
			if(item.hasCapability(ItemCapability.SNIPERRIFLE)){
				return (SniperRifle)item;
			}
		}
		return null;
	}

	/**
	 * override to reset concentration when player get attacked.
	 * @param points number of hit points to deduct.
	 */
	@Override
	public void hurt(int points) {
		super.hurt(points);
		if(this.hasCapability(ItemCapability.SNIPERRIFLE)){ // only player can obtain sniper rifle.
			this.getSniperRifle().resetTarget(); // loses concentration when player get attacked.
			this.getSniperRifle().resetCounter();
		}
	}
}
