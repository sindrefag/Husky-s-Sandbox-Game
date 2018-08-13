package team.hdt.blockadia.game_engine.common.world.biomes;

import team.hdt.blockadia.game_engine.common.Identifier;
import team.hdt.blockadia.game_engine.common.world.block.BlockType;
import team.hdt.blockadia.game_engine.common.world.block.BlockTypes;
import team.hdt.blockadia.game_engine.common.world.gen.interfaces.IBiome;
import team.hdt.blockadia.game_engine.common.world.gen.interfaces.ILayer;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BiomeDesert implements IBiome {

    @Override
    public ILayer getLayer(int y, Random random) {
        return y < 50 ? new LayerSolid(BlockTypes.ROCK) : new ILayer.LayerAir(32, 32);
    }

    /**
     * Get's the chancemap for the chance of getting a {@link IBiome} generated next to this biome
     *
     * @return a {@link HashMap} with all the possible neighbour biomes and their chances.
     */
    @Override
    public Map<IBiome, Integer> getChanceMap() {
        HashMap<IBiome, Integer> chanceMap = new HashMap<>();
        chanceMap.put(Biomes.desert, 1);
        chanceMap.put(Biomes.flatlands, 1);
        return chanceMap;
    }

    private Identifier identifier = new Identifier("biome_desert");

    @Override
    public Identifier getIdentifier() {
        return identifier;
    }

    @Override
    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    private class LayerSolid implements ILayer{

        BlockType type;

        public LayerSolid(BlockType type){
            this.type = type;
        }

        @Override
        public int getWidth() {
            return 32;
        }

        @Override
        public int getHeight() {
            return 32;
        }

        @Override
        public BlockType getBlockAtPos(int x, int z) {
            return type;
        }
    }
}
