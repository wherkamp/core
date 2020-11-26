package org.kakara.core.game.events.entity;

import org.kakara.core.game.events.Cancellable;
import org.kakara.core.game.events.Event;
import org.kakara.core.game.world.GameEntity;

/**
 * This an a GameEntity based event.
 */
public class EntityEvent implements Event, Cancellable {
    private boolean cancelled;
    private final GameEntity gameEntity;

    /**
     * @param gameEntity the GameEntity used by the event.
     */
    public EntityEvent(GameEntity gameEntity) {
        this.gameEntity = gameEntity;
    }

    /**
     * @return the GameEntity used by the event.
     */
    public GameEntity getGameEntity() {
        return gameEntity;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
