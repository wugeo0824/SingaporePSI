package com.xjcrepe.sgpsi.model;

/**
 * Created by LiXijun on 2017/9/9.
 */

public class PsiReadings {

    private float west;

    private float east;

    private float north;

    private float south;

    private float central;

    private String typeName;

    public PsiReadings() {
    }

    public float getWest() {
        return west;
    }

    public float getEast() {
        return east;
    }

    public float getNorth() {
        return north;
    }

    public float getSouth() {
        return south;
    }

    public float getCentral() {
        return central;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PsiReadings that = (PsiReadings) o;

        if (Float.compare(that.west, west) != 0) return false;
        if (Float.compare(that.east, east) != 0) return false;
        if (Float.compare(that.north, north) != 0) return false;
        if (Float.compare(that.south, south) != 0) return false;
        if (Float.compare(that.central, central) != 0) return false;
        return typeName != null ? typeName.equals(that.typeName) : that.typeName == null;
    }

    @Override
    public int hashCode() {
        int result = (west != +0.0f ? Float.floatToIntBits(west) : 0);
        result = 31 * result + (east != +0.0f ? Float.floatToIntBits(east) : 0);
        result = 31 * result + (north != +0.0f ? Float.floatToIntBits(north) : 0);
        result = 31 * result + (south != +0.0f ? Float.floatToIntBits(south) : 0);
        result = 31 * result + (central != +0.0f ? Float.floatToIntBits(central) : 0);
        result = 31 * result + (typeName != null ? typeName.hashCode() : 0);
        return result;
    }
}
