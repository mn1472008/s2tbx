package org.esa.s2tbx.mapper.util;

import com.vividsolutions.jts.geom.Polygon;
import it.unimi.dsi.fastutil.ints.IntArrayList;

/**
 *
 * @author Razvan Dumitrascu
 */
public class SpectrumClassReferencePixels {

    private String className;
    private IntArrayList xPixelPositions;
    private IntArrayList yPixelPositions;
    private int minXPosition;
    private int minYPosition;
    private int maxXPosition;
    private int maxYPosition;

    public SpectrumClassReferencePixels(String className) {
        this.className = className;
        xPixelPositions = new IntArrayList();
        yPixelPositions = new IntArrayList();
    }

   public void addElements(int x, int y) {
       this.xPixelPositions.add(x);
       this.yPixelPositions.add(y);
   }

   public void setBoundingBoxValues(int minXPosition, int minYPosition, int maxXPosition, int maxYPosition ) {
       this.minXPosition = minXPosition;
       this.minYPosition = minYPosition;
       this.maxXPosition = maxXPosition;
       this.maxYPosition = maxYPosition;
   }


   public IntArrayList getXPixelPositions() {
       return this.xPixelPositions;
   }

   public IntArrayList getYPixelPositions() {
       return this.yPixelPositions;
   }

    public String getClassName() {
        return className;
    }

    public int getMinXPosition() {
        return minXPosition;
    }

    public int getMinYPosition() {
        return minYPosition;
    }

    public int getMaxXPosition() {
        return maxXPosition;
    }

    public int getMaxYPosition() {
        return maxYPosition;
    }
}
