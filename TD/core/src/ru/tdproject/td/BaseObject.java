package ru.tdproject.td;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class BaseObject implements Location<Vector2> {
	private Texture img;
	protected Body body;
	protected TDWorld world;
	public String getType(){
		return "Basic";
	};
	
	public BaseObject(TDWorld world,Body body, Texture tex){
		this.world = world;
		this.body = body;
		this.img = tex;
	}
	public BaseObject(){
	}
	
	public Texture getImg() {
		return img;
	}
	public void setImg(Texture img) {
		this.img = img;
	}
	
	public void draw(SpriteBatch batch){
		batch.draw(img, body.getPosition().x*TDContext.PIX_TO_METER, body.getPosition().y*TDContext.PIX_TO_METER);
	}
	
	public void step() {
	}
//	public abstract void step(TDWorld world);
	//SolidOBject doesn't have a Texture, so to avoid NullPtrExeption use should filter Objects by ObjectType 
	@Override
	public Vector2 getPosition() {
		// TODO Auto-generated method stub
		return body.getLinearVelocity();
	}
	@Override
	public float getOrientation() {
		// TODO Auto-generated method stub
		return body.getAngle();
	}
	@Override
	public void setOrientation(float orientation) {
		// TODO Auto-generated method stub
		body.setTransform(body.getPosition(),orientation );
	}
	@Override
	public float vectorToAngle(Vector2 vector) {
		// TODO Auto-generated method stub
		return vector.angle();
	}
	@Override
	public Vector2 angleToVector(Vector2 outVector, float angle) {
		// TODO Auto-generated method stub
		return outVector.setAngleRad(angle);
	}
	@Override
	public Location<Vector2> newLocation() {
		// TODO Auto-generated method stub
		return new BaseObject();
	}
}
