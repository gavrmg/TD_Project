package ru.tdproject.td;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
public class TD_Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	TD_Engine Eng;
	OrthographicCamera camera;
	World _world;
	Unit unit1,unit2;
	Vector2 Castle;
	Vector2 Spawn;
	TDInputHandler InputHandler;
	@Override
	public void create () {
		Castle = new Vector2(200f,200f);
		Spawn = new Vector2(200f,600f);
		_world = new World(Castle,Spawn);
		//img = new Texture("dot.jpg");
		InputHandler = new TDInputHandler(_world);
		
		batch = new SpriteBatch();
		Eng = new TD_Engine(_world,"TDE");
		
		Thread eng_thr = new Thread(Eng);
		eng_thr.start();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 800);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		_world.draw(batch);
		//System.out.println(batch.maxSpritesInBatch);
		batch.end();
		camera.update();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
	}
}
