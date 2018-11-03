package team.hdt.blockadia.engine_rewrite.core.render;

public interface IRenderer {

    void register();

    void prerender();

    void render();

    void unrender();
}
