package team.hdt.blockadia.old_engine_code_1.core.world.misc;

import ga.pheonix.utillib.OpenGl.Colour;
import ga.pheonix.utillib.utils.Maths;
import ga.pheonix.utillib.utils.vectors.Vectors2f;
import ga.pheonix.utillib.utils.vectors.Vectors3f;

public class EnvironmentVariables {

    public static final float GRAVITY = -10;
    public static final Colour VOID_COLOUR = new Colour(1, 0.87f, 0.6f);
    private static final float LARGE_NUMBER = 1000000f;
    public static float starBrightness = 0;
    public static Colour horizonColour = new Colour(1, 0.87f, 0.6f);
    public static Colour skyColour = new Colour(0.6f, 0.9f, 1f);
    public static Vectors3f VISIBLE_SUN_DIR = new Vectors3f(1, -0.08f, 0.5f);
    public static Vectors2f MIST_VALS = new Vectors2f(20, 120);
    public static Colour MIST_COL = new Colour(255, 222, 210, true);
    private static EnvironmentVariables variables = new EnvironmentVariables();
    private static Colour lightColour = new Colour(0.8f, 0.6f, 0.6f);
    private static Vectors3f lightDirection = new Vectors3f(0.4f, -1f, 0.2f);
    private static float ambientWeighting = 0.6f;
    private static float diffuseWeighting = 0.6f;
    private float skyRotateSpeed = 1f;
    private Vectors2f sunScreenCoords;

    private EnvironmentVariables() {
    }

    public static EnvironmentVariables getVariables() {
        return variables;
    }

    public static float getSunEffectBrightness() {
        return 1f - Maths.smoothStep(0, 0.2f, EnvironmentVariables.VISIBLE_SUN_DIR.y);
    }

    public void update() {
//		sunScreenCoords = getSunScreenCoords();
    }

    public Vectors2f getSunScreenPosition() {
        return sunScreenCoords;
    }

    public Colour getLightColour() {
        return lightColour;
    }

    public void setLightColour(Colour colour) {
        lightColour = colour;
    }

    public float getSkyRotateSpeed() {
        return skyRotateSpeed;
    }

    public float getAmbientWeighting() {
        return ambientWeighting;
    }

    public float getDiffuseWeighting() {
        return diffuseWeighting;
    }

    public Vectors3f getLightDirection() {
        return lightDirection;
    }

	/*private Vectors2f getSunScreenCoords() {
		Vectors3f screenCoords = Maths.convertToScreenSpace(getSunPosition(VISIBLE_SUN_DIR),
				EngineMaster.getCamera().getViewMatrix(), MasterRenderer.getProjectionMatrix());
		if (screenCoords == null) {
			return null;
		}
		return new Vectors2f(screenCoords);
	}*/
}
