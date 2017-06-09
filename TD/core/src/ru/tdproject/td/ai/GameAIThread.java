package ru.tdproject.td.ai;

import java.util.Iterator;
import java.util.ListIterator;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

import ru.tdproject.td.BaseObject;
import ru.tdproject.td.TDWorld;

public class GameAIThread implements Runnable {
	
	private TDWorld world;
	private ListIterator iter;
	BaseObject CurrentUnit;
	public GameAIThread(TDWorld world){
		super();
		this.world = world;
	}
	@Override
	public void run() {
		System.out.println("Working!");
		while(true){
			synchronized (world.Iter_lock) {
				iter = world.getUnits().listIterator();
				while(iter.hasNext()){
					CurrentUnit = (BaseObject) iter.next();
					CurrentUnit.step();
				}
				for (BaseObject o : world.getToAdd())
					iter.add(o);
				world.getToAdd().clear();
			}
		}
	}
	
}
