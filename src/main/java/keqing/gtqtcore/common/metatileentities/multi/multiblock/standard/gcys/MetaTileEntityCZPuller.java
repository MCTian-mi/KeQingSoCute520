package keqing.gtqtcore.common.metatileentities.multi.multiblock.standard.gcys;


import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.block.IHeatingCoilBlockStats;
import gregtech.api.capability.IHeatingCoil;
import gregtech.api.capability.impl.HeatingCoilRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.MultiblockShapeInfo;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.recipeproperties.TemperatureProperty;
import gregtech.api.util.GTUtility;
import gregtech.api.util.TextFormattingUtil;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.utils.TooltipHelper;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.*;
import gregtech.common.metatileentities.MetaTileEntities;
import keqing.gtqtcore.api.recipes.GTQTcoreRecipeMaps;
import keqing.gtqtcore.common.block.GTQTMetaBlocks;
import keqing.gtqtcore.common.block.blocks.GTQTTurbineCasing;
import keqing.gtqtcore.common.metatileentities.GTQTMetaTileEntities;
import keqing.gtqtcore.common.metatileentities.multi.multiblock.standard.MetaTileEntityBlazingBlastFurnace;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MetaTileEntityCZPuller extends RecipeMapMultiblockController implements IHeatingCoil {
    private int czpullerTemperarure;

    public MetaTileEntityCZPuller(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTQTcoreRecipeMaps.CZPULLER_RECIPES);
        this.recipeMapWorkable = new HeatingCoilRecipeLogic(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityCZPuller(metaTileEntityId);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (isStructureFormed()) {
            textList.add(new TextComponentTranslation("gregtech.multiblock.blast_furnace.max_temperature",
                    new TextComponentTranslation(TextFormattingUtil.formatNumbers(czpullerTemperarure) + "K").setStyle(new Style().setColor(TextFormatting.DARK_PURPLE))));
        }
        super.addDisplayText(textList);
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        Object type = context.get("CoilType");
        if (type instanceof IHeatingCoilBlockStats) {
            this.czpullerTemperarure = ((IHeatingCoilBlockStats) type).getCoilTemperature();
        } else {
            this.czpullerTemperarure = BlockWireCoil.CoilType.CUPRONICKEL.getCoilTemperature();
        }
            this.czpullerTemperarure += 100 * Math.max(0, GTUtility.getFloorTierByVoltage(getEnergyContainer().getInputVoltage()) - GTValues.MV);
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.czpullerTemperarure = 0;
    }

    @Override
    public boolean checkRecipe(@Nonnull Recipe recipe, boolean consumeIfSuccess) {
        return this.czpullerTemperarure >= recipe.getProperty(TemperatureProperty.getInstance(), 0);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle(" YYYYY ", "   Y   ", "   X   ", "   G   ", "   G   ", "   G   ", "   G   ", "   G   ", "   X   ", "       ")
                .aisle("YYYYYYY", "  YCY  ", "  XCX  ", "  XCX  ", "  XCX  ", "  XCX  ", "  XCX  ", "  XCX  ", "  XXX  ", "   X   ")
                .aisle("YYYYYYY", " XN NY ", " XN NX ", " XN NX ", " XN NX ", " XN NX ", " XNNNX ", " XN NX ", " XRRRX ", " XXXXX ")
                .aisle("YYYYYYY", "YC   CY", "XC   CX", "GC   CG", "GC   CG", "GC   CG", "GC   CG", "GCN NCG", "XXR RXX", " XXMXX ")
                .aisle("YYYYYYY", " YN NY ", " XN NX ", " XN NX ", " XN NX ", " XN NX ", " XNNNX ", " XN NX ", " XRRRX ", " XXXXX ")
                .aisle("YYYYYYY", "  YCY  ", "  XCX  ", "  XCX  ", "  XCX  ", "  XCX  ", "  XCX  ", "  XCX  ", "  XXX  ", "   X   ")
                .aisle(" YYYYY ", "   S   ", "   X   ", "   G   ", "   G   ", "   G   ", "   G   ", "   G   ", "   X   ", "       ")
                .where('S', selfPredicate())
                .where('X', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID)))
                .where('Y', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID))
                        .or(abilities(MultiblockAbility.IMPORT_ITEMS).setExactLimit(1).setPreviewCount(1))
                        .or(abilities(MultiblockAbility.EXPORT_ITEMS).setExactLimit(1).setPreviewCount(1))
                        .or(abilities(MultiblockAbility.IMPORT_FLUIDS).setExactLimit(1).setPreviewCount(1))
                        .or(abilities(MultiblockAbility.MAINTENANCE_HATCH).setExactLimit(1).setPreviewCount(1))
                        .or(abilities(MultiblockAbility.INPUT_ENERGY).setMinGlobalLimited(1).setMaxGlobalLimited(4).setPreviewCount(1))
                )
                .where('M', abilities(MultiblockAbility.MUFFLER_HATCH))
                .where('#', any())
                .where('G', states(MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.TEMPERED_GLASS)))
                .where('N', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF)))
                .where('R', states(MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.STEEL_GEARBOX)))
                .where('C', heatingCoils())
                .build();
    }
    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder()
                .aisle(" YYYYY ", "   Y   ", "   X   ", "   G   ", "   G   ", "   G   ", "   G   ", "   G   ", "   X   ", "       ")
                .aisle("YYYYYYY", "  YCY  ", "  XCX  ", "  XCX  ", "  XCX  ", "  XCX  ", "  XCX  ", "  XCX  ", "  XXX  ", "   X   ")
                .aisle("YYYYYYY", " XN NY ", " XN NX ", " XN NX ", " XN NX ", " XN NX ", " XNNNX ", " XN NX ", " XRRRX ", " XXXXX ")
                .aisle("YYYYYYY", "YC   CY", "XC   CX", "GC   CG", "GC   CG", "GC   CG", "GC   CG", "GCN NCG", "XXR RXX", " XXHXX ")
                .aisle("YYYYYYY", " YN NY ", " XN NX ", " XN NX ", " XN NX ", " XN NX ", " XNNNX ", " XN NX ", " XRRRX ", " XXXXX ")
                .aisle("YYYYYYY", "  YCY  ", "  XCX  ", "  XCX  ", "  XCX  ", "  XCX  ", "  XCX  ", "  XCX  ", "  XXX  ", "   X   ")
                .aisle(" EIOFM ", "   S   ", "   X   ", "   G   ", "   G   ", "   G   ", "   G   ", "   G   ", "   X   ", "       ")
                .where('S', GTQTMetaTileEntities.CZ_PULLER, EnumFacing.SOUTH)
                .where('X', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID))
                .where('Y', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID))
                .where('#', Blocks.AIR.getDefaultState())
                .where('G', MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.TEMPERED_GLASS))
                .where('N', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF))
                .where('R', MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.STEEL_GEARBOX))
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.LV], EnumFacing.SOUTH)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.LV], EnumFacing.SOUTH)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.LV], EnumFacing.SOUTH)
                .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.LV], EnumFacing.SOUTH)
                .where('H', MetaTileEntities.MUFFLER_HATCH[GTValues.LV], EnumFacing.UP)
                .where('M', MetaTileEntities.MAINTENANCE_HATCH, EnumFacing.SOUTH);
        GregTechAPI.HEATING_COILS.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> entry.getValue().getTier()))
                .forEach(entry -> shapeInfo.add(builder.where('C', entry.getKey()).build()));
        return shapeInfo;
    }
    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.SOLID_STEEL_CASING;
    }

    @Override
    public int getCurrentTemperature() {
        return this.czpullerTemperarure;
    }
    @Override
    public void addInformation(ItemStack stack, @Nullable World player, @Nonnull List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(TooltipHelper.RAINBOW_SLOW + I18n.format("硅宝宝之家", new Object[0]));
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.1"));
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.2"));
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.3"));
    }
    @SideOnly(Side.CLIENT)
    @Nonnull
    @Override
    protected ICubeRenderer getFrontOverlay(){
        return Textures.POWER_SUBSTATION_OVERLAY;
    }
}
