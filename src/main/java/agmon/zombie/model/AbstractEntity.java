package agmon.zombie.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

public abstract class AbstractEntity {

	protected final Shape shape;

	protected Direction direction;

	protected Random rand;

	private final Circle sight;

	private boolean exist = true;

	public AbstractEntity(Shape body, int sightRadius) {
		this.shape = body;
		sight = new Circle(body.getCenterX(), body.getCenterY(), sightRadius);
		rand = new Random();
	}

	public float getX() {
		return shape.getCenterX();
	}

	public float getY() {
		return shape.getCenterY();
	}

	public void setX(float x) {
		shape.setCenterX(x);
	}

	public void setY(float y) {
		shape.setCenterY(y);
	}

	protected void moveX(float deltaX) {
		shape.setCenterX(shape.getCenterX() + deltaX);
		sight.setCenterX(shape.getCenterX());
	}

	protected void moveY(float deltaY) {
		shape.setCenterY(shape.getCenterY() + deltaY);
		sight.setCenterY(shape.getCenterY());
	}

	protected void move(float delta) {
		switch (direction) {
		case NORTH:
			moveY(-delta);
			break;
		case NORTH_EAST:
			moveY(-delta);
			moveX(delta);
			break;
		case EAST:
			moveX(delta);
			break;
		case SOUTH_EAST:
			moveY(delta);
			moveX(delta);
			break;
		case SOUTH:
			moveY(delta);
			break;
		case SOUTH_WEST:
			moveY(delta);
			moveX(-delta);
			break;
		case WEST:
			moveX(-delta);
			break;
		case NORTH_WEST:
			moveY(-delta);
			moveX(-delta);
			break;
		default:
			break;
		}

	}

	protected void setDirectionFrom(float x, float y) {
		int tolerance = 10;
		Direction tempDir = Direction.CENTER;
		if (getX() < x - tolerance) {
			tempDir = Direction.WEST;
		} else if (getX() > x + tolerance) {
			tempDir = Direction.EAST;
		}
		if (getY() < y - tolerance) {
			if (tempDir == Direction.EAST) {
				tempDir = Direction.NORTH_EAST;
			} else if (tempDir == Direction.WEST) {
				tempDir = Direction.NORTH_WEST;
			} else {
				tempDir = Direction.NORTH;
			}
		} else if (getY() > y + tolerance) {
			if (tempDir == Direction.EAST) {
				tempDir = Direction.SOUTH_EAST;
			} else if (tempDir == Direction.WEST) {
				tempDir = Direction.SOUTH_WEST;
			} else {
				tempDir = Direction.SOUTH;
			}
		}
		direction = tempDir;

	}

	protected void setDirectionTo(float x, float y) {
		int tolerance = 2;
		Direction tempDir = Direction.CENTER;
		if (getX() < x - tolerance) {
			tempDir = Direction.EAST;
		} else if (getX() > x + tolerance) {
			tempDir = Direction.WEST;
		}
		if (getY() < y - tolerance) {
			if (tempDir == Direction.EAST) {
				tempDir = Direction.SOUTH_EAST;
			} else if (tempDir == Direction.WEST) {
				tempDir = Direction.SOUTH_WEST;
			} else {
				tempDir = Direction.SOUTH;
			}
		} else if (getY() > y + tolerance) {
			if (tempDir == Direction.EAST) {
				tempDir = Direction.NORTH_EAST;
			} else if (tempDir == Direction.WEST) {
				tempDir = Direction.NORTH_WEST;
			} else {
				tempDir = Direction.NORTH;
			}
		}
		direction = tempDir;
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

	public Shape getShape() {
		return shape;
	}

	public abstract void update(int delta);

	public boolean isCollide(AbstractEntity other) {
		return sight.intersects(other.shape);
	}

	public abstract void collide(AbstractEntity other);

	public boolean isExist() {
		return exist;
	}

	protected void setExist(boolean exist) {
		this.exist = exist;
	}

}
