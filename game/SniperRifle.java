package game;

import edu.monash.fit2099.engine.*;

import java.util.List;

/**
 * Class to create a SniperRifle instance
 * A SniperRifle is a weapon that can be used by the player.
 * This weapon requires ammo to fire
 */
public class SniperRifle extends RangedWeapon{
    public static final String WEAPON_NAME = "Sniper Rifle";
    /**
     * final constant
     */
    private static final int DEFAULT_DAMAGE = 45;
    private static final int DEFAULT_ACCURACY = 75;
    private static final int AIM_ACCURACY_1 = 90;    // accuracy when aim once
    private static final int AIM_ACCURACY_2 = 100;   // accuracy when aim twice
    private static final int INSTAKILL = Integer.MAX_VALUE;

    /**
     * Instances
     */
    private int aimCounter;
    private int turnCounter;
    private Actor target = null;


    /**
     * Default constructor
     */
    public SniperRifle() {
        super(WEAPON_NAME, 'R', DEFAULT_DAMAGE, DEFAULT_ACCURACY, "Pew", 2); // set initial ammo count to 2
        addCapability(ItemCapability.SNIPERRIFLE);
        this.aimCounter = 0;
        this.turnCounter = 0;
    }

    /**
     * Copy constructor
     * @param newSniperRifle the SniperRifle object to be copied.
     */
    public SniperRifle(SniperRifle newSniperRifle){
        super("Sniper Rifle", 'R', DEFAULT_DAMAGE, DEFAULT_ACCURACY, "Pew", 5);
        addCapability(ItemCapability.SNIPERRIFLE);
        this.aimCounter = newSniperRifle.aimCounter;
        this.target = newSniperRifle.target;
        this.turnCounter = newSniperRifle.turnCounter;
    }

    /**
     * Allow Sniper rifle to experience passage of turns.
     * This method is override to allow reset of concentration.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        // reset concentration
        if(aimCounter > 0){
            turnCounter ++; // turn counter will start to ticks when aim counter is triggered.
        }
        // if aiming is not continuous (take other action while aiming), lose target.
        if(aimCounter != turnCounter){
            // lose concentration.
            resetTarget();
            resetCounter();
        }
    }

    /**
     *  Getter for accuracy instance.
     *  Return accuracy based on current number of aimCount.
     *  The greater the aimCount the higher the accuracy.
     * @return An integer indicating the accuracy of the attack (up to 100).
     */
    @Override
    public int getAccuracy() {
        if(aimCounter == 1){ // aim once. increase accuracy to 90
            return AIM_ACCURACY_1;
        }else if(aimCounter == 2){ // aim twice increase accuracy to 100
            return  AIM_ACCURACY_2;
        }
        return DEFAULT_ACCURACY;
    }

    /**
     * Getter for current damage
     * @return An integer indicating the current damage based on aimCount.
     */
    @Override
    public int damage() {
        if(aimCounter == 1){ // if aim once double damage
            return DEFAULT_DAMAGE * 2;
        }else if (aimCounter == 2){ // if aim twice instakill
            return INSTAKILL;
        }
        return DEFAULT_DAMAGE;
    }

    /**
     * Getter for aim counter
     * @return current count of aimCounter.
     */
    public int getAimCounter(){
        return this.aimCounter;
    }


    /**
     * This method increment the current count of the aimCounter.
     */
    public void incrementAimCounter(){
        this.aimCounter++;
    }

    /**
     * This method reset both the aimCounter and the turnCounter.
     */
    public void resetCounter(){
        this.aimCounter = 0;
        this.turnCounter = 0;
    }

    /**
     * Setter for target
     * @param target, the Actor that is current being aimed by the sniper.
     */
    public void setTarget(Actor target){
        this.target = target;
    }

    /**
     * This method reset the target back to null.
     */
    public void resetTarget(){
        this.target = null;
    }

    /**
     *Getter for allowable actions
     * @return a list of allowable actions
     */
    @Override
    public List<Action> getAllowableActions() {
        Actions actions = new Actions();
        actions.add(this.allowableActions);
        if(this.hasCapability(ItemCapability.INVENTORY) && this.getAmmoCount() > 0){
            // add sniperMenu into SniperRifle object.
            actions.add(new SniperRifleMenu(target));
        }
        return actions.getUnmodifiableActionList();
    }

}
