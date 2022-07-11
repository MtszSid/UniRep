package com.example.zakupy;

import java.io.Serializable;
import java.util.Objects;

public class ShoppingModel implements Serializable {
    String Type;
    String Name;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingModel that = (ShoppingModel) o;
        return getType().equals(that.getType()) && getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getName());
    }
}
