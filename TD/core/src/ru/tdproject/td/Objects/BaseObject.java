package ru.tdproject.td.Objects;

import com.badlogic.gdx.ai.btree.utils.BehaviorTreeLibrary;
import com.badlogic.gdx.ai.btree.utils.BehaviorTreeLibraryManager;
import com.badlogic.gdx.ai.btree.utils.BehaviorTreeParser;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import ru.tdproject.td.game.TDContext;
import ru.tdproject.td.game.TDWorld;

public abstract class BaseObject implements Location<Vector2> {
	private Texture img;
	private Body body;
	protected TDWorld world;
	public abstract String getType();
	private boolean ToDispose = false;
	
	
	public BaseObject(TDWorld world,Body body, Texture tex){
		this.world = world;
		this.setBody(body);
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
		batch.draw(img, getBody().getPosition().x*TDContext.PIX_TO_METER, getBody().getPosition().y*TDContext.PIX_TO_METER);
	}
	
	public abstract void step();
	@Override
	public Vector2 getPosition() {
		// TODO Auto-generated method stub
		return getBody().getPosition();
	}
	@Override
	public float getOrientation() {
		// TODO Auto-generated method stub
		return getBody().getAngle();
	}
	@Override
	public void setOrientation(float orientation) {
		// TODO Auto-generated method stub
		getBody().setTransform(getBody().getPosition(),orientation );
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
		return null;
	}
	public boolean isToDispose() {
		return ToDispose;
	}
	public void setToDispose(boolean toDispose) {
		ToDispose = toDispose;
	}
	public Body getBody() {
		return body;
	}
	public void setBody(Body body) {
		this.body = body;
	}
}
