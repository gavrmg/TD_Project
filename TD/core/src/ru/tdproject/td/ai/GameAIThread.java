package ru.tdproject.td.ai;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

import ru.tdproject.td.BaseObject;
import ru.tdproject.td.TDWorld;

public class GameAIThread implements Runnable {
	
	private TDWorld world;
	private Array<Body> bodies;
	public GameAIThread(TDWorld world){
		super();
		this.world = world;
		bodies = new Array<Body>();
	}
	@Override
	public void run() {
		System.out.println("Working!");
		while(true){
			synchronized (world.Iter_lock) {
				world.getWorld().getBodies(bodies);
			}
				for (Body o : bodies){
						synchronized (world.Iter_lock) {
							((BaseObject) o.getUserData()).step(world);
						}
						
				}
		}
	}
	
}
