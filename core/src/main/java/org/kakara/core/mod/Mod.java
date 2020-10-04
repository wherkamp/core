package org.kakara.core.mod;

import org.kakara.core.GameInstance;
import org.slf4j.Logger;

/**
 * Represents a Mod
 */
public interface Mod {

    /**
     * The name of the mod
     *
     * @return the mod name
     * @see ModRules
     */
    String getName();

    /**
     * The version of the mod
     *
     * @return the mod version
     * @see ModRules
     */
    String getVersion();

    String[] getAuthors();

    String getDescription();

    ModType getModType();

    ModRules getModRules();

    Logger getLogger();

    GameInstance getGameInstance();

    /**
     * Called before loading any mod stages. use this to create any classes you might need to.
     */
    void preEnable();

    /**
     * Called after all the core stages done
     * Use this to call any custom stages you might have
     */
    void postEnable();

    /**
     * Called after all everything is done. CustomStages are already loaded
     */
    void enableCompletion();

    String getUppercaseName();
}
