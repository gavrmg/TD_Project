package ru.tdproject.td;

import java.util.ArrayList;

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
//создать метод возврата объекта по форме (вход - координаты)

}