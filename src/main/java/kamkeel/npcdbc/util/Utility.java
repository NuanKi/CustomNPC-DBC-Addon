package kamkeel.npcdbc.util;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kamkeel.npcdbc.controllers.FormController;
import kamkeel.npcdbc.data.CustomForm;
import kamkeel.npcdbc.data.PlayerCustomFormData;
import kamkeel.npcdbc.data.SyncedData.DBCData;
import kamkeel.npcdbc.mixin.IPlayerFormData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.scripted.NpcAPI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static noppes.npcs.NoppesUtilServer.sendScrollData;

public class Utility {
    public static boolean isServer() {
        return FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER;
    }

    public static boolean isServer(Entity entity) {
        return entity != null && entity.worldObj != null && !entity.worldObj.isRemote;
    }

    public static boolean isServer(World worldObj) {
        return worldObj != null && !worldObj.isRemote;
    }

    public static void sendMessage(EntityPlayer player, String message) {
        player.addChatMessage(new ChatComponentText(message));
    }

    public static Entity getEntityFromID(World w, String s) { //s is basically getEntityID
        if (!s.isEmpty()) {
            if (Boolean.parseBoolean(s.split(",")[1])) //if player
                return Utility.getEntityByUUID(w, s.split(",")[0]);
            else
                return w.getEntityByID(Integer.parseInt(s.split(",")[0]));
        }
        return null;

    }

    public static String getEntityID(Entity p) {
        if (p instanceof EntityPlayer)
            return p.getUniqueID().toString() + ",true"; //true as in "entity is player"
        else if (p != null)
            return p.getEntityId() + ",false";
        else
            return "";
    }

    public static UUID getUUID(Entity entity) {
        return entity instanceof EntityPlayer ? ((EntityPlayer) entity).getGameProfile().getId() : entity.getUniqueID();
    }

    public static Entity getFromUUID(UUID id, World w) {
        List<Entity> allEntity = w.loadedEntityList;
        for (Entity entity : allEntity)
            if (entity instanceof EntityPlayer) {
                if (((EntityPlayer) entity).getGameProfile().getId().equals(id))
                    return entity;
            } else if (entity.getUniqueID().equals(id))
                return entity;

        return null;
    }

    @SideOnly(Side.CLIENT)
    public static Entity getFromNameClient(String name) {
        return getFromName(name, Minecraft.getMinecraft().theWorld);
    }

    public static Entity getFromName(String name, World w) {
        List<Entity> allEntity = w.loadedEntityList;
        for (Entity entity : allEntity)
            if (entity.getCommandSenderName().equals(name))
                return entity;


        return null;
    }

    public static Entity getEntityByUUID(World w, String s) {
        if (s == null)
            return null;

        List<Entity> allEntity = w.loadedEntityList;
        for (Entity player : allEntity)
            if (player.getUniqueID().toString().equals(s))
                return player;

        return null;

    }

    public static void printStackTrace() {
        for (StackTraceElement ste : Thread.currentThread().getStackTrace())
            System.out.println(ste);
    }

    public static boolean stackTraceContains(String text) {
        for (StackTraceElement ste : Thread.currentThread().getStackTrace())
            if (ste.toString().toLowerCase().contains(text.toLowerCase()))
                return true;
        return false;
    }

    public static double percent(double n, double perc) {
        return n / 100 * perc;
    }

    public static float percent(int n, float perc) {
        return n / 100 * perc;
    }

    public static boolean percentBetween(double n, double maxN, double minPerc, double maxPerc) {
        return n >= Utility.percent(maxN, minPerc) && n < Utility.percent(maxN, maxPerc);
    }

    public static IPlayer getIPlayer(EntityPlayer p) {
        return (IPlayer) NpcAPI.Instance().getIEntity(p);
    }

    @SideOnly(Side.CLIENT)
    public static PlayerCustomFormData getSelfData() {
        if(Minecraft.getMinecraft().thePlayer == null)
            return null;
        IPlayerFormData formData = (IPlayerFormData) PlayerData.get(Minecraft.getMinecraft().thePlayer);
        if(formData == null)
            return null;
        return formData.getCustomFormData();
    }

    @SideOnly(Side.CLIENT)
    public static CustomForm getFormClient(AbstractClientPlayer player) {
        DBCData dbcData = DBCData.get(player);
        if(dbcData == null)
            return null;

        int form = dbcData.currentCustomForm;
        if(form == -1)
            return null;

        return (CustomForm) FormController.getInstance().get(form);
    }

    @SideOnly(Side.CLIENT)
    public static float getFormLevelClient(AbstractClientPlayer player) {
        DBCData dbcData = DBCData.get(player);
        if(dbcData == null)
            return 0f;

        return dbcData.currentCustomFormLevel;
    }

    public static PlayerCustomFormData getFormData(EntityPlayer player) {
        return Utility.getFormData(PlayerDataController.Instance.getPlayerData(player));
    }

    public static PlayerCustomFormData getFormData(PlayerData playerData) {
        return ((IPlayerFormData) playerData).getCustomFormData();
    }

    public static void sendPlayerFormData(EntityPlayerMP player) {
        PlayerCustomFormData data = ((IPlayerFormData) PlayerDataController.Instance.getPlayerData(player)).getCustomFormData();

        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int formID : data.unlockedForms.keySet()) {
            CustomForm form = (CustomForm) FormController.getInstance().get(formID);
            if (form != null) {
                map.put(form.name, form.id);
            }
        }
        sendScrollData(player, map);
    }

}
