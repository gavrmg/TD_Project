package ru.tdproject.td;

import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Castle extends BaseObject   {
private int health;
private String Type;
public Castle(int health,Texture img, String type,Body body,TDWorld world) {
	super(world,body,img);
	this.health = health;
	Type = type;
	//setImg(img);
	//this.body = body;
}
public int getHealth() {
	return health;
}
public void setHealth(int health) {
	this.health = health;
}
public String getType() {
	return Type;
}
public void setType(String type) {
	Type = type;
}
@Override
public void step() {
	// TODO Auto-generated method stub
	
}
@Override
public Vector2 getPosition() {
	return body.getPosition();
}
@Override
public float getOrientation() {
	return body.getAngle();
}
@Override
public void setOrientation(float orientation) {
	body.setTransform(body.getPosition(), orientation);
}
@Override
public float vectorToAngle(Vector2 vector) {
	return vector.angleRad();
}
@Override
public Vector2 angleToVector(Vector2 outVector, float angle) {
	outVector.set((float)Math.cos(angle),(float)Math.sin(angle)); 
	return outVector;
}
@Override
public Location<Vector2> newLocation() {
	return null;
}

	

}
