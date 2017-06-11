package ru.tdproject.td;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.behaviors.Seek;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import ru.tdproject.td.ai.Messages;

public class Projectile extends BaseObject implements Steerable<Vector2>,Telegraph {

	private int Damage;
	private Location<Vector2> Target;
	public BaseObject getTarget() {
		return (BaseObject) Target;
	}
	public void setTarget(Location<Vector2> target) {
		Target = target;
	}


	private float speed;
	private Seek<Vector2> seek;
	private SteeringAcceleration<Vector2> Steering;
	public Projectile( Body body, float speed,  Texture img, TDWorld world, int Damage, Location<Vector2> Target) {
		super(world,body,img);
		this.Damage = Damage;
		this.Target = Target;
		this.seek = new Seek<Vector2>(this,Target);
		this.speed = speed;
		Steering = new SteeringAcceleration<Vector2>(getLinearVelocity());
		// TODO Auto-generated constructor stub
	}
	@Override
	public void step(){
		seek.calculateSteering(Steering);
		body.setLinearVelocity(Steering.linear);
		//body.getWorld().getContactList().select(predicate)
		if (body.getFixtureList().first().testPoint(((BaseObject)Target).body.getPosition())){
			world.getMessager().dispatchMessage((Telegraph)this, (Telegraph)Target, Messages.Attacked.code, Damage);
			setToDispose(true);
		}
		if (((BaseObject)Target).body == null)
			setToDispose(true);
			//for ((ContactEdgebody.getC)
		//if
	}
	@Override
	public float getZeroLinearSpeedThreshold() {
		// TODO Auto-generated method stub
		return 0.01f;
	}
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Projectile";
	}
	@Override
	public Vector2 getPosition() {
		// TODO Auto-generated method stub
		return body.getPosition();
	}
	@Override
	public float getOrientation() {
		// TODO Auto-generated method stub
		return body.getAngle();
	}
	@Override
	public void setOrientation(float orientation) {
		// TODO Auto-generated method stub
		body.setTransform(body.getPosition(), orientation);
	}
	@Override
	public float vectorToAngle(Vector2 vector) {
		// TODO Auto-generated method stub
		vector = (Vector2) vector;
		return (float) Math.atan2(((Vector2) vector).y,((Vector2) vector).x);
	}
	@Override
	public float getMaxLinearSpeed() {
		// TODO Auto-generated method stub
		return speed;
	}
	@Override
	public void setMaxLinearSpeed(float maxLinearSpeed) {
		// TODO Auto-generated method stub
		this.speed = maxLinearSpeed;
	}
	@Override
	public float getMaxLinearAcceleration() {
		// TODO Auto-generated method stub
		return 5;
	}
	@Override
	public void setMaxLinearAcceleration(float maxLinearAcceleration) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public float getMaxAngularSpeed() {
		// TODO Auto-generated method stub
		return 1;
	}
	@Override
	public void setMaxAngularSpeed(float maxAngularSpeed) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public float getMaxAngularAcceleration() {
		// TODO Auto-generated method stub
		return 10;
	}
	@Override
	public void setMaxAngularAcceleration(float maxAngularAcceleration) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Vector2 getLinearVelocity() {
		// TODO Auto-generated method stub
		return body.getLinearVelocity();
	}
	@Override
	public float getAngularVelocity() {
		// TODO Auto-generated method stub
		return body.getAngularVelocity();
	}
	@Override
	public float getBoundingRadius() {
		// TODO Auto-generated method stub
		return body.getFixtureList().first().getShape().getRadius();
	}
	@Override
	public boolean isTagged() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setTagged(boolean tagged) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setZeroLinearSpeedThreshold(float value) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean handleMessage(Telegram msg) {
		// TODO Auto-generated method stub
		return false;
	}

}
