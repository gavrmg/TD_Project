package ru.tdproject.td;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
public class TD_Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	TD_Engine Eng;
	//OrthographicCamera camera;
	World _world;
	Unit unit1;
	
	@Override
	public void create () {
		_world = new World();
		img = new Texture("dot.jpg");
		
		unit1 = new Unit("A", new Vector2(200f,200f), 10f, new Vector2(1f,0f), true, img);
		_world.addObject(unit1);
		
		batch = new SpriteBatch();
		Eng = new TD_Engine(_world,"TDE");
		
		Thread eng_thr = new Thread(Eng);
		eng_thr.start();
		
		//camera = new OrthographicCamera();
		//camera.setToOrtho(false, 800, 800);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//batch.setProjectionMatrix(camera.combined);
		batch.begin();
		_world.draw(batch);
		batch.end();
		//camera.update();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
