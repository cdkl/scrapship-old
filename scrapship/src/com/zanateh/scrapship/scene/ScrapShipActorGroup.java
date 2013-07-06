package com.zanateh.scrapship.scene;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class ScrapShipActorGroup extends Group {

	public void postUpdate()
	{
		for( Actor actor : getChildren() ) {
			if( actor instanceof ScrapShipActor ) {
				((ScrapShipActor) actor).postUpdate();
			}
			else if( actor instanceof ScrapShipActorGroup) {
				((ScrapShipActorGroup) actor).postUpdate();
			}
			
		}
	}
	

}
