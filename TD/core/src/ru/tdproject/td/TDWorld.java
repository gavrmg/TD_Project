package ru.tdproject.td;
import java.util.ArrayList;

import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import ru.tdproject.td.GameLogic.ProjectileContactFilter;
public class TDWorld {
	private World World;
	private TiledMap Map;
	public Object Iter_lock = new Object();
	public Texture UnitImg = new Texture("dot.jpg");
	public Texture TowerImg = new Texture("tower.png");
	private Texture CastleImg = new Texture("castle.png");
	private Texture ProjectileImg = new Texture("projectile.png");
	private Vector2 MaxCoordsPix;
	private TDContext _context = new TDContext();
	private Castle castle;
	private ArrayList<BaseObject> Units;
	private ArrayList<BaseObject> ToAdd;
	public ContactFilter filter;
	public TDWorld(TiledMap map) {
		super();
		filter = new ProjectileContactFilter();
		World = new com.badlogic.gdx.physics.box2d.World(new Vector2(0,0),true);
		World.setContactFilter(filter);
		Map = map;
		//MaxCoordsPix = new Vector2(map.getProperties().get(key))
		System.out.println(map.getProperties().getKeys().toString());
		Units = new ArrayList<BaseObject>();
		ToAdd = new ArrayList<BaseObject>();
		castle = createCastle(3, CastleImg, 0, 10, 0, 10f, 10f);
	}
	public Castle getCastle() {
		return castle;
	}
	public void setCastle(Castle castle) {
		this.castle = castle;
	}
	public World getWorld() {
		return World;
	}
	public void setWorld(World world) {
		World = world;
	}
	public TiledMap getMap() {
		return Map;
	}
	public void setMap(TiledMap map) {
		Map = map;
	}
	public Body CreateWorldActorBody(float x, float y,BodyType Type, Shape shape){
		//You have to create a shape BEFORE calling this function
		BodyDef Definition = new BodyDef();
		Definition.active=true;
		Definition.awake=true;
		Definition.type = Type;
		Definition.position.set(x, y);
		FixtureDef FixDef = new FixtureDef();
		FixDef.shape = shape;
		FixDef.density = 1;
		FixDef.friction = 1;
		FixDef.restitution = 1;
		
		Body body = World.createBody(Definition);
		body.createFixture(FixDef);
		return body;
	}
	public Body CreateBody(float x, float y,BodyType Type){
		//You have to create a shape BEFORE calling this function
		BodyDef Definition = new BodyDef();
		Definition.active=true;
		Definition.awake=true;
		Definition.type = BodyType.KinematicBody;
		Definition.position.set(x, y);
		Body body = World.createBody(Definition);
		return body;
	}
	public Body CreateFixtureForBody(Body body,Shape shape,boolean IsSensor){
		//You have to create a shape BEFORE calling this function
		FixtureDef Definition = new FixtureDef();
		Definition.shape = shape;
		Definition.isSensor=IsSensor;
		body.createFixture(Definition);
		return body;
	}

	public void createUnit(float Size,Texture img,float attackRange,attackType AT,int Health,float speed,float x, float y){
		CircleShape shape = new CircleShape();
		shape.setRadius(Size);
		Body body = CreateWorldActorBody(x, y, BodyType.DynamicBody, shape);
		if (AT == attackType.Ranged){
			CircleShape AttackRangeSensor = new CircleShape();
			AttackRangeSensor.setRadius(attackRange);
			CreateFixtureForBody(body,AttackRangeSensor,true);
			AttackRangeSensor.dispose();
		}
		Unit ThisUnit = new Unit("Unit", img, attackRange,AT, Health,speed,body,this);
		body.setUserData(ThisUnit);
			ToAdd.add(ThisUnit);
		shape.dispose();
	}
	public ArrayList<BaseObject> getUnits() {
		return Units;
	}
	public void setUnits(ArrayList<BaseObject> units) {
		Units = units;
	}
	public void createTower(float Size,Texture img,float attackRange,int Health,float speed,float x, float y){
		CircleShape shape = new CircleShape();
		shape.setRadius(Size);
		synchronized (Iter_lock) {
			Body body = CreateWorldActorBody(x, y, BodyType.StaticBody, shape);
			CircleShape AttackRangeSensor = new CircleShape();
			AttackRangeSensor.setRadius(attackRange);
			CreateFixtureForBody(body,AttackRangeSensor,true);
			AttackRangeSensor.dispose();
			Tower ThisUnit = new Tower("Tower", img, attackRange, Health, body, this);
			body.setUserData(ThisUnit);
			ToAdd.add(ThisUnit);
		}
		shape.dispose();
	}
	public void createProjectile(int Damage,Location<Vector2> Target,float x,float y){
		CircleShape shape = new CircleShape();
		shape.setRadius(0.1f);
		synchronized (Iter_lock) {
			Body body = CreateWorldActorBody(x, y, BodyType.DynamicBody, shape);
			Projectile ThisUnit = new Projectile(body,5f, ProjectileImg, this,Damage,Target);
			body.setUserData(ThisUnit);
			ToAdd.add(ThisUnit);
		}
		shape.dispose();
	}
	public ArrayList<BaseObject> getToAdd() {
		return ToAdd;
	}
	public void setToAdd(ArrayList<BaseObject> toAdd) {
		ToAdd = toAdd;
	}
	public Castle createCastle(float Size,Texture img,float attackRange,int Health,float speed,float x, float y){
		CircleShape shape = new CircleShape();
		shape.setRadius(Size);
		synchronized (Iter_lock) {
			Body body = CreateWorldActorBody(x, y, BodyType.StaticBody, shape);
			Castle ThisUnit = new Castle(Health, img,  "Castle",body,this);
			shape.dispose();
			Units.add(ThisUnit);
			return ThisUnit;
		}
	}
//	public void createSolidObject(RectangleMapObject object){
//		PolygonShape shape = new PolygonShape();
//		shape.setAsBox(object.getRectangle().);
//		SolidObject  ThisUnit = new SolidObject();
//		synchronized (Iter_lock) {
//			Body body = CreateWorldActorBody(1, object.getPolygon().getX(), object.getPolygon().getY(), BodyType.StaticBody, shape);
//			body.setUserData(ThisUnit);
//		}
//		shape.dispose();
//	}
	public void initWorld(){
		Body body = CreateBody(0,0,BodyType.StaticBody);
		PolygonShape shape = new PolygonShape(); 
		Vector2 Center = new Vector2();
		Rectangle obj = new Rectangle();
		MapObjects objects = new MapObjects();
		MapLayers Layers = Map.getLayers();
		synchronized (Iter_lock) {
			for (MapLayer o : Layers) {
				if (o.getProperties().containsKey("Solid")) {
					objects = o.getObjects();
					for (MapObject Obj : objects) {
						obj = ((RectangleMapObject) Obj).getRectangle();
						shape.setAsBox(obj.getHeight() / (2*TDContext.PIX_TO_METER), obj.getWidth() / (2*TDContext.PIX_TO_METER),
								obj.getCenter(Center).scl(1f/TDContext.PIX_TO_METER)/*.add(0, -_context.MAP_SIZE)*/,
								0);//Magic constant representing 1 tile per meter. To be moved into TDContext
						body.createFixture(shape, 0f);
					}
				}
			}
		}
		shape.dispose();
	}
	
//	public void LogicStep(){
//		World.
//	}
}
