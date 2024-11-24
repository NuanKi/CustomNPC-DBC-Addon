package kamkeel.npcdbc.data.statuseffect.types;

import kamkeel.npcdbc.CustomNpcPlusDBC;
import kamkeel.npcdbc.config.ConfigDBCEffects;
import kamkeel.npcdbc.constants.Effects;
import kamkeel.npcdbc.controllers.BonusController;
import kamkeel.npcdbc.data.PlayerBonus;
import kamkeel.npcdbc.data.dbcdata.DBCData;
import kamkeel.npcdbc.data.statuseffect.PlayerEffect;
import kamkeel.npcdbc.data.statuseffect.StatusEffect;
import net.minecraft.entity.player.EntityPlayer;

public class DeathEffect extends StatusEffect {
    private static final String bonusName = "RupturaDeAlma";

    public DeathEffect() {
        name = "Ruptura de Alma";
        id = Effects.DEATH_PENALTY;
        icon = CustomNpcPlusDBC.ID + ":textures/gui/icons.png";
        iconX = 0;
        iconY = 16;
    }

    @Override
    public void init(EntityPlayer player, PlayerEffect playerEffect){
        DBCData dbcData = DBCData.get(player);
        PlayerBonus deathBonus = new PlayerBonus(bonusName, (byte) 1);
        if (ConfigDBCEffects.DeathPenaltyStr) {
            deathBonus.strength = -dbcData.STR * ((float) ConfigDBCEffects.DeathPenaltyPercent / 200);
        }
        if (ConfigDBCEffects.DeathPenaltyDex) {
            deathBonus.dexterity = -dbcData.DEX * ((float) ConfigDBCEffects.DeathPenaltyPercent / 200);
        }
        if (ConfigDBCEffects.DeathPenaltyCon) {
            deathBonus.constituion = -dbcData.CON * ((float) ConfigDBCEffects.DeathPenaltyPercent / 100);
        }
        if (ConfigDBCEffects.DeathPenaltyWil) {
            deathBonus.willpower = -dbcData.WIL * ((float) ConfigDBCEffects.DeathPenaltyPercent / 200);
        }
        if (ConfigDBCEffects.DeathPenaltySpi) {
            deathBonus.spirit = -dbcData.SPI * ((float) ConfigDBCEffects.DeathPenaltyPercent / 100);
        }
        BonusController.getInstance().applyBonus(player, deathBonus);
    }

    @Override
    public void kill(EntityPlayer player, PlayerEffect playerEffect) {
        BonusController.getInstance().removeBonus(player, bonusName);
    }
}
