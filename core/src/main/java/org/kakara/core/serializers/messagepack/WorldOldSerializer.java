package org.kakara.core.serializers.messagepack;

import org.kakara.core.Kakara;
import org.kakara.core.data.Entry;
import org.kakara.core.data.OldSerializer;
import org.kakara.core.world.World;

public class WorldOldSerializer extends OldSerializer<World> {
    @Override
    public Entry disassembleObject(World item) {
        return new Entry(item.getUUID());
    }

    @Override
    public World assembleObject(Entry entry) {
        return Kakara.getWorldManager().getWorldByUUID(entry.uuid());
    }
}