package com.zanateh.scrapship.camera;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.zanateh.scrapship.ScrapShipGame;

public class CameraManager {

	Vector3 cameraPos = new Vector3();

	public enum CameraMode {
		Free,
		Target
	};
	
	CameraMode cameraMode = CameraMode.Free;
	private IHasPosition target = null;
	
	public CameraManager() {
	}

	public void setCameraMode(CameraMode cameraMode)
	{
		this.cameraMode = cameraMode; 
	}
	
	public void setTarget(IHasPosition target) {
		this.target = target;
	}
	
	public void setCameraPos(Vector3 cameraPos) {
		this.cameraPos.set(cameraPos);
	}
	
	public void setupRenderCamera(ScrapShipGame game)
	{
		if(this.cameraMode == CameraMode.Target) {
			Vector2 targetPos = target.getPosition();
			setCameraPos(new Vector3(targetPos.x, targetPos.y, 0));
		}
		
		Camera camera = game.getCamera();
		camera.translate(new Vector3(this.cameraPos).sub(camera.position));
		camera.update();
	}
	
}
