package org.kakara.core.game.game.entity;


import org.kakara.core.game.game.Entity;
import org.kakara.core.game.world.Location;

public interface GamePath {

    Location[] getRoute();

    /**
     * IDK
     *
     * @param entity the entity to move
     * @param speed  the speed
     */
    @Deprecated
    void moveEntityAlongRoute(Entity entity, float speed);

}
