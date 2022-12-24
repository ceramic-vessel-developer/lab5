package figury;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Neutron extends Figura{
    public Neutron(Graphics2D buf, int del, int w, int h, int x, int y, int weight, int heightt, AnimPanel panel) {
        super(buf, del, w, h,x,y,weight,heightt,panel);
        clr = Color.gray;
        shape = new Ellipse2D.Double(x, y, weight,heightt);
        aft = new AffineTransform();
        area = new Area(shape);
    }

    @Override
    public void detect_collision() {
        for (UranUnstable object:UranUnstable.exists) {
            int[] coordinates = object.get_position();
            if (shape.intersects(coordinates[0],coordinates[1],coordinates[2],coordinates[3] )){
                System.out.println("TERAZ");
                try {
                    object.collision();
                    destroy();
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        }

    }

    public void destroy() throws InterruptedException {
        kanwa.timer.removeActionListener(this);
        Thread.currentThread().join();
    }
}
