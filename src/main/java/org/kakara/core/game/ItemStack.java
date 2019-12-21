package org.kakara.core.game;

/**
 * Unlike an Item this represents an specific item in the game.
 * ex. damage data, Meta Data, and count.
 * <p>
 * To create a ItemStack use GameInstance#createItemStack(Item item)
 **/
public interface ItemStack {

    /**
     * This returns the item type this is
     *
     * @return the item.
     * @see Item
     */
    Item getItem();

    /**
     * This is the meta data to this item.
     *
     * @return The ItemStack MetaData
     * @see MetaData
     */
    MetaData getMetaData();

    int count();

}
