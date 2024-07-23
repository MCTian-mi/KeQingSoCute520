package keqing.gtqtcore.loaders.recipes.handlers;

import net.minecraftforge.common.config.Config;

import static gregtech.api.unification.material.Materials.*;
import static keqing.gtqtcore.api.recipes.GTQTcoreRecipeMaps.BIOMASS_GENERATOR_RECIPES;

public class BioReactor {

    public static void init() {
        BiomassGeneratorRecipes();
    }

    private static void BiomassGeneratorRecipes() {
        BIOMASS_GENERATOR_RECIPES.recipeBuilder()
                .fluidInputs(Biomass.getFluid(200))
                .EUt(120)
                .duration(10)
                .buildAndRegister();

        BIOMASS_GENERATOR_RECIPES.recipeBuilder()
                .fluidInputs(FermentedBiomass.getFluid(200))
                .EUt(120)
                .duration(20)
                .buildAndRegister();

        BIOMASS_GENERATOR_RECIPES.recipeBuilder()
                .fluidInputs(BacterialSludge.getFluid(200))
                .EUt(480)
                .duration(30)
                .buildAndRegister();

        BIOMASS_GENERATOR_RECIPES.recipeBuilder()
                .fluidInputs(EnrichedBacterialSludge.getFluid(200))
                .EUt(1920)
                .duration(40)
                .buildAndRegister();

        BIOMASS_GENERATOR_RECIPES.recipeBuilder()
                .fluidInputs(SterileGrowthMedium.getFluid(200))
                .EUt(1920)
                .duration(50)
                .buildAndRegister();

        BIOMASS_GENERATOR_RECIPES.recipeBuilder()
                .fluidInputs(Mutagen.getFluid(200))
                .EUt(480)
                .duration(60)
                .buildAndRegister();
    }
}
