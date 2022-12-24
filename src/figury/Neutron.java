package figury;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class Neutron extends Figura{
    public Neutron(Graphics2D buf, int del, int w, int h, int x, int y, int weight, int heightt, AnimPanel panel) {
        super(buf, del, w, h,x,y,weight,heightt,panel);
        clr = Color.gray;
        shape = new Ellipse2D.Double(x, y, weight,heightt);
        aft = new AffineTransform();
        area = new Area(shape);
    }

    @Override
    public void detect_collision() throws NullPointerException {
        ArrayList <UranUnstable> list = (ArrayList<UranUnstable>) UranUnstable.exists.clone();

        for (UranUnstable object : list) {
            if(object != null) {
                int[] coordinates = object.get_position();
                if (shape.intersects(coordinates[0], coordinates[1], coordinates[2], coordinates[3])) {
                    object.collision();
                    destroy();
                    break;
                }
            }
        }
    }

    public void destroy(){
        kanwa.timer.removeActionListener(this);
    }
}
