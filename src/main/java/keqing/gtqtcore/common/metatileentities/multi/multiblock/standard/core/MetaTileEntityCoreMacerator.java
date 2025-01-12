package keqing.gtqtcore.common.metatileentities.multi.multiblock.standard.core;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockTurbineCasing;
import gregtech.common.blocks.MetaBlocks;
import keqing.gtqtcore.api.metaileentity.multiblock.GTQTMultiblockCore;
import keqing.gtqtcore.api.recipes.GTQTcoreRecipeMaps;
import keqing.gtqtcore.client.textures.GTQTTextures;
import keqing.gtqtcore.common.block.GTQTMetaBlocks;
import keqing.gtqtcore.common.block.blocks.GTQTADVBlock;
import keqing.gtqtcore.common.block.blocks.GTQTIsaCasing;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static gregtech.api.GTValues.IV;
import static gregtech.api.GTValues.VA;
import static keqing.gtqtcore.api.GCYSValues.HV;
import static keqing.gtqtcore.common.block.blocks.GTQTTurbineCasing.TurbineCasingType.IRIDIUM_CASING;
import static keqing.gtqtcore.common.block.blocks.GTQTTurbineCasing1.TurbineCasingType.SA_TURBINE_CASING;

public class MetaTileEntityCoreMacerator extends GTQTMultiblockCore {

    public MetaTileEntityCoreMacerator(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, new RecipeMap[]{
                RecipeMaps.MACERATOR_RECIPES
        });
    }
    @Override
    public int getMinVa()
    {
        return VA[HV];
    }
    @Override
    public int getCoreNum() {
        return 8;
    }
    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityCoreMacerator(metaTileEntityId);
    }


    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("CCC", "CCC", "CCC")
                .aisle("FCF", "CGC", "FCF")
                .aisle("FCF", "CGC", "FCF")
                .aisle("FCF", "CGC", "FCF")
                .aisle("PPP", "PSP", "PPP")
                .where('P', states(getCasingState()))
                .where('S', this.selfPredicate())
                .where('C', states(getCasingState())
                        .setMinGlobalLimited(16)
                        .or(autoAbilities()))
                .where('G', states(getSecondCasingState()))
                .where('F', states(getFrameState()))
                .build();
    }
    private static IBlockState getFrameState() {
        return MetaBlocks.FRAMES.get(Materials.StainlessSteel).getBlock(Materials.StainlessSteel);
    }
    private static IBlockState getCasingState() {
        return GTQTMetaBlocks.ADV_BLOCK.getState(GTQTADVBlock.CasingType.Stellite);
    }

    private static IBlockState getSecondCasingState() {
        return MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.POLYTETRAFLUOROETHYLENE_PIPE);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GTQTTextures.Stellite;
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
        return GTQTTextures.CRYSTALLIZATION_CRUCIBLE_OVERLAY;
    }

    @Override
    public boolean canBeDistinct() {
        return true;
    }
}

