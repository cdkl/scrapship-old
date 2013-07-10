package com.zanateh.scrapship.ship;

import java.util.ArrayList;

import com.zanateh.scrapship.ship.component.ComponentThruster;
import com.zanateh.scrapship.ship.component.ThrustAction;

public class ShipControl {
	
	private float forwardThrust = 0;
	public ArrayList<ComponentThruster> forwardThrusters = new ArrayList<ComponentThruster>();
	private float reverseThrust = 0;
	public ArrayList<ComponentThruster> reverseThrusters = new ArrayList<ComponentThruster>();
	private float leftThrust = 0;
	public ArrayList<ComponentThruster> leftThrusters = new ArrayList<ComponentThruster>();
	private float rightThrust = 0;
	public ArrayList<ComponentThruster> rightThrusters = new ArrayList<ComponentThruster>();
	
	public void setForwardThrust(float thrust) {
		forwardThrust = thrust;
		for(ComponentThruster thruster : forwardThrusters) {
			thruster.addAction(new ThrustAction(thrust));
		}
	}

	public void setReverseThrust(float thrust) {
		reverseThrust = thrust;
		for(ComponentThruster thruster : reverseThrusters) {
			thruster.addAction(new ThrustAction(thrust));
		}
	}
	
	public void setLeftThrust(float thrust) {
		leftThrust = thrust;
		for(ComponentThruster thruster : leftThrusters) {
			thruster.addAction(new ThrustAction(thrust));
		}
	}
	
	public void setRightThrust(float thrust) {
		rightThrust = thrust;
		for(ComponentThruster thruster : rightThrusters) {
			thruster.addAction(new ThrustAction(thrust));
		}
	}

	public void remove() {
		forwardThrusters.clear();		
		reverseThrusters.clear();
		leftThrusters.clear();
		rightThrusters.clear();
	}
	
	public void dispose() {
		remove();
	}
}
