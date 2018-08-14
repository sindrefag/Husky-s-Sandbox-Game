package team.hdt.blockadia.game_engine.util.toolbox;

import org.lwjgl.BufferUtils;
import team.hdt.blockadia.game_engine.common.util.math.vectors.Vectors3f;

import java.awt.*;
import java.nio.FloatBuffer;

public class Colour {

    private Vectors3f col = new Vectors3f();
    private float a = 1;

    public Colour(float r, float g, float b) {
        col.set(r, g, b);
    }

    public Colour(Vectors3f colour) {
        col.set(colour);
    }

    public Colour(float r, float g, float b, float a) {
        col.set(r, g, b);
        this.a = a;
    }

    public Colour(float r, float g, float b, boolean convert) {
        if (convert) {
            col.set(r / 255f, g / 255f, b / 255f);
        } else {
            col.set(r, g, b);
        }
    }

    public Colour() {
    }

    public static Colour sub(Colour colLeft, Colour colRight, Colour dest) {
        if (dest == null) {
            return new Colour(Vectors3f.sub(colLeft.col, colRight.col, null));
        } else {
            Vectors3f.sub(colLeft.col, colRight.col, dest.col);
            return dest;
        }
    }

    public static float calculateDifference(Colour colA, Colour colB) {
        return Colour.sub(colB, colA, null).length();
    }

    public static Colour hsvToRgb(float hue, float saturation, float value) {
        int h = (int) (hue * 6);
        float f = hue * 6 - h;
        float p = value * (1 - saturation);
        float q = value * (1 - f * saturation);
        float t = value * (1 - (1 - f) * saturation);
        switch (h) {
            case 0:
                return new Colour(value, t, p);
            case 1:
                return new Colour(q, value, p);
            case 2:
                return new Colour(p, value, t);
            case 3:
                return new Colour(p, q, value);
            case 4:
                return new Colour(t, p, value);
            case 5:
                return new Colour(value, p, q);
            default:
                return new Colour();
        }
    }

    public static Colour interpolateColours(Colour colour1, Colour colour2, float blend, Colour dest) {
        float colour1Weight = 1 - blend;
        float r = (colour1Weight * colour1.col.x) + (blend * colour2.col.x);
        float g = (colour1Weight * colour1.col.y) + (blend * colour2.col.y);
        float b = (colour1Weight * colour1.col.z) + (blend * colour2.col.z);
        if (dest == null) {
            return new Colour(r, g, b);
        } else {
            dest.setColour(r, g, b);
            return dest;
        }
    }

    public static Colour add(Colour colour1, Colour colour2, Colour dest) {
        if (dest == null) {
            return new Colour(Vectors3f.add(colour1.col, colour2.col, null));
        } else {
            Vectors3f.add(colour1.col, colour2.col, dest.col);
            return dest;
        }
    }

    public Vectors3f getVector() {
        return col;
    }

    public FloatBuffer getAsFloatBuffer() {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(4);
        buffer.put(new float[]{col.x, col.y, col.z, a});
        buffer.flip();
        return buffer;
    }

    public float getR() {
        return col.x;
    }

    public void setR(float r) {
        col.x = r;
    }

    public float getG() {
        return col.y;
    }

    public void setG(float g) {
        col.y = g;
    }

    public float getB() {
        return col.z;
    }

    public void setB(float b) {
        col.z = b;
    }

    public Colour duplicate() {
        return new Colour(col.x, col.y, col.z);
    }

    public void multiplyBy(Colour colour) {
        this.col.x *= colour.col.x;
        this.col.y *= colour.col.y;
        this.col.z *= colour.col.z;
    }

    public void setColour(float r, float g, float b) {
        col.set(r, g, b);
    }

    public void setColour(Vectors3f colour) {
        col.set(colour);
    }

    public void setColour(Colour colour) {
        this.col.set(colour.col);
    }

    public void setColour(float r, float g, float b, float a) {
        col.set(r, g, b);
        this.a = a;
    }

    public boolean isEqualTo(Colour colour) {
        return (col.x == colour.col.x && col.y == colour.col.y && col.z == colour.col.z);
    }

    public Colour scale(float value) {
        col.scale(value);
        return this;
    }

    public String toString() {
        return ("(" + col.x + ", " + col.y + ", " + col.z + ")");
    }

    public Colour getUnit() {
        Colour colour = new Colour();
        if (col.x == 0 && col.y == 0 && col.z == 0) {
            return colour;
        }
        colour.setColour(this);
        colour.scale(1f / length());
        return colour;
    }

    public float length() {
        return (float) Math.sqrt(lengthSquared());
    }

    public float lengthSquared() {
        return col.lengthSquared();
    }

    public void setColour(HsvColour hsv) {
        this.setColour(hsvToRgb(hsv.getHue(), hsv.getSaturation(), hsv.getValue()));
    }

    public void setHsvColour(float hue, float sat, float value) {
        this.setColour(hsvToRgb(hue, sat, value));
    }

    public HsvColour getHsvColour() {
        float[] hsv = new float[3];
        Color.RGBtoHSB((int) (col.x * 255), (int) (col.y * 255), (int) (col.z * 255), hsv);
        return new HsvColour(hsv[0], hsv[1], hsv[2]);
    }

}
