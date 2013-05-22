package com.zanateh.scrapship.state;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.zanateh.scrapship.ScrapShipGame;
import com.zanateh.scrapship.Ship;
import com.zanateh.scrapship.ShipControl;

public class PlayState extends GameState {

	private World world;
	
	// Debug display stuff for box2d
	private Matrix4 debugMatrix;
	Box2DDebugRenderer debugRenderer;

	ArrayList<Ship> shipList = new ArrayList<Ship>();
	
	PlayStateInputProcessor inputProcessor;
	
	boolean debugRender = false;
	public boolean getDebugRender() { return debugRender; }

	
	@Override
	public void Init(ScrapShipGame game) {
		super.Init(game);

		world = new World(new Vector2(0,0.0f), false);
		debugRenderer = new Box2DDebugRenderer();
		
		inputProcessor = new PlayStateInputProcessor(this);
		Gdx.input.setInputProcessor(inputProcessor);
		
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
	public void Cleanup() {
		// TODO Auto-generated method stub
		world.dispose();

		for(Ship ship : shipList)
		{
			ship.dispose();
		}
		shipList.clear();
		Gdx.input.setInputProcessor(null);
		
	}

	@Override
	public void Pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void HandleEvents(ScrapShipGame game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Update(ScrapShipGame game) {
		for(Ship ship : shipList ) {
			ship.update();
		}

		world.step(game.getUpdateFrame(),7,3);
		world.clearForces();
	}

	@Override
	public void Draw(ScrapShipGame game) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		game.getCamera().update();
		game.getSpriteBatch().setProjectionMatrix(game.getCamera().combined);
		game.getSpriteBatch().begin();
		
		if(getDebugRender()) {
			debugMatrix = new Matrix4(game.getCamera().combined);
			debugRenderer.render(world,  debugMatrix);
		}

		
		for(Ship ship : shipList ) {
			ship.draw(game.getSpriteBatch());
		}

		game.getSpriteBatch().end();
	}

	public void toggleDebugRender() {
		debugRender =! debugRender;
	}


	public void reset() {
		game.changeState(new PlayState());
	}
	
}