package org.pub.pt.data.sources.accuweather.domain;

public class Temperature {
    float value;
    float realFeel;
    float realFeelMax;

    public Temperature(float value, float realFeel, float realFeelMax) {
        this.value = value;
        this.realFeel = realFeel;
        this.realFeelMax = realFeelMax;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getRealFeel() {
        return realFeel;
    }

    public void setRealFeel(float realFeel) {
        this.realFeel = realFeel;
    }

    public float getRealFeelMax() {
        return realFeelMax;
    }

    public void setRealFeelMax(float realFeelMax) {
        this.realFeelMax = realFeelMax;
    }
}
