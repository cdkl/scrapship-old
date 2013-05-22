package com.zanateh.scrapship.ship;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class BasicThruster implements IThrust {

	// Position of thruster on body
	Vector2 pos;
	// direction of acceleration (NOT the direction of "Thrust")
	Vector2 vec;
	// Strength of thruster
	float strength;
	// Current thruster power setting: should be 0-1
	float power;
	
	public BasicThruster(Vector2 pos, Vector2 vec, float strength) {
		this.pos = new Vector2(pos);
		this.vec = new Vector2(vec);
		this.strength = strength;
		this.power = 0;
	}

	@Override
	public void setThrust(float thrust) {
		power = thrust;
	}

	@Override
	public void applyThrust(Body body) {
		body.applyForce(body.getWorldVector(new Vector2(vec).scl(strength*power)), body.getWorldPoint(pos), true);
	}

	
}
