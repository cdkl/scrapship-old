package com.zanateh.scrapship.state;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class DragManager {

	static Actor selectedActor = null;
	
	public static void setSelected(Actor actor)
	{
		selectedActor = actor;
		
	}
}
