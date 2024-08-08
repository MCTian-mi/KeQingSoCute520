package keqing.gtqtcore.loaders.recipes.chain;

import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.GTRecipeHandler;
import net.minecraftforge.fluids.FluidStack;


import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.ingotHot;
import static keqing.gtqtcore.api.recipes.GTQTcoreRecipeMaps.*;
import static keqing.gtqtcore.api.unification.GCYSMaterials.*;
import static keqing.gtqtcore.api.unification.GTQTMaterials.*;
import static keqing.gtqtcore.common.metatileentities.GTQTMetaTileEntities.CRYOGENIC_REACTOR;
import static keqing.gtqtcore.common.metatileentities.GTQTMetaTileEntities.DISTILLATION_TOWER;

public class NaquadahChain {
    public static void init() {



        //TODO

        //硅岩前处理-----------------------------------------------------------------------------

        //傻逼ep，不会写产线就不要写
        //硅岩换氧化硅岩，前处理得到硅岩后，衔接如下产线
        //需要移除硅岩矿
        //浓缩富集硅岩矿泥 氧化硅岩混合物粉 氧化精金粉

        BLAST_RECIPES.recipeBuilder()
                .input(dust,Yanghuaguiyan,1)
                .fluidInputs(FluoroantimonicAcid.getFluid(1500))
                .output(dust,TitaniumTrifluoride,2)
                .fluidOutputs(Dichunduguiyanruye.getFluid(1000))
                .blastFurnaceTemp(3000)
                .duration(100).EUt(480).buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust,SodiumOxide,27)
                .fluidInputs(Dichunduguiyanruye.getFluid(10000))
                .output(dust,Galliumoh,70)
                .output(dust,Antimony,15)
                .fluidOutputs(Dichunduguiyanrongye.getFluid(9000))
                .duration(1000).EUt(1920).buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(DiethylhexylPhosphoricAcid.getFluid(4000))
                .fluidInputs(Dichunduguiyanrongye.getFluid(36000))
                .fluidOutputs(Nqad.getFluid(30000))
                .fluidOutputs(Feifu.getFluid(10000))
                .duration(1000).EUt(1920).buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Feifu.getFluid(1500))
                .input(dust,Quicklime,30)
                .fluidOutputs(Feiye.getFluid(1000))
                .output(dust,CalciumDifluoride,30)
                .duration(1000).EUt(1920).buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(Feiye.getFluid(4000))
                .fluidOutputs(SaltWater.getFluid(2000))
                .fluidOutputs(HydrofluoricAcid.getFluid(1000))
                .fluidOutputs(Phenol.getFluid(1000))
                .duration(100).EUt(1920).buildAndRegister();

        NEUTRON_ACTIVATOR.recipeBuilder()
                .fluidInputs(Nqad.getFluid(3000))
                .fluidOutputs(Gaochunguiyanrongye.getFluid(2000))
                .output(dust,Nongsuofujiguiyankuangni,1)
                .output(dust,Yanghuaguiyanhunhe,2)
                .output(dust,Adamantite,1)
                .EUt(VA[IV])
                .part(200)
                .duration(400)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder()
                .input(dust,Yanghuaguiyanhunhe,1)
                .input(dust,Carbon,1)
                .output(ingotHot,Naquadah,1)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .blastFurnaceTemp(5000)
                .duration(100).EUt(480).buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().EUt(VA[LuV]).duration(600)
                .fluidInputs(FluoroantimonicAcid.getFluid(1000))
                .input(dust, Yanghuaguiyanhunhe, 6)
                .fluidOutputs(ImpureEnrichedNaquadahSolution.getFluid(2000))
                .fluidOutputs(ImpureNaquadriaSolution.getFluid(2000))
                .output(dust, TitaniumTrifluoride, 4)
                .buildAndRegister();


        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,SodiumOxide,27)
                .fluidInputs(Gaochunguiyanrongye.getFluid(5000))
                .output(dust,Yanghuaguiyan,30)
                .fluidOutputs(DiethylhexylPhosphoricAcid.getFluid(1000))
                .duration(200).EUt(120).buildAndRegister();

        //中子活化器			6s	中子动能460~480MeV	浓缩富集硅岩矿泥粉 16			硫酸富集硅岩粉 165	低纯硫酸超能硅岩粉 2	硫酸钠粉 140
        NEUTRON_ACTIVATOR.recipeBuilder()
                .input(dust,Nongsuofujiguiyankuangni,16)
                .fluidOutputs(ImpureEnrichedNaquadahSolution.getFluid(16000))
                .output(dust,SodiumSulfide,140)
                .output(dust,Dichunliusuanchaonengguiyan,2)
                .EUt(VA[IV])
                .part(200)
                .duration(400)
                .buildAndRegister();

        //化学反应釜		1920	25s		低纯硫酸超能硅岩粉 3	P-507 500L	水 3,000L			低纯硫酸超能硅岩溶液 3,500L
        //蒸馏塔		7680	25s			低纯硫酸超能硅岩溶液 7,000L		氧化富集硅岩粉 2		高纯超能硅岩溶液 5,400L	稀硫酸 12,000L	P-507 1,000L
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust,Dichunliusuanchaonengguiyan,3)
                .fluidInputs(DiethylhexylPhosphoricAcid.getFluid(500))
                .fluidInputs(Water.getFluid(3000))
                .fluidOutputs(Dichunliusuanchaonengguiyanr.getFluid(3500))
                .duration(200).EUt(120).buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(Dichunliusuanchaonengguiyanr.getFluid(7000))
                .fluidOutputs(Gaochunchaonengguiyanrongye.getFluid(200))
                .fluidOutputs(DiethylhexylPhosphoricAcid.getFluid(1000))
                .fluidOutputs(ImpureNaquadriaSolution.getFluid(6000))
                .fluidOutputs(SulfuricAcid.getFluid(12000))
                .duration(200).EUt(7680).buildAndRegister();

        NEUTRON_ACTIVATOR.recipeBuilder()
                .fluidInputs(Gaochunchaonengguiyanrongye.getFluid(4000))
                .output(dust,Dichunliusuanchaonengguiyan,2)
                .fluidOutputs(NaquadriaSolution.getFluid(1000))
                .fluidOutputs(Gaochunguiyanrongye.getFluid(1000))
                .EUt(VA[IV])
                .part(100)
                .duration(400)
                .buildAndRegister();
        //硅岩后处理-----------------------------------------------------------------------------
        //  Enriched Naquadah Chain
        GTRecipeHandler.removeRecipesByInputs(CENTRIFUGE_RECIPES,
                ImpureEnrichedNaquadahSolution.getFluid(2000));

        GTRecipeHandler.removeRecipesByInputs(CENTRIFUGE_RECIPES,
                EnrichedNaquadahSolution.getFluid(3000));

        //  Impure Enriched Naquadah Solution + Fluorine -> Hexafluoride Enriched Naquadah Solution
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(ImpureEnrichedNaquadahSolution.getFluid(1000))
                .fluidInputs(Fluorine.getFluid(6000))
                .fluidOutputs(HexafluorideEnrichedNaquadahSolution.getFluid(1000))
                .EUt(VA[IV])
                .duration(400)
                .buildAndRegister();

        //  Hexafluoride Enriched Naquadah Solution + Xenon -> Xenon Hexafluoro Enriched Naquadate
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HexafluorideEnrichedNaquadahSolution.getFluid(1000))
                .fluidInputs(Xenon.getFluid(1000))
                .fluidOutputs(XenonHexafluoroEnrichedNaquadate.getFluid(1000))
                .EUt(VA[HV])
                .duration(200)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        //  Palladium on Carbon + Gold Trifluoride + Xenon Hexafluoro Enriched Naquadate + Fluoroantimonic Acid + Hydrogen -> Enriched Naquadah Solution + Enriched Naquadah Residue Solution + Hydrofluoric Acid
        CHEMICAL_PLANT.recipeBuilder()
                .notConsumable(dust, PalladiumOnCarbon)
                .input(dust, GoldTrifluoride, 4)
                .fluidInputs(XenonHexafluoroEnrichedNaquadate.getFluid(1000))
                .fluidInputs(FluoroantimonicAcid.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(9000))
                .fluidOutputs(EnrichedNaquadahSolution.getFluid(1000))
                .fluidOutputs(EnrichedNaquadahResidueSolution.getFluid(1000))
                .fluidOutputs(HydrofluoricAcid.getFluid(8000))
                .EUt(VA[LuV])
                .recipeLevel(4)
                .duration(1200)
                .buildAndRegister();

        //  Enriched Naquadah Residue Solution -> Trinium Sulfide + Xenoauric Fluoroantimonic Acid
        DRYER_RECIPES.recipeBuilder()
                .fluidInputs(EnrichedNaquadahResidueSolution.getFluid(2000))
                .output(dust, TriniumSulfide)
                .fluidOutputs(XenoauricFluoroantimonicAcid.getFluid(1000))
                .EUt(VA[EV])
                .duration(480)
                .buildAndRegister();

        //  Xenoauric Fluoroantimonic Acid Cycle
        ELECTROLYZER_RECIPES.recipeBuilder()
                .fluidInputs(XenoauricFluoroantimonicAcid.getFluid(1000))
                .output(dust, Gold)
                .output(dust, AntimonyTrifluoride)
                .fluidOutputs(Xenon.getFluid(1000))
                .fluidOutputs(HydrofluoricAcid.getFluid(3000))
                .EUt(VA[IV])
                .duration(1200)
                .buildAndRegister();

        //  Gold Chloride + Bromine Trifluoride -> Gold Trifluoride + Bromine + Chlorine
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(GoldChloride.getFluid(1000))
                .fluidInputs(BromineTrifluoride.getFluid(2000))
                .output(dust, GoldTrifluoride, 8)
                .fluidOutputs(Bromine.getFluid(2000))
                .fluidOutputs(Chlorine.getFluid(6000))
                .EUt(VA[MV])
                .duration(200)
                .buildAndRegister();

        //  Bromine + Chlorine -> Bromine Trifluoride
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Bromine.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(3000))
                .circuitMeta(3)
                .fluidOutputs(BromineTrifluoride.getFluid(1000))
                .EUt(VA[LV])
                .duration(120)
                .buildAndRegister();

        //  Gold + Chlorine -> Gold Chloride
        ELECTROLYZER_RECIPES.recipeBuilder()
                .input(dust, Gold, 2)
                .fluidInputs(Chlorine.getFluid(6000))
                .fluidOutputs(GoldChloride.getFluid(1000))
                .EUt(VA[MV])
                .duration(360)
                .buildAndRegister();

        //  Naquadria Chain
        GTRecipeHandler.removeRecipesByInputs(CENTRIFUGE_RECIPES,
                ImpureNaquadriaSolution.getFluid(2000));

        GTRecipeHandler.removeRecipesByInputs(CENTRIFUGE_RECIPES,
                AcidicNaquadriaSolution.getFluid(3000));

        //  Impure Naquadria Solution + Fluorine -> Hexafluoride Naquadria Solution
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(ImpureNaquadriaSolution.getFluid(1000))
                .fluidInputs(Fluorine.getFluid(6000))
                .fluidOutputs(HexafluorideNaquadriaSolution.getFluid(1000))
                .EUt(VA[IV])
                .duration(400)
                .buildAndRegister();

        //  HexafluorideNaquadriaSolution + Radon Difluoride -> Radon Naquadria Octafluoride + Naquadria Residue Solution
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HexafluorideNaquadriaSolution.getFluid(1000))
                .fluidInputs(RadonDifluoride.getFluid(1000))
                .fluidOutputs(RadonNaquadriaOctafluoride.getFluid(1000))
                .fluidOutputs(NaquadriaResidueSolution.getFluid(1000))
                .EUt(VA[HV])
                .duration(800)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        //  Radon + Fluorine -> Radon Difluoride
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Radon.getFluid(1000))
                .fluidInputs(Fluorine.getFluid(2000))
                .circuitMeta(2)
                .fluidOutputs(RadonDifluoride.getFluid(1000))
                .EUt(VA[MV])
                .duration(100)
                .buildAndRegister();

        //  Naquadria Residue Solution -> Indium Phosphide + Naquadria Solution
        BURNER_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(NaquadriaResidueSolution.getFluid(2000))
                .output(dust, IndiumPhosphide)
                .fluidOutputs(NaquadriaSolution.getFluid(1000))
                .EUt(VA[IV])
                .duration(1200)
                .blastFurnaceTemp(880)
                .buildAndRegister();

        //  Naquadria Solution -> Sulfur + Naquadria Waste + Sulfur dust
        DRYER_RECIPES.recipeBuilder()
                .fluidInputs(NaquadriaSolution.getFluid(3000))
                .output(dust, Sulfur, 6)
                .fluidOutputs(NaquadriaWaste.getFluid(1000))
                .EUt(VA[HV])
                .duration(100)
                .buildAndRegister();

        //  Caesium Xenontrioxide Fluoride
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(CaesiumFluoride.getFluid(1000))
                .fluidInputs(XenonTrioxide.getFluid(1000))
                .fluidOutputs(CaesiumXenontrioxideFluoride.getFluid(1000))
                .EUt(VA[MV])
                .duration(480)
                .buildAndRegister();

        //  Caesium + Fluorine -> Cesium Fluoride
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Caesium)
                .fluidInputs(Fluorine.getFluid(1000))
                .circuitMeta(1)
                .fluidOutputs(CaesiumFluoride.getFluid(1000))
                .EUt(VA[MV])
                .duration(100)
                .buildAndRegister();

        //  Xenon + Oxygen -> Xenon Trioxide
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Xenon.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(3000))
                .fluidOutputs(XenonTrioxide.getFluid(1000))
                .EUt(VA[MV])
                .duration(100)
                .buildAndRegister();

        //  Radon Naquadria Octafluoride + Caesium Xenontrioxide Fluoride -> Naquadria Caesium Xenonnonfluoride + Radon Trioxide
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(RadonNaquadriaOctafluoride.getFluid(1000))
                .fluidInputs(CaesiumXenontrioxideFluoride.getFluid(1000))
                .fluidOutputs(NaquadriaCaesiumXenonnonfluoride.getFluid(1000))
                .fluidOutputs(RadonTrioxide.getFluid(1000))
                .EUt(VA[IV])
                .duration(800)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        //  Naquadria Caesium Xenonnonfluoride + Nitryl Fluoride -> Naquadria Caesiumfluoride + Nitrosonium Octafluoroxenate
        CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(NaquadriaCaesiumXenonnonfluoride.getFluid(1000))
                .fluidInputs(NitrylFluoride.getFluid(2000))
                .fluidOutputs(NaquadriaCaesiumfluoride.getFluid(1000))
                .fluidOutputs(NitrosoniumOctafluoroxenate.getFluid(1000))
                .EUt(VA[EV])
                .duration(400)
                .temperature(75)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        //  Nitrogen Dioxide + Fluorine -> Nitryl Fluoride
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(NitrogenDioxide.getFluid(1000))
                .fluidInputs(Fluorine.getFluid(1000))
                .fluidOutputs(NitrylFluoride.getFluid(1000))
                .EUt(VA[MV])
                .duration(160)
                .buildAndRegister();

        //  Sulfuric Acid + Naquadria Caesiumfluoride -> Acidic Naquadria Caesiumfluoride
        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(SulfuricAcid.getFluid(2000))
                .fluidInputs(NaquadriaCaesiumfluoride.getFluid(1000))
                .fluidOutputs(AcidicNaquadriaCaesiumfluoride.getFluid(3000))
                .EUt(VA[IV])
                .duration(360)
                .buildAndRegister();

        //  Acidic Naquadria Caesiumfluoride -> Naquadria Sulfate + Caesium + Fluorine
        ELECTROLYZER_RECIPES.recipeBuilder()
                .fluidInputs(AcidicNaquadriaCaesiumfluoride.getFluid(1000))
                .output(dust, NaquadriaSulfate)
                .output(dust, Caesium)
                .fluidOutputs(Fluorine.getFluid(3000))
                .EUt(VA[LuV])
                .duration(120)
                .buildAndRegister();

        //  Acidic Naquadria Solution Cycle
        BURNER_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(AcidicNaquadriaSolution.getFluid(3000))
                .fluidOutputs(NaquadriaWaste.getFluid(1000))
                .fluidOutputs(ImpureEnrichedNaquadahSolution.getFluid(1000))
                .EUt(VA[ZPM])
                .duration(1000)
                .blastFurnaceTemp(1280)
                .buildAndRegister();
    }
}
