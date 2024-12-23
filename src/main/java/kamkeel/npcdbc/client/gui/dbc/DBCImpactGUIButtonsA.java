package kamkeel.npcdbc.client.gui.dbc;

import kamkeel.npcdbc.CustomNpcPlusDBC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class DBCImpactGUIButtonsA extends GuiButton {
   private String string;

   public DBCImpactGUIButtonsA(int par1, int par2, int par3, String par6Str) {
      super(par1, par2, par3, 10, 10, "");
      this.string = par6Str;
   }

   public void drawButton(Minecraft par1Minecraft, int par2, int par3) {
      if (this.visible) {
         FontRenderer var4 = par1Minecraft.fontRenderer;
         ResourceLocation txx = new ResourceLocation(CustomNpcPlusDBC.ID + ":textures/gui/buttons.png");
         par1Minecraft.getTextureManager().bindTexture(txx);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.field_146123_n = par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;
         int var5 = this.getHoverState(this.field_146123_n);
         int s = 0;
         if (this.string.equals(">")) {
            s = 20;
         } else if (this.string.equals("<")) {
            s = 50;
         } else if (this.string.equals("^")) {
            s = 30;
         } else if (this.string.equals("v")) {
            s = 40;
         }

         this.drawTexturedModalRect(this.xPosition, this.yPosition, s, var5 * 10, this.width, this.height);
         this.mouseDragged(par1Minecraft, par2, par3);
      }

   }
}
