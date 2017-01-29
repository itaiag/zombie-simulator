package agmon.zombie.controller;

import java.util.List;

import agmon.zombie.Common;
import agmon.zombie.model.AbstractEntity;
import agmon.zombie.model.Direction;
import agmon.zombie.model.Noise;

public class CollisionDetector {

	public void detect(List<AbstractEntity> entities) {

		for (AbstractEntity entity0 : entities) {
			for (AbstractEntity entity1 : entities) {
				if (entity0 == entity1) {
					continue;
				}
				if (entity0 instanceof Noise) {
					continue;
				}
				if (entity0.isCollide(entity1)) {
					entity0.collide(entity1);
				}
			}
		}
		for (AbstractEntity entity : entities) {
			if (entity.getX() <= 5) {
				entity.setX(5);
				entity.wallCollision(Direction.WEST);
			} else if (entity.getX() >= Common.WIDTH - 5) {
				entity.setX(Common.WIDTH - 5);
				entity.wallCollision(Direction.EAST);
			} else if (entity.getY() <= 5) {
				entity.setY(5);
				entity.wallCollision(Direction.NORTH);
			} else if (entity.getY() >= Common.HEIGHT - Common.STATE_AREA_HEIGHT - 5) {
				entity.setY(Common.HEIGHT - Common.STATE_AREA_HEIGHT - 5);
				entity.wallCollision(Direction.SOUTH);
			}
		}
	}

}
