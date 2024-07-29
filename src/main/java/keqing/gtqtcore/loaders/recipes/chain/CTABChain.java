package keqing.gtqtcore.loaders.recipes.chain;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraftforge.client.event.ColorHandlerEvent;

import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtechfoodoption.GTFOMaterialHandler.Stearin;
import static gregtechfoodoption.GTFOMaterialHandler.Vanillin;
import static keqing.gtqtcore.api.recipes.GTQTcoreRecipeMaps.*;
import static keqing.gtqtcore.api.unification.GCYSMaterials.*;
import static keqing.gtqtcore.api.unification.GTQTMaterials.*;
import static net.minecraft.init.Items.STRING;

public class CTABChain {
    public static void init() {
        DNA();
        CTAB();
        Hydroxyethanethiol();
        TRIS();
        Isoamylalcohol();
    }

    private static void DNA() {
        CHEMICAL_PLANT.recipeBuilder()
                .input(OrePrefix.dust,Meat,16)
                .input(OrePrefix.dust,Salt,16)
                .fluidInputs(TRIS.getFluid(1000))
                .fluidInputs(Hydroxyethanethiol.getFluid(1000))
                .fluidInputs(CTAB.getFluid(1000))
                .fluidInputs(Isoamylalcohol.getFluid(1000))
                .fluidInputs(Chloroform.getFluid(40))
                .fluidOutputs(DNAa.getFluid(4000))
                .recipeLevel(3)
                .duration(400).EUt(VA[HV]).buildAndRegister();

        BIOLOGICAL_REACTION_RECIPES.recipeBuilder()
                .fluidInputs(DNAa.getFluid(400))
                .fluidInputs(AmmoniumChloride.getFluid(500))
                .fluidOutputs(DNAb.getFluid(400))
                .rate(60)
                .duration(400).EUt(VA[HV]).buildAndRegister();

        BIOLOGICAL_REACTION_RECIPES.recipeBuilder()
                .fluidInputs(DNAb.getFluid(100))
                .fluidInputs(Bacteria.getFluid(200))
                .fluidOutputs(DNAc.getFluid(100))
                .rate(60)
                .duration(400).EUt(VA[HV]).buildAndRegister();

        BIOLOGICAL_REACTION_RECIPES.recipeBuilder()
                .fluidInputs(DNAc.getFluid(100))
                .fluidInputs(Biomass.getFluid(200))
                .fluidOutputs(DNAd.getFluid(100))
                .rate(60)
                .duration(400).EUt(VA[HV]).buildAndRegister();

        BIOLOGICAL_REACTION_RECIPES.recipeBuilder()
                .fluidInputs(DNAd.getFluid(100))
                .fluidInputs(Biomass.getFluid(200))
                .input(OrePrefix.dust,Agar)
                .fluidOutputs(DNAe.getFluid(100))
                .rate(60)
                .duration(400).EUt(VA[HV]).buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder()
                .input(OrePrefix.dust,Wool,9)
                .output(STRING,4)
                .duration(80).EUt(VA[LV]).buildAndRegister();
        //定向培养
        //这里得到干细胞 定向
        CommmonOut(DNAe);
        //特殊培养
        SpecialOut(DNAh,Gunpowder,HV,20);
        SpecialOut(DNAl,Blaze,HV,20);
        SpecialOut(DNAp,EnderPearl,HV,40);
        SpecialOut(DNAw,Wool,HV,40);
        SpecialOut(DNAn,NetherStar,IV,80);
    }

