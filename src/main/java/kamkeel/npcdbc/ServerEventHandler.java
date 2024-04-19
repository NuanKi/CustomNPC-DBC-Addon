package kamkeel.npcdbc;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kamkeel.npcdbc.data.DBCData;
import kamkeel.npcdbc.mixin.IPlayerFormData;
import kamkeel.npcdbc.network.PacketHandler;
import kamkeel.npcdbc.network.packets.PingPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.data.PlayerData;

public class ServerEventHandler {
    @SideOnly(Side.SERVER)
    @SubscribeEvent
    public void loginEvent(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event) {
        DBCData dbcData = DBCData.get(event.player);
        dbcData.loadNBTData(null);
        dbcData.syncAllClients();
    }

    @SubscribeEvent
    public void onServerTick(TickEvent.PlayerTickEvent event) {
        if (event.player == null || event.player.worldObj == null)
            return;

        EntityPlayer player = event.player;
        if (event.side == Side.SERVER && event.phase == TickEvent.Phase.START) {
            // Send Form Information
            if (PlayerDataController.Instance != null) {
                PlayerData playerData = PlayerDataController.Instance.getPlayerData(player);
                if (((IPlayerFormData) playerData).getFormUpdate()) {
                    NBTTagCompound formCompound = new NBTTagCompound();
                    playerData.getDBCSync(formCompound);
                    NoppesUtilServer.sendDBCCompound((EntityPlayerMP) player, formCompound);
                    ((IPlayerFormData) playerData).finishFormInfo();
                }
            }

            if (player.ticksExisted % 10 == 0) {
                DBCData dbcData = DBCData.get(player);
                dbcData.loadNBTData(null);
                // dbcData.syncAllClients();
            }
        }
    }


    @SubscribeEvent
    public void addTracking(PlayerEvent.StartTracking event){
        if(event.target instanceof EntityPlayer && !(event.target instanceof FakePlayer)){
            DBCData data = DBCData.get((EntityPlayer) event.target);
            if (data == null) {
                return;
            }
            data.loadNBTData(null);
            PacketHandler.Instance.sendToPlayer(new PingPacket(data).generatePacket(),
                ((EntityPlayerMP)event.entityPlayer));
        }
    }
}
