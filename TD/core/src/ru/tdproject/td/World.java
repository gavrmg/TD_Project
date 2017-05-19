package ru.tdproject.td;

import java.util.ArrayList;
import java.util.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class World {

	public World(Vector2 Castle,Vector2 Spawn){
		Units=new ArrayList<BaseObject>();
		//=new ArrayList<BaseObject>();
		CastlePos=Castle;
		SpawnPos=Spawn;
		toAdd = new ArrayList<BaseObject>();
	}
	public Object Iter_lock = new Object();

	public Vector2 getCastlePos() {
		return CastlePos;
	}

	private Castle Goal;
	
	public void setCastlePos(Vector2 castlePos) {
		CastlePos = castlePos;
	}

	private Rectangle Border = new Rectangle(0, 0, 800, 800);

	public Castle getGoal() {
		return Goal;
	}

	public Rectangle getBorder() {
		return Border;
	}

	public void setBorder(Rectangle border) {
		Border = border;
	}

	public void setGoal(Castle goal) {
		Goal = goal;
	}

	public Vector2 getSpawnPos() {
		return SpawnPos;
	}

	public void setSpawnPos(Vector2 spawnPos) {
		SpawnPos = spawnPos;
	}

	//
	private Vector2 CastlePos;
	private Vector2 SpawnPos;
	private ArrayList<BaseObject> Units;

	public BaseObject getObjectByIndex(int i) {
		return Units.get(i);
	}

	public void addObject(BaseObject obj) {
		synchronized (lock) {
			toAdd.add(obj);
		}
	}

	public void draw(SpriteBatch batch) {
		synchronized (lock) {
			if (!toAdd.isEmpty()) {
				Units.addAll(toAdd);
				toAdd.clear();
			}
			Iterator i = Units.iterator();
			Boolean flag = false;
			BaseObject u = null;
			while (i.hasNext())
			{
				synchronized (Iter_lock) {
					u = (BaseObject) i.next();
					u.draw(batch);
				}
			}		
			getGoal().draw(batch);
		}
	}
	ArrayList<BaseObject> toAdd;
	public void move() {
		synchronized (lock) {
			if (!toAdd.isEmpty()) {
				Units.addAll(toAdd);
				toAdd.clear();
			}
			ListIterator i = Units.listIterator();
			BaseObject u = null;
			synchronized (Iter_lock) {
			while (i.hasNext())
				{
					u = (BaseObject) i.next();
					u.step(Border);
				}
		}	
		}
	}

	RandomXS128 Rand = new RandomXS128(0);

	public Object lock = new Object();

	Texture img = new Texture(Gdx.files.internal("dot.jpg"));

	public void createUnit() {
		synchronized (lock) {
			Vector2 pos = new Vector2(Rand.nextInt(800), Rand.nextInt(800));
			Vector2 arr = new Vector2(getCastlePos().x - pos.x, getCastlePos().y - pos.y).nor();
			Unit s_unit = new Unit(new Circle(pos, 10), 0.2f, img, this);
			s_unit.setArrow(arr);
			addObject(s_unit);
			// System.
		}
	}
	public void createTower(int x, int y) {
		synchronized (lock) {
			Vector2 pos = new Vector2(x,y);
			Vector2 arr = new Vector2(1,0).nor();
			Tower s_tower = new Tower("Tower", new Circle(pos, 2),0, arr, img = new Texture(Gdx.files.internal("tower.png")), this, 400, 100);
			//s_tower.setArrow(arr);
			addObject(s_tower);
			// System.
		}
	}

	public void check_Castle() {
		if (getGoal().getLife() == 0)
			System.out.println("!");
	}
//	private predicate<BaseObject> IsDead(){
//		return p -> p.getLife <=0;
//	}
	public void checkDead(){
//			for (BaseObject object : Units)
//				if (object.getLife() <= 0)
//				synchronized (Units) {
//					boolean o = Units.remove(object);System.out.println(o);
//////			Units.removeIf(getLife<=0)
//				}
		Iterator i = Units.iterator();
		Boolean flag = false;
		BaseObject u = null;
		while (i.hasNext())
		{
			synchronized (Iter_lock) {
			u = (BaseObject) i.next();
				if (u.getLife() <= 0)
					i.remove();
			}
		}
	}
	// создать метод возврата объекта по форме (вход - координаты)

	public ArrayList<BaseObject> getUnits() {
		return Units;
	}

	public void setUnits(ArrayList<BaseObject> units) {
		Units = units;
	}

}