    private static void SpecialOut(Material material1,Material material2,int n,int rate) {
        //培养
        BIOLOGICAL_REACTION_RECIPES.recipeBuilder()
                .fluidInputs(DNAe.getFluid(1000))
                .fluidInputs(Mutagen.getFluid(1000))
                .input(OrePrefix.dust,material2,64)
                .fluidOutputs(material1.getFluid(200))
                .circuitMeta(1)
                .rate(rate)
                .duration(400).EUt(VA[n]).buildAndRegister();
        //增值
        BIOLOGICAL_REACTION_RECIPES.recipeBuilder()
                .fluidInputs(material1.getFluid(1000))
                .fluidInputs(Mutagen.getFluid(1000))
                .fluidOutputs(material1.getFluid(2000))
                .circuitMeta(2)
                .rate(rate)
                .duration(1200).EUt(VA[n+2]).buildAndRegister();

        //生产
        BIOLOGICAL_REACTION_RECIPES.recipeBuilder()
                .fluidInputs(material1.getFluid(100))
                .output(OrePrefix.dust,material2,16)
                .output(OrePrefix.dust,Bone,16)
                .output(OrePrefix.dust,Meat,16)
                .circuitMeta(3)
                .rate(rate)
                .duration(4000).EUt(VA[n]).buildAndRegister();
    }
    private static void CommmonOut(Material material) {
        BIOLOGICAL_REACTION_RECIPES.recipeBuilder()
                .fluidInputs(DNAe.getFluid(1000))
                .fluidInputs(Mutagen.getFluid(1000))
                .fluidOutputs(DNAe.getFluid(200))
                .circuitMeta(2)
                .rate(60)
                .duration(400).EUt(VA[HV]).buildAndRegister();

        BIOLOGICAL_REACTION_RECIPES.recipeBuilder()
                .fluidInputs(material.getFluid(100))
                .output(OrePrefix.dust,Bone,16)
                .output(OrePrefix.dust,Meat,16)
                .circuitMeta(3)
                .rate(60)
                .duration(4000).EUt(VA[HV]).buildAndRegister();
    }
    private static void Isoamylalcohol() {
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(OrePrefix.dust,PotassiumDichromate)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .inputs(Vanillin.getItemStack())
                .fluidOutputs(Isoamylalcohola.getFluid(1000))
                .duration(400).EUt(VA[HV]).buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder()
                .fluidInputs(Isoamylalcohola.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(2000))
                .fluidOutputs(Isoamylalcohol.getFluid(1000))
                .duration(400).EUt(VA[HV]).buildAndRegister();
    }

    private static void TRIS() {
        //是用硝基甲烷和过量多聚甲醛为主要原料，在一定温度下缩合反应成三羟甲基硝基甲烷；再在一定温度、压力、溶剂和催化剂下，通过加氢还原反应，经结晶，重结晶制成三羟甲基氨基甲烷
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Tetranitromethane.getFluid(1000))
                .fluidInputs(Formaldehyde.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(2000))
                .fluidOutputs(TRISN.getFluid(1000))
                .duration(400).EUt(VA[HV]).buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(TRISN.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(2000))
                .fluidOutputs(TRISP.getFluid(1000))
                .duration(400).EUt(VA[HV]).buildAndRegister();

        AUTOCLAVE_RECIPES.recipeBuilder()
                .fluidInputs(TRISP.getFluid(100))
                .fluidOutputs(TRIS.getFluid(100))
                .duration(4000).EUt(VA[HV]).buildAndRegister();

        CRYSTALLIZER_RECIPES.recipeBuilder()
                .fluidInputs(TRISN.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(2000))
                .fluidOutputs(TRIS.getFluid(1000))
                .blastFurnaceTemp(4500)
                .duration(400).EUt(VA[IV]).buildAndRegister();


    }

    private static void Hydroxyethanethiol() {
        //以二硫化钠和氯乙醇为主要原料，合成二硫代二甘醇，经电解还原可制得2-巯基乙醇
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(OrePrefix.dust,SodiumSulfide)
                .fluidInputs(EthyleneChlorohydrin.getFluid(1000))
                .fluidOutputs(Erliudaierganchun.getFluid(1000))
                .duration(400).EUt(VA[HV]).buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder()
                .fluidInputs(Erliudaierganchun.getFluid(1000))
                .notConsumable(OrePrefix.plate,Palladium)
                .fluidOutputs(Hydroxyethanethiol.getFluid(1000))
                .duration(400).EUt(VA[HV]).buildAndRegister();
    }

    private static void CTAB() {
        //先由十六醇和溴素在红磷催化作用下反应下制得溴代十六烷，再和三甲胺季铵反应即可。
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Hexadecanol.getFluid(1000))
                .fluidInputs(Bromine.getFluid(1000))
                .notConsumable(OrePrefix.gem,RedPhosphorus)
                .fluidOutputs(Xiudaishiliuwan.getFluid(1000))
                .duration(400).EUt(VA[HV]).buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Xiudaishiliuwan.getFluid(1000))
                .fluidInputs(Trimethylamine.getFluid(1000))
                .fluidOutputs(CTAB.getFluid(1000))
                .duration(400).EUt(VA[HV]).buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Stearin.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(Hexadecanol.getFluid(1000))
                .duration(400).EUt(VA[HV]).buildAndRegister();
    }
}
