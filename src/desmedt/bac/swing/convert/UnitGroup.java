package desmedt.bac.swing.convert;

import static desmedt.bac.swing.convert.Unit.*;

public enum UnitGroup {
    MASS(KILOGRAM, POUND, OUNCE),
    TEMPERATURE(CELSIUS,KELVIN,FAHRENHEIT),
    DISTANCE(METER,MILES,FEET,INCH);
    
    public final Unit[] units;
    
    UnitGroup(Unit... units) {
        this.units = units;
    }
    
    
    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
