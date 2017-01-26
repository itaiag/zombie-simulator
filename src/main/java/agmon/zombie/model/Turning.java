package agmon.zombie.model;

import org.newdawn.slick.geom.Circle;

public class Turning extends AbstractEntity {

	private int timeUntilTurning = 1000;

	private final EntityAdder entities;

	public Turning(EntityAdder entities, float x, float y) {
		super(new Circle(x, y, 3), 3);
		this.entities = entities;

	}

	@Override
	public void update(int delta) {
		timeUntilTurning -= delta;
		if (timeUntilTurning <= 0) {
			Zombie zombie = new Zombie(getX(), getY());
			entities.add(zombie);
			setExist(false);
		}

	}

	@Override
	public void collide(AbstractEntity other) {

	}

}
