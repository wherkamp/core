package org.kakara.core.world;

import java.util.List;

public interface Chunk {
    List<GameBlock> getGameBlocks();

    ChunkLocation getLocation();
}
