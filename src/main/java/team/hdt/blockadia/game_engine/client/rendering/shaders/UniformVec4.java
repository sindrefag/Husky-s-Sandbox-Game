package team.hdt.blockadia.game_engine.client.rendering.shaders;

import org.lwjgl.opengl.GL20;
import team.hdt.blockadia.game_engine.common.util.math.vectors.Vectors4f;

public class UniformVec4 extends Uniform {

    public UniformVec4(String name) {
        super(name);
    }

    public void loadVec4(Vectors4f vector) {
        loadVec4(vector.x, vector.y, vector.z, vector.w);
    }

    public void loadVec4(float x, float y, float z, float w) {
        GL20.glUniform4f(super.getLocation(), x, y, z, w);
    }

}
