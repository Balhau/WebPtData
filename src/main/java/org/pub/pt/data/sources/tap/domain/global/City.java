package org.pub.pt.data.sources.tap.domain.global;

public class City {
    private final String cityCode;
    private final String cityName;

    public City(String cityCode, String cityName) {
        this.cityCode = cityCode;
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getCityName() {
        return cityName;
    }
}
