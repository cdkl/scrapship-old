package com.zanateh.scrapship.ship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
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
	
	private Sprite sprite;
	
	public BasicThruster(Vector2 pos, Vector2 vec, float strength) {
		this.pos = new Vector2(pos);
		this.vec = new Vector2(vec);
		this.strength = strength;
		this.power = 0;
		
		sprite = new Sprite(new Texture(Gdx.files.internal("data/thruster.png")));
		sprite.setSize(0.25f, 0.25f);
		sprite.setOrigin(0, 0);//sprite.getHeight()/2);
	}

	@Override
	public void setThrust(float thrust) {
		power = thrust;
	}

	@Override
	public void applyThrust(Body body) {
		body.applyForce(body.getWorldVector(new Vector2(vec).scl(strength*power)), body.getWorldPoint(pos), true);
	}

	public void draw(SpriteBatch batch, Vector2 parentPos, float parentRotationDeg) {
		if( power > 0 ) {
			Vector2 spriteRenderOffset = new Vector2(0, sprite.getHeight()/2);
			spriteRenderOffset.rotate(vec.angle());
					
			Vector2 spritePos = new Vector2(pos);
			spritePos.add(spriteRenderOffset);
			spritePos.rotate(parentRotationDeg);
			spritePos.add(parentPos);		
			sprite.setPosition(spritePos.x, spritePos.y);
			
			float rotation = parentRotationDeg + vec.angle() + 180;
			sprite.setRotation(rotation);
			sprite.draw(batch);
		}
	}
	
}
