package keqing.gtqtcore.common.metatileentities.multi.multiblock.standard.kqcc;


import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IOpticalComputationProvider;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.Widget;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.gui.widgets.ClickButtonWidget;
import gregtech.api.gui.widgets.WidgetGroup;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.IProgressBarMultiblock;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.util.TextComponentUtil;
import gregtech.api.util.TextFormattingUtil;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.core.sound.GTSoundEvents;
import keqing.gtqtcore.api.GTQTValue;
import keqing.gtqtcore.api.blocks.impl.WrappedIntTired;
import keqing.gtqtcore.api.predicate.TiredTraceabilityPredicate;
import keqing.gtqtcore.api.utils.GTQTUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.util.RelativeDirection.*;
import static keqing.gtqtcore.api.unification.GTQTMaterials.LiquidNitrogen;

public class MetaTileEntityADVKQCC extends MultiblockWithDisplayBase implements IOpticalComputationProvider, IProgressBarMultiblock {
    private boolean isWorkingEnabled;

    private IFluidHandler coolantHandler;
    int level;
    float HOT;
    private int CPU1;
    private int GPU1;
    private int RAM1;
    int thresholdPercentage=0;
    private IEnergyContainer energyContainer;


    @Override
    @Nonnull
    protected Widget getFlexButton(int x, int y, int width, int height) {
        WidgetGroup group = new WidgetGroup(x, y, width, height);
        group.addWidget(new ClickButtonWidget(0, 0, 9, 18, "", this::decrementThreshold)
                .setButtonTexture(GuiTextures.BUTTON_THROTTLE_MINUS)
                .setTooltipText("gtqtcore.multiblock.kqcc.threshold_decrement"));
        group.addWidget(new ClickButtonWidget(9, 0, 9, 18, "", this::incrementThreshold)
                .setButtonTexture(GuiTextures.BUTTON_THROTTLE_PLUS)
                .setTooltipText("gtqtcore.multiblock.kqcc.threshold_increment"));
        return group;
    }
    private void incrementThreshold(Widget.ClickData clickData) {
        this.thresholdPercentage = MathHelper.clamp(thresholdPercentage + 1, 0, 2);
    }

    private void decrementThreshold(Widget.ClickData clickData) {
        this.thresholdPercentage = MathHelper.clamp(thresholdPercentage - 1, 0, 2);
    }


    public MetaTileEntityADVKQCC(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
        this.energyContainer = new EnergyContainerList(new ArrayList<>());
    }
    FluidStack COLD_STACK = Water.getFluid(10);
    FluidStack COLD_STACKA = PCBCoolant.getFluid(5);
    FluidStack COLD_STACKB = LiquidNitrogen.getFluid(1);

