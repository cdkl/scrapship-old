package com.zanateh.scrapship.scene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class ScrapShipStage extends Stage {

	public ScrapShipStage(int width, int height, boolean keepAspect,
			SpriteBatch spriteBatch) 
	{
		super(width, height, keepAspect, spriteBatch);
	}

	public void postUpdate() {
		for( Actor actor : this.getActors()) {
			if(actor instanceof ScrapShipActor) {
				((ScrapShipActor) actor).postUpdate();
			}
			else if( actor instanceof ScrapShipActorGroup) {
				((ScrapShipActorGroup) actor).postUpdate();
			}
		}
	}
}
