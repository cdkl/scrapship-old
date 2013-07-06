package com.zanateh.scrapship.state;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zanateh.scrapship.camera.CameraManager;
import com.zanateh.scrapship.scene.ScrapShipStage;
import com.zanateh.scrapship.ship.ShipControl;

public class PlayStateInputProcessor extends ScrapShipStage {

	PlayState state;
	ShipControl shipControl = null;
	CameraManager cameraManager = null;
	
	public PlayStateInputProcessor(PlayState state,int width, int height, boolean keepAspect,
			SpriteBatch spriteBatch) {
		super(width, height, keepAspect, spriteBatch);
		this.state = state;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if( keycode == Input.Keys.D ) {
			if(cameraManager != null ) cameraManager.toggleDebugRender();
			return true;
		}
		
		if( shipControl != null ) {
			if( keycode == Input.Keys.UP ) {
				shipControl.setForwardThrust(1);
				return true;
			}
			if( keycode == Input.Keys.DOWN ) {
				shipControl.setReverseThrust(1);
				return true;
			}
			if( keycode == Input.Keys.LEFT ) {
				shipControl.setLeftThrust(1);
				return true;
			}	
			if( keycode == Input.Keys.RIGHT ) {
				shipControl.setRightThrust(1);
				return true;
			}	
		}
		
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if( shipControl != null ) {
			if( keycode == Input.Keys.UP ) {
				shipControl.setForwardThrust(0);
				return true;
			}
			if( keycode == Input.Keys.DOWN ) {
				shipControl.setReverseThrust(0);
				return true;
			}
			if( keycode == Input.Keys.LEFT ) {
				shipControl.setLeftThrust(0);
				return true;
			}	
			if( keycode == Input.Keys.RIGHT ) {
				shipControl.setRightThrust(0);
				return true;
			}	
		}
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		if( character == '`') {
			state.reset();
			return true;
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		if(cameraManager != null ) cameraManager.incrementZoom(amount);
		return true;
	}

	public void setShipControl(ShipControl shipControl) {
		this.shipControl = shipControl;
	}

	public void removeShipControl(ShipControl shipControl) {
		if(this.shipControl == shipControl) {
			this.shipControl = null;
		}
	}
	
	public void setCameraManager(CameraManager cameraManager) {
		this.cameraManager = cameraManager;
	}

}
