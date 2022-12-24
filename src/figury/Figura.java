/**
 * 
 */
package figury;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.Random;

/**
 * @author tb
 *
 */
public abstract class Figura implements Runnable, ActionListener/*, Shape*/ {

	// wspolny bufor
	protected Graphics2D buffer;
	protected Area area;
	// do wykreslania
	protected Shape shape;
	// przeksztalcenie obiektu
	protected AffineTransform aft;
	// przesuniecie
	private int dx, dy;
	private int delay;
	private int width;
	private int height;
	public Color clr;
	public AnimPanel kanwa;

	protected static final Random rand = new Random();

	public Figura(Graphics2D buf, int del, int w, int h, int x, int y, int weight, int heightt,AnimPanel panel) {
		delay = del;
		buffer = buf;
		width = w;
		height = h;
		kanwa = panel;

		dx = 1 + rand.nextInt(10) -5;
		dy = 1 + rand.nextInt(10) -5;
		if (dx == 0 && dy ==0){
			dx = 1;
			dy = 1;
		}
	}

	@Override
	public void run() {
		area.transform(aft);
		shape = area;

		while (true) {
			// przygotowanie nastepnego kadru
			shape = nextFrame();
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	protected Shape nextFrame(){
		// zapamietanie na zmiennej tymczasowej
		// aby nie przeszkadzalo w wykreslaniu
		area = new Area(area);
		aft = new AffineTransform();
		Rectangle bounds = area.getBounds();
		int cx = bounds.x + bounds.width / 2;
		int cy = bounds.y + bounds.height / 2;
		// odbicie
		if (cx < 0 || cx > width)
			dx = -dx;
		if (cy < 0 || cy > height)
			dy = -dy;
		aft.translate(dx, dy);
		area.transform(aft);
		detect_collision();
		return area;
	}

	public void detect_collision(){
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// wypelnienie obiektu
		buffer.setColor(clr.brighter());
		buffer.fill(shape);
		// wykreslenie ramki
		buffer.setColor(clr.darker());
		buffer.draw(shape);
	}

	public int[] get_position(){
		area = new Area(area);
		Rectangle bounds = area.getBounds();

		return new int[]{bounds.x , bounds.y, bounds.width, bounds.height};
	}

}
