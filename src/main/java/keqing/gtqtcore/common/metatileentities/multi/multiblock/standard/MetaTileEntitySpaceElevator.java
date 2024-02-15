package keqing.gtqtcore.common.metatileentities.multi.multiblock.standard;

import gregtech.api.capability.IOpticalComputationProvider;
import gregtech.api.capability.IOpticalComputationReceiver;
import gregtech.api.capability.impl.ComputationRecipeLogic;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.Widget;
import gregtech.api.gui.widgets.ClickButtonWidget;
import gregtech.api.gui.widgets.WidgetGroup;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.recipeproperties.IRecipePropertyStorage;
import gregtech.api.util.GTTransferUtils;
import gregtech.api.util.RelativeDirection;
import gregtech.api.util.TextFormattingUtil;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.MetaBlocks;
import keqing.gtqtcore.api.GTQTValue;
import keqing.gtqtcore.api.blocks.impl.WrappedIntTired;
import keqing.gtqtcore.api.predicate.TiredTraceabilityPredicate;
import keqing.gtqtcore.api.recipes.GTQTcoreRecipeMaps;
import keqing.gtqtcore.api.recipes.properties.LASERNetProperty;
import keqing.gtqtcore.api.recipes.properties.SEProperty;
import keqing.gtqtcore.api.unification.GTQTMaterials;
import keqing.gtqtcore.api.utils.GTQTLog;
import keqing.gtqtcore.api.utils.GTQTUtil;
import keqing.gtqtcore.client.textures.GTQTTextures;
import keqing.gtqtcore.common.block.GTQTMetaBlocks;
import keqing.gtqtcore.common.block.blocks.GTQTSpaceElevator;
import keqing.gtqtcore.common.metatileentities.multi.multiblock.standard.overwrite.MetaTileEntityCrackingUnit;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static keqing.gtqtcore.common.items.GTQTMetaItems.MINING_DRONE_LV;
import static keqing.gtqtcore.common.items.GTQTMetaItems.*;


public class MetaTileEntitySpaceElevator extends MultiMapMultiblockController implements IOpticalComputationReceiver {
    private IOpticalComputationProvider computationProvider;
    public IOpticalComputationProvider getComputationProvider() {
        return this.computationProvider;
    }
    private int motortier;
    int coe = 0;
    private int Atier;// 矿机
    private int Btier;// 超频
    private int Ctier;// 耗能
    private int Dtier;// 并行

