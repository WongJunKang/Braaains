package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Base class for Actors in the Zombie World
 * @author ram
 *
 */
public abstract class ZombieActor extends Actor {
	private final int MAX_HITPOINTS;

	public ZombieActor(String name, char displayChar, int hitPoints, ZombieCapability team) {
		super(name, displayChar, hitPoints);
		MAX_HITPOINTS = hitPoints;
		addCapability(team);
	}
	
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions list = super.getAllowableActions(otherActor, direction, map);
		if (otherActor.hasCapability(ZombieCapability.UNDEAD) != this.hasCapability(ZombieCapability.UNDEAD))
			list.add(new AttackAction(this));
		return list;
	}

	/**
	 * getter for ZombitActor hitpoints
	 * @return current hit point of the actor.
	 */
	public int getHitPoints(){
		return this.hitPoints;
	}

	/**
	 * getter for maximum hit points of the actor
	 * @return the maximum hit points of current actor.
	 */
	public int getMaxHitPoints() {
		return MAX_HITPOINTS;
	}
}
