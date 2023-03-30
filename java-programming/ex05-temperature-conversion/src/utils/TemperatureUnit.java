package utils;
public enum TemperatureUnit {
    CELSIUS("Celsius"),
    FAHRENHEIT("Fahrenheit");

    private String unit;

    TemperatureUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return this.unit;
    }
}
