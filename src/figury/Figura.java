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
import java.util.ArrayList;
import java.util.List;
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
	protected int licznik;
	// przesuniecie
	private double dx, dy;
	// rozciaganie
	private double sf;
	// kat obrotu
	private double an;
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

		dx = 1 + rand.nextDouble()*10 -5;
		dy = 1 + rand.nextDouble()*10 -5;
		sf = 1 + 0.05 * rand.nextDouble();
		an = 0.1 * rand.nextDouble();

//		clr = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
		// reszta musi być zawarta w realizacji klasy Figure
		// (tworzenie figury i przygotowanie transformacji)

	}

	@Override
	public void run() {
		area.transform(aft);
		shape = area;

		while (true) {
			// przygotowanie nastepnego kadru
			try {
				shape = nextFrame();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	protected Shape nextFrame() throws InterruptedException {
		licznik++;
		// zapamietanie na zmiennej tymczasowej
		// aby nie przeszkadzalo w wykreslaniu
		area = new Area(area);
		aft = new AffineTransform();
		Rectangle bounds = area.getBounds();
		int cx = bounds.x + bounds.width / 2;
		int cy = bounds.y + bounds.height / 2;
		if(licznik == 1){
			System.out.printf("cx: %d   cy: %d  bound.x: %d    bound.y: %d    bound.height: %d     bound.width:  %d\n",cx,cy,bounds.x,bounds.y,bounds.height,bounds.width);
		}
		// odbicie
		if (cx < 0 || cx > width)
			dx = -dx;
		if (cy < 0 || cy > height)
			dy = -dy;
		// zwiekszenie lub zmniejszenie
		if (bounds.height > height / 3 || bounds.height < 10)
			sf = 1 / sf;
		// konstrukcja przeksztalcenia
//		aft.translate(cx, cy);
//		aft.scale(sf, sf);
//		aft.rotate(an);
//		aft.translate(-cx, -cy);
		aft.translate(dx, dy);
		// przeksztalcenie obiektu
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
