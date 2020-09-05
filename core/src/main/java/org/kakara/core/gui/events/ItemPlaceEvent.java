package org.kakara.core.gui.events;

import org.kakara.core.events.Cancellable;
import org.kakara.core.events.HandlerList;
import org.kakara.core.game.ItemStack;
import org.kakara.core.gui.Inventory;
import org.kakara.core.player.Player;

public class ItemPlaceEvent extends InventoryEvent implements Cancellable {
    private final static HandlerList HANDLERLIST = new HandlerList(ItemPlaceEvent.class);
    private boolean cancelled = false;
    private ItemStack itemStack;

    public ItemPlaceEvent(Player player, Inventory menu, ItemStack itemStack) {
        super(player, menu);
        this.itemStack = itemStack;
    }

    public static HandlerList getHandlers() {
        return HANDLERLIST;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        cancelled = b;
    }

    @Override
    public HandlerList getHandlerList() {
        return HANDLERLIST;
    }
}