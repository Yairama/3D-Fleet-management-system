package models;

import textures.ModelTexture;

public class TexturedModel {
    public RawModel rawModel;
    private ModelTexture texture;

    public RawModel getRawModel() {
        return rawModel;
    }

    public ModelTexture getTexture() {
        return texture;
    }

    public TexturedModel(RawModel model, ModelTexture texture){
        this.rawModel = model;
        this.texture = texture;
    }
}
