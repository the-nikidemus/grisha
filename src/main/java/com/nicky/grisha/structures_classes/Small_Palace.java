package com.nicky.grisha.structures_classes;

import net.minecraft.structure.*;
import org.apache.logging.log4j.Level;

import com.mojang.serialization.Codec;
import com.nicky.grisha.Grisha;
import com.nicky.grisha.structures.StructuresMain;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.Optional;

public class Small_Palace extends StructureFeature<StructurePoolFeatureConfig> {

	public Small_Palace(Codec<StructurePoolFeatureConfig> codec) {
        // Create the pieces layout of the structure and give it to the game
        super(codec, Small_Palace::createPiecesGenerator, PostPlacementProcessor.EMPTY);
	}
	
	public static final Pool<SpawnSettings.SpawnEntry> STRUCTURE_MONSTERS = Pool.of(
			new SpawnSettings.SpawnEntry(EntityType.ILLUSIONER, 300, 4, 9),
            new SpawnSettings.SpawnEntry(EntityType.EVOKER, 100, 4, 9)
    );

    public static final Pool<SpawnSettings.SpawnEntry> STRUCTURE_CREATURES = Pool.of(
            new SpawnSettings.SpawnEntry(EntityType.HORSE, 30, 3, 8)
    );

    private static boolean isFeatureChunk(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context) {
        BlockPos spawnXZPosition = context.chunkPos().getCenterAtY(0);

        // Grab height of land. Will stop at first non-air block.
        int landHeight = context.chunkGenerator().getHeightInGround(spawnXZPosition.getX(), spawnXZPosition.getZ(), Heightmap.Type.WORLD_SURFACE_WG, context.world());

        // Grabs column of blocks at given position. In overworld, this column will be made of stone, water, and air.
        // In nether, it will be netherrack, lava, and air. End will only be endstone and air. It depends on what block
        // the chunk generator will place for that dimension.
        VerticalBlockSample columnOfBlocks = context.chunkGenerator().getColumnSample(spawnXZPosition.getX(), spawnXZPosition.getZ(), context.world());

        // Combine the column of blocks with land height and you get the top block itself which you can test.
        BlockState topBlock = columnOfBlocks.getState(landHeight);

        // Now we test to make sure our structure is not spawning on water or other fluids.
        // You can do height check instead too to make it spawn at high elevations.
        return topBlock.getFluidState().isEmpty(); //landHeight > 100;
    }

    public static Optional<StructurePiecesGenerator<StructurePoolFeatureConfig>> createPiecesGenerator(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context) {

        // Check if the spot is valid for our structure. This is just as another method for cleanness.
        // Returning an empty optional tells the game to skip this spot as it will not generate the structure.
        if (!Small_Palace.isFeatureChunk(context)) {
            return Optional.empty();
        }

        /*
         * The only reason we are using StructurePoolFeatureConfig here is that further down, we are using
         * StructurePoolBasedGenerator.generate which requires StructurePoolFeatureConfig. However, if you create your own
         * StructurePoolBasedGenerator.generate, you could reduce the amount of workarounds like above that you need
         * and give yourself more opportunities and control over your structures.
         *
         * An example of a custom StructurePoolBasedGenerator.generate in action can be found here (warning, it is using Mojmap mappings):
         * https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/blob/1.18/src/main/java/com/telepathicgrunt/repurposedstructures/world/structures/pieces/PieceLimitedJigsawManager.java
         */
        StructurePoolFeatureConfig newConfig = new StructurePoolFeatureConfig(
                // The path to the starting Template Pool JSON file to read.
                //
                // Note, this is "structure_tutorial:run_down_house/start_pool" which means
                // the game will automatically look into the following path for the template pool:
                // "resources/data/structure_tutorial/worldgen/template_pool/run_down_house/start_pool.json"
                // This is why your pool files must be in "data/<modid>/worldgen/template_pool/<the path to the pool here>"
                // because the game automatically will check in worldgen/template_pool for the pools.
                () -> context.registryManager().get(Registry.STRUCTURE_POOL_KEY)
                        .get(new Identifier(Grisha.MOD_ID, "small_palace/start_centre_back_pool")),

                // How many pieces outward from center can a recursive jigsaw structure spawn.
                // Our structure is only 1 piece outward and isn't recursive so any value of 1 or more doesn't change anything.
                // However, I recommend you keep this a decent value like 7 so people can use datapacks to add additional pieces to your structure easily.
                // But don't make it too large for recursive structures like villages or you'll crash server due to hundreds of pieces attempting to generate!
                10
        );

        // Create a new context with the new config that has our json pool. We will pass this into JigsawPlacement.addPieces
        StructureGeneratorFactory.Context<StructurePoolFeatureConfig> newContext = new StructureGeneratorFactory.Context<>(
                context.chunkGenerator(),
                context.biomeSource(),
                context.seed(),
                context.chunkPos(),
                newConfig,
                context.world(),
                context.validBiome(),
                context.structureManager(),
                context.registryManager()
        );

        // Turns the chunk coordinates into actual coordinates we can use. (Gets center of that chunk)
        BlockPos blockpos = context.chunkPos().getCenterAtY(0);

        Optional<StructurePiecesGenerator<StructurePoolFeatureConfig>> structurePiecesGenerator =
                StructurePoolBasedGenerator.generate(
                        newContext, // Used for StructurePoolBasedGenerator to get all the proper behaviors done.
                        PoolStructurePiece::new, // Needed in order to create a list of jigsaw pieces when making the structure's layout.
                        blockpos, // Position of the structure. Y value is ignored if last parameter is set to true.
                        false,  // Special boundary adjustments for villages. It's... hard to explain. Keep this false and make your pieces not be partially intersecting.
                        // Either not intersecting or fully contained will make children pieces spawn just fine. It's easier that way.
                        true // Place at heightmap (top land). Set this to false for structure to be place at the passed in blockpos's Y value instead.
                        // Definitely keep this false when placing structures in the nether as otherwise, heightmap placing will put the structure on the Bedrock roof.
                );
        /*
         * Note, you are always free to make your own StructurePoolBasedGenerator class and implementation of how the structure
         * should generate. It is tricky but extremely powerful if you are doing something that vanilla's jigsaw system cannot do.
         * Such as for example, forcing 3 pieces to always spawn every time, limiting how often a piece spawns, or remove the intersection limitation of pieces.
         *
         * An example of a custom StructurePoolBasedGenerator.generate in action can be found here (warning, it is using Mojmap mappings):
         * https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/blob/1.18/src/main/java/com/telepathicgrunt/repurposedstructures/world/structures/pieces/PieceLimitedJigsawManager.java
         */

        if(structurePiecesGenerator.isPresent()) {
            // I use to debug and quickly find out if the structure is spawning or not and where it is.
            // This is returning the coordinates of the center starting piece.
            StructuresMain.LOGGER.log(Level.DEBUG, "Small Palace at " + blockpos);
        }

        // Return the pieces generator that is now set up so that the game runs it when it needs to create the layout of structure pieces.
        return structurePiecesGenerator;
    }

}