    int dim;
    public MetaTileEntitySpaceElevator(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, new RecipeMap[]{
                GTQTcoreRecipeMaps.SPACE_ELEVATOR_DRILLING_MODULE,
                GTQTcoreRecipeMaps.SPACE_ELEVATOR_MINING_MODULE
                /*
                GTLiteRecipeMaps.SPACE_ELEVATOR_MINING_MODULE,
                GTLiteRecipeMaps.SPACE_ELEVATOR_ASSEMBLING_MODULE
*/
        });
        this.recipeMapWorkable = new SpaceElevatorRecipeLogic(this);
    }

    @Override
    public boolean checkRecipe(@Nonnull Recipe recipe, boolean consumeIfSuccess) {
        if(recipe.getProperty(SEProperty.getInstance(), 0)<=motortier)
        {
            return super.checkRecipe(recipe, consumeIfSuccess);
        }
        return false;
    }
    private int getCoe() {
        var slots = this.getInputInventory().getSlots();
        for (int i = 0; i < slots; i++) {
            ItemStack item =  this.getInputInventory().getStackInSlot(i);
            if(item.getDisplayName().equals("轨道采集器（§7LV§r）"))
            {
                GTQTLog.logger.info("into 1");
                return 1;
            }
            if(item.getDisplayName().equals("轨道采集器（§bMV§r）"))
            {
                GTQTLog.logger.info("into 2");
                return 2;
            }
            if(item.getDisplayName().equals("轨道采集器（§6HV§r）"))
            {
                return 3;
            }
            if(item.getDisplayName().equals("轨道采集器（§5EV§r）"))
            {
                return 4;
            }
            if(item.getDisplayName().equals("轨道采集器（§1IV§r）"))
            {
                return 5;
            }
            if(item.getDisplayName().equals("轨道采集器（§dLuV§r）"))
            {
                return 6;
            }
            if(item.getDisplayName().equals("轨道采集器（§cZPM§r）"))
            {
                return 7;
            }
            if(item.getDisplayName().equals("轨道采集器（§3UV§r）"))
            {
                return 8;
            }
            if(item.getDisplayName().equals("轨道采集器（§4UHV§r）"))
            {
                return 9;
            }
            if(item.getDisplayName().equals("轨道采集器（§aUEV§r）"))
            {
                return 10;
            }
            if(item.getDisplayName().equals("轨道采集器（§2UIV§r）"))
            {
                return 11;
            }
            if(item.getDisplayName().equals("轨道采集器（§eUXV§r）"))
            {
                return 12;
            }
            if(item.getDisplayName().equals("轨道采集器（§9OpV§r）"))
            {
                return 13;
            }
            if(item.getDisplayName().equals("轨道采集器（§c§lMAX§r）"))
            {
                return 14;
            }
        }
        return 0;
    }

    @Override
    @Nonnull
    protected Widget getFlexButton(int x, int y, int width, int height) {
        WidgetGroup group = new WidgetGroup(x, y, width, height);
        group.addWidget(new ClickButtonWidget(0, 0, 9, 18, "", this::decrementThreshold)
                .setButtonTexture(GuiTextures.BUTTON_THROTTLE_MINUS)
                .setTooltipText("gtqtcore.multiblock.se.threshold_decrement"));
        group.addWidget(new ClickButtonWidget(9, 0, 9, 18, "", this::incrementThreshold)
                .setButtonTexture(GuiTextures.BUTTON_THROTTLE_PLUS)
                .setTooltipText("gtqtcore.multiblock.se.threshold_increment"));
        return group;
    }

    private void incrementThreshold(Widget.ClickData clickData) {
        this.dim = MathHelper.clamp(dim + 1, 0, motortier*2);
    }

    private void decrementThreshold(Widget.ClickData clickData) {
        this.dim = MathHelper.clamp(dim - 1, 0, motortier*2);
    }
    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntitySpaceElevator(metaTileEntityId);
    }
    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if(dataId == GTQTValue.UPDATE_TIER){
            this.dim = buf.readInt();
        }
        if(dataId == GTQTValue.REQUIRE_DATA_UPDATE){
            this.writeCustomData(GTQTValue.UPDATE_TIER,buf1 -> buf1.writeInt(this.dim));
        }
    }
    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        Object motortier = context.get("SETiredStats");
        Object Atier = context.get("SEATiredStats");
        Object Btier = context.get("SEBTiredStats");
        Object Ctier = context.get("SECTiredStats");
        Object Dtier = context.get("SEDTiredStats");
        this.motortier = GTQTUtil.getOrDefault(() -> motortier instanceof WrappedIntTired,
                () -> ((WrappedIntTired)motortier).getIntTier(),
                0);

        this.Atier = GTQTUtil.getOrDefault(() -> Atier instanceof WrappedIntTired,
                () -> ((WrappedIntTired)Atier).getIntTier(),
                0);
        this.Btier = GTQTUtil.getOrDefault(() -> Btier instanceof WrappedIntTired,
                () -> ((WrappedIntTired)Btier).getIntTier(),
                0);
        this.Ctier = GTQTUtil.getOrDefault(() -> Ctier instanceof WrappedIntTired,
                () -> ((WrappedIntTired)Ctier).getIntTier(),
                0);
        this.Dtier = GTQTUtil.getOrDefault(() -> Dtier instanceof WrappedIntTired,
                () -> ((WrappedIntTired)Dtier).getIntTier(),
                0);

        this.writeCustomData(GTQTValue.UPDATE_TIER, buf -> buf.writeInt(this.motortier));
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        textList.add(new TextComponentTranslation("gtqtcore.multiblock.se.motor",motortier,dim,getCoe()));
        textList.add(new TextComponentTranslation("gtqtcore.multiblock.se.abcdn"));
        textList.add(new TextComponentTranslation("gtqtcore.multiblock.se.abcd",Atier,Btier,Ctier,Dtier));

    }


    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
    }

    /*
        @Override
        public boolean checkRecipe(@Nonnull Recipe recipe,
                                   boolean consumeIfSuccess) {
            if (super.checkRecipe(recipe, consumeIfSuccess)) {
                recipe.getProperty(SpaceElevatorCasingTierProperty.getInstance(), 0);
            }
            return false;
        }
    */
    @Nonnull
    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.DOWN, RelativeDirection.FRONT)
                .aisle("                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "               FF FF               ", "               AAAAA               ")
                .aisle("                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "               D   D               ", "            FFFFF FFFFF            ", "            AAAAAAAAAAA            ")
                .aisle("                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "            DDDDE EDDDD            ", "          FFFFFFF FFFFFFF          ", "          AAAAAAAAAAAAAAA          ")
                .aisle("                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                E E                ", "          DD   DE ED   DD          ", "         FFFFFFD   DFFFFFF         ", "        AAAAAAAAAAAAAAAAAAA        ")
                .aisle("                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                E E                ", "               DE ED               ", "               D   D               ", "         FFF           FFF         ", "       AAAAAAAAAAAAAAAAAAAAA       ")
                .aisle("                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                E E                ", "               DE ED               ", "               DE ED               ", "                                   ", "                                   ", "      AAAAAAAAAAAAAAAAAAAAAAA      ")
                .aisle("                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                E E                ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "                                   ", "                                   ", "                                   ", "     AAAAAAAAAAAAAAAAAAAAAAAAA     ")
                .aisle("                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                E E                ", "              HDE EDH              ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "    AAAAAAAAAAAAAAAAAAAAAAAAAAA    ")
                .aisle("                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                E E                ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "            HH DE ED HH            ", "                                   ", "                                   ", "                                   ", "                                   ", "               X2X2X               ", "               X2X2X               ", "               X2X2X               ", "               X2X2X               ", "   AAAAAAAAAAAAX2X2XAAAAAAAAAAAA   ")
                .aisle("                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                E E                ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "                                   ", "         E               E         ", "         EHH           HHE         ", "         E               E         ", "         E               E         ", "         E               E         ", "         E               E         ", "         E     X2X2X     E         ", "         E     I2I2I     E         ", "         E     X2X2X     E         ", "   FF    E     X2X2X     E    FF   ", "   AAAAAAAAAAAAX2X2XAAAAAAAAAAAA   ")
                .aisle("                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                E E                ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "          E             E          ", "          E             E          ", "          E             E          ", "          E             E          ", "          E             E          ", "         HE             EH         ", "                                   ", "                                   ", "                                   ", "                                   ", "                2 2                ", "                2 2                ", "   D            2 2            D   ", "  FFF          F2F2F          FFF  ", "  AAAAAAAAAAAAA 2 2 AAAAAAAAAAAAA  ")
                .aisle("                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                E E                ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "                                   ", "                                   ", "           E           E           ", "           E           E           ", "           E           E           ", "           E           E           ", "           E           E           ", "                                   ", "                                   ", "         H               H         ", "                                   ", "                                   ", "                                   ", "                                   ", "                2 2                ", "                2 2                ", "   D            2 2            D   ", "  FFF          F2F2F          FFF  ", "  AAAAAAAAAAAAA 2 2 AAAAAAAAAAAAA  ")
                .aisle("                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                E E                ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "             HH     HH             ", "            E         E            ", "            E         E            ", "            E         E            ", "            E         E            ", "            E         E            ", "            E         E            ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "        H                 H        ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "  D                             D  ", " FFF          FFFFFFF          FFF ", " AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA ")
                .aisle("                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                FFF                ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "             E       E             ", "             E       E             ", "             E       E             ", "             E       E             ", "             E       E             ", "             E       E             ", "             E       E             ", "            HE       EH            ", "             E       E             ", "             E       E             ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "        H                 H        ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "  D                             D  ", " FFF         FFFFFFFFF         FFF ", " AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA ")
                .aisle("                FFF                ", "                 E                 ", "                 E                 ", "                 E                 ", "                 E                 ", "                 E                 ", "               F   F               ", "              E     E              ", "              E     E              ", "              E     E              ", "              E     E              ", "              E     E              ", "              E     E              ", "              E     E              ", "              E     E              ", "              E     E              ", "                                   ", "                                   ", "                FFF                ", "                                   ", "                                   ", "            H         H            ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                FFF                ", "                                   ", "                                   ", "                                   ", "                                   ", "       H                   H       ", "                                   ", "                                   ", "                                   ", "                                   ", "                XXX                ", "                X~X                ", "  D             XXX             D  ", " FFF        FFFFFFFFFFF        FFF ", " AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA ")
                .aisle("               F D F               ", "                 D                 ", "                 D                 ", "                 D                 ", "                 D                 ", "                 D                 ", "              F  D  F              ", "                 D                 ", "            D    D    D            ", "            D    D    D            ", "            D    D    D            ", "            D    D    D            ", "            D    D    D            ", "            D    D    D            ", "            D    D    D            ", "           DD    D    DD           ", "           D     D     D           ", "           D     D     D           ", "           D   F D F   D           ", "           D     C     D           ", "           D     C     D           ", "           D     C     D           ", "           D     C     D           ", "          DD     C     DD          ", "          D      C      D          ", "          D      C      D          ", "          D      C      D          ", "         DD      C      DD         ", "         D     FDCDF     D         ", "         D      DCD      D         ", "        DD      DCD      DD        ", "        D       DCD       D        ", "        D       DCD       D        ", "       DD      DDCDD      DD       ", "       D       D C D       D       ", "       D       D C D       D       ", "      DD       D C D       DD      ", "      D        D C D        D      ", "     DD XX     XDCDX     XX DD     ", "    DD  XI     XDCDX     IX  DD    ", " DDDD   XX     XDCDX     XX   DDDD ", "FFFD    XXFFFFFDDDDDFFFFFXX    DFFF", "AAAAAAAAXX  AAAXXXXXAAA  XXAAAAAAAA")
                .aisle("              F     F              ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "             F       F             ", "            E         E            ", "            E         E            ", "            E         E            ", "            E         E            ", "            E         E            ", "            E         E            ", "            E         E            ", "           EE         EE           ", "           EE         EE           ", "           E           E           ", "           E           E           ", "           E  F     F  E           ", "           E           E           ", "           E           E           ", "           E           E           ", "          EE           EE          ", "          EE           EE          ", "          E             E          ", "          E             E          ", "         EE             EE         ", "         EE             EE         ", "         E    FD   DF    E         ", "        EE     D   D     EE        ", "        EE     D   D     EE        ", "        E      D   D      E        ", "       EE      D   D      EE       ", "       EE      D   D      EE       ", "       E                   E       ", "      EE                   EE      ", "      EE                   EE      ", "     EE                     EE     ", "    EEE 3333  XD   DX  1111 EEE    ", "   EEE  3333  XD   DX  1111  EEE   ", "  EE    3333  XD   DX  1111    EE  ", "FFF     3333FFFDDDDDFFF1111     FFF", "AAAAAAAA3333AAAXXXXXAAA1111AAAAAAAA")
                .aisle("              FD   DF              ", "              ED   DE              ", "              ED   DE              ", "              ED   DE              ", "              ED   DE              ", "              ED   DE              ", "             F D   D F             ", "               D   D               ", "               D   D               ", "               D   D               ", "               D   D               ", "               D   D               ", "               D   D               ", "               D   D               ", "               D   D               ", "               D   D               ", "               D   D               ", "               D - D               ", "              FD - DF              ", "               C - C               ", "               C - C               ", "               C - C               ", "               C - C               ", "               C - C               ", "               C - C               ", "               C - C               ", "               C - C               ", "               C - C               ", "              FC - CF              ", "               C - C               ", "               C - C               ", "               C - C               ", "               C - C               ", "               C - C               ", "               C - C               ", "               C - C               ", "               C - C               ", "               C - C               ", "        XX    XC - CX    XX        ", "        XI    XC - CX    IX        ", "        XX    XC - CX    XX        ", "        XXFFFFFDDDDDFFFFFXX        ", "AAAAAAAAXX  AAAXXXXXAAA  XXAAAAAAAA")
                .aisle("              F     F              ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "             F       F             ", "            E         E            ", "            E         E            ", "            E         E            ", "            E         E            ", "            E         E            ", "            E         E            ", "            E         E            ", "           EE         EE           ", "           EE         EE           ", "           E           E           ", "           E           E           ", "           E  F     F  E           ", "           E           E           ", "           E           E           ", "           E           E           ", "          EE           EE          ", "          EE           EE          ", "          E             E          ", "          E             E          ", "         EE             EE         ", "         EE             EE         ", "         E    FD   DF    E         ", "        EE     D   D     EE        ", "        EE     D   D     EE        ", "        E      D   D      E        ", "       EE      D   D      EE       ", "       EE      D   D      EE       ", "       E                   E       ", "      EE                   EE      ", "      EE                   EE      ", "     EE                     EE     ", "    EEE 3333  XD   DX  1111 EEE    ", "   EEE  3333  XD   DX  1111  EEE   ", "  EE    3333  XD   DX  1111    EE  ", "FFF     3333FFFDDDDDFFF1111     FFF", "AAAAAAAA3333AAAXXXXXAAA1111AAAAAAAA")
                .aisle("               F D F               ", "                 D                 ", "                 D                 ", "                 D                 ", "                 D                 ", "                 D                 ", "              F  D  F              ", "                 D                 ", "            D    D    D            ", "            D    D    D            ", "            D    D    D            ", "            D    D    D            ", "            D    D    D            ", "            D    D    D            ", "            D    D    D            ", "           DD    D    DD           ", "           D     D     D           ", "           D     D     D           ", "           D   F D F   D           ", "           D     C     D           ", "           D     C     D           ", "           D     C     D           ", "           D     C     D           ", "          DD     C     DD          ", "          D      C      D          ", "          D      C      D          ", "          D      C      D          ", "         DD      C      DD         ", "         D     FDCDF     D         ", "         D      DCD      D         ", "        DD      DCD      DD        ", "        D       DCD       D        ", "        D       DCD       D        ", "       DD      DDCDD      DD       ", "       D       D C D       D       ", "       D       D C D       D       ", "      DD       D C D       DD      ", "      D        D C D        D      ", "     DD XX     XDCDX     XX DD     ", "    DD  XI     XDCDX     IX  DD    ", " DDDD   XX     XDCDX     XX   DDDD ", "FFFD    XXFFFFFDDDDDFFFFFXX    DFFF", "AAAAAAAAXX  AAAXXXXXAAA  XXAAAAAAAA")
                .aisle("                FFF                ", "                 E                 ", "                 E                 ", "                 E                 ", "                 E                 ", "                 E                 ", "               F   F               ", "              E     E              ", "              E     E              ", "              E     E              ", "              E     E              ", "              E     E              ", "              E     E              ", "              E     E              ", "              E     E              ", "              E     E              ", "                                   ", "                                   ", "                FFF                ", "                                   ", "                                   ", "            H         H            ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                FFF                ", "                                   ", "                                   ", "                                   ", "                                   ", "       H                   H       ", "                                   ", "                                   ", "                                   ", "                                   ", "                XXX                ", "                XGX                ", "  D             XXX             D  ", " FFF        FFFFFFFFFFF        FFF ", " AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA ")
                .aisle("                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                FFF                ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "             E       E             ", "             E       E             ", "             E       E             ", "             E       E             ", "             E       E             ", "             E       E             ", "             E       E             ", "            HE       EH            ", "             E       E             ", "             E       E             ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "        H                 H        ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "  D                             D  ", " FFF         FFFFFFFFF         FFF ", " AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA ")
                .aisle("                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                E E                ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "             HH     HH             ", "            E         E            ", "            E         E            ", "            E         E            ", "            E         E            ", "            E         E            ", "            E         E            ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "        H                 H        ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "  D                             D  ", " FFF          FFFFFFF          FFF ", " AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA ")
                .aisle("                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                E E                ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "                                   ", "                                   ", "           E           E           ", "           E           E           ", "           E           E           ", "           E           E           ", "           E           E           ", "                                   ", "                                   ", "         H               H         ", "                                   ", "                                   ", "                                   ", "                                   ", "                4 4                ", "                4 4                ", "   D            4 4            D   ", "  FFF          F4F4F          FFF  ", "  AAAAAAAAAAAAA 4 4 AAAAAAAAAAAAA  ")
                .aisle("                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                E E                ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "          E             E          ", "          E             E          ", "          E             E          ", "          E             E          ", "          E             E          ", "         HE             EH         ", "                                   ", "                                   ", "                                   ", "                                   ", "                4 4                ", "                4 4                ", "   D            4 4            D   ", "  FFF          F4F4F          FFF  ", "  AAAAAAAAAAAAA 4 4 AAAAAAAAAAAAA  ")
                .aisle("                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                E E                ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "                                   ", "         E               E         ", "         EHH           HHE         ", "         E               E         ", "         E               E         ", "         E               E         ", "         E               E         ", "         E     X4X4X     E         ", "         E     I4I4I     E         ", "         E     X4X4X     E         ", "   FF    E     X4X4X     E    FF   ", "   AAAAAAAAAAAAX4X4XAAAAAAAAAAAA   ")
                .aisle("                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                E E                ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "            HH DE ED HH            ", "                                   ", "                                   ", "                                   ", "                                   ", "               X4X4X               ", "               X4X4X               ", "               X4X4X               ", "               X4X4X               ", "   AAAAAAAAAAAAX4X4XAAAAAAAAAAAA   ")
                .aisle("                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                E E                ", "              HDE EDH              ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "    AAAAAAAAAAAAAAAAAAAAAAAAAAA    ")
                .aisle("                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                E E                ", "               DE ED               ", "               DE ED               ", "               DE ED               ", "                                   ", "                                   ", "                                   ", "     AAAAAAAAAAAAAAAAAAAAAAAAA     ")
                .aisle("                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                E E                ", "               DE ED               ", "               DE ED               ", "                                   ", "                                   ", "      AAAAAAAAAAAAAAAAAAAAAAA      ")
                .aisle("                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                E E                ", "               DE ED               ", "               D   D               ", "         FFF           FFF         ", "       AAAAAAAAAAAAAAAAAAAAA       ")
                .aisle("                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                E E                ", "          DD   DE ED   DD          ", "         FFFFFFD   DFFFFFF         ", "        AAAAAAAAAAAAAAAAAAA        ")
                .aisle("                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "            DDDDE EDDDD            ", "          FFFFFFF FFFFFFF          ", "          AAAAAAAAAAAAAAA          ")
                .aisle("                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "               D   D               ", "            FFFFF FFFFF            ", "            AAAAAAAAAAA            ")
                .aisle("                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "                                   ", "               FF FF               ", "               AAAAA               ")
                .where('~', this.selfPredicate())
                .where('G', abilities(MultiblockAbility.COMPUTATION_DATA_RECEPTION))
                .where('E', states(getCasingState()))
                .where('B', states(getSecondCasingState()))
                .where('X', states(getThirdCasingState()))
                .where('H', states(getFrameState()))
                .where('F', states(getFourthCasingState()))
                .where('C', TiredTraceabilityPredicate.CP_SE_CASING)
                .where('1', TiredTraceabilityPredicate.CP_SEA_CASING)
                .where('2', TiredTraceabilityPredicate.CP_SEB_CASING)
                .where('3', TiredTraceabilityPredicate.CP_SEC_CASING)
                .where('4', TiredTraceabilityPredicate.CP_SED_CASING)
                .where('A', states(getFifthCasingState()))
                .where('D', states(getSixthCasingState()))
                .where('I', states(getSixthCasingState())
                        .or(autoAbilities()))
                .where('-', air())
                .where(' ', any())
                .build();
    }

    private static IBlockState getCasingState() {
        return GTQTMetaBlocks.SPACE_ELEVATOR.getState(GTQTSpaceElevator.ElevatorCasingType.SUPPORT_STRUCTURE);
    }

    private static IBlockState getSecondCasingState() {
        return  GTQTMetaBlocks.SPACE_ELEVATOR.getState(GTQTSpaceElevator.ElevatorCasingType.CABLE_CASING);
    }

    private static IBlockState getThirdCasingState() {
        return  GTQTMetaBlocks.SPACE_ELEVATOR.getState(GTQTSpaceElevator.ElevatorCasingType.SUPPORT_STRUCTURE);
    }

    private static IBlockState getFourthCasingState() {
        return  GTQTMetaBlocks.SPACE_ELEVATOR.getState(GTQTSpaceElevator.ElevatorCasingType.INTERNAL_STRUCTURE);
    }

    private static IBlockState getFifthCasingState() {
        return  GTQTMetaBlocks.SPACE_ELEVATOR.getState(GTQTSpaceElevator.ElevatorCasingType.FLOOR);
    }

    private static IBlockState getSixthCasingState() {
        return  GTQTMetaBlocks.SPACE_ELEVATOR.getState(GTQTSpaceElevator.ElevatorCasingType.BASIC_CASING);
    }

    private static IBlockState getFrameState() {
        return MetaBlocks.FRAMES.get(GTQTMaterials.MARM200Steel).getBlock(GTQTMaterials.MARM200Steel);
    }
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        data.setInteger("Dim", dim);
        return super.writeToNBT(data);
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        dim = data.getInteger("Dim");
        super.readFromNBT(data);
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeVarInt(dim);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        dim = buf.readVarInt();
    }

    public int getThrottle() {
        return dim;
    }
    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GTQTTextures.SPACE_ELEVATOR_CASING;
    }
    @SideOnly(Side.CLIENT)
    @Nonnull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.FUSION_REACTOR_OVERLAY;
    }

    @Override
    public void addInformation(ItemStack stack,
                               @Nullable World player,
                               @Nonnull List<String> tooltip,
                               boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtlitecore.machine.space_elevator.tooltip.13"));
    }

    @Override
    public boolean canBeDistinct() {
        return true;
    }

    private class SpaceElevatorRecipeLogic extends ComputationRecipeLogic {

        public SpaceElevatorRecipeLogic(MetaTileEntitySpaceElevator tileEntity) {
            super(tileEntity,ComputationType.SPORADIC);
        }

        @Override
        public int getParallelLimit() {
            return Atier*Dtier;
        }

        public void setMaxProgress(int maxProgress) {
            this.maxProgressTime = maxProgress*(100-Btier*10);
        }

        @Override
        protected void outputRecipeOutputs() {
            int outNum = getCoe();
            setOutPutItem(outNum);
            GTTransferUtils.addItemsToItemHandler(this.getOutputInventory(), false, this.itemOutputs);
            GTTransferUtils.addFluidsToFluidHandler(this.getOutputTank(), false, this.fluidOutputs);
        }
        private void setOutPutItem(int num)
        {
            if(num !=0)
            {
                List<ItemStack> MyitemOutputs = new ArrayList<>();
                for (ItemStack item: this.itemOutputs
                ) {
                    int group = num/64;
                    for (int i = 0; i < group; i++) {
                        MyitemOutputs.add(new ItemStack(item.getItem(),64));
                    }
                    MyitemOutputs.add(new ItemStack(item.getItem(),num%64));
                }
                GTTransferUtils.addItemsToItemHandler(this.getOutputInventory(), false, MyitemOutputs);
            }
        }

        @Override
        protected void modifyOverclockPost(int[] resultOverclock,  IRecipePropertyStorage storage) {
            super.modifyOverclockPost(resultOverclock, storage);
            resultOverclock[0] *= 1.0f - Ctier * 0.1; // each coil above cupronickel (coilTier = 0) uses 10% less
            resultOverclock[0] = Math.max(1, resultOverclock[0]);
        }
    }


}