package ru.tdproject.td;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import ru.tdproject.td.Objects.BaseObject;
import ru.tdproject.td.Objects.Unit;
import ru.tdproject.td.game.TDContext;
import ru.tdproject.td.game.TDInputHandler;
import ru.tdproject.td.game.TDWorld;
import ru.tdproject.td.game.TD_Engine;

import com.badlogic.gdx.ai.*;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
public class TD_Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	TD_Engine Eng;
	private OrthographicCamera camera;
	TDWorld _world;
	Unit unit1,unit2;
	Vector2 Castle;
	Vector2 Spawn;
	TDInputHandler InputHandler;
	TiledMapRenderer MapRender;
	TiledMap map;
	public static TDContext _context = new TDContext();
	BaseObject o;
	public MessageDispatcher TD_Msg;
	Box2DDebugRenderer debugRenderer;
	Iterator iter;
	Vector3 ScreenCoords;
	@Override
	public void create () {
		Box2D.init();
		debugRenderer = new Box2DDebugRenderer();
		Castle = new Vector2(200f,200f);
		Spawn = new Vector2(200f,600f);
		map = new TmxMapLoader().load("TestMap.tmx");
		_world = new TDWorld(map);
		Eng = new TD_Engine(_world,"TDE");
		batch = new SpriteBatch();
		InputHandler = new TDInputHandler(_world,this, _context);
		Thread eng_thr = new Thread(Eng);
		eng_thr.start();
		ScreenCoords = new Vector3();
		setCamera(new OrthographicCamera());
		getCamera().setToOrtho(false, 800, 800);
		getCamera().zoom = 1;
		MapRender = new OrthogonalTiledMapRenderer(_world.getMap());
		Iterator iter;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		getCamera().translate(InputHandler.Delta.x,InputHandler.Delta.y);
		//camera.combined.mul(MeterToTile);
		batch.setProjectionMatrix(getCamera().combined);
		MapRender.setView(getCamera());
		MapRender.render();
		batch.begin();
		
		synchronized (_world.Iter_lock) {
			iter = _world.getUnits().iterator();
			while (iter.hasNext()) {
				o = (BaseObject) iter.next();
				o.draw(batch);
			}
		}
		
		batch.end();
		debugRenderer.render(_world.getWorld(), getCamera().combined.scl(TDContext.PIX_TO_METER));
		getCamera().update();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public void setCamera(OrthographicCamera camera) {
		this.camera = camera;
	}
}
