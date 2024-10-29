package kamkeel.npcdbc.data.statuseffect.types;

import JinRyuu.JRMCore.JRMCoreHDBC;
import kamkeel.npcdbc.CustomNpcPlusDBC;
import kamkeel.npcdbc.config.ConfigDBCEffects;
import kamkeel.npcdbc.constants.Effects;
import kamkeel.npcdbc.data.dbcdata.DBCData;
import kamkeel.npcdbc.data.statuseffect.PlayerEffect;
import kamkeel.npcdbc.data.statuseffect.StatusEffect;
import net.minecraft.entity.player.EntityPlayer;

public class RegenOW extends StatusEffect {

    public RegenOW() {
        name = "Otherworldly Restoration";
        id = Effects.OW_REGEN;
        icon = CustomNpcPlusDBC.ID + ":textures/gui/icons.png";
        iconX = 0;
        iconY = 32;
    }

    @Override
    public void onTick(EntityPlayer player, PlayerEffect playerEffect) {
        DBCData dbcData = DBCData.get(player);
        int percentToRegen = ConfigDBCEffects.HealthRegenPercent;
        if (!JRMCoreHDBC.isAlive(player)) {
            if (dbcData.Body > 0)
                dbcData.stats.restoreHealthPercent(percentToRegen);
            if (dbcData.Ki > 0)
                dbcData.stats.restoreKiPercent(percentToRegen);
            if (dbcData.Stamina > 0)
                dbcData.stats.restoreStaminaPercent(percentToRegen);
        } else {
            playerEffect.kill();
        }
    }
}
