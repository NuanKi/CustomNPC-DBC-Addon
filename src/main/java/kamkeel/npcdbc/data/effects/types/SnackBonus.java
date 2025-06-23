package kamkeel.npcdbc.data.effects.types;

import kamkeel.npcdbc.CustomNpcPlusDBC;
import kamkeel.npcdbc.constants.Effects;
import kamkeel.npcdbc.data.effects.AddonEffect;

public class SnackBonus extends AddonEffect {

    public SnackBonus() {
        name = "Snack Bonus";
        langName = "effect.snackbonus";
        id = Effects.SNACK_BONUS;
        icon = CustomNpcPlusDBC.ID + ":textures/gui/icons.png";
        iconX = 16;
        iconY = 16;
    }
}
