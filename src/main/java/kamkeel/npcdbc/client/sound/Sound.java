package kamkeel.npcdbc.client.sound;

import kamkeel.npcdbc.network.PacketHandler;
import kamkeel.npcdbc.network.packets.PlaySound;
import kamkeel.npcdbc.network.packets.StopSound;
import kamkeel.npcdbc.util.Utility;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;

public class Sound extends MovingSound {
    public String key;
    public String soundDir;
    public Entity entity;

    public float range = 16;
    public boolean onlyOneCanExist = true;

    public Sound(String soundDir) {
        super(new ResourceLocation(soundDir));
    }

    public Sound(String soundDir, Entity entity) {
        super(new ResourceLocation(soundDir));
        this.soundDir = soundDir;
        this.entity = entity;
        key = toString();
    }

    public void update() {

        if (this.entity == null || entity.isDead || (Minecraft.getMinecraft().gameSettings.getSoundLevel(SoundCategory.MASTER) == 0)) {
            stop(false);
            return;
        }

        this.xPosF = (float) this.entity.posX;
        this.yPosF = (float) this.entity.posY;
        this.zPosF = (float) this.entity.posZ;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }


    public void play(boolean forOthers) {
        PlaySoundAtEntityEvent event = new PlaySoundAtEntityEvent(entity, soundDir, volume, getPitch());

        if (MinecraftForge.EVENT_BUS.post(event) || onlyOneCanExist && SoundHandler.playingSounds.containsKey(key))
            return;

        SoundHandler.playingSounds.put(key, this);
        Minecraft.getMinecraft().getSoundHandler().playSound(this);
        if (forOthers)
            PacketHandler.Instance.sendToServer(new PlaySound(this).generatePacket());
    }

    public void stop(boolean forOthers) {
        this.donePlaying = true;
        Minecraft.getMinecraft().getSoundHandler().stopSound(this);
        SoundHandler.playingSounds.remove(this.key);
        if (forOthers)
            PacketHandler.Instance.sendToServer(new StopSound(this).generatePacket());
    }

    public boolean isPlaying() {
        return Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(this);
    }

    public NBTTagCompound writeToNbt() {
        NBTTagCompound c = new NBTTagCompound();

        c.setFloat("volume", volume);
        c.setBoolean("repeat", repeat);
        c.setFloat("range", range);
        c.setBoolean("onlyOneCanExist", onlyOneCanExist);
        c.setString("soundDir", soundDir);
        c.setString("key", key);
        c.setInteger("dimensionID", entity.worldObj.provider.dimensionId);
        c.setString("entity", Utility.getEntityID(entity));

        return c;
    }

    public String toString() {
        return entity.getEntityId() + ":" + soundDir;
    }

    public static Sound createFromNBT(NBTTagCompound compound) {
        String directory = compound.getString("soundDir");
        Sound sound = new Sound(directory);

        sound.setVolume(compound.getFloat("volume"));
        sound.soundDir = directory;
        sound.repeat = compound.getBoolean("repeat");
        sound.range = compound.getFloat("range");
        sound.onlyOneCanExist = compound.getBoolean("onlyOneCanExist");
        sound.key = compound.getString("key");

        int dimID = compound.getInteger("dimensionID");
        World world = Utility.getWorld(dimID);
        sound.entity = Utility.getEntityFromID(world, compound.getString("entity"));
        return sound;
    }
}