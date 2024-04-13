package kamkeel.npcdbc.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;
import kamkeel.npcdbc.data.SyncedData.DBCData;
import kamkeel.npcdbc.data.SyncedData.PerfectSync;
import kamkeel.npcdbc.util.u;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

import java.io.IOException;

public class PacketSyncData extends AbstractMessage<PacketSyncData> {
    private String s;
    private NBTTagCompound data;

    public PacketSyncData() {
    }


    public PacketSyncData(Entity p, String id, NBTTagCompound data) {
        s = id;
        if (u.isServer()) {
            if (!s.contains("Entity")) // player
                this.data = data;
            else this.data = data;
        }
    }

    @Override
    public void process(EntityPlayer p, Side side) {
        if (side == Side.SERVER) {
            updateServerData(p);

        } else {
            if (s.contains("register")) registerClient(p);

            else if (!s.contains("Entity")) { // player
                if (s.contains("updateDBCData")) DBCData.get(p).loadNBTData(data);

            } else {// non player entity
                Entity e = u.getEntityFromID(p.worldObj, s.split(":")[1]);
                if (e != null) {

                }
            }
        }
    }

    public void updateServerData(EntityPlayer p) {
        if (!s.contains("updateServer")) return;
        String[] d = s.split(";");
        String dn = d[1];
        String tag = d[2];
        String type = d[3];
        String value = d[4];

        Entity e = s.contains("Entity") ? u.getEntityFromID(p.worldObj, s.split(";")[5]) : p;
        NBTTagCompound cmpd = PerfectSync.get(e, dn).compound();

        if (type.equals("Int")) cmpd.setInteger(tag, Integer.parseInt(value));
        else if (type.equals("Float")) cmpd.setFloat(tag, Float.parseFloat(value));
        else if (type.equals("Double")) cmpd.setDouble(tag, Double.parseDouble(value));
        else if (type.equals("Long")) cmpd.setLong(tag, Long.parseLong(value));
        else if (type.equals("Byte")) cmpd.setByte(tag, Byte.parseByte(value));
        else if (type.equals("Boolean")) cmpd.setBoolean(tag, Boolean.parseBoolean(value));
        else if (type.equals("String")) cmpd.setString(tag, value);

        PerfectSync.get(e, dn).save(false);

    }

    public void registerClient(EntityPlayer p) {

        String[] d = s.split(";");
        String dn = d[1];


        if (dn.equals(DBCData.dn) && !DBCData.has(p)) p.registerExtendedProperties(DBCData.dn, new DBCData(p));
    }

    @Override
    public void read(PacketBuffer buffer) throws IOException {
        s = ByteBufUtils.readUTF8String(buffer);
        data = buffer.readNBTTagCompoundFromBuffer();
    }

    @Override
    public void write(PacketBuffer buffer) throws IOException {
        ByteBufUtils.writeUTF8String(buffer, s);
        buffer.writeNBTTagCompoundToBuffer(data);
    }

}
