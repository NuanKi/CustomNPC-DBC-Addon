package kamkeel.npcdbc.data.statuseffect.types;

import kamkeel.npcdbc.CustomNpcPlusDBC;
import kamkeel.npcdbc.constants.Effects;
import kamkeel.npcdbc.data.statuseffect.PlayerEffect;
import kamkeel.npcdbc.data.statuseffect.StatusEffect;
import net.minecraft.entity.player.EntityPlayer;

public class SnackBonus extends StatusEffect {

    public SnackBonus() {
        name = "Snack Potenciador";
        id = Effects.SNACK_BONUS;
        icon = CustomNpcPlusDBC.ID + ":textures/gui/icons.png";
        iconX = 16;
        iconY = 16;
    }

    @Override
    public void process(EntityPlayer player, PlayerEffect playerEffect){

    }
}
