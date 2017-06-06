package ru.tdproject.td;

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
	long sum = 0;
	long i;
	long start,current;
	public TD_Engine(TDWorld _world, String name) {
		super();
		this._world = _world;
		this.Name = name;
		Iter_lock = new Object();
//		_world.setGoal(new Castle(new Circle(_world.getCastlePos(),2), new Texture(Gdx.files.internal("castle.png")),_world));
		this.CastlePos = new Vector2(20f,20f);
		_world.initWorld();
		_world.createCastle(10,CastleImg,10,0, CastlePos.x,CastlePos.y, 0);
		_world.createUnit(1, UnitImg, 10, attackType.Melee, 10, 10, 4, 1);
		System.out.println("Engine!");
	}
	public void run(){
		System.out.println("Working!");
		while(true){
			sum = 0;
			start = System.currentTimeMillis();
			synchronized (_world.Iter_lock) {
				_world.getWorld().step(1 / 100f, 10, 10);
			//	System.out.println("I'm here");
			}
			current = System.currentTimeMillis();
			sum=(current-start);
			try {
				Thread.sleep(10-sum);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

	//public void 
	//1 tile = 1 meter
	
