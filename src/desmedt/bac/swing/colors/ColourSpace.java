package desmedt.bac.swing.colors;

import java.awt.*;
import java.util.HashMap;

public enum ColourSpace {
    RGB(new String[]{Labels.RED, Labels.BLUE, Labels.GREEN, Labels.ALPHA}),
    HSB(new String[]{ Labels.HUE, Labels.BRIGHTNESS, Labels.SATURATION, Labels.ALPHA }),
    CMYK(new String[]{ Labels.CYAN, Labels.MAGENTA, Labels.YELLOW, Labels.KEY, Labels.ALPHA });
    
    public final String[] labels;
    
    private ColourSpace(String[] labels){
        this.labels = labels;
    }
    
    private static class Labels{
        private static final String RED = "red";
        private static final String BLUE = "blue";
        private static final String GREEN = "green";
        private static final String ALPHA = "alpha";
        private static final String HUE = "hue";
        private static final String SATURATION = "saturation";
        private static final String BRIGHTNESS = "brightness";
        private static final String CYAN = "cyan";
        private static final String MAGENTA = "magenta";
        private static final String YELLOW = "yellow";
        private static final String KEY = "key";
    }
    
    public static class Data {
        
        
        static HashMap<ColourSpace,HashMap<String,Integer>> data = new HashMap<>(){{
            put(RGB, new HashMap<>() {{
                put(Labels.RED, 255);
                put(Labels.BLUE, 255);
                put(Labels.GREEN, 255);
                put(Labels.ALPHA, 255);
            }});
            put(HSB, new HashMap<>() {{
                put(Labels.HUE, 255);
                put(Labels.SATURATION, 255);
                put(Labels.BRIGHTNESS, 255);
                put(Labels.ALPHA, 255);
            }});
            put(CMYK, new HashMap<>() {{
                put(Labels.CYAN, 255);
                put(Labels.MAGENTA, 255);
                put(Labels.YELLOW, 255);
                put(Labels.KEY, 255);
                put(Labels.ALPHA, 255);
            }});
        }};
        
        public static HashMap<ColourSpace,HashMap<String,Integer>> geData(ColourSpace colourSpace, int[] colourValues){
            HashMap<String, Integer> source = data.get(colourSpace);
//            switch (colourSpace){
//                case RGB:
//                    break;
//                case HSB:
//                    break;
//                case CMYK:
//                    break;
//            }
//            float[] rgbComponents = color.getRGBComponents(new float[4]);
//            float[] hsbComponents = color.getRGBComponents(new float[4]);
            
            return data;
        }
        
        private static int[] rgbToCmyk(int r, int g, int b) {
            double percentageR = r / 255.0 * 100;
            double percentageG = g / 255.0 * 100;
            double percentageB = b / 255.0 * 100;
        
            double k = 100 - Math.max(Math.max(percentageR, percentageG), percentageB);
        
            if (k == 100) {
                return new int[]{ 0, 0, 0, 100 };
            }
        
            int c = (int)((100 - percentageR - k) / (100 - k) * 100);
            int m = (int)((100 - percentageG - k) / (100 - k) * 100);
            int y = (int)((100 - percentageB - k) / (100 - k) * 100);
        
            return new int[]{ c, m, y, (int)k };
        }
    
        private static int[] cmykToRgb(int c, int m, int y, int k) {
            int r = 255 * (1 - c/100) * (1 - k/100);
            int g = 255 * (1 - m/100) * (1 - k/100);
            int b = 255 * (1 - y/100) * (1 - k/100);
            int[] rgb = new int[]{ r, g, b };
        
            return rgb;
        }
    }
}
