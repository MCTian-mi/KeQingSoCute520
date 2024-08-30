package keqing.gtqtcore.api.unification.matreials;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.fluids.attribute.FluidAttributes;
import gregtech.api.recipes.Recipe;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.material.properties.BlastProperty;
import keqing.gtqtcore.api.unification.GTQTMaterials;
import net.minecraft.util.text.TextFormatting;

import java.util.Collection;

import static gregicality.multiblocks.api.unification.GCYMMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;

import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.material.info.MaterialIconSet.*;
import static gregtech.api.util.GTUtility.gregtechId;
import static keqing.gtqtcore.api.unification.GCYSMaterials.*;
import static keqing.gtqtcore.api.unification.GTQTMaterials.*;


public class SecondDegreeMaterials {
    private static int startId = 21000;
    private static final int END_ID = startId + 1000;

    private static int getMaterialsId() {
        if (startId < END_ID) {
            return startId++;
        }
        throw new ArrayIndexOutOfBoundsException();
    }
    
    public SecondDegreeMaterials() {
    }
    public static void register() {

        GTQTMaterials.PineOil = new Material.Builder(getMaterialsId(), gregtechId( "pine_oil"))
                .fluid()
                .color(0xd6ac37)
                .build();

        GTQTMaterials.Periodicium = new Material.Builder(getMaterialsId(), gregtechId("periodicium"))
                .color(0x3D4BF6)
                .ingot(6)
                .iconSet(MaterialIconSet.SHINY)
                .blast(13500)
                .components(Hydrogen,1, Helium,1, Lithium,1, Beryllium,1,
                        Boron,1, Carbon,1, Nitrogen,1, Oxygen,1,
                        Fluorine,1, Neon,1, Sodium,1, Magnesium,1,
                        Aluminium,1, Silicon,1, Phosphorus,1, Sulfur,1,
                        Chlorine,1, Argon,1, Potassium,1, Calcium,1,
                        Scandium,1, Titanium,1, Vanadium,1, Chrome,1,
                        Manganese,1, Iron,1, Cobalt,1, Nickel,1,
                        Copper,1, Zinc,1, Gallium,1, Germanium,1,
                        Arsenic,1, Selenium,1, Bromine,1, Krypton,1,
                        Rubidium,1, Strontium,1, Yttrium,1, Zirconium,1,
                        Niobium,1, Molybdenum,1, Technetium,1, Ruthenium,1,
                        Rhodium,1, Palladium,1, Silver,1, Cadmium,1,
                        Indium,1, Tin,1, Antimony,1, Tellurium,1,
                        Iodine,1, Xenon,1, Caesium,1, Barium,1,
                        Lanthanum,1, Cerium,1, Praseodymium,1, Neodymium,1,
                        Promethium,1, Samarium,1, Europium,1, Gadolinium,1,
                        Terbium,1, Dysprosium,1, Holmium,1, Erbium,1,
                        Thulium,1, Ytterbium,1, Lutetium,1, Hafnium,1,
                        Tantalum,1, Tungsten,1, Rhenium,1, Osmium,1,
                        Iridium,1, Platinum,1, Gold,1, Mercury,1,
                        Thallium,1, Lead,1, Bismuth,1, Polonium,1,
                        Astatine,1, Radon,1, Francium,1, Radium,1,
                        Actinium,1, Thorium,1, Protactinium,1, Uranium235,1,
                        Neptunium,1, Plutonium239,1, Americium,1, Curium,1,
                        Berkelium,1, Californium,1, Einsteinium,1, Fermium,1,
                        Mendelevium,1, Nobelium,1, Lawrencium,1, Rutherfordium,1,
                        Dubnium,1, Seaborgium,1, Bohrium,1, Hassium,1,
                        Meitnerium,1, Darmstadtium,1, Roentgenium,1, Copernicium,1,
                        Nihonium,1, Flerovium,1, Moscovium,1, Livermorium, 1,Tennessine,1,
                        Oganesson,1)
                .build();

        //注册泡沫
        GTQTMaterials.AlmandineFront  = new Material.Builder(getMaterialsId(), gregtechId("almandine_front")).fluid().color(0xD70505).build();
        GTQTMaterials.PentlanditeFront  = new Material.Builder(getMaterialsId(), gregtechId("pentlandite_front")).fluid().color(0x8c800a).build();
        GTQTMaterials.ChalcopyriteFront  = new Material.Builder(getMaterialsId(), gregtechId( "chalcopyrite_front")).fluid().color(0x896826).build();
        GTQTMaterials.GrossularFront  = new Material.Builder(getMaterialsId(), gregtechId( "grossular_front")).fluid().color(0xab5860).build();
        GTQTMaterials.MonaziteFront  = new Material.Builder(getMaterialsId(), gregtechId("monazite_front")).fluid().color(0x2e3f2e).build();
        GTQTMaterials.NickelFront  = new Material.Builder(getMaterialsId(), gregtechId("nickel_front")).fluid().color(0xABABD5).build();
        GTQTMaterials.PlatinumFront  = new Material.Builder(getMaterialsId(), gregtechId( "platinum_front")).fluid().color(0xe2e2b2).build();
        GTQTMaterials.PyropeFront  = new Material.Builder(getMaterialsId(), gregtechId("pyrope_front")).fluid().color(0x682E57).build();
        GTQTMaterials.RedstoneFront  = new Material.Builder(getMaterialsId(), gregtechId( "redstone_front")).fluid().color(0xAC0505).build();
        GTQTMaterials.SpessartineFront  = new Material.Builder(getMaterialsId(), gregtechId( "spessartine_front")).fluid().color(0XDF5A5A).build();
        GTQTMaterials.SphaleriteFront  = new Material.Builder(getMaterialsId(), gregtechId( "sphalerite_front")).fluid().color(0xD9D9D9).build();

        GTQTMaterials.MetallicHydrogen = new Material.Builder(getMaterialsId(), gregtechId("metallic_hydrogen"))
                .ingot().fluid()
                .iconSet(MaterialIconSet.SHINY)
                .flags(GENERATE_PLATE, GENERATE_RING, GENERATE_ROUND, GENERATE_ROTOR, GENERATE_BOLT_SCREW, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_LONG_ROD, GENERATE_FRAME)
                .color(0x4682B4)
                .fluidPipeProperties(10240, 32000, true, true, true, true)
                .components(Hydrogen, 1)
                .build();

        GTQTMaterials.Ethylenimine = new Material.Builder(getMaterialsId(), gregtechId( "ethylenimine"))
                .fluid()
                .color(0x483D8B)
                .components(Carbon, 2, Hydrogen, 5, Nitrogen, 1)
                .build();

        GTQTMaterials.Polyethyleneimine = new Material.Builder(getMaterialsId(), gregtechId("polyethylenimine"))
                .fluid()
                .color(0x483DB4)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 2, Hydrogen, 5, Nitrogen, 1)
                .build();

