package ru.tdproject.td;

import java.util.Iterator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import ru.tdproject.td.ai.Messages;
import ru.tdproject.td.utils.attackType;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.steer.behaviors.Seek;
import com.badlogic.gdx.ai.utils.Location;
public class Unit extends BaseObject implements Steerable<Vector2>,Telegraph {
	private float AttackRange;
	private attackType AttackType;
	private int Health;
	private String Type;
	private float speed;
	//private Body body;
	private Seek<Vector2> seek;
	private SteeringAcceleration<Vector2> SeekOutput;
	public Unit(String Type,Texture img, float attackRange, attackType attackType, int health,float speed,Body body,TDWorld _world) {
		super(_world,body,img);
		AttackRange = attackRange;
		AttackType = attackType;
		Health = health;
		this.Type = Type;
		this.speed = speed;
		SeekOutput = new SteeringAcceleration<Vector2>(getLinearVelocity());
		seek = new Seek<Vector2>(this, _world.getCastle());
		//Location choice to be moved to logic
	}
	public Unit() {
		super();
	}
	
	//Logic step
	
	@Override
	public void step(){
		seek.calculateSteering(SeekOutput);
		//System.out.println(SeekOutput.linear);
		body.setLinearVelocity(SeekOutput.linear);
		//System.out.println(SeekOutput.linear);
		if (Health <= 0)
			setToDispose(true);
	}
	
	//Getters, setters and implemented functions
	
	public float getAttackRange() {
		return AttackRange;
	}
	public void setAttackRange(float attackRange) {
		AttackRange = attackRange;
	}
	public attackType getAttackType() {
		return AttackType;
	}
	public void setAttackType(attackType attackType) {
		AttackType = attackType;
	}
	public int getHealth() {
		return Health;
	}
	public void setHealth(int health) {
		Health = health;
	}
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return Type;
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
		return 1;
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
	public float getZeroLinearSpeedThreshold() {
		// TODO Auto-generated method stub
		return 0.01f;
	}

	@Override
	public void setZeroLinearSpeedThreshold(float value) {
		// TODO Auto-generated method stub
		
	}
	public Location<Vector2> newLocation() {
		// TODO Auto-generated method stub
		return new Unit();
	}

	@Override
	public boolean handleMessage(Telegram msg) {
		// TODO Auto-generated method stub
		if(msg.message == Messages.Attacked.code)
			Health-=(int) msg.extraInfo;
		return false;
	}

	
}
