package com.xjcrepe.sgpsi.model;

import java.math.BigDecimal;

/**
 * Created by LiXijun on 2017/9/9.
 */

public class PsiReadings {

    private BigDecimal west;

    private BigDecimal east;

    private BigDecimal north;

    private BigDecimal south;

    private BigDecimal central;

    public PsiReadings() {
    }

    public BigDecimal getWest() {
        return west;
    }

    public BigDecimal getEast() {
        return east;
    }

    public BigDecimal getNorth() {
        return north;
    }

    public BigDecimal getSouth() {
        return south;
    }

    public BigDecimal getCentral() {
        return central;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PsiReadings that = (PsiReadings) o;

        if (west != null ? !west.equals(that.west) : that.west != null) return false;
        if (east != null ? !east.equals(that.east) : that.east != null) return false;
        if (north != null ? !north.equals(that.north) : that.north != null) return false;
        if (south != null ? !south.equals(that.south) : that.south != null) return false;
        return central != null ? central.equals(that.central) : that.central == null;
    }

    @Override
    public int hashCode() {
        int result = west != null ? west.hashCode() : 0;
        result = 31 * result + (east != null ? east.hashCode() : 0);
        result = 31 * result + (north != null ? north.hashCode() : 0);
        result = 31 * result + (south != null ? south.hashCode() : 0);
        result = 31 * result + (central != null ? central.hashCode() : 0);
        return result;
    }
}
