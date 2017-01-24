package agmon.zombie.model;

import org.newdawn.slick.geom.Circle;

public class Noise extends AbstractEntity {

	private static final int MAX_RADIUS = 85;

	public Noise(int x, int y) {
		super(new Circle(x, y, 5), 5);
	}

	@Override
	public void update(int delta) {
		Circle circle = (Circle) shape;
		// I am not sure why, but changing the circle radius changes also the
		// center x and y. So we need to set it again.
		float x = circle.getCenterX();
		float y = circle.getCenterY();
		if (circle.getRadius() <= MAX_RADIUS) {
			circle.setRadius(circle.getRadius() + 1);
			circle.setCenterX(x);
			circle.setCenterY(y);
		} else {
			setExist(false);
		}
	}

	@Override
	public void collide(AbstractEntity other) {

	}

	public int getRadius() {
		return (int) ((Circle) shape).getRadius();
	}

}
