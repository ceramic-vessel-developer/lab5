package figury;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class UranStable extends Figura{
    public UranStable(Graphics2D buf, int del, int w, int h, int x, int y, int weight, int heightt,AnimPanel panel) {
        super(buf, del, w, h,x,y,weight,heightt,panel);
        clr = Color.cyan;

        shape = new Ellipse2D.Double(x,
                y,
                weight,
                heightt);
        aft = new AffineTransform();
        area = new Area(shape);
    }

}
