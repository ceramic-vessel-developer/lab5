package figury;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class UranUnstable extends Figura{
    static ArrayList<UranUnstable> exists = new ArrayList<>();

    public UranUnstable(Graphics2D buf, int del, int w, int h, int x, int y, int weight, int heightt, AnimPanel panel) {
        super(buf, del, w, h,x,y,weight,heightt, panel);
        clr = Color.red;

        shape = new Ellipse2D.Double(x,
                y,
                weight,
                heightt);
        aft = new AffineTransform();
        area = new Area(shape);
        exists.add(this);
    }

    public void collision(){
        exists.remove(this);
        int[] coord = get_position();
        kanwa.addFig(coord[0],coord[1],Figures.NEUTRON);
        kanwa.addFig(coord[0],coord[1],Figures.NEUTRON);
        kanwa.addFig(coord[0],coord[1],Figures.URAN);
        destroy();
    }
    public void destroy(){
        kanwa.timer.removeActionListener(this);
    }

}
