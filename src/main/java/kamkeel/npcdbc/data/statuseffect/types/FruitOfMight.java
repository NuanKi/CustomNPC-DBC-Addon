package kamkeel.npcdbc.data.statuseffect.types;

import kamkeel.npcdbc.CustomNpcPlusDBC;
import kamkeel.npcdbc.constants.Effects;
import kamkeel.npcdbc.data.PlayerDBCInfo;
import kamkeel.npcdbc.data.aura.Aura;
import kamkeel.npcdbc.data.dbcdata.DBCData;
import kamkeel.npcdbc.data.statuseffect.PlayerEffect;
import kamkeel.npcdbc.data.statuseffect.StatusEffect;
import kamkeel.npcdbc.util.PlayerDataUtil;
import net.minecraft.entity.player.EntityPlayer;

public class FruitOfMight extends StatusEffect {
    public static Aura fruitOfMightAura = null;
    public float kiToDrain = 1.0f;

    public FruitOfMight() {
        name = "FruitOfMight";
        id = Effects.FRUIT_OF_MIGHT;
        icon = CustomNpcPlusDBC.ID + ":textures/gui/statuseffects.png";
        iconX = 64;
        iconY = 0;

        if (fruitOfMightAura == null) {
            fruitOfMightAura = new Aura();
            fruitOfMightAura.id = -10;
            fruitOfMightAura.display.setColor("color1", 0x0); //black
            fruitOfMightAura.display.setColor("color3", 0xb329ba); //purple
            fruitOfMightAura.display.hasLightning = true;
            fruitOfMightAura.display.lightningColor = 0xb329ba; //purple
        }
    }

    public void init(EntityPlayer player, PlayerEffect playerEffect) {
        PlayerDBCInfo c = PlayerDataUtil.getDBCInfo(player);
        c.currentAura = fruitOfMightAura.id;
        c.updateClient();

    }

    @Override
    public void process(EntityPlayer player, PlayerEffect playerEffect) {
        DBCData dbcData = DBCData.get(player);
        dbcData.stats.restoreKiPercent(-kiToDrain);
        if (dbcData.Ki <= 0)
            playerEffect.kill();
    }

    @Override
    public void runout(EntityPlayer player, PlayerEffect playerEffect) {
        PlayerDBCInfo c = PlayerDataUtil.getDBCInfo(player);
        if (c.currentAura == fruitOfMightAura.id) {
            c.currentAura = -1;
            c.updateClient();
        }
    }
}
