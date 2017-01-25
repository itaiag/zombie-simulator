package agmon.zombie.model;

import java.util.Random;

import org.newdawn.slick.geom.Circle;

public class Person extends AbstractEntity {

	public enum MovingState {
		WALKING, RUNNING, STANDING
	}

	private MovingState movingState;

	private int experience;

	private int runningTimer;

	private int changeDirectionTimer;

	private int respondedToCollisionTimer;

	private final EntityAdder entities;

	private final Dice dice;

	public Person(EntityAdder entities, int x, int y) {
		super(new Circle(x, y, 3), 10);
		this.entities = entities;
		movingState = MovingState.WALKING;
		direction = Direction.values()[new Random().nextInt(Direction.values().length)];
		dice = new Dice();
	}

	@Override
	public void update(int delta) {
		changeDirectionTimer -= delta;
		respondedToCollisionTimer -= delta;
		if (changeDirectionTimer <= 0) {
			changeDirectionTimer = 10000;
			direction = Direction.values()[new Random().nextInt(Direction.values().length)];

		}
		if (movingState == MovingState.RUNNING) {
			runningTimer -= delta;
			if (runningTimer <= 0) {
				movingState = MovingState.WALKING;
			}
		}
		switch (movingState) {
		case RUNNING:
			move(3);
			break;
		case STANDING:
			break;
		case WALKING:
			move(2);
			break;

		}
	}

	@Override
	public void collide(AbstractEntity other) {
		if (respondedToCollisionTimer > 0) {
			return;
		}
		respondedToCollisionTimer = 10;
		float experienceAffect = 0.05f * experience;
		if (experienceAffect > 0.3f) {
			experienceAffect = 0.3f;
		}
		if (other instanceof Zombie) {
			switch (dice.throwDice(0.5f - experienceAffect)) {
			case 0:
				// Running
				movingState = MovingState.RUNNING;
				runningTimer = 1000;
				moveFrom(other.getX(), other.getY());
				break;
			case 1:
				// attack
				Noise noise = new Noise(getX(), getY());
				entities.add(noise);
				switch (dice.throwDice(0.5f - experienceAffect)) {
				case 0:
					// Becoming a zombie
					setExist(false);
					Zombie zombie = new Zombie(getX(), getY());
					entities.add(zombie);
					break;
				case 1:
					((Zombie) other).setExist(false);
					experience++;
					break;
				}
				break;
			default:
				throw new IllegalStateException("Unkown percentage");

			}
		}

	}

	public int getExperience() {
		return experience;
	}

}
