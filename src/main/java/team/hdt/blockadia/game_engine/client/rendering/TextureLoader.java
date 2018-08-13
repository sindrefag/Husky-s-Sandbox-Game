package team.hdt.blockadia.game_engine.client.rendering;

import de.matthiasmann.twl.utils.PNGDecoder;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class TextureLoader {

    public static final String TEXTURE_PATH = "src/main/resources/assets/blockadia/textures/";
    public static final String TEXTURE_TYPE = "matte";
    private static int dirt, grassSide, grassTop, stone, textureSheet, mainMenuLogo, mainMenuBackground;
    private static boolean loaded = false;

    private TextureLoader() {

    }

    public static void bind(Textures texture) {
        int tex = 0;
        switch (texture) {
            case DIRT:
                tex = dirt;
                break;
            case GRASS_SIDE:
                tex = grassSide;
                break;
            case GRASS_TOP:
                tex = grassTop;
                break;
            case STONE:
                tex = stone;
                break;
            case SHEET:
                tex = textureSheet;
                break;
            case MAIN_MENU_LOGO:
                tex = mainMenuLogo;
                break;
            case MAIN_MENU_BACKGROUND:
                tex = mainMenuBackground;
                break;
        }
        if (tex == 0)
            System.err.println(String.format("Texture %s not loaded. Will be WHITE", texture));
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, tex);
    }

    public static void unbind() {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }

    public static void loadTextures(boolean blur) {
        if (loaded)
            return;
        else {
            int filter;
            if (blur)
                filter = GL11.GL_LINEAR;
            else
                filter = GL11.GL_NEAREST;
            dirt = GL11.glGenTextures();
            grassSide = GL11.glGenTextures();
            stone = GL11.glGenTextures();
            textureSheet = GL11.glGenTextures();
            mainMenuLogo = GL11.glGenTextures();
            mainMenuBackground = GL11.glGenTextures();
            loadTexture(decodeImage("sheet.png"), textureSheet, filter, filter);
            System.out.println("Successfully loaded textures");
            loaded = true;
        }
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }

    /**
     * @param texture    The {@code Texture} object that has the texture information
     * @param textureID  The ID generated by {@code GL11.GLGenTextures()}
     * @param min_filter {@code GL11.GL_NEAREST} for actually showing pixels, {@code GL11.GL_LINEAR} for blurring
     * @param mag_filter {@code GL11.GL_NEAREST} for actually showing pixels, {@code GL11.GL_LINEAR} for blurring
     */
    private static void loadTexture(Texture texture, int textureID, int min_filter, int mag_filter) {
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, min_filter);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, mag_filter);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, texture.width, texture.height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, texture.buffer);
    }

    private static Texture decodeImage(String tex) {
        InputStream in = null;
        PNGDecoder decoder = null;
        ByteBuffer buffer = null;
        try {
            in = new FileInputStream(TEXTURE_PATH + TEXTURE_TYPE + tex);
            System.out.println(TEXTURE_PATH + TEXTURE_TYPE + tex);
            decoder = new PNGDecoder(in);
            buffer = BufferUtils.createByteBuffer(4 * decoder.getWidth() * decoder.getHeight());
            decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
        } catch (IOException e) {
            System.err.println("Could not load texture at: " + TEXTURE_PATH + TEXTURE_TYPE + tex);
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                System.err.println("Could not close the stream");
                e.printStackTrace();
            }
        }
        buffer.flip();
        System.out.println("Texture image: " + decoder.getWidth() + " " + decoder.getHeight());
        return new Texture(buffer, decoder.getWidth(), decoder.getHeight());
    }

    public enum Textures {
        DIRT, GRASS_SIDE, GRASS_TOP, STONE, SHEET, MAIN_MENU_LOGO, MAIN_MENU_BACKGROUND
    }
}