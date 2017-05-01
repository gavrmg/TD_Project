package ru.tdproject.td;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class World {

	
public World(){
	objects=new ArrayList<BaseObject>();
}
//
private ArrayList<BaseObject> objects;
public BaseObject getObjectByIndex(int i){
	return objects.get(i);
}

public void addObject(BaseObject obj)
{
	objects.add(obj);
}

public void draw(SpriteBatch batch)
{
	for (BaseObject object : objects) {
		object.draw(batch);
	}
}

public void move()
{
	for (BaseObject object : objects) {
		object.move();
	}
}


//создать метод возврата объекта по форме (вход - координаты)

}