package game;

import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.*;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	Enum<ItemCapability>weaponType;

	/**
	 * Constructor.
	 *
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
		weaponType = ItemCapability.MELEEWEAPON;
	}

	/**
	 * Constructor (Overload)
	 * @param target the Actor to attack
	 * @param newWeaponType ItemCapability that specify the current weapon type.
	 */
	public AttackAction(Actor target, Enum<ItemCapability> newWeaponType) {
		this.target = target;
		this.weaponType = newWeaponType;
	}

	/**
	 *	Execute AttackAction
	 * get available weapon and tune damage and weapon based on actor type and weapon type.
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return the main attack logic of the system.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Weapon weapon;
		weapon = actor.getWeapon();
		int damage = weapon.damage();
		// player's logic
		if(actor.hasCapability(ZombieCapability.PLAYER)){
			if(weaponType == ItemCapability.SHOTGUN){
				weapon = ((Player)actor).getShotgun();
				damage = weapon.damage();

			}else if(weaponType == ItemCapability.SNIPERRIFLE){
				SniperRifle sniper = ((Player)actor).getSniperRifle();

				// create a copy of sniper rifle to output.
				SniperRifle copiedSniper = new SniperRifle(sniper);
				weapon = copiedSniper.asWeapon();
				damage = copiedSniper.damage();

				// minus ammo whenever shoots
				sniper.minusAmmo();

				// reset aim counter of sniper.
				sniper.resetCounter();
				sniper.resetTarget();
			}
		}
		return attackLogic(actor, map, weapon, damage);
	}

	/**
	 *  The main attack logic of attack action
	 *  In charge of actor's death, misses, item drop and attack
	 *
	 * @param actor actor that perform an AttackAction.
	 * @param map map that actor is currently on.
	 * @param weapon weapon that the actor is currently using.
	 * @param damage damage of the weapon.
	 * @return a string description of an AttackAction.
	 */
	private String attackLogic(Actor actor, GameMap map, Weapon weapon, int damage){
		if (miss(actor, weapon)) {
			return actor + " misses " + target + ".";
		}

		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		target.hurt(damage);

		// May drop limb if target is zombie (player actor)
		if (target.hasCapability(ZombieCapability.UNDEAD)){
			((Zombie)target).dropLeg(map);
			((Zombie)target).dropArm(map);
		}

		if (!target.isConscious()) {
			if(target.hasCapability(ZombieCapability.UNDEAD)){
				if (target.hasCapability(ZombieCapability.MAMBO)){
					List<Item> items = map.at(0,0).getItems();
					for (Item item:items){
						if (item.hasCapability(ItemCapability.TOTEM)){
							MamboTotem totem = (MamboTotem)item;
							totem.setKilled();
						}
					}
				}
				Item corpse = new PortableItem("dead " + target, '%');
				map.locationOf(target).addItem(corpse);
			}else{
				// When human died, drop a HumanCorpse object on the ground.
				map.locationOf(target).addItem(new HumanCorpse(target.toString()));
			}

			Actions dropActions = new Actions();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)
				drop.execute(target, map);
			map.removeActor(target);

			result += System.lineSeparator() + target + " is killed.";
		}
		return result;
	}


//	 * This method calculates the chance of an attack hitting another actor returning
//	 * a boolean indicating miss or not miss.
//	 *
//	 * @param actor Actor to calculate for
//	 * @param weapon Weapon used by the actor
//	 * @return miss, a boolean value indicating whether the attack miss or not (hit).
//	 */
	private boolean miss(Actor actor, Weapon weapon){
		boolean miss;
		if(actor.hasCapability(ZombieCapability.UNDEAD)){ // Zombie
			Zombie thisZombie = (Zombie) actor;
			if (thisZombie.getWeapon() instanceof IntrinsicWeapon){
				if(weapon.verb().equals("bites")){
					miss = rand.nextInt(100) > thisZombie.getBiteAccuracy(); // 80 % miss chance, due to 20 accuracy
					if(!miss){ // heal Zombie if Bite is successful.
						actor.heal(thisZombie.getBiteHealAmount());
					}
				}else{ // punch attack
					miss = rand.nextInt(100) > thisZombie.getPunchAccuracy(); // 30 % miss chance, due to 70 accuracy
				}
			}else{ // all other weapons have a 50 % chance to miss its target.
				miss = rand.nextBoolean();
			}
		}else{ //player
			Player player = (Player)actor;
			if(weapon instanceof Shotgun){
				miss = rand.nextInt(100) > ((Shotgun)weapon).getAccuracy(); // accuracy for shotgun
			}else if(weapon instanceof SniperRifle){
				miss = rand.nextInt(100) > ((SniperRifle)weapon).getAccuracy(); //accuracy of sniper rifle.
			} else{
				miss = rand.nextInt(100) > player.getAccuracy(); // default accuracy for human
			}
		}
		return miss;
	}


	/**
	 * Method that display a String description of an attack action.
	 * @param actor The actor performing the action.
	 * @return a String description of an attack action.
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}}
