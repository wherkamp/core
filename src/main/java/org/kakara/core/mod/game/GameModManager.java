package org.kakara.core.mod.game;

import me.kingtux.other.TheCodeOfAMadMan;
import org.apache.commons.lang3.StringUtils;
import org.kakara.core.GameInstance;
import org.kakara.core.Kakara;
import org.kakara.core.exceptions.IllegalModException;
import org.kakara.core.mod.Mod;
import org.kakara.core.mod.ModLoader;
import org.kakara.core.mod.ModManager;
import org.kakara.core.mod.ModType;
import org.kakara.core.resources.ResourceType;
import org.kakara.core.resources.TextureResolution;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameModManager implements ModManager {
    private List<Mod> loadedMods = new ArrayList<>();
    private ModLoader modLoader;
    private Mod coreMod;
    private GameInstance gameInstance;

    public GameModManager(Mod coreMod) {
        this.coreMod = coreMod;
    }

    public void load(GameInstance gameInstance) {
        this.gameInstance = gameInstance;
        this.modLoader = new GameModLoader(gameInstance);

    }

    @Override
    public List<Mod> getLoadedMods() {
        List<Mod> list = new ArrayList<>(loadedMods);
        list.add(coreMod);
        return list;
    }

    @Override
    public void loadMods(List<File> modsToLoad) {
        for (File file : modsToLoad) {
            try {
                GameMod mod = (GameMod) modLoader.load(file);
                if (mod == null) {
                    Kakara.LOGGER.error("Unable to load(No Error): " + file.getName());
                    continue;
                }
                loadedMods.add(mod);
                Kakara.LOGGER.info(String.format("Enabling %s %s by %s", mod.getName(), mod.getVersion(), StringUtils.join(mod.getAuthors(), ",")));
                loadResources(mod, file);
                mod.enable();
            } catch (IOException | IllegalModException e) {
                Kakara.LOGGER.error("Unable to load mod:  " + file.getName(), e);
            }
        }
    }

    private void loadResources(Mod mod, File file) throws IOException {
        List<String> paths = TheCodeOfAMadMan.getResourcesInJar(file, "resources", true);
        for (String s : paths) {
            String path = s.replace("/resources/", "");
            String[] splitPath = path.split("/");
            if (splitPath[0].equalsIgnoreCase("texture")) {
                String newPath = StringUtils.join(Arrays.stream(splitPath).collect(Collectors.toList()), "/", 2, splitPath.length);
                gameInstance.getResourceManager().registerTexture(newPath, TextureResolution.get(Integer.parseInt(splitPath[1])), mod);
            } else {
                String newPath = StringUtils.join(Arrays.stream(splitPath).collect(Collectors.toList()), "/", 1, splitPath.length);
                gameInstance.getResourceManager().registerResource(newPath, ResourceType.valueOf(splitPath[0].toUpperCase()), mod);
            }
        }
    }

    @Override
    public void unloadMods(List<Mod> modsToUnload) {
        for (Mod mod : modsToUnload) {
            if (mod == coreMod) continue;
            Kakara.LOGGER.info(String.format("Disabling %s %s", mod.getName(), mod.getVersion()));
            if (!(mod instanceof GameMod)) continue;
            ((GameMod) mod).disable();
            loadedMods.remove(mod);
            try {
                modLoader.unload(mod);
            } catch (IOException e) {
                Kakara.LOGGER.error("Unable to unload mod:  " + mod.getName(), e);
            }
        }
    }

    @Override
    public List<Mod> getModsByType(ModType modType) {
        return loadedMods.stream().filter(mod -> mod.getModType() == modType).collect(Collectors.toList());
    }

    @Override
    public void unLoadMods(ModType type) {
        unloadMods(getModsByType(type));
    }

    @Override
    public ModLoader getModLoader() {
        return modLoader;
    }
}
