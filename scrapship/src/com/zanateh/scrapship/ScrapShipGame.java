package com.zanateh.scrapship;

import java.awt.List;
import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
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
	
	// Debug display stuff for box2d
	private Matrix4 debugMatrix;
	Box2DDebugRenderer debugRenderer;
	boolean debugRender = false;
	
	@Override
	public void create() {		
		ScrapInputProcessor inputProcessor = new ScrapInputProcessor(this);
		Gdx.input.setInputProcessor(inputProcessor);
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 10, 7);
		batch = new SpriteBatch();
	
		world = new World(new Vector2(0,0.0f), false);
		debugRenderer = new Box2DDebugRenderer();
		
		
		Ship ship1 = new Ship(world);
		ship1.setPosition(new Vector2(0.5f,3.5f));
		ship1.setVelocity(new Vector2(1,0));
		shipList.add(ship1);
		Ship ship2 = new Ship(world);
		ship2.setPosition(new Vector2(8,4.4f));
		ship2.setVelocity(new Vector2(-1,0));
		shipList.add(ship2);
		
		ShipControl control = new ShipControl();
		inputProcessor.setShipControl(control);
		ship1.setShipControl(control);
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

		if(debugRender) {
			debugMatrix = new Matrix4(camera.combined);
			debugRenderer.render(world,  debugMatrix);
		}
		batch.end();
		
	}

	public void update()
	{
		for(Ship ship : shipList ) {
			ship.update();
		}

		world.step(updateFrame,7,3);
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

	public void toggleDebugRender() {
		debugRender =! debugRender;
	}
}
