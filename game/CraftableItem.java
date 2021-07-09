package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a children class of Item that gives the item an additional allowable action CraftAction.
 */

public abstract class CraftableItem extends PortableItem{


    private Item item;
    /***
     * Constructor.
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param newItem item to be crafted into
     */
    public CraftableItem(String name, char displayChar, Item newItem) {
        super(name, displayChar);
        this.item = newItem;
    }

    /**
     * This method adds CraftAction into a CraftableItem.
     * @return a list of unmodifiable actions.
     */
    @Override
    public List<Action> getAllowableActions() {
        Actions actions = new Actions();
        actions.add(this.allowableActions);
        if(this.hasCapability(ItemCapability.INVENTORY)){
            actions.add(new CraftAction(this, this.item));
        }
        return actions.getUnmodifiableActionList();
    }
}
