package com.zanateh.scrapship;

import java.awt.List;
import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;

public class ScrapShipGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	float updateFrame = 1.0f/60.0f;
	public float getUpdateFrame() { return updateFrame; }
	float leftoverRender = 0.0f;
	
	private World world;
	public World getWorld() { return world; }
	
	ArrayList<Ship> shipList = new ArrayList<Ship>();
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024, 768);
		batch = new SpriteBatch();
	
		world = new World(new Vector2(0,10), false);
		shipList.add(new Ship(world));
	}

	@Override
	public void dispose() {
		batch.dispose();
		world.dispose();

		for(Ship ship : shipList)
		{
			ship.dispose();
		}
	}

	@Override
	public void render() {		
		// Run the update loop as often as necessary to advance the game engine.
		float deltaTime = Gdx.graphics.getDeltaTime() + leftoverRender;
		while( deltaTime >= updateFrame ) {
			deltaTime -= updateFrame;
			update();
		}
		
		leftoverRender = deltaTime;
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for(Ship ship : shipList ) {
			ship.draw(batch);
		}
		batch.end();
		
	}

	public void update()
	{
//		if(Gdx.input.isTouched()) {
//			Vector3 touchPos = new Vector3();
//			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//			camera.unproject(touchPos);
//			shipRect.x = touchPos.x - shipRect.width / 2;
//			shipRect.y = touchPos.y - shipRect.height / 2;
//		}
		world.step(updateFrame,7,3);
		for(Ship ship : shipList ) {
			ship.update();
		}
		world.clearForces();
	}
	
	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
