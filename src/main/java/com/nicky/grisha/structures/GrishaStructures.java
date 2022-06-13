package com.nicky.grisha.structures;

import com.nicky.grisha.structures_classes.Campsite;
import com.nicky.grisha.structures_classes.Small_Palace;
import com.nicky.grisha.structures_classes.Stone;

import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

public class GrishaStructures {
	/**
    /**
     * Registers the structure itself and sets what its path is. In this case, the
     * structure will have the Identifier of structure_tutorial:run_down_house.
     *
     * It is always a good idea to register your Structures so that other mods and datapacks can
     * use them too directly from the registries. It great for mod/datapacks compatibility.
     */
    public static StructureFeature<StructurePoolFeatureConfig> STONE = new Stone(StructurePoolFeatureConfig.CODEC);
    public static StructureFeature<StructurePoolFeatureConfig> SMALL_PALACE = new Small_Palace(StructurePoolFeatureConfig.CODEC);
    public static StructureFeature<StructurePoolFeatureConfig> CAMPSITE = new Campsite(StructurePoolFeatureConfig.CODEC);
    
    /**
     * This is where we use Fabric API's structure API to setup the StructureFeature
     * See the comments in below for more details.
     */
    public static void setupAndRegisterStructureFeatures() {

        // This is Fabric API's builder for structures.
        // It has many options to make sure your structure will spawn and work properly.
        // Give it your structure and the identifier you want for it.
        FabricStructureBuilder.create(new Identifier(StructuresMain.MOD_ID, "stone"), STONE)

                /* Generation stage for when to generate the structure. there are 10 stages you can pick from!
                   This surface structure stage places the structure before plants and ores are generated. */
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)

                .defaultConfig(new StructureConfig(
                        10, /* average distance apart in chunks between spawn attempts */
                        5, /* minimum distance apart in chunks between spawn attempts. MUST BE LESS THAN ABOVE VALUE */
                        534313544 /* this modifies the seed of the structure so no two structures always spawn over each-other. Make this large and unique. */))

                /*
                 * Whether surrounding land will be modified automatically to conform to the bottom of the structure.
                 * Basically, it adds land at the base of the structure like it does for Villages and Outposts.
                 * Doesn't work well on structure that have pieces stacked vertically or change in heights.
                 *
                 * Note: The air space this method will create will be filled with water if the structure is below sealevel.
                 * This means this is best for structure above sealevel so keep that in mind.
                 */
                .adjustsSurface()

                /* Finally! Now we register our structure and everything above will take effect. */
                .register();
        
        FabricStructureBuilder.create(new Identifier(StructuresMain.MOD_ID, "small_palace"), SMALL_PALACE)
        .step(GenerationStep.Feature.SURFACE_STRUCTURES)
        .defaultConfig(new StructureConfig(
        		80, /* average distance apart in chunks between spawn attempts */
                50, /* minimum distance apart in chunks between spawn attempts. MUST BE LESS THAN ABOVE VALUE */
                58476814))
        .adjustsSurface()
        .register();
        
        FabricStructureBuilder.create(new Identifier(StructuresMain.MOD_ID, "campsite"), CAMPSITE)
        .step(GenerationStep.Feature.SURFACE_STRUCTURES)
        .defaultConfig(new StructureConfig(
        		40, /* average distance apart in chunks between spawn attempts */
                20, /* minimum distance apart in chunks between spawn attempts. MUST BE LESS THAN ABOVE VALUE */
                54373156))
        .adjustsSurface()
        .register();

        // Add more structures here and so on
    }
}