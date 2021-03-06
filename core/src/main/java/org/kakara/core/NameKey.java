package org.kakara.core;

import org.kakara.core.mod.Mod;

import java.util.Objects;

/**
 * This is a object version of the MOD:KEY system.
 * All Values must be uppercase
 */
public class NameKey {
    private final String name;
    private final String key;
    private String value;

    public NameKey(String name, String key) {
        this.name = name.toUpperCase();
        this.key = key.toUpperCase();
    }

    public NameKey(Mod mod, String key) {
        this(mod.getUppercaseName(), key);
    }

    public NameKey(String asString) {
        String[] split = asString.toUpperCase().split(":");
        if (split.length != 2) throw new IllegalArgumentException("Must follow NAME:KEY");
        name = split[0];
        key = split[1];
        value = asString;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        if (value == null) value = String.format("%s:%s", name, key);
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NameKey that = (NameKey) o;
        return that.toString().equals(toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, key);
    }
}
