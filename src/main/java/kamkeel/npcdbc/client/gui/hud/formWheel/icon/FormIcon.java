package kamkeel.npcdbc.client.gui.hud.formWheel.icon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kamkeel.npcdbc.CustomNpcPlusDBC;
import kamkeel.npcdbc.client.gui.hud.formWheel.HUDFormWheel;
import kamkeel.npcdbc.client.utils.Color;
import kamkeel.npcdbc.constants.DBCForm;
import kamkeel.npcdbc.data.form.Form;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class FormIcon extends Gui {
    public ResourceLocation bodyHairIcon = new ResourceLocation(CustomNpcPlusDBC.ID + ":textures/gui/hud/formwheel/icon/humanoid/body1.png");
    public ResourceLocation auraIcon = new ResourceLocation(CustomNpcPlusDBC.ID + ":textures/gui/hud/formwheel/icon/aurapng");
    public HUDFormWheel parent;

    public Color hairColor = new Color(0x2ACDEE, 1).lerpRGBA(new Color(0, 1), 0.2f), auraColor = new Color(0x2ACDEE, 1);
    public int width = 16, height = 16;

    public FormIcon(HUDFormWheel parent, Form formToCopy){
        this.parent = parent;
    }
    public FormIcon(HUDFormWheel parent, int DBCFormID){
        this.parent = parent;
    }

    public void draw(){
        TextureManager renderEngine = Minecraft.getMinecraft().renderEngine;
//        drawRect(-width/2, -height/2, width/2, height/2, 0x55FF0000);
//        GL11.glEnable(GL11.GL_BLEND);
        GL11.glPushMatrix();

        // TODO Replace textures
        //      The current textures are too small (only take up 1/3rd of the size)
        //      So I have to scale up the result.
        GL11.glScalef(3, 3, 3);
        if (auraIcon != null) {
            renderEngine.bindTexture(auraIcon);
            auraColor.glColor();
            drawTexturedRect(-width/2, -height/2, width/2, height/2, 0, 1, 0, 1);
        }

        if (bodyHairIcon != null) {
            renderEngine.bindTexture(bodyHairIcon);
            GL11.glColor4f(1, 1, 1, 1);
            drawTexturedRect(-width/2, -height/2, width/2, height/2, 0, 1, 0, 0.5f);
            hairColor.glColor();
            drawTexturedRect(-width/2, -height/2, width/2, height/2, 0, 1, 0.5f, 1f);
        }
        GL11.glPopMatrix();
    }

    private void drawTexturedRect(float left, float top, float right, float bottom, float minU, float maxU, float minV, float maxV){
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(left, bottom, (double)this.zLevel, minU, maxV);
        tessellator.addVertexWithUV(right, bottom, (double)this.zLevel, maxU, maxV);
        tessellator.addVertexWithUV(right, top, (double)this.zLevel, maxU, minV);
        tessellator.addVertexWithUV(left, top, (double)this.zLevel, minU, minV);
        tessellator.draw();
    }



}