        GTQTMaterials.RedMud = new Material.Builder(getMaterialsId(), gregtechId("red_mud"))
                .fluid()
                .color(0x483DB4)
                .flags(DISABLE_DECOMPOSITION)
                .components(Rutile, 1,Gallium,1, HydrochloricAcid, 2)
                .build();

        GTQTMaterials.CarbenDisulfide  = new Material.Builder(getMaterialsId(), gregtechId("carbon_disulfide"))
                .fluid()
                .colorAverage()
                .components(Carbon, 1, Sulfur, 2)
                .build();

        GTQTMaterials.UreaMix = new Material.Builder(getMaterialsId(), gregtechId("urea_mix"))
                .fluid()
                .color(0x443610)
                .build();

        GTQTMaterials.FermentationBase = new Material.Builder(getMaterialsId(), gregtechId( "fermentation_base"))
                .fluid()
                .color(0x5E5839)
                .build();

        GTQTMaterials.Resin = new Material.Builder(getMaterialsId(), gregtechId("resin"))
                .fluid()
                .color(0x353533)
                .build();

        GTQTMaterials.CalciumCarbonate = new Material.Builder(getMaterialsId(), gregtechId("calcium_carbonate"))
                .dust()
                .components(Materials.Calcium, 1, Materials.Carbon, 1, Materials.Oxygen, 3)
                .color(0xE8E8CB)
                .build();

        GTQTMaterials.PropionicAcid = new Material.Builder(getMaterialsId(), gregtechId("propionic_acid"))
                .fluid()
                .color(0xB3BC88)
                .build();

        GTQTMaterials.SodiumAluminate = new Material.Builder(getMaterialsId(), gregtechId( "sodium_aluminate"))
                .dust()
                .colorAverage()
                .components(Sodium, 1, Aluminium, 1, Oxygen, 2)
                .build();

        GTQTMaterials.CarbenDisulfide  = new Material.Builder(getMaterialsId(), gregtechId("carbon_disulfide"))
                .fluid()
                .colorAverage()
                .components(Carbon, 1, Sulfur, 2)
                .build();

