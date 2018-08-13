package team.hdt.sandboxgame.game_engine.client.skybox;

import team.hdt.sandboxgame.game_engine.common.Loader;

public class CubeGenerator {

	private static final int VERTEX_COUNT = 8;
	private static final int[] INDICES = { 0, 1, 3, 1, 2, 3, 1, 5, 2, 2, 5, 6, 4, 7, 5, 5, 7, 6, 0,
			3, 4, 4, 3, 7, 7, 3, 6, 6, 3, 2, 4, 5, 0, 0, 5, 1 };

	public static int generateCube(float size) {
		int vao = Loader.createInterleavedVAO(getVertexPositions(size), INDICES, 3);
		return vao;
	}

	private static float[] getVertexPositions(float size) {
		return new float[] { -size, size, size, size, size, size, size, -size, size, -size, -size,
				size, -size, size, -size, size, size, -size, size, -size, -size, -size, -size,
				-size };
	}

}