    FluidStack COLD_STACK1 = Water.getFluid(40);
    FluidStack COLD_STACKA1 = PCBCoolant.getFluid(20);
    FluidStack COLD_STACKB1 = LiquidNitrogen.getFluid(4);
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        data.setFloat("HOT", HOT);
        data.setInteger("thresholdPercentage", thresholdPercentage);
        return super.writeToNBT(data);
    }
   
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        HOT = data.getFloat("HOT");
        thresholdPercentage = data.getInteger("thresholdPercentage");
    }
    @Override
    protected void updateFormedValid() {
        consumeEnergy();
        if(HOT>10) HOT=HOT-10;

        if(HOT>5000) {

            if(thresholdPercentage!=2) {
                if (COLD_STACK.isFluidStackIdentical(coolantHandler.drain(COLD_STACK, false))) {
                    coolantHandler.drain(COLD_STACK, true);
                    if(HOT>770) HOT = HOT - 770*4;
                }
                if (COLD_STACKA.isFluidStackIdentical(coolantHandler.drain(COLD_STACKA, false))) {
                    coolantHandler.drain(COLD_STACKA, true);
                    if(HOT>1440) HOT = HOT - 1440*4;
                }
                if (COLD_STACKB.isFluidStackIdentical(coolantHandler.drain(COLD_STACKB, false))) {
                    coolantHandler.drain(COLD_STACKB, true);
                    if(HOT>2880) HOT = HOT - 2880*4;
                }
            }

            if(thresholdPercentage==2) {
                if (COLD_STACK1.isFluidStackIdentical(coolantHandler.drain(COLD_STACK1, false))){
                    coolantHandler.drain(COLD_STACK1, true);
                    if(HOT>1440)HOT = HOT - 1440*4;
                }
                if (COLD_STACKA1.isFluidStackIdentical(coolantHandler.drain(COLD_STACKA1, false))) {
                    coolantHandler.drain(COLD_STACKA1, true);
                    if(HOT>2880)HOT = HOT - 2880*4;
                }
                if (COLD_STACKB1.isFluidStackIdentical(coolantHandler.drain(COLD_STACKB1, false))) {
                    coolantHandler.drain(COLD_STACKB1, true);
                    if(HOT>5760)HOT = HOT - 5760*4;
                }
            }
        }
    }
    private boolean hasNotEnoughEnergy;
    private void consumeEnergy() {
        int energyToConsume = CWTT()*30*thresholdPercentage;
        boolean hasMaintenance = ConfigHolder.machines.enableMaintenance && hasMaintenanceMechanics();
        if (hasMaintenance) {
            // 10% more energy per maintenance problem
            energyToConsume += getNumMaintenanceProblems() * energyToConsume / 10;
        }

        if (this.hasNotEnoughEnergy && energyContainer.getInputPerSec() > 19L * energyToConsume) {
            this.hasNotEnoughEnergy = false;
        }

        if (this.energyContainer.getEnergyStored() >= energyToConsume) {
            if (!hasNotEnoughEnergy) {
                long consumed = this.energyContainer.removeEnergy(energyToConsume);
                if (consumed == -energyToConsume) {
                    isWorkingEnabled= HOT < 45000;
                } else {
                    this.hasNotEnoughEnergy = true;
                    isWorkingEnabled=false;
                }
            }
        } else {
            this.hasNotEnoughEnergy = true;
            isWorkingEnabled=false;
        }
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
        return Textures.RESEARCH_STATION_OVERLAY;
    }
    @Override
    protected BlockPattern createStructurePattern() {
        FactoryBlockPattern pattern = FactoryBlockPattern.start(RIGHT, UP, FRONT)
                .aisle("NN","SN","NN","NN")
                .aisle("CN","BN","AN","NE").setRepeatable(4,16)
                .aisle("XN","NN","NN","NN")
                .where('S', selfPredicate())
                .where('X', abilities(MultiblockAbility.COMPUTATION_DATA_TRANSMISSION))
                .where('N', states(getCasingState())
                        .or(abilities(MultiblockAbility.MAINTENANCE_HATCH).setExactLimit(1))
                        .or(abilities(MultiblockAbility.IMPORT_FLUIDS).setMinGlobalLimited(1).setPreviewCount(1)))
                .where('E', states(getCasingState())
                        .or(abilities(MultiblockAbility.INPUT_ENERGY).setMinLayerLimited(1).setMaxLayerLimited(1)))
                .where('A', TiredTraceabilityPredicate.CP_GPU_CASING1.get())
                .where('B', TiredTraceabilityPredicate.CP_CPU_CASING1.get())
                .where('C', TiredTraceabilityPredicate.CP_RAM_CASING1.get())
                .where('#', any());
        return pattern.build();
    }
    public boolean hasMufflerMechanics() {
        return false;
    }
    @Override
    protected boolean shouldShowVoidingModeButton() {
        return false;
    }
    @SideOnly(Side.CLIENT)
    @Override
    public SoundEvent getSound() {
        return GTSoundEvents.COMPUTATION;
    }
    @Override
    public SoundEvent getBreakdownSound() {
        return GTSoundEvents.BREAKDOWN_ELECTRICAL;
    }

    protected IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.PTFE_INERT_CASING);
    }
    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gtqtcore.multiblock.kqcc.description1")};
    }
    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        textList.add(new TextComponentTranslation("gregtech.multiblock.kqcc.eu", CWTT()*30,HOT));
        textList.add(new TextComponentTranslation("gregtech.multiblock.kqcc.start", thresholdPercentage));

        if((RAM1)>=(GPU1)&&(RAM1)>=(CPU1))
            textList.add(new TextComponentTranslation("gregtech.multiblock.kqcc.normal"));
        else
            textList.add(new TextComponentTranslation("gregtech.multiblock.kqcc.lack"));

        if (coolantHandler != null) {
            FluidStack STACKA = coolantHandler.drain(Water.getFluid(Integer.MAX_VALUE), false);
            int liquidWaterAmount = STACKA == null ? 0 : STACKA.amount;

            FluidStack STACKB = coolantHandler.drain(PCBCoolant.getFluid(Integer.MAX_VALUE), false);
            int liquidPCBAmount = STACKB == null ? 0 : STACKB.amount;

            FluidStack STACKC = coolantHandler.drain(LiquidNitrogen.getFluid(Integer.MAX_VALUE), false);
            int liquidNITAmount = STACKC == null ? 0 : STACKC.amount;
            textList.add(new TextComponentTranslation("gtqtcore.multiblock.kqcc.water.amount", TextFormattingUtil.formatNumbers((liquidWaterAmount)),TextFormattingUtil.formatNumbers((liquidPCBAmount)),TextFormattingUtil.formatNumbers((liquidNITAmount))));
        }
        textList.add(new TextComponentTranslation("GPU:%s CPU:%s RAM:%s level:%s", GPU1,CPU1,RAM1,level));
        textList.add(new TextComponentTranslation("预估算力:%s", returncwt()));
        if(thresholdPercentage==0) textList.add(new TextComponentTranslation("gregtech.multiblock.kqcc.cwtt", returncwt(),0));
        if(thresholdPercentage==1) textList.add(new TextComponentTranslation("gregtech.multiblock.kqcc.cwtt", returncwt(),HEAT()));
        if(thresholdPercentage==2) textList.add(new TextComponentTranslation("gregtech.multiblock.kqcc.cwtt", returncwt(),HEAT()*4));
    }

    int CWTT()
    {
        if((RAM1)>=(GPU1)&&(RAM1)>=(CPU1)) return (GPU1+CPU1)*2*level;
        else return (RAM1)*level*2;
    };
    int HEAT()
    {
        return CPU1*CPU1*level;
    };
    int returncwt()
    {
        if(isWorkingEnabled) {

            if(thresholdPercentage==2) HOT=HOT+ (float) HEAT() /16;
            if(thresholdPercentage==1)  HOT=HOT+ (float) HEAT() /32;
            if(HOT<30000) return CWTT()*(thresholdPercentage);
            else return CWTT()*thresholdPercentage;
        }
        else return 0;
    }
    @Override
    protected void addWarningText(List<ITextComponent> textList) {
        super.addWarningText(textList);
        if (isStructureFormed()) {
            if (HOT>=30000) {
                textList.add(new TextComponentTranslation("gtqtcore.multiblock.kqcc.hot"));
            }
        }
    }
    @Override
    public int requestCWUt(int cwut, boolean simulate, Collection<IOpticalComputationProvider> seen) {
        seen.add(this);
        return returncwt();
    }

    @Override
    public int getMaxCWUt(Collection<IOpticalComputationProvider> seen) {
        seen.add(this);
        return (GPU1+CPU1)*thresholdPercentage*level*2;
    }

    @Override
    public boolean canBridge(Collection<IOpticalComputationProvider> seen) {
        seen.add(this);
        return true;
    }
    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityADVKQCC(metaTileEntityId);
    }
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return Textures.INERT_PTFE_CASING;
    }
    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        this.energyContainer = new EnergyContainerList(getAbilities(MultiblockAbility.INPUT_ENERGY));
        this.coolantHandler = new FluidTankList(false, getAbilities(MultiblockAbility.IMPORT_FLUIDS));
        List<IEnergyContainer> i = getAbilities(MultiblockAbility.INPUT_ENERGY);
        this.level=i.size();
        Object CPU1 = context.get("CPU1TieredStats");
        Object GPU1 = context.get("GPU1TieredStats");
        Object RAM1 = context.get("RAM1TieredStats");
        this.CPU1 = GTQTUtil.getOrDefault(() -> CPU1 instanceof WrappedIntTired,
                () -> ((WrappedIntTired)CPU1).getIntTier(),
                0);

        this.GPU1 = GTQTUtil.getOrDefault(() -> GPU1 instanceof WrappedIntTired,
                () -> ((WrappedIntTired)GPU1).getIntTier(),
                0);

        this.RAM1 = GTQTUtil.getOrDefault(() -> RAM1 instanceof WrappedIntTired,
                () -> ((WrappedIntTired)RAM1).getIntTier(),
                0);

    }
    private TextFormatting getDisplayTemperatureColor() {
        if (HOT < 25000) {
            return TextFormatting.GREEN;
        } else if (HOT < 45000) {
            return TextFormatting.YELLOW;
        }
        return TextFormatting.RED;
    }

    @Override
    public int getNumProgressBars() {
        return 2;
    }

    @Override
    public double getFillPercentage(int index) {
        return index == 0 ? 1.0 * returncwt() / ((GPU1+CPU1)*level*4) :
                Math.min(1.0, HOT / 50000);
    }

    @Override
    public TextureArea getProgressBarTexture(int index) {
        return index == 0 ? GuiTextures.PROGRESS_BAR_HPCA_COMPUTATION : GuiTextures.PROGRESS_BAR_FUSION_HEAT;
    }

    @Override
    public void addBarHoverText(List<ITextComponent> hoverList, int index) {
        if (index == 0) {
            ITextComponent cwutInfo = TextComponentUtil.stringWithColor(
                    TextFormatting.AQUA,
                    returncwt()+ " / " + ((GPU1*level)+(CPU1*level))*4 + " CWU/t");
            hoverList.add(TextComponentUtil.translationWithColor(
                    TextFormatting.GRAY,
                    "gregtech.multiblock.hpca.computation",
                    cwutInfo));
        } else {
            ITextComponent tempInfo = TextComponentUtil.stringWithColor(
                    getDisplayTemperatureColor(),
                    Math.round(HOT / 100.0D) + "°C");
            hoverList.add(TextComponentUtil.translationWithColor(
                    TextFormatting.GRAY,
                    "gregtech.multiblock.hpca.temperature",
                    tempInfo));
        }
    }
}