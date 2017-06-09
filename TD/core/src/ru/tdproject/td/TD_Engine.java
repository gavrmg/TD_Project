package ru.tdproject.td;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class TD_Engine implements Runnable {
	private TDWorld _world = null;
	private String Name = null;
	public Object Iter_lock;
	public Vector2 CastlePos;
	private Texture UnitImg = new Texture("dot.jpg");
	private Texture TowerImg = new Texture("tower.png");
	private Texture CastleImg = new Texture("castle.png");
	private Iterator iter;
	boolean lag_flag;
	BaseObject CurrentUnit;

	long WorkTime = 0;
	long lag = 0;
	long start, current;

	public TD_Engine(TDWorld _world, String name) {
		super();
		this._world = _world;
		this.Name = name;
		Iter_lock = new Object();
		// _world.setGoal(new Castle(new Circle(_world.getCastlePos(),2), new
		// Texture(Gdx.files.internal("castle.png")),_world));
		this.CastlePos = new Vector2(20f, 20f);
		_world.initWorld();
		_world.createUnit(1, UnitImg, 2, attackType.Melee, 10, 2, 4, 1);
		lag_flag = false;
		System.out.println("Engine!");
	}

	public void run() {
		System.out.println("Working!");
		while (true) {
			start = System.currentTimeMillis();
			synchronized (_world.Iter_lock) {
				if (!lag_flag)
					WorkTime = 0;
				_world.getWorld().step(1 / 100f, 6, 2);
			}
			current = System.currentTimeMillis();

			WorkTime = (current - start);
			if (WorkTime <= 10) {
				if (lag_flag)
					lag -= WorkTime;
				try {
					Thread.sleep(10 - WorkTime - lag);
					lag = 0;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					System.out.println(WorkTime);
				}
			} else {
				lag = WorkTime - 10 + lag;
				lag_flag = true;
			}
		}
	}
}

// public void
// 1 tile = 1 meter
