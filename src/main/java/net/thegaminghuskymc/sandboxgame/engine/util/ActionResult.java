package net.thegaminghuskymc.sandboxgame.engine.util;

public class ActionResult<T> {
    private final EnumActionResult type;
    private final T result;

    public ActionResult(EnumActionResult typeIn, T resultIn) {
        this.type = typeIn;
        this.result = resultIn;
    }

    //Just a generic helper function to make typecasing easier...
    public static <T> ActionResult<T> newResult(EnumActionResult result, T value) {
        return new ActionResult<T>(result, value);
    }

    public EnumActionResult getType() {
        return this.type;
    }

    public T getResult() {
        return this.result;
    }
}