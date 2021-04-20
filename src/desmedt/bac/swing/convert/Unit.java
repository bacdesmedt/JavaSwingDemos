package desmedt.bac.swing.convert;

public enum Unit {
    METER, MILES, FEET, INCH, YARD, CELSIUS, FAHRENHEIT, KELVIN, KILOGRAM, POUND, OUNCE;
    
    
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