        GTQTMaterials.CarbenDisulfide  = new Material.Builder(getMaterialsId(), gregtechId("carbon_disulfide"))
                .fluid()
                .colorAverage()
                .components(Carbon, 1, Sulfur, 2)
                .build();

        //硫酸锌粉
        GTQTMaterials.ZincSulfate  = new Material.Builder(getMaterialsId(), gregtechId("zinc_sulfate"))
                .dust()
                .components(Zinc, 1, Sulfur, 1,Oxygen,4)
                .color(0x4682B4)
                .build();

        //粗制铑金属粉
        GTQTMaterials.RhodiumRaw  = new Material.Builder(getMaterialsId(), gregtechId("rhodium_raw"))
                .dust()
                .color(0x40E0D0)
                .build();

        //铑盐粉
        GTQTMaterials.RhodiumSalt  = new Material.Builder(getMaterialsId(), gregtechId("rhodium_salt"))
                .fluid().dust()
                .color(0x8DB6CD)
                .build();
        //硝酸铑粉
        GTQTMaterials.RhodiumNitrateSolution  = new Material.Builder(getMaterialsId(), gregtechId("rhodium_nitrate_solution"))
                .dust()
                .color(0x8470FF)
                .build();
        //再沉淀铑粉
        GTQTMaterials.RhodiumDo  = new Material.Builder(getMaterialsId(), gregtechId("rhodium_do"))
                .dust()
                .color(0x8B7765)
                .build();

        //四氧化钌水溶
        GTQTMaterials.RutheniumfOxide  = new Material.Builder(getMaterialsId(), gregtechId("rutheniumf_oxide"))
                .fluid().dust()
                .color(0x8B4726)
                .build();

        //氧化钌
        GTQTMaterials.RutheniumOxide  = new Material.Builder(getMaterialsId(), gregtechId("ruthenium_oxide"))
                .dust()
                .color(0xA020F0)
                .build();

        //高纯超细氧化钌
        GTQTMaterials.RutheniumgOxide  = new Material.Builder(getMaterialsId(), gregtechId("rutheniumg_oxide"))
                .dust()
                .color(0xADFF2F)
                .build();

        //酸性铱溶液
        GTQTMaterials.IridiumFluid  = new Material.Builder(getMaterialsId(), gregtechId("Iridium_fluid"))
                .dust()
                .color(0xFFFF00)
                .build();

        //硫化金处理
        //焙烧
        GTQTMaterials.LeanGoldBs  = new Material.Builder(getMaterialsId(), gregtechId("len_gold_bs"))
                .dust()
                .color(0xFFFF00)
                .build();

        //氰化钠浸出
        GTQTMaterials.LeanGoldJc  = new Material.Builder(getMaterialsId(), gregtechId("len_gold_js"))
                .fluid()
                .color(0xFFFF00)
                .build();

        GTQTMaterials.LeanCopperJc  = new Material.Builder(getMaterialsId(), gregtechId("len_copper_js"))
                .dust()
                .color(0xFFFF00)
                .build();

        GTQTMaterials.LeanGoldCl  = new Material.Builder(getMaterialsId(), gregtechId("len_gold_Cl"))
                .fluid()
                .color(0xFFFF00)
                .build();

        GTQTMaterials.LeanGoldDr  = new Material.Builder(getMaterialsId(), gregtechId("len_gold_dr"))
                .dust()
                .color(0xFFFF00)
                .build();

        GTQTMaterials.RichGoldBs  = new Material.Builder(getMaterialsId(), gregtechId("rich_gold_bs"))
                .dust()
                .color(0xFFFF00)
                .build();

        GTQTMaterials.RichCopperJc  = new Material.Builder(getMaterialsId(), gregtechId("rich_copper_js"))
                .dust()
                .color(0xFFFF00)
                .build();

