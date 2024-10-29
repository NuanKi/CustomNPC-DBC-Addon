package kamkeel.npcdbc.data.statuseffect.types;

import kamkeel.npcdbc.CustomNpcPlusDBC;
import kamkeel.npcdbc.config.ConfigDBCEffects;
import kamkeel.npcdbc.constants.Effects;
import kamkeel.npcdbc.controllers.BonusController;
import kamkeel.npcdbc.data.PlayerBonus;
import kamkeel.npcdbc.data.statuseffect.PlayerEffect;
import kamkeel.npcdbc.data.statuseffect.StatusEffect;
import net.minecraft.entity.player.EntityPlayer;

public class BrokenSpirit extends StatusEffect {
    public PlayerBonus weakenedSpirit;

    public BrokenSpirit() {
        name = "Broken Spirit";
        id = Effects.BROKEN_SPIRIT;
        icon = CustomNpcPlusDBC.ID + ":textures/gui/icons.png";
        iconX = 32;
        iconY = 32;
        length = ConfigDBCEffects.ZenkaiHALFLength;

        weakenedSpirit = new PlayerBonus("BrokenSpirit", (byte) 0, (float) ConfigDBCEffects.BrokenSpiritStr, (float) ConfigDBCEffects.BrokenSpiritDex, (float) ConfigDBCEffects.BrokenSpiritWil);
    }

    @Override
    public void onAdded(EntityPlayer player, PlayerEffect playerEffect){
        BonusController.getInstance().applyBonus(player, weakenedSpirit);
    }

    @Override
    public void onRemoved(EntityPlayer player, PlayerEffect playerEffect) {
        BonusController.getInstance().removeBonus(player, weakenedSpirit);

    }
}
