/**
 * *	This file is part of the project https://github.com/toss-dev/VoxelEngine
 * *
 * *	License is available here: https://raw.githubusercontent.com/toss-dev/VoxelEngine/master/LICENSE.md
 * *
 * *	PEREIRA Romain
 * *                                       4-----7
 * *                                      /|    /|
 * *                                     0-----3 |
 * *                                     | 5___|_6
 * *                                     |/    | /
 * *                                     1-----2
 */

package net.thegaminghuskymc.sandboxgame.game.client.renderer.lines;

import net.thegaminghuskymc.sandboxgame.engine.resourcepacks.R;
import net.thegaminghuskymc.sandboxgame.game.client.opengl.GLH;
import net.thegaminghuskymc.sandboxgame.game.client.opengl.GLProgram;
import net.thegaminghuskymc.sandboxgame.game.client.renderer.camera.CameraProjective;
import org.lwjgl.opengl.GL20;

public class ProgramLines extends GLProgram {

    protected int _mvp_matrix;

    public ProgramLines() {
        super();
        this.addShader(GLH.glhLoadShader(R.getResPath("shaders/lines.fs"), GL20.GL_FRAGMENT_SHADER));
        this.addShader(GLH.glhLoadShader(R.getResPath("shaders/lines.vs"), GL20.GL_VERTEX_SHADER));
        this.link();
    }

    @Override
    public void bindAttributes() {
        super.bindAttribute(0, "pos");
        super.bindAttribute(1, "color");
    }

    @Override
    public void linkUniforms() {
        this._mvp_matrix = super.getUniform("mvp_matrix");
    }

    /** load global uniforms */
    public void loadGlobalUniforms(CameraProjective camera) {
        super.loadUniformMatrix(this._mvp_matrix, camera.getMVPMatrix());
    }
}