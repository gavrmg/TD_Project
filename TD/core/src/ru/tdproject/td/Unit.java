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
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.steer.behaviors.Seek;
import com.badlogic.gdx.ai.utils.Location;
public class Unit extends BaseObject implements Steerable<Vector2> {
	private float AttackRange;
	private attackType AttackType;
	private int Health;
	private String Type;
	private float speed;
	private Body body;
	private Seek<Vector2> seek;
	private SteeringAcceleration<Vector2> SeekOutput;
	private TDWorld _world;
	public Unit(String Type,Texture img, float attackRange, attackType attackType, int health,float speed,Body body,TDWorld _world) {
		//super();
		setImg(img);
		AttackRange = attackRange;
		AttackType = attackType;
		Health = health;
		this.Type = Type;
		this.speed = speed;
		this.body = body;
		SeekOutput = new SteeringAcceleration<Vector2>(getLinearVelocity());
		this._world = _world;
		seek = new Seek<Vector2>(this, _world.getCastle());
		//Location choice to be moved to logic
	}
	
	//Logic step
	
	@Override
	public void step(TDWorld world){
		seek.calculateSteering(SeekOutput);
		//System.out.println(SeekOutput.linear);
		body.setLinearVelocity(SeekOutput.linear);
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
	public ru.tdproject.td.ObjectType getObjectType() {
		// TODO Auto-generated method stub
		return ObjectType.Active;
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
	public Vector2 angleToVector(Vector2 outVector, float angle) {
		// TODO Auto-generated method stub
		return ((Vector2)outVector).set((float)Math.sin(angle),(float)Math.cos(angle));
	}
	@Override
	public Location newLocation() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public float getZeroLinearSpeedThreshold() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setZeroLinearSpeedThreshold(float value) {
		// TODO Auto-generated method stub
		
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
		return 0;
	}
	@Override
	public void setMaxLinearAcceleration(float maxLinearAcceleration) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public float getMaxAngularSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setMaxAngularSpeed(float maxAngularSpeed) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public float getMaxAngularAcceleration() {
		// TODO Auto-generated method stub
		return 0;
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

}
