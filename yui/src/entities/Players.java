/* Copyright (C) 2019 Huarcaya Rodriguez Julio Ricardo
 * This program is not free software: you can not redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or 
 * any later version.
 */

package entities;


import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import entities.Punto;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import models.TexturedModel;
import renderEngine.DisplayManager;
import terrains.Terrain;

public class Players extends Entity{
	
	private static final float RUN_SPEED=50;
	private static final float TURN_SPEED=50;
	private static final float GRAVITY=-50;
	private static final float JUMP_POWER=30;
	
	private static final float TERRAIN_HEIGHT=0;
	
	private float currentSpeed=0;
	private float currentTurnSpeed=0;
	private float upwardsSpeed=0;
	private double distance1;
	private double time;
	private int count=0;
	private double actualdistance;
	
	private boolean isInAir=false;
	short started=0;

	public Players(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(model, position, rotX, rotY, rotZ, scale);

	}

	public void moves(Terrain terrain, ArrayList<Punto> Pointlist) {
		checkInputs1(Pointlist);
		super.increaseRotation(0, currentTurnSpeed*DisplayManager.getFrameTimeSeconds(), 0);
		float distance=currentSpeed*DisplayManager.getFrameTimeSeconds();
		float dx= (float)(distance*Math.sin(Math.toRadians(super.getRotY())));
		float dz= (float)(distance*Math.cos(Math.toRadians(super.getRotY())));
		super.increasePosition(dx, 0, dz);
		upwardsSpeed +=GRAVITY*DisplayManager.getFrameTimeSeconds();
		super.increasePosition(0, upwardsSpeed*DisplayManager.getFrameTimeSeconds(), 0);
		float terrainHeight=terrain.getHeightOfTerrain(super.getPosition().x, super.getPosition().z);
		if(super.getPosition().y<terrainHeight) {
			upwardsSpeed=0;
			isInAir=false;
			super.getPosition().y=terrainHeight;
		}
	
	}
	
	private void jump() {
		if(!isInAir) {
			this.upwardsSpeed=JUMP_POWER;
			isInAir=false;
			
		}
		//this.upwardsSpeed=JUMP_POWER;
	}

	private void checkInputs1(ArrayList<Punto> Pointlist) {


			Punto punto1=Pointlist.get(count);
			Punto punto2=Pointlist.get(count+1);
			 if (punto2.z>=super.getPosition().z){//(punto2.z>punto1.z)
				 this.rotY=(float)Math.toDegrees(Math.atan((punto2.x-super.getPosition().x)/(punto2.z-super.getPosition().z)));//(punto2.x-punto1.x)/(punto2.z-punto1.z)
			 } if(punto2.z<super.getPosition().z){
				 if (punto2.x<super.getPosition().x) {
				 this.rotY=(float)(-90-(Math.toDegrees(Math.atan((punto2.z-super.getPosition().z)/(punto2.x-super.getPosition().x)))));
			 }
				 if (punto2.x>super.getPosition().x) {
					 this.rotY=(float)(90+(-Math.toDegrees(Math.atan((punto2.z-super.getPosition().z)/(punto2.x-super.getPosition().x))))); 
			 }
			 }
			 if(Keyboard.isKeyDown(Keyboard.KEY_J)) {
					this.currentSpeed=RUN_SPEED;}
			 //if(Keyboard.isKeyDown(Keyboard.KEY_K)) {
			//		this.currentSpeed=-RUN_SPEED;}
			 if(Keyboard.isKeyDown(Keyboard.KEY_L)) {
					this.currentSpeed=0;}
			 if (count==Pointlist.size()-2){
				 if (Math.sqrt(Math.pow(super.getPosition().x-punto2.x, 2)+Math.pow(super.getPosition().z-punto2.z, 2))<RUN_SPEED/100) {
					 this.currentSpeed=0;
				 }	 
			 }
			 if (count!=Pointlist.size()-2){
			 distance1=Math.sqrt(Math.pow(punto1.x-punto2.x, 2)+Math.pow(punto1.z-punto2.z, 2));
			 actualdistance=Math.sqrt(Math.pow(super.getPosition().x-punto2.x, 2)+Math.pow(super.getPosition().z-punto2.z, 2));
			// System.out.println(actualdistance + " count : " + count + " roty: " + this.rotY  + " dx: " + (punto2.x-super.getPosition().x) +  " dz: " + (punto2.z-super.getPosition().z) + " xd : "+(90+(90-Math.toDegrees(Math.atan((punto2.z-super.getPosition().z)/(punto2.x-super.getPosition().x))))));
			 if(actualdistance<(RUN_SPEED/100)) {
				 count++;
			 }
			 }
			 
			 /*distance1=Math.sqrt(Math.pow(punto1.x-punto2.x, 2)+Math.pow(punto1.z-punto2.z, 2));
			 
				time=distance1/RUN_SPEED;
				this.currentSpeed=RUN_SPEED;
				
				long timeinmiliseconds=(int) (time*1000);
				
				int timeinnanoseconds=(int) ( time*1000000000-timeinmiliseconds*1000000);
				//System.out.println(timeinnanoseconds);
				System.out.println("time: " + time*1000000000 + " miliseconds:"+timeinmiliseconds+ " result: "+timeinnanoseconds);
				
				try {
					Thread.sleep(timeinmiliseconds, timeinnanoseconds);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}*/

		
	}
	private void Runto(Punto punto1, Punto punto2) {
		
		distance1=Math.sqrt(Math.pow(punto1.x-punto2.x, 2)+Math.pow(punto1.z-punto2.z, 2));
		time=distance1/RUN_SPEED;
		long timeinmiliseconds=Math.round(time*1000);
		
		int timeinnanoseconds=(int) Math.round(time*1000000000-timeinmiliseconds*1000000);
		
		try {
			Thread.sleep(timeinmiliseconds, timeinnanoseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
	}
	
}
