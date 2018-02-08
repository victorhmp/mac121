/******************************************************************************
 *  Compilation:  javac ColorStudy.java
 *  Execution:    java ColorStudy
 *
 *  Display Albers squares for all 256 blues and grays
 *
 ******************************************************************************/
import java.awt.Color;

public class ColorStudy {  
    public static void main(String[] args) {
        StdDraw.setXscale(-1, 16);
        StdDraw.setYscale(-1, 16);
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                int gray = i*16 + (15-j);
                int saturation = i + j*16;
                Color c1 = Color.getHSBColor(0.5806794f, saturation/255.0f, 0.6509804f);
                Color c2 = new Color(gray, gray, gray);
                StdDraw.setPenColor(c1);
                StdDraw.filledSquare(i, j, 0.5);
                StdDraw.setPenColor(c2);
                StdDraw.filledSquare(i, j, 0.25);
            }
        }
    }
}
