package org.kakara.core;

import org.kakara.core.mod.Mod;

import java.util.Objects;

/**
 * This is a object version of the Mod:Key system.
 */
public class NameKey {
    private String name;
    private String key;

    public NameKey(String key, String name) {
        this.name = name;
        this.key = key;
    }

    public NameKey(Mod mod, String key) {
        this(mod.getName(), key);
    }

    public NameKey(String asString) {
        String[] split = asString.split(":");
        if (split.length != 2) throw new IllegalArgumentException("Must follow key:name");
        key = split[0];
        name = split[1];
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return String.format("%s:%s", key, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NameKey that = (NameKey) o;
        return name.equalsIgnoreCase(that.name) &&
                key.equalsIgnoreCase(that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, key);
    }
}