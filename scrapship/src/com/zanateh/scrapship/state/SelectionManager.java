package com.zanateh.scrapship.state;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class SelectionManager {

	ISelectable selectedActor = null;
	Vector2 selectionPosition = new Vector2(0,0);
	
	public void setSelected(ISelectable actor)
	{
		selectedActor = actor;
		selectedActor.select();
	}
	
	public ISelectable releaseSelected()
	{
		ISelectable actor = selectedActor;
		actor.release();
		selectedActor = null;
		return actor;
	}
	
	public ISelectable getSelected() {
		return selectedActor;
	}
	
	public boolean isSelected(ISelectable actor)
	{
		return actor == selectedActor;
	}

	public void setSelectionPosition(Vector2 stageCoordinates) {
		this.selectionPosition.set(stageCoordinates);
		if( this.selectedActor != null ) {
			this.selectedActor.setPosition(selectionPosition);
		}
	}
}
