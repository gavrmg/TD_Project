package ru.tdproject.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

public class TD_Engine implements Runnable {
	private World _world = null;
	private String Name = null;
	
	public TD_Engine(World world, String name) {
		super();
		this._world = world;
		this.Name = name;
		_world.setGoal(new Castle(new Circle(_world.getCastlePos(),2), new Texture(Gdx.files.internal("castle.png")),_world));

	}
	public void run(){
		for(int i = 0;i<10;i++)
			_world.createUnit();
		while (true){
			_world.move();
			_world.check_Castle();
			_world.checkDead();
			try {
				Thread.sleep(20);
			}
			catch(InterruptedException e){
				System.out.println("Thread crashed");
			}
		}
	}
}
