package keqing.gtqtcore.common.metatileentities.multi.multiblock.standard.core;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.damagesources.DamageSources;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.MetaBlocks;
import gregtech.core.advancement.AdvancementTriggers;
import keqing.gtqtcore.api.metaileentity.multiblock.GTQTMultiblockCore;
import keqing.gtqtcore.api.unification.GTQTMaterials;
import keqing.gtqtcore.client.textures.GTQTTextures;
import keqing.gtqtcore.common.block.GTQTMetaBlocks;
import keqing.gtqtcore.common.block.blocks.GTQTADVBlock;
import keqing.gtqtcore.common.block.blocks.GTQTIsaCasing;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static gregtech.api.GTValues.IV;
import static gregtech.api.GTValues.VA;
import static keqing.gtqtcore.common.block.blocks.GTQTTurbineCasing.TurbineCasingType.IRIDIUM_CASING;

public class MetaTileEntityIndustrialHammer extends GTQTMultiblockCore {

    public MetaTileEntityIndustrialHammer(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, new RecipeMap[]{
                RecipeMaps.FORGE_HAMMER_RECIPES,
                RecipeMaps.FORMING_PRESS_RECIPES,
                RecipeMaps.COMPRESSOR_RECIPES
        });
    }
    @Override
    public int getMinVa()
    {
        return VA[IV];
    }
    @Override
    public int getCoreNum() {
        return 64;
    }
    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityIndustrialHammer(metaTileEntityId);
    }


    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("CCC", "CCC", "CCC", "FFF")
                .aisle("CCC", "CGC", "CGC", "FCF")
                .aisle("PPP", "PSP", "PPP", "FFF")
                .where('P', states(getCasingState()))
                .where('S', this.selfPredicate())
                .where('C', states(getCasingState())
                        .setMinGlobalLimited(12)
                        .or(autoAbilities()))
                .where('G', states(getSecondCasingState()))
                .where('F', states(getFrameState()))
                .build();
    }
    private static IBlockState getFrameState() {
        return MetaBlocks.FRAMES.get(GTQTMaterials.BlackTitanium).getBlock(GTQTMaterials.BlackTitanium);
    }
    private static IBlockState getCasingState() {
        return GTQTMetaBlocks.ADV_BLOCK.getState(GTQTADVBlock.CasingType.Hdcs);
    }

    private static IBlockState getSecondCasingState() {
        return GTQTMetaBlocks.ISA_CASING.getState(GTQTIsaCasing.CasingType.IRIDIUM_TURBINE);
    }
    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GTQTTextures.Hdcs;
    }

    @Override
    public void update() {
        super.update();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        this.getFrontOverlay().renderOrientedState(renderState, translation, pipeline, getFrontFacing(), true,
                isStructureFormed());
    }
    @SideOnly(Side.CLIENT)
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return GTQTTextures.DRYER_OVERLAY;
    }

    @Override
    public boolean canBeDistinct() {
        return true;
    }
}

