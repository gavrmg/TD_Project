package ru.tdproject.td;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.maps.Map;

public class World  {

	private Castle Goal;
	private ArrayList<BaseObject> toAdd;
	private Vector2 CastlePos;
	private Vector2 SpawnPos;
	private ArrayList<BaseObject> Units;
	private Rectangle Border; 

	private RandomXS128 Rand;

	private static Object Iter_lock = new Object();
	private static Object lock = new Object();

	public static Object get_iter_lock() {
		return Iter_lock;
	}
	private Texture img;
	
	public World(Vector2 Castle, Vector2 Spawn) {
		Border = new Rectangle(0, 0, 800, 800);
		Units = new ArrayList<BaseObject>();
		// =new ArrayList<BaseObject>();
		CastlePos = Castle;
		SpawnPos = Spawn;
		toAdd = new ArrayList<BaseObject>();
		Rand = new RandomXS128(0);
		img = new Texture(Gdx.files.internal("dot.jpg"));
	}


	public Vector2 getCastlePos() {
		return CastlePos;
	}


	public void setCastlePos(Vector2 castlePos) {
		CastlePos = castlePos;
	}


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

	public BaseObject getObjectByIndex(int i) {
		return Units.get(i);
	}

	public void addObject(BaseObject obj) {
		synchronized (lock) {
			toAdd.add(obj);
		}
	}


	public void draw(SpriteBatch batch) {
		synchronized (Iter_lock) {
			if (!toAdd.isEmpty()) {
				Units.addAll(toAdd);
				toAdd.clear();
			}
			Iterator i = Units.iterator();
			BaseObject u = null;
			while (i.hasNext()) {
				u = (BaseObject) i.next();
				u.draw(batch);
			}
			getGoal().draw(batch);
		}
	}


	public void move() {
		synchronized (Iter_lock) {
			if (!toAdd.isEmpty()) {
				Units.addAll(toAdd);
				toAdd.clear();
			}
			ListIterator<BaseObject> i = Units.listIterator();
			while (i.hasNext()) {
				i.next().step(Border);
			}
		}
	}


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

	public void createTower(float x, float y) {
		synchronized (lock) {
			Vector2 pos = new Vector2(x, y);
			Vector2 arr = new Vector2(1, 0).nor();
			Tower s_tower = new Tower("Tower", new Circle(pos, 2), 0, arr,
					img = new Texture(Gdx.files.internal("tower.png")), this, 400, 100);
			addObject(s_tower);
		}
	}

	public void check_Castle() {
		if (getGoal().getLife() == 0)
			System.out.println("!");
	}

	public void checkDead() {
		Predicate<BaseObject> isDead = obj -> obj.getLife() <= 0;
		synchronized (Iter_lock) {
			Units.removeIf(isDead);
		}
	}
	// создать метод возврата объекта по форме (вход - координаты)

	public ArrayList<BaseObject> getUnits() {
		return Units;
	}

	public void setUnits(ArrayList<BaseObject> units) {
		Units = units;
	}
	
//	public BaseObject getNearestUnit(){
//		return ;
//	};
	
}
