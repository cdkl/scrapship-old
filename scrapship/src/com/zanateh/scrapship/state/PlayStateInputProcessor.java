package com.zanateh.scrapship.state;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.zanateh.scrapship.ship.ShipControl;

public class PlayStateInputProcessor implements InputProcessor {

	PlayState state;
	ShipControl shipControl;
	
	public PlayStateInputProcessor(PlayState game) {
		this.state = game;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if( keycode == Input.Keys.D ) {
			state.toggleDebugRender();
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
		// TODO Auto-generated method stub
		return false;
	}

	public void setShipControl(ShipControl shipControl) {
		this.shipControl = shipControl;
	}

	public void removeShipControl(ShipControl shipControl) {
		if(this.shipControl == shipControl) {
			this.shipControl = null;
		}
	}

}
