package kamkeel.npcdbc.client.gui.dbc;

import JinRyuu.JRMCore.*;
import cpw.mods.fml.common.FMLCommonHandler;
import kamkeel.npcdbc.CustomNpcPlusDBC;
import kamkeel.npcdbc.client.gui.dbc.constants.GuiInfo;
import kamkeel.npcdbc.config.ConfigDBCClient;
import kamkeel.npcdbc.mixins.late.IDBCGuiScreen;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiConfirmOpenLink;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class AbstractJRMCGui2 extends GuiScreen implements GuiYesNoCallback {
    // private static final ResourceLocation menuTexture = new ResourceLocation("jinryuumodscore:gui2.png");
    protected ResourceLocation menuTexture = new ResourceLocation(CustomNpcPlusDBC.ID + ":textures/gui/gui_dark.png");
    protected HashMap<String, JRMCoreLabel> dynamicLabels = new HashMap<>();

    protected ArrayList<JRMCoreLabel> hoverableStaticLabels = new ArrayList<>();

    public HashMap<Integer, JRMCoreLabel> horribleDBCDynamicLabels = new HashMap<>();

    protected int menuImageWidth = 256;
    protected int menuImageHeight = 159;

    protected int guiWidthOffset;
    protected int guiHeightOffset;

    private URI clickedUrl;


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks){
        super.drawScreen(mouseX, mouseY, partialTicks);
        for (JRMCoreLabel label: this.dynamicLabels.values()) {
            label.drawLabel(this.mc, mouseX, mouseY);
        }
        for(JRMCoreLabel label : this.hoverableStaticLabels){
            label.drawLabel(this.mc, mouseX, mouseY);
        }

        //Going over these twice specifically because of wrong layering
        for(JRMCoreLabel label : this.hoverableStaticLabels){
            label.hover(this.mc, mouseX, mouseY);
        }
        for (JRMCoreLabel label:  this.dynamicLabels.values()) {
            label.hover(this.mc, mouseX, mouseY);
        }

    }

    @Override
    public void initGui(){

    }

    @Override
    public boolean doesGuiPauseGame(){
        return false;
    }

}
