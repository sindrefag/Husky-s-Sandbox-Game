package team.hdt.blockadia.game_engine_old.common.entity;

import team.hdt.blockadia.game_engine.core.util.math.vectors.Vectors3f;
import team.hdt.blockadia.game_engine_old.client.entity.util.AllEntityAspects;
import team.hdt.blockadia.game_engine_old.client.entity.util.SharePeacefulAspects;
import team.hdt.blockadia.game_engine_old.client.entity.util.SharedHostileAspects;
import team.hdt.blockadia.game_engine_old.client.rendering.TexturedModel;
import team.hdt.blockadia.game_engine_old.common.util.interfaces.Nonnull;
import team.hdt.blockadia.game_engine_old.common.util.interfaces.Nullable;

public class BaseEntity {
    public static int ID;
    public float positionX;
    public float positionY;
    public float positionZ;
    public float rotationXZ;
    public float rotationXY;
    public float rotationZY;
    public TexturedModel model;
    public boolean CanburnInDay;
    @Nullable
    public boolean IsAquatic;
    @Nonnull
    public boolean isItem;
    public float scale = AllEntityAspects.scale;

    public BaseEntity(TexturedModel model, int id, String type) {
        this.model = model;
        this.ID = id;
        this.isItem = false;
        switch (type) {
            case ("hostile"):
                setHostileDefalt();
            case ("peaceful"):
                setPassiveDefalt();
        }

    }

    public BaseEntity(TexturedModel model, int id, boolean canburnInDay, boolean isAquatic) {
        this.model = model;
        this.ID = id;
        this.CanburnInDay = canburnInDay;
        this.IsAquatic = isAquatic;
        this.isItem = false;

    }
    public BaseEntity(TexturedModel model, int id, boolean isItem) {
        this.model = model;
        this.ID = id;
        this.isItem = isItem;
    }
    public BaseEntity(TexturedModel model, int id, boolean isItem, boolean canburnInDay, float scale) {
        this.model = model;
        this.ID = id;
        this.isItem = isItem;
        this.CanburnInDay = canburnInDay;
        if((scale == 0) == true) {
            this.scale = 1.0F;
        }
        else {
            this.scale = scale;
        }
    }

    public static int getID() {
        return ID;
    }

    public void setPassiveDefalt() {
        this.CanburnInDay = SharePeacefulAspects.burnInDay;
        this.IsAquatic = SharePeacefulAspects.liveInWater;
    }

    public void setHostileDefalt() {
        this.CanburnInDay = SharedHostileAspects.burnInDay;
        this.IsAquatic = SharedHostileAspects.liveInWater;
    }

    public void move(float x, float y, float z) {
        this.positionX = x;
        this.positionY = y;
        this.positionZ = z;
    }

    public void rotation(float rotationXY, float rotationXZ, float rotationZY) {
        this.rotationXY = rotationXY;
        this.rotationXZ = rotationXZ;
        this.rotationZY = rotationZY;
    }

    public TexturedModel getModel() {
        return model;
    }

    public boolean CanburnInDay() {
        return CanburnInDay;
    }

    public boolean isAquatic() {
        return IsAquatic;
    }

    public float getScale() {
        return scale;
    }

    public Vectors3f getPosition(){
        return new Vectors3f(positionX,positionY,positionZ);
    }
    public Vectors3f getRotation(){
        return new Vectors3f(rotationXY,rotationXZ,rotationZY);
    }
}
