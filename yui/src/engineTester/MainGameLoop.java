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
import toolbox.Readfile2;
import toolbox.Readfile3;
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
		double trz = Maths.trx(762546.5153617859);
		double trx = Maths.trz(9251158.233398438);
		//rangox: 2003.65771484375
		//rangoy: 1670.9846382141113
		//miny=9251158.233398438
		//minx: 762546.5153617859
		
		Readfile r= new Readfile();
		r.openFile();
		ArrayList<Punto> Pointlist=r.GetList();
		r.closeFile();
		for (Punto punto:Pointlist) {
			punto.x=(float) (punto.x-trx);
			punto.z=(float) ((2003.65771484375*(punto.z-trz)/1670.9846382141113 )-2003.65771484375);
			//punto.z=(float)((punto.z-trz));
			
		}
		Readfile2 s= new Readfile2();
		s.openFile();
		ArrayList<Punto> Pointlist2=s.GetList();
		s.closeFile();
		for (Punto punto:Pointlist2) {
			punto.x=(float) (punto.x-trx);
			punto.z=(float) ((2003.65771484375*(punto.z-trz)/1670.9846382141113 )-2003.65771484375);
			//punto.getZ=(float)((punto.z-trz));
			
		}
		Readfile3 t= new Readfile3();
		t.openFile();
		ArrayList<Punto> Pointlist3=t.GetList();
		t.closeFile();
		for (Punto punto:Pointlist3) {
			punto.x=(float) (punto.x-trx);
			punto.z=(float) ((2003.65771484375*(punto.z-762546.5153617859)/1670.9846382141113 )-2003.65771484375);
			//punto.z=(float)((punto.z-762546.5153617859-2003.65771484375));
			
		}
		

		// *********TERRAIN STUFF**********
		RawModel carromodel=OBJLoader.loadObjModel("carrito", loader);
		
		TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("text"));
		TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("mud"));
		TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("grassFlowers"));
		TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path"));
		TexturedModel textcarro=new TexturedModel(carromodel,new ModelTexture(loader.loadTexture("bricks")));

		TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture,gTexture, bTexture);
		TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));

		// *****************************************

		TexturedModel rocks = new TexturedModel(OBJFileLoader.loadOBJ("excavator", loader),new ModelTexture(loader.loadTexture("rocks")));

		ModelTexture fernTextureAtlas = new ModelTexture(loader.loadTexture("fern"));fernTextureAtlas.setNumberOfRows(2);

		TexturedModel fern = new TexturedModel(OBJFileLoader.loadOBJ("fern", loader),fernTextureAtlas);

		TexturedModel bobble = new TexturedModel(OBJFileLoader.loadOBJ("pine", loader),new ModelTexture(loader.loadTexture("pine")));
		bobble.getTexture().setHasTransparency(true);

		fern.getTexture().setHasTransparency(true);

		                                                                            Terrain terrain = new Terrain(0, -1, loader, texturePack,blendMap, "jeimap");
		List<Terrain> terrains = new ArrayList<Terrain>();
		terrains.add(terrain);

		TexturedModel lamp = new TexturedModel(OBJLoader.loadObjModel("lamp", loader),
				new ModelTexture(loader.loadTexture("lamp")));
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
		
		Random random = new Random(5666778);
		for (int i = 0; i < 60; i++) {
			if (i % 3 == 0) {
				float x = random.nextFloat() * 150;
				float z = random.nextFloat() * -150;
				if ((x > 50 && x < 100) || (z < -50 && z > -100)) {
				} else {
					float y = terrain.getHeightOfTerrain(x, z);

					entities.add(new Entity(fern, 3, new Vector3f(x, y, z), 0,random.nextFloat() * 360, 0, 0.9f));
				}
			}
			if (i % 2 == 0) {

				float x = random.nextFloat() * 150;
				float z = random.nextFloat() * -150;
				if ((x > 50 && x < 100) || (z < -50 && z > -100)) {

				} else {
					float y = terrain.getHeightOfTerrain(x, z);
					entities.add(new Entity(bobble, 1, new Vector3f(x, y, z), 0,
							random.nextFloat() * 360, 0, random.nextFloat() * 0.6f + 0.8f));
				}
			}
		}
		entities.add(new Entity(rocks, new Vector3f(20, terrain.getHeightOfTerrain(0, -1), -1), 0, 0, 0, 75));

		//*******************OTHER SETUP***************

		List<Light> lights = new ArrayList<Light>();
		Light sun = new Light(new Vector3f(10000, 10000, -1000), new Vector3f(1.3f, 1.3f, 1.3f));
		lights.add(sun);

		MasterRenderer renderer = new MasterRenderer(loader);

		RawModel bunnyModel = OBJLoader.loadObjModel("person", loader);
		TexturedModel stanfordBunny = new TexturedModel(bunnyModel, new ModelTexture(
				loader.loadTexture("playerTexture")));
		double altura =terrain.getHeightOfTerrain(Pointlist2.get(0).x, Pointlist2.get(0).z);
		System.out.println(Pointlist.get(0).x+ " x/z " +Pointlist.get(0).z);
		System.out.println(Pointlist2.get(0).x+ " x/z " +Pointlist2.get(0).z + " height: " + altura );

		//Player player=new Player(stanfordBunny,new Vector3f(Pointlist3.get(0).x,0, Pointlist3.get(0).z),0,90,0,1);
		Player player=new Player(stanfordBunny,new Vector3f(0,0, -1),0,90,0,1);
		//entities.add(player);
		Players player1=new Players(textcarro,new Vector3f(Pointlist.get(0).x,0,Pointlist.get(0).z),0,0,0,1);
		entities.add(player1);
		Players player2=new Players(textcarro,new Vector3f(Pointlist2.get(0).x,0,Pointlist2.get(0).z),0,0,0,1);
		entities.add(player2);
		Players player3=new Players(textcarro,new Vector3f(Pointlist3.get(0).x,0,Pointlist3.get(0).z),0,0,0,1);
		entities.add(player3);
		Camera camera = new Camera(player);
		List<GuiTexture> guiTextures = new ArrayList<GuiTexture>();
		GuiRenderer guiRenderer = new GuiRenderer(loader);
		MousePicker picker = new MousePicker(camera, renderer.getProjectionMatrix(), terrain);
	
		//**********Water Renderer Set-up************************
		
		WaterFrameBuffers buffers = new WaterFrameBuffers();
		WaterShader waterShader = new WaterShader();
		WaterRenderer waterRenderer = new WaterRenderer(loader, waterShader, renderer.getProjectionMatrix(), buffers);
		List<WaterTile> waters = new ArrayList<WaterTile>();
		WaterTile water = new WaterTile(75, -75, 0);
		waters.add(water);
		
		//****************Game Loop Below*********************

		while (!Display.isCloseRequested()) {
			player.move(terrain);
			player1.moves(terrain, Pointlist);
			player2.moves(terrain, Pointlist2);
			player3.moves(terrain, Pointlist3);
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