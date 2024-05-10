package kamkeel.npcdbc.network.packets;

import io.netty.buffer.ByteBuf;
import kamkeel.npcdbc.client.sound.SoundHandler;
import kamkeel.npcdbc.client.sound.Sound;
import kamkeel.npcdbc.network.AbstractPacket;
import kamkeel.npcdbc.network.PacketHandler;
import kamkeel.npcdbc.util.ByteBufUtils;
import kamkeel.npcdbc.util.Utility;
import net.minecraft.entity.player.EntityPlayer;

import java.io.IOException;

public final class StopSound extends AbstractPacket {
    public static final String packetName = "NPC|StopSound";

    public Sound sound;


    public StopSound() {
    }

    public StopSound(Sound sound) {
        this.sound = sound;
    }


    @Override
    public String getChannel() {
        return packetName;
    }

    @Override
    public void sendData(ByteBuf out) throws IOException {
        ByteBufUtils.writeNBT(out, sound.writeToNbt());


    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        sound = Sound.createFromNBT(ByteBufUtils.readNBT(in));

        if (Utility.isServer())
            stop(sound);
        else {
            if (SoundHandler.playingSounds.containsKey(sound.key)) {
                SoundHandler.playingSounds.get(sound.key).stop(false);
            }
        }
    }

    public static void stop(Sound sound) {
        if (sound == null || sound.entity == null)
            return;
        PacketHandler.Instance.sendToTrackingPlayers(sound.entity, new StopSound(sound).generatePacket());
    }

}
