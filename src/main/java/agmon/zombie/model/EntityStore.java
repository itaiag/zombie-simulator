package agmon.zombie.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntityStore implements EntityAdder {

	private List<AbstractEntity> entities;
	
	private List<AbstractEntity> buffer;

	public EntityStore() {
		entities = new ArrayList<AbstractEntity>();
		buffer = new ArrayList<AbstractEntity>();
	}

	@Override
	public void add(AbstractEntity entity) {
		buffer.add(entity);
	}

	public void removeDeads() {
		Iterator<AbstractEntity> i = entities.iterator();
		while (i.hasNext()) {
			AbstractEntity entity = i.next();
			if (!entity.isExist()) {
				i.remove();
			}
		}
	}

	public boolean isEmpty() {
		return entities.isEmpty();
	}

	public List<AbstractEntity> getEntities() {
		return entities;
//		return new ArrayList<AbstractEntity>(entities);
	}

	public void update(int delta) {
		if (!buffer.isEmpty()){
			entities.addAll(buffer);
			buffer.clear();
			
		}
		for (AbstractEntity entity : entities) {
			entity.update(delta);
		}
	}

}
