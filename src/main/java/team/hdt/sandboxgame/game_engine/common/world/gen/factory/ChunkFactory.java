package team.hdt.sandboxgame.game_engine.common.world.gen.factory;

import team.hdt.sandboxgame.game_engine.common.Identifier;
import team.hdt.sandboxgame.game_engine.common.registry.BiomeRegistry;
import team.hdt.sandboxgame.game_engine.common.world.Chunk;
import team.hdt.sandboxgame.game_engine.common.world.gen.interfaces.IBiome;
import team.hdt.sandboxgame.game_engine.common.world.gen.interfaces.ILayer;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ChunkFactory {

    public ChunkFactory(){}

    private static Random random = new Random();
    private IBiome IBiome;
    static int currentY;

     public void initFactory(IBiome biome, boolean chooseOwn){
         if(chooseOwn) {
             Map<Identifier, IBiome> biomes = BiomeRegistry.registries.registry;
             Map<Integer, IBiome> biomeList = new HashMap<>();
             biomes.forEach((identifier, IBiome) -> {
                 int i = biomeList.size();
                 biomeList.put(i, IBiome);
             });
             IBiome = biomeList.get(random.nextInt(biomeList.size()-1));
         } else {
             this.IBiome = biome;
         }
    }

    public Chunk getChunk(){
         Chunk chunk = new Chunk(Chunk.CHUNK_SIZE,Chunk.CHUNK_SIZE,Chunk.CHUNK_SIZE,Chunk.ChunkType.HORIZONTAL);
         do {
             ILayer layer = IBiome.getLayer(currentY, random);
             for (int x = 0; x < Chunk.CHUNK_SIZE; x++) {
                for (int z = 0; z < Chunk.CHUNK_SIZE; z++){
                    chunk.setBlock(x, currentY, z, layer.getBlockAtPos(x, z));
                }
             }
             currentY++;
         } while (currentY < 300);
         return chunk;
    }

    public void getNeighbourFactory(){
        Map<IBiome, Integer> map = IBiome.getChanceMap();
        Map<Integer, IBiome> list = new HashMap<>();
        map.forEach((iBiome, i) -> {
            int index = list.size();
            for(int index2 = index; index2 < i; index2++) {
                list.put(index2, IBiome);
            }
        });
        IBiome = list.get(random.nextInt(list.size()-1));
    }

}
