package team.hdt.sandboxgame.game_engine.common.registry;

import team.hdt.sandboxgame.game_engine.common.Identifier;
import team.hdt.sandboxgame.game_engine.common.util.interfaces.Nullable;

public interface IdRegistry<T extends RegistryEntry> extends Registry<T> {
    @Override
    default void register(Identifier identifier, T value) {
        throw new UnsupportedOperationException("must register entry with id");
    }

    void register(Identifier identifier, T value, int id);

    @Nullable
    T get(int id);

    int getId(Identifier identifier);
}