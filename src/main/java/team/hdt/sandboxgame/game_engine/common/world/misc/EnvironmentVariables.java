package team.hdt.sandboxgame.game_engine.common.world.misc;

import team.hdt.sandboxgame.game_engine.common.util.math.vectors.Vectors2f;
import team.hdt.sandboxgame.game_engine.common.util.math.vectors.Vectors3f;
import team.hdt.sandboxgame.game_engine.common.world.dayNightCycle.DayNightCycle;
import team.hdt.sandboxgame.game_engine.util.toolbox.Colour;
import team.hdt.sandboxgame.game_engine.util.toolbox.Maths;

public class EnvironmentVariables {

	private static EnvironmentVariables variables = new EnvironmentVariables();

	public static final float GRAVITY = -10;
	public static float starBrightness = 0;

	private static final float LARGE_NUMBER = 1000000f;
	private static Colour lightColour = new Colour(0.8f, 0.6f, 0.6f);
	private static Vectors3f lightDirection = new Vectors3f(0.4f, -1f, 0.2f);
	public static final Colour VOID_COLOUR = new Colour(1, 0.87f, 0.6f);
	private static float ambientWeighting = 0.6f;
	private static float diffuseWeighting = 0.6f;
	public static Colour horizonColour = new Colour(1, 0.87f, 0.6f);
	public static Colour skyColour = new Colour(0.6f, 0.9f, 1f);
	public static Vectors3f VISIBLE_SUN_DIR = new Vectors3f(1, -0.08f, 0.5f);
	
	public static Vectors2f MIST_VALS = new Vectors2f(20, 120);
	public static Colour MIST_COL = new Colour(255, 222, 210, true);
	
	private float skyRotateSpeed = 1f;
	
	private Vectors2f sunScreenCoords;
	
	public static DayNightCycle cycle = new DayNightCycle();

	public static EnvironmentVariables getVariables() {
		return variables;
	}
	
	public static float getSunEffectBrightness(){
		return 1f - Maths.smoothStep(0, 0.2f, EnvironmentVariables.VISIBLE_SUN_DIR.y);
	}

	private EnvironmentVariables() {
	}
	
	public void setLightColour(Colour colour){
		lightColour = colour;
	}

	public void update() {
//		sunScreenCoords = getSunScreenCoords();
		cycle.update();
	}
	
	public void setLightDirection(Vectors3f lightDir){
		lightDirection.set(lightDir);
	}
	
	public Vectors2f getSunScreenPosition(){
		return sunScreenCoords;
	}

	public Colour getLightColour() {
		return lightColour;
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

	private Vectors3f getSunPosition(Vectors3f sunDirection) {
		Vectors3f sunPos = new Vectors3f(sunDirection);
		sunPos.negate();
		sunPos.scale(LARGE_NUMBER);
		return sunPos;
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