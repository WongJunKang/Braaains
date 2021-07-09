package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

public class EndableWorld extends World {
    /**
     * World class, but with endings and a better quit function
     */

    private Boolean win, end;
    private int zombieCount, humanCount;
    /**
     * Constructor.
     *
     * @param display the Display that will display this World.
     */
    public EndableWorld(Display display) {
        super(display);
        win = false;
        end = false;
    }


    /**
     * runs the whole game. Added ability for ending games and quitting game.
     */
    @Override
    public void run() {
        if (player == null)
//            throw new IllegalStateException();


        // initialize the last action map to nothing actions;
        for (Actor actor : actorLocations) {
            lastActionMap.put(actor, new DoNothingAction());
        }

        // This loop is basically the whole game
        while (stillRunning() && !end) {
            zombieCount = 0;
            humanCount = 0;
            GameMap playersMap = actorLocations.locationOf(player).map();
            playersMap.draw(display);

            // Process all the actors.
            for (Actor actor : actorLocations) {
                if (actor.hasCapability(ZombieCapability.UNDEAD)){
                    zombieCount += 1;
                }
                else if (actor.hasCapability(ZombieCapability.ALIVE)){
                    humanCount += 1;
                }
                    processActorTurn(actor);
            }

            // Tick over all the maps. For the map stuff.
            for (GameMap gameMap : gameMaps) {
                List<Item> items = gameMap.at(0,0).getItems();
                for (Item item : items){
                    if (item.hasCapability(ItemCapability.TOTEM)){
                        MamboTotem totem = (MamboTotem)item;
                        if (!totem.getKilled()){
                                zombieCount += 1;
                        }
                    }
                }
                setWin();
                gameMap.tick();
            }
        }
        display.println(endGameMessage());
    }


    /**
     * checks if player won or lost (if applicable)
     * @return a string that player won or lost
     */
    private String checkWin(){
        if (win){
            return ("You've killed all the zombies! You've won!");
        }
        else{
            return ("You've lost. There's always next time.");
        }
    }

    /**
     * returns strings for ending game
     * @return strings for ending game
     */
    @Override
    protected String endGameMessage() {
        String retStr = "";
        if (end){
            retStr += checkWin() + "\n";
        }
        retStr += "Game Over";
        return retStr;
    }

    /**
     * determines if player completed the game and if they won or lost
     */
    private void setWin(){
        if (zombieCount == 0 && humanCount > 1 && actorLocations.contains(player)){
            win = true;
            end = true;
        }
        else if (humanCount == 1){
            end = true;
        }
    }
}
