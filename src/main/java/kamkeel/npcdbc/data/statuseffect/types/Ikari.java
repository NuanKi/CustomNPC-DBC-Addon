package kamkeel.npcdbc.data.statuseffect.types;

import kamkeel.npcdbc.CustomNpcPlusDBC;
import kamkeel.npcdbc.constants.DBCRace;
import kamkeel.npcdbc.constants.Effects;
import kamkeel.npcdbc.data.PlayerDBCInfo;
import kamkeel.npcdbc.data.statuseffect.PlayerEffect;
import kamkeel.npcdbc.data.statuseffect.StatusEffect;
import kamkeel.npcdbc.scripted.DBCAPI;
import kamkeel.npcdbc.util.PlayerDataUtil;
import net.minecraft.entity.player.EntityPlayer;
import kamkeel.npcdbc.data.dbcdata.DBCData;
import kamkeel.npcdbc.data.form.Form;

public class Ikari extends StatusEffect {
    private float kiToDrain;
    private static final String FORM_NAME = "Ikari";

    public Ikari() {
        name = "Ikari";
        id = Effects.IKARI;
        icon = CustomNpcPlusDBC.ID + ":textures/gui/icons.png";
        iconX = 64;
        iconY = 0;
        kiToDrain = (float) -0.3;
    }

    @Override
    public void onAdded(EntityPlayer player, PlayerEffect playerEffect) {
        DBCData dbcData = DBCData.get(player);
        PlayerDBCInfo dbcInfo = PlayerDataUtil.getDBCInfo(player);
        Form form = (Form) DBCAPI.Instance().getForm(FORM_NAME);

        if (dbcData == null || dbcInfo == null || form == null) {
            playerEffect.kill();
            return;
        }

        if (dbcData.Race == DBCRace.SAIYAN || dbcData.Race == DBCRace.HALFSAIYAN) {
            if (dbcData.getState() == 0) {
                dbcInfo.currentForm = form.id;
                dbcInfo.updateClient();
            }
        } else {
            playerEffect.kill();
        }
    }

    @Override
    public void onTick(EntityPlayer player, PlayerEffect playerEffect) {
        DBCData dbcData = DBCData.get(player);
        PlayerDBCInfo dbcInfo = PlayerDataUtil.getDBCInfo(player);
        Form form = (Form) DBCAPI.Instance().getForm(FORM_NAME);

        if (dbcData == null || dbcInfo == null || form == null) {
            playerEffect.kill();
            return;
        }

        if (dbcData.Race == DBCRace.SAIYAN || dbcData.Race == DBCRace.HALFSAIYAN) {
            if (dbcData.getState() == 0) {
                dbcData.stats.restoreKiPercent(kiToDrain);
                if (dbcInfo.currentForm != form.id) {
                    dbcInfo.currentForm = form.id;
                }
            } else {
                if (dbcInfo.currentForm != -1) {
                    dbcInfo.currentForm = -1;
                    dbcInfo.updateClient();
                }
            }
        }
    }

    @Override
    public void onRemoved(EntityPlayer player, PlayerEffect playerEffect) {
        DBCData dbcData = DBCData.get(player);
        PlayerDBCInfo dbcInfo = PlayerDataUtil.getDBCInfo(player);

        if (dbcData == null || dbcInfo == null) {
            return;
        }

        if (dbcData.Race == DBCRace.SAIYAN || dbcData.Race == DBCRace.HALFSAIYAN) {
            dbcInfo.currentForm = -1;
            dbcInfo.updateClient();
        }
    }
}
