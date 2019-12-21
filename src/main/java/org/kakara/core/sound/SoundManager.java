package org.kakara.core.sound;

import org.kakara.core.game.Location;
import org.kakara.core.mod.Mod;
import org.kakara.core.player.Player;

public interface SoundManager {

    void playPublicSound(Location location, String soundFile, Mod mod);

    void playPrivateSound(Player player, String soundFile, Mod mod);
}
