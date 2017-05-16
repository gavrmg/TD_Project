package ru.tdproject.td;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class World {

	
public World(Vector2 Castle,Vector2 Spawn){
	objects=new ArrayList<BaseObject>();
	CastlePos=Castle;
	SpawnPos=Spawn;
}
public Vector2 getCastlePos() {
	return CastlePos;
}

public void setCastlePos(Vector2 castlePos) {
	CastlePos = castlePos;
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
private ArrayList<BaseObject> objects;

public BaseObject getObjectByIndex(int i){
	return objects.get(i);
}

public void addObject(BaseObject obj)
{
	synchronized (lock) {
		objects.add(obj);
	}
}

public void draw(SpriteBatch batch)
{
	synchronized (lock) {
		for (BaseObject object : objects) {
			object.draw(batch);
		}
	}
}

public void move()
{
	synchronized (lock) {
		for (BaseObject object : objects) {
			object.move();
		}
	}
}

Object lock = new Object();

Texture img = new Texture(Gdx.files.internal("dot.jpg"));
public void createUnit(){
	synchronized(lock){
		Vector2 arr = new Vector2(getCastlePos().x-getSpawnPos().x, getCastlePos().y-getSpawnPos().y).nor();
		Unit s_unit = new Unit("A", 20f, arr, true, img, new Circle(getSpawnPos(),1));
		addObject(s_unit);
		//System.
	}
}
public void check_Castle(){
	UnitObject o = null;
	for (BaseObject baseObject : objects) {
		if (baseObject.gettype() != "B")
			o = (UnitObject) baseObject;
			if (o.getForm().contains(getCastlePos())) {
				synchronized (lock) {
					o.getImg().dispose();
					objects.remove(baseObject);
				}
			}
	}
}

//создать метод возврата объекта по форме (вход - координаты)

}