        //  13078 Dichlorocyclooctadieneplatinium
        GTQTMaterials.Dichlorocyclooctadieneplatinium = new Material.Builder(getMaterialsId(), gregtechId("dichlorocyclooctadieneplatinium"))
                .dust()
                .color(0xD4E982)
                .iconSet(BRIGHT)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 8, Hydrogen, 12, Chlorine, 2, Platinum, 1)
                .build();

        //  13079 Diiodobiphenyl
        GTQTMaterials.Diiodobiphenyl = new Material.Builder(getMaterialsId(), gregtechId("diiodobiphenyl"))
                .dust()
                .color(0x000C52)
                .iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 12, Hydrogen, 8, Iodine, 2)
                .build();

        //  11086 Silver Tetrafluoroborate
        GTQTMaterials.SilverTetrafluoroborate = new Material.Builder(getMaterialsId(), gregtechId("silver_tetrafluoroborate"))
                .liquid()
                .color(0x818024)
                .flags(DISABLE_DECOMPOSITION)
                .components(Silver, 1, Boron, 1, Fluorine, 4)
                .build()
                .setFormula("AgBF4", true);


        //  11088 Trimethyltin Chloride
        GTQTMaterials.TrimethyltinChloride = new Material.Builder(getMaterialsId(), gregtechId("trimethyltin_chloride"))
                .liquid()
                .color(0x7F776F)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 3, Hydrogen, 6, Tin, 1, Chlorine, 1)
                .build()
                .setFormula("(CH3)3SnCl", true);

        //  11084 Silver Chloride
        GTQTMaterials.SilverChloride = new Material.Builder(getMaterialsId(), gregtechId("silver_chloride"))
                .dust()
                .color(0x8D8D8D)
                .iconSet(METALLIC)
                .components(Silver, 1, Chlorine, 1)
                .build();

        //  13082 Octene
        GTQTMaterials.Octene = new Material.Builder(getMaterialsId(), gregtechId("octene"))
                .liquid()
                .color(0x818022)
                .components(Carbon, 8, Hydrogen, 16)
                .build();

        //  13077 Cyclooctadiene
        GTQTMaterials.Cyclooctadiene = new Material.Builder(getMaterialsId(), gregtechId("cyclooctadiene"))
                .liquid()
                .color(0x40AC40)
                .components(Carbon, 8, Hydrogen, 12)
                .build();

        //  13084 Hexafluoropropylene
        GTQTMaterials.Hexafluoropropylene = new Material.Builder(getMaterialsId(), gregtechId("hexafluoropropylene"))
                .liquid()
                .color(0x141D6F)
                .components(Carbon, 3, Fluorine, 6)
                .build();

        //  11007 Hexachloroplatinic Acid
        GTQTMaterials.HexachloroplatinicAcid = new Material.Builder(getMaterialsId(), gregtechId("hexachloroplatinic_acid"))
                .liquid(new FluidBuilder().attribute(FluidAttributes.ACID))
                .color(0xFEF4D1)
                .components(Hydrogen, 2, Platinum, 1, Chlorine, 6)
                .build();

        //  11089 Potassium Tetrachloroplatinate
        GTQTMaterials.PotassiumTetrachloroplatinate = new Material.Builder(getMaterialsId(), gregtechId("potassium_tetrachloroplatinate"))
                .dust()
                .color(0xF1B04F)
                .iconSet(SHINY)
                .components(Potassium, 2, Platinum, 1, Chlorine, 4)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("K2PtCl4", true);

        //  13080 Hydroxylamine Disulfate
        GTQTMaterials.HydroxylamineDisulfate = new Material.Builder(getMaterialsId(), gregtechId("hydroxylamine_disulfate"))
                .liquid()
                .color(0x91A6D2)
                .components(Nitrogen, 4, Hydrogen, 16, Oxygen, 10, Sulfur, 2)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("(NH3OH)2(NH4)2(SO4)2", true);

        //  13120 Hydroxylamine Hydrochloride
        GTQTMaterials.HydroxylamineHydrochloride = new Material.Builder(getMaterialsId(), gregtechId("hydroxylamine_hydrochloride"))
                .liquid()
                .color(0x893E28)
                .components(Hydrogen, 4, Oxygen, 1, Nitrogen, 1, Chlorine,1 )
                .build()
                .setFormula("HONH2HCl", true);

        //  13081 Hydroxylamine
        GTQTMaterials.Hydroxylamine = new Material.Builder(getMaterialsId(), gregtechId("hydroxylamine"))
                .liquid()
                .color(0x91C791)
                .components(Hydrogen, 3, Nitrogen, 1, Oxygen, 1)
                .build()
                .setFormula("H3NO", true);

        //  11091 Ammonium Persulfate
        GTQTMaterials.AmmoniumPersulfate = new Material.Builder(getMaterialsId(), gregtechId("ammonium_persulfate"))
                .liquid()
                .color(0x4242B7)
                .components(Nitrogen, 2, Hydrogen, 8, Sulfur, 2, Oxygen, 8)
                .build()
                .setFormula("(NH4)2S2O8", true);

        //  11187 Lanthanum Fullerene Mixture
        GTQTMaterials.LanthanumFullereneMixture = new Material.Builder(getMaterialsId(), gregtechId("lanthanum_fullerene_mixture"))
                .dust()
                .color(0xD26D8E)
                .iconSet(BRIGHT)
                .build()
                .setFormula("(C60H30)La2", true);

        //  11085 Silver Oxide
        GTQTMaterials.SilverOxide = new Material.Builder(getMaterialsId(), gregtechId("silver_oxide"))
                .dust()
                .color(0xA4A4A4)
                .components(Silver, 2, Oxygen, 1)
                .build();

        //  11087 Tin Chloride
        GTQTMaterials.TinChloride = new Material.Builder(getMaterialsId(), gregtechId("tin_chloride"))
                .dust()
                .liquid()
                .color(0xDBDBDB)
                .iconSet(METALLIC)
                .components(Tin, 1, Chlorine, 2)
                .build();

        //  12025 Magneto Resonatic
        GTQTMaterials.MagnetoResonatic = new Material.Builder(getMaterialsId(), gregtechId("magneto_resonatic"))
                .gem()
                .color(0xFF97FF)
                .iconSet(MAGNETIC)
                .flags(NO_SMELTING, GENERATE_LENS)
                .build();
        MagnetoResonatic.setFormula("(Si₅O₁₀Fe)₃(Bi₂Te₃)₆(ZrO₂)Fe");

        //  12023 Prasiolite
        Prasiolite = new Material.Builder(getMaterialsId(), gregtechId("prasiolite"))
                .gem()
                .color(0x9EB749)
                .iconSet(QUARTZ)
                .flags(CRYSTALLIZABLE, GENERATE_LENS)
                .components(SiliconDioxide, 5, Iron, 1)
                .build();

        //  12024 Bismuth Tellurite
        GTQTMaterials.BismuthTellurite = new Material.Builder(getMaterialsId(), gregtechId("bismuth_tellurite"))
                .dust()
                .color(0x0E8933)
                .iconSet(DULL)
                .components(Bismuth, 2, Tellurium, 3)
                .build();


        //  11188 Lanthanum Embedded Fullerene
        GTQTMaterials.LanthanumEmbeddedFullerene = new Material.Builder(getMaterialsId(), gregtechId("lanthanum_embedded_fullerene"))
                .dust()
                .color(0x84FFAC)
                .iconSet(BRIGHT)
                .build()
                .setFormula("(C60H30)La2", true);

        //  12035 Lanthanum Fullerene Nanotube
        GTQTMaterials.LanthanumFullereneNanotube = new Material.Builder(getMaterialsId(), gregtechId("lanthanum_fullerene_nanotube"))
                .ingot()
                .color(0xD24473)
                .iconSet(BRIGHT)
                .flags(DISABLE_DECOMPOSITION,GENERATE_PLATE, GENERATE_RING, GENERATE_ROUND, GENERATE_ROTOR, GENERATE_BOLT_SCREW, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_LONG_ROD, GENERATE_FRAME)
                .build()
                .setFormula("(C60H30)C48La2", true);

        //  11219 Neutronium Nanotube
        GTQTMaterials.NeutroniumNanotube = new Material.Builder(getMaterialsId(), gregtechId("neutronium_nanotube"))
                .ingot()
                .color(0xDDBDCC)
                .iconSet(BRIGHT)
                .flags(DISABLE_DECOMPOSITION, GENERATE_PLATE)
                .components(Carbon, 48, Neutronium, 1)
                .build()
                .setFormula("Nt:CNT");

        GTQTMaterials.Gabbro = new Material.Builder(getMaterialsId(), gregtechId("gabbro"))
                .dust()
                .color(0x5C5C5C).iconSet(ROUGH)
                .components(Labradorite, 5, Bytownite, 3, Olivine, 2, Augite, 1, Biotite, 1)
                .build();

        GTQTMaterials.Gneiss = new Material.Builder(getMaterialsId(), gregtechId("gneiss"))
                .dust()
                .color(0x643631).iconSet(ROUGH)
                .components(Albite, 4, SiliconDioxide, 3, Biotite, 1, Muscovite, 1)
                .build();

        GTQTMaterials.Limestone = new Material.Builder(getMaterialsId(), gregtechId("limestone"))
                .dust()
                .color(0xa9a9a9).iconSet(ROUGH)
                .components(Calcite, 4, Dolomite, 1)
                .build();

        GTQTMaterials.Phyllite = new Material.Builder(getMaterialsId(), gregtechId("phyllite"))
                .dust()
                .color(0x716f71).iconSet(ROUGH)
                .components(Albite, 3, SiliconDioxide, 3, Muscovite, 4)
                .build();

        GTQTMaterials.Shale = new Material.Builder(getMaterialsId(), gregtechId("shale"))
                .dust()
                .color(0x3f2e2f).iconSet(ROUGH)
                .components(Calcite, 6, Clay, 2, SiliconDioxide, 1, Fluorite, 1)
                .build();

        GTQTMaterials.Slate = new Material.Builder(getMaterialsId(), gregtechId("slate"))
                .dust()
                .color(0x756869).iconSet(ROUGH)
                .components(SiliconDioxide, 5, Muscovite, 2, Clinochlore, 2, Albite, 1)
                .build();

        GTQTMaterials.Kimberlite = new Material.Builder(getMaterialsId(), gregtechId("kimberlite"))
                .dust()
                .color(0x201313).iconSet(ROUGH)
                .components(Forsterite, 3, Augite, 3, Andradite, 2, Lizardite, 1)
                .build();

        //铁系泡沫
        GTQTMaterials.IronFront  = new Material.Builder(getMaterialsId(), gregtechId( "iron_front")).fluid().color(0xD6D6D6).build();
        GTQTMaterials.BandedIronFront  = new Material.Builder(getMaterialsId(), gregtechId( "banded_iron_front")).fluid().color(0xDAA520).build();
        GTQTMaterials.BrownLimoniteFront  = new Material.Builder(getMaterialsId(), gregtechId( "brown_limonite_front")).fluid().color(0xD2691E).build();
        GTQTMaterials.YellowLimoniteFront  = new Material.Builder(getMaterialsId(), gregtechId( "yellow_limonite_front")).fluid().color(0xCDCD00).build();
        GTQTMaterials.ChromiteFront  = new Material.Builder(getMaterialsId(), gregtechId( "chromite_front")).fluid().color(0x8E8E38).build();
        GTQTMaterials.IlmeniteFront  = new Material.Builder(getMaterialsId(), gregtechId( "ilmenite_front")).fluid().color(0x8B814C).build();
        GTQTMaterials.MagnetiteFront  = new Material.Builder(getMaterialsId(), gregtechId( "magnetite_front")).fluid().color(0x8B7500).build();
        GTQTMaterials.PyriteFront  = new Material.Builder(getMaterialsId(), gregtechId( "pyrite_front")).fluid().color(0x8B5A2B).build();
        GTQTMaterials.TantaliteFront  = new Material.Builder(getMaterialsId(), gregtechId( "tantalite_front")).fluid().color(0x8DEEEE).build();
        //铜系泡沫
        GTQTMaterials.CopperFront  = new Material.Builder(getMaterialsId(), gregtechId( "copper_front")).fluid().color(0xD2691E).build();
        GTQTMaterials.TetrahedriteFront  = new Material.Builder(getMaterialsId(), gregtechId( "tetrahedrite_front")).fluid().color(0x8B2323).build();
        GTQTMaterials.ChalcociteFront  = new Material.Builder(getMaterialsId(), gregtechId( "chalocite_front")).fluid().color(0x595959).build();
        //铝系
        GTQTMaterials.AluminiumFront  = new Material.Builder(getMaterialsId(), gregtechId( "aluminium_front")).fluid().color(0x1E90FF).build();
        GTQTMaterials.BauxiteFront  = new Material.Builder(getMaterialsId(), gregtechId( "bauxite_front")).fluid().color(0x8B4726).build();

        //  13085 Fluorinated Ethylene Propylene
        GTQTMaterials.FluorinatedEthylenePropylene = new Material.Builder(getMaterialsId(), gregtechId("fluorinated_ethylene_propylene"))
                .liquid() // TODO polymer?
                .color(0xC8C8C8)
                .iconSet(DULL)
                .components(Carbon, 5, Fluorine, 10)
                .build();

        //  11248 Lead Zirconate Titanate
        GTQTMaterials.LeadZirconateTitanate = new Material.Builder(getMaterialsId(), gregtechId("lead_zirconate_titanate"))
                .gem(3)
                .color(0x359ADE)
                .iconSet(OPAL)
                .flags(GENERATE_PLATE,GENERATE_DOUBLE_PLATE,GENERATE_DENSE,GENERATE_ROD,GENERATE_BOLT_SCREW,GENERATE_FRAME,GENERATE_GEAR,GENERATE_LONG_ROD,GENERATE_FOIL,GENERATE_RING,GENERATE_SPRING,GENERATE_SPRING_SMALL,GENERATE_SMALL_GEAR,GENERATE_FINE_WIRE,GENERATE_ROTOR,GENERATE_ROUND)
                .components(Lead, 12, Titanium, 1, Zirconium, 1, Oxygen, 16)
                .build()
                .setFormula("PbZrTiO3", true);

        //  24086 Blood Cells
        GTQTMaterials.BloodCells = new Material.Builder(getMaterialsId(), gregtechId("blood_cells"))
                .liquid()
                .color(0xC43A31)
                .iconSet(DULL)
                .build();

        //  24087 Blood Plasma
        GTQTMaterials.BloodPlasma = new Material.Builder(getMaterialsId(), gregtechId("blood_plasma"))
                .liquid()
                .color(0x882822)
                .build();

        //  24088 bFGF
        GTQTMaterials.BFGF = new Material.Builder(getMaterialsId(), gregtechId("bfgf"))
                .liquid()
                .color(0xA15C72)
                .build()
                .setFormula("bFGF", false);

        //  24089 EGF
        GTQTMaterials.EGF = new Material.Builder(getMaterialsId(), gregtechId("egf"))
                .liquid()
                .color(0x993300)
                .build()
                .setFormula("EGF", false);

        //  24090 CAT
        GTQTMaterials.CAT = new Material.Builder(getMaterialsId(), gregtechId("cat"))
                .liquid()
                .color(0x72B5EA)
                .build()
                .setFormula("CAT", false);

        //  13169 Biotin
        GTQTMaterials.Biotin = new Material.Builder(getMaterialsId(), gregtechId("biotin"))
                .liquid()
                .color(0x08C74A)
                .components(Carbon, 10, Hydrogen, 16, Nitrogen, 2, Oxygen, 3, Sulfur, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        //  13170 Linoleic Acid
        GTQTMaterials.LinoleicAcid = new Material.Builder(getMaterialsId(), gregtechId("linoleic_acid"))
                .liquid()
                .color(0x919C2B)
                .components(Carbon, 18, Hydrogen, 32, Oxygen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        //  13161 Cyclopentadiene
        GTQTMaterials.Cyclopentadiene = new Material.Builder(getMaterialsId(), gregtechId("cyclopentadiene"))
                .liquid()
                .color(0x8BEB2A)
                .components(Carbon, 5, Hydrogen, 6)
                .build();

        //  13171 Vitamin A
        GTQTMaterials.VitaminA = new Material.Builder(getMaterialsId(), gregtechId("vitamin_a"))
                .liquid()
                .color(0xAB5EC3)
                .components(Carbon, 20, Hydrogen, 30, Oxygen, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        //  13172 β-Ionone
        GTQTMaterials.BetaIonone = new Material.Builder(getMaterialsId(), gregtechId("beta_ionone"))
                .liquid()
                .color(0xC3A0B2)
                .components(Carbon, 13, Hydrogen, 20, Oxygen, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        //  13173 Citral
        GTQTMaterials.Citral = new Material.Builder(getMaterialsId(), gregtechId("citral"))
                .liquid()
                .color(0xE4E77E)
                .components(Carbon, 10, Hydrogen, 16, Oxygen, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        //  13175 Propargyl Chloride
        GTQTMaterials.PropargylChloride = new Material.Builder(getMaterialsId(), gregtechId("propargyl_chloride"))
                .liquid()
                .color(0x156101)
                .components(Carbon, 3, Hydrogen, 3, Chlorine, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("HC2CH2Cl", true);

        //  13177 Ethanolamine
        GTQTMaterials.Ethanolamine = new Material.Builder(getMaterialsId(), gregtechId("ethanolamine"))
                .liquid()
                .color(0xD3DEA2)
                .components(Carbon, 2, Hydrogen, 7, Nitrogen, 1, Oxygen, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("HOCH2CH2NH2", true);

        //  13176 Propargyl Alcohol
        GTQTMaterials.PropargylAlcohol = new Material.Builder(getMaterialsId(), gregtechId("propargyl_alcohol"))
                .liquid()
                .color(0xB7AB44)
                .components(Carbon, 3, Hydrogen, 4, Oxygen, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("CHCCH2OH", true);

        //  24075 Exotic Mutagen
        GTQTMaterials.ExoticMutagen = new Material.Builder(getMaterialsId(), gregtechId("exotic_mutagen"))
                .liquid(new FluidBuilder().temperature(18406).attributes(FluidAttributes.ACID))
                .color(0x9C31F9)
                .build()
                .setFormula(TextFormatting.OBFUSCATED + "aaa", false);

        //  13168 B27
        GTQTMaterials.B27 = new Material.Builder(getMaterialsId(), gregtechId("b_27"))
                .liquid()
                .color(0xC2B7E3)
                .components(Carbon, 142, Hydrogen, 230, Nitrogen, 36, Oxygen, 44, Sulfur, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        //  11247 Ce:LAG
        GTQTMaterials.CeLAG = new Material.Builder(getMaterialsId(), gregtechId("ce_lag"))
                .gem()
                .color(0x00A816)
                .iconSet(GEM_VERTICAL)
                .flags(CRYSTALLIZABLE, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_LENS)
                .components(CeriumOxide, 1, LutetiumOxide, 1, Alumina, 5)
                .build()
                .setFormula("Ce:LAG", true);

        //  13162 Lithium Cyclopentadienide
        GTQTMaterials.LithiumCyclopentadienide = new Material.Builder(getMaterialsId(), gregtechId("lithium_cyclopentadienide"))
                .liquid()
                .color(0x963D5F)
                .components(Carbon, 5, Hydrogen, 5, Lithium, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        //  13163 Californium Cyclopentadienide
        GTQTMaterials.CaliforniumCyclopentadienide = new Material.Builder(getMaterialsId(), gregtechId("californium_cyclopentadienide"))
                .liquid()
                .color(0x821554)
                .components(Carbon, 15, Hydrogen, 15, Californium, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        //  11236 Californium Trichloride
        GTQTMaterials.CaliforniumTrichloride = new Material.Builder(getMaterialsId(), gregtechId("californium_trichloride"))
                .dust()
                .color(0x8B67D1)
                .iconSet(METALLIC)
                .components(Californium, 1, Chlorine, 3)
                .build();

        //  24064 Free Electron Gas
        GTQTMaterials.FreeElectronGas = new Material.Builder(getMaterialsId(), gregtechId("free_electron_gas"))
                .gas(new FluidBuilder()
                        .translation("gregtech.fluid.generic"))
                .color(0x507BB3)
                .build()
                .setFormula(TextFormatting.OBFUSCATED  + "a" + TextFormatting.RESET + "§ee" + TextFormatting.OBFUSCATED + "a", false);


        //  15012 Heavy Quark Degenerate Matter
        GTQTMaterials.HeavyQuarkDegenerateMatter = new Material.Builder(getMaterialsId(), gregtechId("heavy_quark_degenerate_matter"))
                .ingot()
                .liquid(new FluidBuilder().temperature((int) (V[UV] + V[HV] * V[HV])))
                .plasma(new FluidBuilder().temperature((int) (V[UV] * V[HV])))
                .color(0x5DBD3A)
                .iconSet(BRIGHT)
                .blast(b -> b
                        .temp(14960, BlastProperty.GasTier.HIGHEST)
                        .blastStats(VA[UIV]))
                .flags(DISABLE_DECOMPOSITION, GENERATE_PLATE, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_ROD, GENERATE_FRAME, GENERATE_DOUBLE_PLATE)
                .cableProperties(V[UXV], 576, 1024, false)
                .build()
                .setFormula(TextFormatting.OBFUSCATED + "aaaaaa", false);

        //  13200 Polyethylene Terephthalate (PET)
        GTQTMaterials.PolyethyleneTerephthalate = new Material.Builder(getMaterialsId(), gregtechId("polyethylene_terephthalate"))
                .polymer()
                .liquid()
                .color(0x1E5C58)
                .components(Carbon, 10, Hydrogen, 6, Oxygen, 4)
                .flags(GENERATE_PLATE, GENERATE_FOIL)
                .build();

        //  13197 Para Toluic Acid
        GTQTMaterials.ParaToluicAcid = new Material.Builder(getMaterialsId(), gregtechId("para_toluic_acid"))
                .liquid(new FluidBuilder().attribute(FluidAttributes.ACID))
                .color(0x4FA597)
                .components(Carbon, 8, Hydrogen, 8, Oxygen, 2)
                .build();

        //  13198 Methylparatoluate
        GTQTMaterials.Methylparatoluate = new Material.Builder(getMaterialsId(), gregtechId("methylparatoluate"))
                .liquid()
                .color(0x76BCB0)
                .components(Carbon, 9, Hydrogen, 10, Oxygen, 2)
                .build();

        //  13199 Dimethyl Terephthalate
        GTQTMaterials.DimethylTerephthalate = new Material.Builder(getMaterialsId(), gregtechId("dimethyl_terephthalate"))
                .liquid()
                .color(0x05D8AF)
                .components(Carbon, 10, Hydrogen, 10, Oxygen, 4)
                .build();
    }
}
