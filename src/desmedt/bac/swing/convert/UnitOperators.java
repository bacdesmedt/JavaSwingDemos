package desmedt.bac.swing.convert;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class UnitOperators {
    
    //MASS
    public static final BiFunction<Unit,Double, Map<Unit,Double>> CONVERTER = (source, value) -> {
        Map<Unit,Double> result = new HashMap<>();
        result.put(source,value);
        
        switch (source){

            case METER:
                result.put(Unit.MILES, meterToMiles(value));
                result.put(Unit.YARD, meterToYards(value));
                result.put(Unit.FEET, meterToFeet(value));
                result.put(Unit.INCH, meterToInch(value));
                break;
            case MILES:
                result.put(Unit.METER, milesToMeter(value));
                result.put(Unit.YARD, milesToYards(value));
                result.put(Unit.FEET, milesToFeet(value));
                result.put(Unit.INCH, milesToInch(value));
                break;
            case FEET:
                result.put(Unit.METER, feetToMeter(value));
                result.put(Unit.YARD, feetToYards(value));
                result.put(Unit.MILES, feetToMiles(value));
                result.put(Unit.INCH, feetToInch(value));
                break;
            case INCH:
                result.put(Unit.METER, inchToMeter(value));
                result.put(Unit.YARD, inchToYards(value));
                result.put(Unit.MILES, inchToMiles(value));
                result.put(Unit.FEET, inchToFeet(value));
                break;
            case CELSIUS:
                result.put(Unit.FAHRENHEIT, celsiusToFahrenheit(value));
                result.put(Unit.KELVIN, celsiusToKelvin(value));
                break;
            case FAHRENHEIT:
                result.put(Unit.KELVIN, fahrenheitToKelvin(value));
                result.put(Unit.CELSIUS, fahrenheitToCelsius(value));
                break;
            case KELVIN:
                result.put(Unit.CELSIUS, kelvinToCelsius(value));
                result.put(Unit.FAHRENHEIT, kelvinToFahrenheit(value));
                break;
            case KILOGRAM:
                result.put(Unit.POUND, kiloToPound(value));
                result.put(Unit.OUNCE, kiloToOunce(value));
                break;
            case POUND:
                result.put(Unit.OUNCE, poundToOunce(value));
                result.put(Unit.KILOGRAM, poundToKilo(value));
                break;
            case OUNCE:
                result.put(Unit.KILOGRAM, ounceToKilo(value));
                result.put(Unit.POUND, ounceToPound(value));
                break;
        }
        
        return result;
    };
    
    public static double kiloToPound(double kilo) {
        return kilo * 2.20462262;
    }
    
    public static double kiloToOunce(double kilo) {
        return kilo * 35.2739619;
    }
    
    public static double poundToKilo(double pound) {
        return pound * 0.45359237;
    }
    
    public static double poundToOunce(double pound) {
        return pound * 16;
    }
    
    public static double ounceToKilo(double ounce) {
        return ounce * 0.0283495231;
    }
    
    public static double ounceToPound(double ounce) {
        return ounce * 0.0625;
    }
    
    //TEMPERATURE
    public static double celsiusToFahrenheit(double celsius) {
        return celsius * 1.8 + 32;
    }
    
    public static double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }
    
    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) / 1.8;
    }
    
    public static double fahrenheitToKelvin(double fahrenheit) {
        return (fahrenheit + 459.67) / 1.8;
    }
    
    public static double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }
    
    public static double kelvinToFahrenheit(double kelvin) {
        return (kelvin * 1.8) - 459.67;
    }
    
    //LENGTH
    public static double meterToMiles(double meter) {
        return (meter * 0.6214) / 1000;
    }
    
    public static double meterToFeet(double meter) {
        return meter * 3.281;
    }
    
    public static double meterToInch(double meter) {
        return meter * 39.37;
    }
    
    public static double meterToYards(double meter) {
        return meter / 0.9144;
    }
    
    public static double milesToMeter(double miles) {
        return miles * 1609.3;
    }
    
    public static double milesToFeet(double miles) {
        return miles * 5280;
    }
    
    public static double milesToInch(double miles) {
        return milesToFeet(miles) * 12;
    }
    
    public static double milesToYards(double miles) {
        return milesToFeet(miles) / 3;
    }
    
    public static double feetToMeter(double feet) {
        return feet * 0.3048;
    }
    
    public static double feetToMiles(double feet) {
        return feet / 5280;
    }
    
    public static double feetToInch(double feet) {
        return feet * 12;
    }
    
    public static double feetToYards(double feet) {
        return feet / 3;
    }
    
    public static double inchToMeter(double inch) {
        return inch * 0.0254;
    }
    
    public static double inchToMiles(double inch) {
        return inchToFeet(inch) / 5280;
    }
    
    public static double inchToFeet(double inch) {
        return inch / 12;
    }
    
    public static double inchToYards(double inch) {
        return inchToFeet(inch) / 3;
    }
    
}
