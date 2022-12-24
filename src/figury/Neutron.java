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
        ArrayList <UranUnstable> lol = (ArrayList<UranUnstable>) UranUnstable.exists.clone();
        for (UranUnstable object:lol) {
            int[] coordinates = object.get_position();
            if (shape.intersects(coordinates[0],coordinates[1],coordinates[2],coordinates[3] )){
                System.out.println("TERAZ");
                object.collision();
                destroy();
            }
        }

    }

    public void destroy(){
        kanwa.timer.removeActionListener(this);
    }
}
