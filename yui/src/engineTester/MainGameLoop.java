package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.RawModel;
import models.TexturedModel;
import normalMappingObjConverter.NormalMappedObjLoader;
import objConverter.ModelData;
import objConverter.OBJFileLoader;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrains.Terrain;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;
import toolbox.Maths;
import toolbox.MousePicker;
import toolbox.Readfile;
import water.WaterFrameBuffers;
import water.WaterRenderer;
import water.WaterShader;
import water.WaterTile;
import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import entities.Players;
import entities.Punto;
import guis.GuiRenderer;
import guis.GuiTexture;
/* Copyright (C) 2019 Huarcaya Rodriguez Julio Ricardo
 * This program is not free software: you can not redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or 
 * any later version.
 */
public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
		Readfile r1= new Readfile("1-2.txt");
		ArrayList<Punto> Pointlist = r1.getPointlist();
		Readfile r2= new Readfile("2-3.txt");
		ArrayList<Punto> Pointlist2 = r2.getPointlist();
		Readfile r3= new Readfile("3-6.txt");
		ArrayList<Punto> Pointlist3 = r3.getPointlist();
		Readfile r4= new Readfile("5-6.txt");
		ArrayList<Punto> Pointlist4 = r4.getPointlist();
		Readfile r5= new Readfile("6-9.txt");
		ArrayList<Punto> Pointlist5 = r5.getPointlist();
		Readfile r6= new Readfile("7-6.txt");
		ArrayList<Punto> Pointlist6 = r6.getPointlist();
		Readfile r7= new Readfile("8-9.txt");
		ArrayList<Punto> Pointlist7 = r7.getPointlist();
		Readfile r8= new Readfile("9-4.txt");
		ArrayList<Punto> Pointlist8 = r8.getPointlist();
		Readfile r9= new Readfile("10-11.txt");
		ArrayList<Punto> Pointlist9 = r9.getPointlist();
		Readfile r10= new Readfile("1.txt");
		ArrayList<Punto> Pointlist10 = r10.getPointlist();
		Readfile r11= new Readfile("2.txt");
		ArrayList<Punto> Pointlist11 = r11.getPointlist();
		Readfile r12= new Readfile("3.txt");
		ArrayList<Punto> Pointlist12 = r12.getPointlist();
		Readfile r13= new Readfile("4.txt");
		ArrayList<Punto> Pointlist13 = r13.getPointlist();
		Readfile r14= new Readfile("5.txt");
		ArrayList<Punto> Pointlist14 = r14.getPointlist();
		Readfile r15= new Readfile("6.txt");
		ArrayList<Punto> Pointlist15 = r15.getPointlist();
		Readfile r16= new Readfile("7.txt");
		ArrayList<Punto> Pointlist16 = r16.getPointlist();

		// *********TERRAIN STUFF**********
		RawModel carromodel=OBJLoader.loadObjModel("carrito3", loader);
		RawModel excavadoramodel=OBJLoader.loadObjModel("excavadora", loader);
		RawModel ripmodel=OBJLoader.loadObjModel("tum", loader);
		RawModel rosemodel=OBJLoader.loadObjModel("rose", loader);
		RawModel perfmodel=OBJLoader.loadObjModel("perf", loader);
		
		TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("text"));
		TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("mud"));
		TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("textgris"));
		TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path"));
		TexturedModel textcarro=new TexturedModel(carromodel,new ModelTexture(loader.loadTexture("texturapintada4")));
		TexturedModel textexcavadora=new TexturedModel(excavadoramodel,new ModelTexture(loader.loadTexture("ex4")));
		TexturedModel textrip=new TexturedModel(ripmodel,new ModelTexture(loader.loadTexture("texttum")));
		TexturedModel textrose=new TexturedModel(rosemodel,new ModelTexture(loader.loadTexture("rosetext")));
		TexturedModel textperf=new TexturedModel(perfmodel,new ModelTexture(loader.loadTexture("textperf")));

		TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture,gTexture, bTexture);
		TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));

		// *****************************************

		ModelTexture fernTextureAtlas = new ModelTexture(loader.loadTexture("fern"));fernTextureAtlas.setNumberOfRows(2);

		TexturedModel fern = new TexturedModel(OBJFileLoader.loadOBJ("fern", loader),fernTextureAtlas);

		TexturedModel bobble = new TexturedModel(OBJFileLoader.loadOBJ("pine", loader),new ModelTexture(loader.loadTexture("pine")));
		bobble.getTexture().setHasTransparency(true);

		fern.getTexture().setHasTransparency(true);

		                                                                            Terrain terrain = new Terrain(0, -1, loader, texturePack,blendMap, "jeimap");
		List<Terrain> terrains = new ArrayList<Terrain>();
		terrains.add(terrain);

		TexturedModel lamp = new TexturedModel(OBJLoader.loadObjModel("lamp", loader),new ModelTexture(loader.loadTexture("lamp")));
		lamp.getTexture().setUseFakeLighting(true);

		List<Entity> entities = new ArrayList<Entity>();
		List<Entity> normalMapEntities = new ArrayList<Entity>();
		
		//******************NORMAL MAP MODELS************************
		
		TexturedModel barrelModel = new TexturedModel(NormalMappedObjLoader.loadOBJ("barrel", loader),
				new ModelTexture(loader.loadTexture("barrel")));
		barrelModel.getTexture().setNormalMap(loader.loadTexture("barrelNormal"));
		barrelModel.getTexture().setShineDamper(10);
		barrelModel.getTexture().setReflectivity(0.5f);
		
		TexturedModel crateModel = new TexturedModel(NormalMappedObjLoader.loadOBJ("crate", loader),
				new ModelTexture(loader.loadTexture("crate")));
		crateModel.getTexture().setNormalMap(loader.loadTexture("crateNormal"));
		crateModel.getTexture().setShineDamper(10);
		crateModel.getTexture().setReflectivity(0.5f);
		
		TexturedModel boulderModel = new TexturedModel(NormalMappedObjLoader.loadOBJ("boulder", loader),
				new ModelTexture(loader.loadTexture("boulder")));
		boulderModel.getTexture().setNormalMap(loader.loadTexture("boulderNormal"));
		boulderModel.getTexture().setShineDamper(10);
		boulderModel.getTexture().setReflectivity(0.5f);
		
		
		//************ENTITIES*******************
		
		Entity entity = new Entity(barrelModel, new Vector3f(75, 10, -75), 0, 0, 0, 1f);
		Entity entity2 = new Entity(boulderModel, new Vector3f(85, 10, -75), 0, 0, 0, 1f);
		Entity entity3 = new Entity(crateModel, new Vector3f(65, 10, -75), 0, 0, 0, 0.04f);
		normalMapEntities.add(entity);
		normalMapEntities.add(entity2);
		normalMapEntities.add(entity3);
		float coorax=920.0138f;
		float cooraz=-507.54327f;
		//Maths.getRandomAround(920.0138f, -507.5432, limit, terrain)
		//Maths.getRandomAround(x, z)

		
		

		//*******************OTHER SETUP***************

		List<Light> lights = new ArrayList<Light>();
		Light sun = new Light(new Vector3f(10000, 10000, -1000), new Vector3f(1.3f, 1.3f, 1.3f));
		lights.add(sun);

		MasterRenderer renderer = new MasterRenderer(loader);

		RawModel bunnyModel = OBJLoader.loadObjModel("person", loader);
		TexturedModel stanfordBunny = new TexturedModel(bunnyModel, new ModelTexture(loader.loadTexture("playerTexture")));
		entities.add(new Entity(textrip, new Vector3f((float)1767.6525, terrain.getHeightOfTerrain((float)1767.6525,(float) -1368.7086), (float)-1368.7086), 0, 180, 0, 2));
		entities.add(new Entity(textrose, new Vector3f((float)1777.653, terrain.getHeightOfTerrain((float)1777.653,(float) -1371.3364), (float)-1371.3364), 0, 180, 0, (float)0.05));
		
		Player player=new Player(stanfordBunny,new Vector3f((float)814.94147,0, (float)-1141.3142),0,90,0,(float)0.05);
		entities.add(player);
		Players player1=new Players(textcarro,new Vector3f(Pointlist.get(0).x,0,Pointlist.get(0).z),0,90,0,1);
		entities.add(player1);
		Players player2=new Players(textcarro,new Vector3f(Pointlist2.get(0).x,0,Pointlist2.get(0).z),0,90,0,1);
		entities.add(player2);
		Players player3=new Players(textcarro,new Vector3f(Pointlist3.get(0).x,0,Pointlist3.get(0).z),0,90,0,1);
		entities.add(player3);
		Players player4=new Players(textcarro,new Vector3f(Pointlist4.get(0).x,0,Pointlist4.get(0).z),0,90,0,1);
		entities.add(player4);
		Players player5=new Players(textcarro,new Vector3f(Pointlist5.get(0).x,0,Pointlist5.get(0).z),0,90,0,1);
		entities.add(player5);
		Players player6=new Players(textcarro,new Vector3f(Pointlist6.get(0).x,0,Pointlist6.get(0).z),0,90,0,1);
		entities.add(player6);
		Players player7=new Players(textcarro,new Vector3f(Pointlist7.get(0).x,0,Pointlist7.get(0).z),0,90,0,1);
		entities.add(player7);
		Players player8=new Players(textcarro,new Vector3f(Pointlist8.get(0).x,0,Pointlist8.get(0).z),0,90,0,1);
		entities.add(player8);
		Players player9=new Players(textcarro,new Vector3f(Pointlist9.get(0).x,0,Pointlist9.get(0).z),0,90,0,1);
		entities.add(player9);
		Players player10=new Players(textcarro,new Vector3f(Pointlist10.get(0).x,0,Pointlist10.get(0).z),0,90,0,1);
		entities.add(player10);
		Players player11=new Players(textcarro,new Vector3f(Pointlist11.get(0).x,0,Pointlist11.get(0).z),0,90,0,1);
		entities.add(player11);
		Players player12=new Players(textcarro,new Vector3f(Pointlist12.get(0).x,0,Pointlist12.get(0).z),0,90,0,1);
		entities.add(player12);
		Players player13=new Players(textcarro,new Vector3f(Pointlist13.get(0).x,0,Pointlist13.get(0).z),0,90,0,1);
		entities.add(player13);
		Players player14=new Players(textcarro,new Vector3f(Pointlist14.get(0).x,0,Pointlist14.get(0).z),0,90,0,1);
		entities.add(player14);
		Players player15=new Players(textcarro,new Vector3f(Pointlist15.get(0).x,0,Pointlist15.get(0).z),0,90,0,1);
		entities.add(player15);
		Players player16=new Players(textcarro,new Vector3f(Pointlist16.get(0).x,0,Pointlist16.get(0).z),0,90,0,1);
		entities.add(player16);
		entities.add(new Entity(textperf, new Vector3f((float)973.45795, terrain.getHeightOfTerrain((float)973.45795,(float) -1044.9641), (float)-1044.9641), 0, 180, 0, 1));
		entities.add(new Entity(textperf, new Vector3f((float)899.5118, terrain.getHeightOfTerrain((float)899.5118,(float) -988.5052), (float)-988.5052), 0, 90, 0, 1));
		entities.add(new Entity(textexcavadora, new Vector3f((float)1002.4283, terrain.getHeightOfTerrain((float)1002.4283,(float) -1104.8986), (float)-1104.8986), 0, 180, 0, 20));
		entities.add(new Entity(textexcavadora, new Vector3f((float)814.94147, terrain.getHeightOfTerrain((float)814.94147,(float) -1141.3142), (float)-1141.3142), 0, 0, 0, 20));

		Camera camera = new Camera(player);
		List<GuiTexture> guiTextures = new ArrayList<GuiTexture>();
		GuiRenderer guiRenderer = new GuiRenderer(loader);
		MousePicker picker = new MousePicker(camera, renderer.getProjectionMatrix(), terrain);
	
		//**********Water Renderer Set-up************************
		
		WaterFrameBuffers buffers = new WaterFrameBuffers();
		WaterShader waterShader = new WaterShader();
		WaterRenderer waterRenderer = new WaterRenderer(loader, waterShader, renderer.getProjectionMatrix(), buffers);
		List<WaterTile> waters = new ArrayList<WaterTile>();
		WaterTile water = new WaterTile((float)859.3326, (float)-1069.1678,5+terrain.getHeightOfTerrain((float)859.3326,(float)-1069.1678));
		waters.add(water);
		
		//****************Game Loop Below*********************

		while (!Display.isCloseRequested()) {
			player.move(terrain);
			player1.moves(terrain, Pointlist);
			player2.moves(terrain, Pointlist2);
			player3.moves(terrain, Pointlist3);
			player4.moves(terrain, Pointlist4);
			player5.moves(terrain, Pointlist5);
			player6.moves(terrain, Pointlist6);
			player7.moves(terrain, Pointlist7);
			player8.moves(terrain, Pointlist8);
			player9.moves(terrain, Pointlist9);
			player10.moves(terrain, Pointlist10);
			player11.moves(terrain, Pointlist11);
			player12.moves(terrain, Pointlist12);
			player13.moves(terrain, Pointlist13);
			player14.moves(terrain, Pointlist14);
			player15.moves(terrain, Pointlist15);
			player16.moves(terrain, Pointlist16);
			camera.move();
			picker.update();
			entity.increaseRotation(0, 1, 0);
			entity2.increaseRotation(0, 1, 0);
			entity3.increaseRotation(0, 1, 0);
			GL11.glEnable(GL30.GL_CLIP_DISTANCE0);
			
			//render reflection teture
			buffers.bindReflectionFrameBuffer();
			float distance = 2 * (camera.getPosition().y - water.getHeight());
			camera.getPosition().y -= distance;
			camera.invertPitch();
			renderer.renderScene(entities, normalMapEntities, terrains, lights, camera, new Vector4f(0, 1, 0, -water.getHeight()+1));
			camera.getPosition().y += distance;
			camera.invertPitch();
			
			//render refraction texture
			buffers.bindRefractionFrameBuffer();
			renderer.renderScene(entities, normalMapEntities, terrains, lights, camera, new Vector4f(0, -1, 0, water.getHeight()));
			
			//render to screen
			GL11.glDisable(GL30.GL_CLIP_DISTANCE0);
			buffers.unbindCurrentFrameBuffer();	
			renderer.renderScene(entities, normalMapEntities, terrains, lights, camera, new Vector4f(0, -1, 0, 100000));	
			waterRenderer.render(waters, camera, sun);
			guiRenderer.render(guiTextures);
			
			DisplayManager.updateDisplay();
		}

		//*********Clean Up Below**************
		
		buffers.cleanUp();
		waterShader.cleanUp();
		guiRenderer.cleanUp();
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}


}
