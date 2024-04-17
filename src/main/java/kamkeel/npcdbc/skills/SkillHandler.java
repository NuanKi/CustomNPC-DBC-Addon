package kamkeel.npcdbc.skills;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import kamkeel.npcdbc.client.KeyHandler;
import kamkeel.npcdbc.util.Utility;
import net.minecraft.client.Minecraft;


public class SkillHandler {
    @SubscribeEvent
    public void onSkill(TickEvent.PlayerTickEvent event){
        if(event.side == Side.SERVER || event.player == null)
            return;

        if(event.phase == TickEvent.Phase.START){
            Minecraft mc = Minecraft.getMinecraft();
            if(mc.currentScreen == null && KeyHandler.AscendKey.getIsKeyPressed()){
                performAscend();
            }
            else {
                Transform.decrementRage();
            }
        }
    }

    private void performAscend() {
        if (Utility.getFormDataClient().getSelectedForm() != null) {
            Transform.Ascend(Utility.getFormDataClient().getSelectedForm());
        } else {
            Minecraft mc = Minecraft.getMinecraft();
            Utility.sendMessage(mc.thePlayer, "§cYou have not selected a custom form!");
        }
    }
}
