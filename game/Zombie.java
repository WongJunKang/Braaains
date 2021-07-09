package game;

import edu.monash.fit2099.engine.*;

import java.util.Objects;
import java.util.Random;

/**
 * A Zombie.
 *
 * @author ram
 *
 */
public class Zombie extends ZombieActor {
	private int noOfLeg;
	private int noOfArm;
	private static final int NO_OF_LEG = 2;
	private static final int NO_OF_ARM = 2;
	private static final int LIMB_DROP_RATE = 12; // arm and leg drop rate (add up to 24%)
	private static final int WEAPON_DROP_RATE = 50; // weapon drop rate over 100.
	private static final int PUNCH_RATE = 50; // 50 % chance of punching
	private static final int PUNCH_ACCURACY = 70;
	private static final int BITE_ACCURACY = 20;
	private static final int BITE_HEAL_AMOUNT = 5;
	private boolean hasWeapon;  // zombie either has 1 weapon, or dont have a weapon.


	/**
	 * A java array of behaviours.
	 */
	private Behaviour[] behaviours = {
//			new SpeechBehaviour(),
//			new AttackBehaviour(ZombieCapability.ALIVE),
//			new PickUpItemBehaviour(),
//			new HuntBehaviour(Human.class, 10),
//			new WanderBehaviour()
	};

	/**
	 * A java array of behaviours that does not involve any movements.
	 */
	private Behaviour[] noMovementBehaviours = {
//			new SpeechBehaviour(),
//			new AttackBehaviour(ZombieCapability.ALIVE),
//			new PickUpItemBehaviour()
	};

	/**
	 * Constructor
	 * @param name Zombie's name
	 */
	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
		noOfLeg = NO_OF_LEG;
		noOfArm = NO_OF_ARM;
		hasWeapon = false;
	}

	/**
	 * returns an intrinsic weapon based on return of punchOrBite
	 * @return an IntrinsicWeapon object.
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		boolean punch = punchOrBite(this.noOfArm);
		if(punch){
			return new IntrinsicWeapon(10, "punches");
		}else{
			return new IntrinsicWeapon(30, "bites");
		}
	}

	/**
	 * This method will decide whether the zombie will use a punch or a bite attack based on the number of arm a zombie
	 * currently has.
	 * @param noOfArm, number of arm of current Zombie in integer
	 * @return punch, a boolean indicate whether zombie punches or not(bite)
	 */
	private boolean punchOrBite(int noOfArm){
		Random random = new Random();
		// true indicate punch, false indicate bite
		boolean punch;
		if(noOfArm == 0){ // Zombie can only bite if it has no hand
			punch = false;
		}else if(noOfArm == 1){
			// There is a 25% chance to get bite attack if zombie has only 1 arm (half of original 50%)
			punch = random.nextInt(100) < (PUNCH_RATE/2);
		}else{ // There is a 50% chance for zombie to perform a bite or punch attack
			punch = random.nextInt(100) < PUNCH_RATE;
		}
		return punch;
	}

	/**
	 * If a Zombie can attack, it will.  If not, it will chase any human within 10 spaces.  
	 * If no humans are close enough it will wander randomly.
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Zombie is
	 * @param display the Display where the Zombie's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		Behaviour[] newBehaviours = CalculateBehaviour(lastAction);
		for (Behaviour behaviour : newBehaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}

	/**
	 * calculates allowable actions based on number of legs and last action of actor
	 * @param lastAction last action of actor
	 * @return a array of behaviours
	 */
	private Behaviour[] CalculateBehaviour(Action lastAction){
		// check if lastAction is move action.
		boolean isMoveAction = ((Objects.isNull(lastAction)) || lastAction.getClass().equals(MoveActorAction.class));

		// (if zombie has 2 leg) or (Zombie has 1 leg and last action is not a movement action)
 		if(this.noOfLeg == NO_OF_LEG || ((this.noOfArm == 1) && !(isMoveAction))){
			return behaviours;
		} else{
			return noMovementBehaviours;
		}
	}

	/**
	 * allows zombie to lose legs and creates zombie legs based on number of legs lost.
	 * @param map game map
	 */
	public void dropLeg(GameMap map){
		int drop = calculateLimbDropRate(this.noOfLeg);
		noOfLeg-= drop;
		for(int i=0; i < drop; i++){
			map.locationOf(this).addItem(new ZombieLimb("Zombie Leg", 'L', 30, "bonks", new ZombieClub()));
		}
	}

	/**
	 * allows zombie to lose arms and creates zombie arms based on number of arms lost.
	 * @param map game map
	 */
	public void dropArm(GameMap map){
		int drop = calculateLimbDropRate(this.noOfArm);
		noOfArm -= drop;
		for(int i=0; i < drop; i++){
			map.locationOf(this).addItem(new ZombieLimb("Zombie Arm", 'Q', 30, "slaps", new ZombieMace()));
		}
		// drop weapon
		dropWeapon(map);
	}


	/**
	 * calculates chance of zombie dropping held weapon based on number of arms.
	 * @param map game map
	 */
	private void dropWeapon(GameMap map) {
		Random random = new Random();
		// noOfArm will never be 2, when drop weapon is called.
		// if noOfArm = 0, random generator will always return 0, which is <= weapon drop rate.
		// if noOfArm = 1, random generator will return as normal.
		boolean dropWeapon = (random.nextInt(100) * noOfArm) <= WEAPON_DROP_RATE;
		// has 50% chance to drop weapon if the current zombie drop an arm (still has 1 arm)
		if (dropWeapon) {
			for (Item item : this.getInventory()) {
				if (item.hasCapability(ItemCapability.MELEEWEAPON)) {
					item.getDropAction().execute(this, map);
					hasWeapon = false;
					break;
				}
			}
		}
	}

	/**
	 * calculates number of limbs lost.
	 * @param limbCount number of limbs (either arms or legs, not both) the zombie has.
	 * @return number of limbs(either arms or legs, not both) lost by zombie
	 */
	private int calculateLimbDropRate(int limbCount){
		Random random = new Random();
		int dropLimb = 0;
		// will only increment when zombie still has limb
		for(int i=0; i < limbCount; i++){
			if(random.nextInt(100) <= LIMB_DROP_RATE){
				dropLimb++;
			}
		}
		return dropLimb;
	}


	/**
	 * getter for number of arms
	 * @return number of arms
	 */
	public int getNoOfArm(){
		return this.noOfArm;
	}

	/**
	 * getter for number of legs
	 * @return number of legs
	 */
	public int getNoOfLeg(){
		return this.noOfLeg;
	}

	/**
	 * getter for punch accuracy
	 * @return punch accuracy of zombie
	 */
	public int getPunchAccuracy(){
		return PUNCH_ACCURACY;
	}

	/**
	 * getter for bite accuracy
	 * @return bite accuracy of zombie
	 */
	public int getBiteAccuracy(){
		return BITE_ACCURACY;
	}

	/**
	 * getter for bite heal amount
	 * @return amount healed when zombie bites.
	 */
	public int getBiteHealAmount(){
		return BITE_HEAL_AMOUNT;
	}

	/**
	 * getter for if zombie has weapon.
	 * @return if zombie has weapon.
	 */
	public boolean hasWeapon(){
		return this.hasWeapon;
	}

	/**
	 * setter for if zombie has weapon
	 * @param hasWeapon if zombie has weapon
	 */
	public void setHasWeapon(boolean hasWeapon){
		this.hasWeapon = hasWeapon;
	}

}
