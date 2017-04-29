package ru.tdproject.td;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
public class TD_Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	TD_Engine Eng;
	//OrthographicCamera camera;
	TestLogic logic;
	@Override
	public void create () {
		logic = new TestLogic();
		batch = new SpriteBatch();
		img = new Texture("dot.jpg");
		Eng = new TD_Engine(logic,"TDE");
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
		batch.draw(img, logic.getPos().x, logic.getPos().y);
		batch.end();
		System.out.println("In Render..."+logic.getPos().x+" "+ logic.getPos().y);
		//camera.update();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
