package agmon.zombie.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.geom.Circle;

public class Person extends AbstractEntity {

	public static final int TIME_SHOOTING = 1000;
	
	public enum State {
		WALKING, SHOOTING
	}

	private State state;

	private Random rand;

	private int shootingTimer;

	public Person(int x, int y) {
		super(new Circle(x, y, 3));
		state = State.WALKING;
		direction = Direction.values()[new Random().nextInt(Direction.values().length)];
		rand = new Random();
	}

	@Override
	public void update(int delta) {
		shootingTimer -= delta;
		if (shootingTimer <= 0) {
			state = State.WALKING;
		}
		move(1);
	}

	public void wallCollision(Direction direction) {
		List<Direction> options = new ArrayList<Direction>();
		switch (direction) {
		case NORTH:
			options.add(Direction.SOUTH);
			options.add(Direction.SOUTH_EAST);
			options.add(Direction.SOUTH_WEST);
			break;
		case SOUTH:
			options.add(Direction.NORTH);
			options.add(Direction.NORTH_EAST);
			options.add(Direction.NORTH_WEST);
			break;
		case EAST:
			options.add(Direction.WEST);
			options.add(Direction.NORTH_WEST);
			options.add(Direction.SOUTH_WEST);
			break;
		case WEST:
			options.add(Direction.EAST);
			options.add(Direction.NORTH_EAST);
			options.add(Direction.SOUTH_EAST);
			break;
		default:
			return;
		}

		this.direction = options.get(rand.nextInt(options.size()));
	}

	public boolean isCollide(Person other) {
		return shape.intersects(other.shape);
	}

	public void personCollide(Person person1) {
		state = State.SHOOTING;
		shootingTimer = TIME_SHOOTING;
	}

	public State getState() {
		return state;
	}

	public int getShootingTimer() {
		return shootingTimer;
	}
	
	
	
	

}
