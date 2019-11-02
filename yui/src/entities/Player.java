package entities;

import models.TexturedModel;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.DisplayManager;
import terrains.Terrain;
import toolbox.FileCreator;
import toolbox.Readfile;

public class Player extends Entity {

	private static final float RUN_SPEED = 20;
	private static final float TURN_SPEED = 160;
	private static final float GRAVITY = 0;
	private static final float JUMP_POWER = 50;

	private float currentSpeed = 0;
	private float currentTurnSpeed = 0;
	private float upwardsSpeed = 0;

	private boolean isInAir = false;
	private ArrayList<Punto> Pointlist= new ArrayList<Punto>();

	public Player(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ,
			float scale) {
		super(model, position, rotX, rotY, rotZ, scale);
	}

	public void move(Terrain terrain) {
		checkInputs();
		super.increaseRotation(0, currentTurnSpeed * DisplayManager.getFrameTimeSeconds(), 0);
		float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
		float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
		float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
		super.increasePosition(dx, 0, dz);
		upwardsSpeed += GRAVITY * DisplayManager.getFrameTimeSeconds();
		super.increasePosition(0, upwardsSpeed * DisplayManager.getFrameTimeSeconds(), 0);
		float terrainHeight = terrain.getHeightOfTerrain(getPosition().x, getPosition().z);
		if (super.getPosition().y < terrainHeight) {
			upwardsSpeed = 0;
			isInAir = false;
			super.getPosition().y = terrainHeight;
		}
	}

	private void jump() {
		if (!isInAir) {
			this.upwardsSpeed = JUMP_POWER;
			isInAir = false;
		}
	}

	private void checkInputs() {
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			this.currentSpeed = RUN_SPEED;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			this.currentSpeed = -RUN_SPEED;
		}
		else {
			this.currentSpeed = 0;
		}
		if((Keyboard.isKeyDown(Keyboard.KEY_W))&&(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))){
			this.currentSpeed = RUN_SPEED*10;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			this.currentTurnSpeed = -TURN_SPEED;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			this.currentTurnSpeed = TURN_SPEED;
		} else {
			this.currentTurnSpeed = 0;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			jump();
		}else if(Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			this.upwardsSpeed=-JUMP_POWER;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_E)) {
			this.upwardsSpeed=0;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
			if (Pointlist.size()==0) {
				Pointlist.add(new Punto(this.getPosition().x,this.getPosition().z));
			}
			
			if(!(Pointlist.size()==0)) {
				//(new Punto(this.getPosition().x,this.getPosition().z).equals(Pointlist.get(Pointlist.size()-1
				if(!(this.getPosition().x==Pointlist.get(Pointlist.size()-1).x)){
					if(!(this.getPosition().z==Pointlist.get(Pointlist.size()-1).z)){
				Pointlist.add(new Punto(this.getPosition().x,this.getPosition().z));
			}
				}
			}
		}else if(Keyboard.isKeyDown(Keyboard.KEY_P)){
			FileCreator s= new FileCreator(Pointlist);
		}
	}

}
