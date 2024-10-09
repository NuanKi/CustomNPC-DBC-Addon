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

public class HumanSpirit extends StatusEffect {

    public HumanSpirit() {
        name = "HumanSpirit";
        id = Effects.HUMAN_SPIRIT;
        icon = CustomNpcPlusDBC.ID + ":textures/gui/icons.png";
        iconX = 224;
        iconY = 0;
        length = ConfigDBCEffects.HumanSpiritLength;
    }

    @Override
    public void init(EntityPlayer player, PlayerEffect playerEffect){
        DBCData dbcData = DBCData.get(player);
        PlayerBonus humanSpiritBonus = new PlayerBonus(name, (byte) 1);
        humanSpiritBonus.constituion = dbcData.CON * ((float) ConfigDBCEffects.HumanSpiritConBoostPercent / 100);
        humanSpiritBonus.dexterity = dbcData.DEX * ((float) ConfigDBCEffects.HumanSpiritDexBoostPercent / 100);
        BonusController.getInstance().applyBonus(player, humanSpiritBonus);
    }

    @Override
    public void kill(EntityPlayer player, PlayerEffect playerEffect) {
        BonusController.getInstance().removeBonus(player, name);
    }
}
