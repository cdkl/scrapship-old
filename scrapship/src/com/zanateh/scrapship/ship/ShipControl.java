package com.zanateh.scrapship.ship;

import java.util.ArrayList;

public class ShipControl {
	
	private float forwardThrust = 0;
	public ArrayList<IThrust> forwardThrusters = new ArrayList<IThrust>();
	private float reverseThrust = 0;
	public ArrayList<IThrust> reverseThrusters = new ArrayList<IThrust>();
	private float leftThrust = 0;
	public ArrayList<IThrust> leftThrusters = new ArrayList<IThrust>();
	private float rightThrust = 0;
	public ArrayList<IThrust> rightThrusters = new ArrayList<IThrust>();
	
	public void setForwardThrust(float thrust) {
		forwardThrust = thrust;
		for(IThrust thruster : forwardThrusters) {
			thruster.setThrust(thrust);
		}
	}

	public void setReverseThrust(float thrust) {
		reverseThrust = thrust;
		for(IThrust thruster : reverseThrusters) {
			thruster.setThrust(thrust);
		}
	}
	
	public void setLeftThrust(float thrust) {
		leftThrust = thrust;
		for(IThrust thruster : leftThrusters) {
			thruster.setThrust(thrust);
		}
	}
	
	public void setRightThrust(float thrust) {
		rightThrust = thrust;
		for(IThrust thruster : rightThrusters) {
			thruster.setThrust(thrust);
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
