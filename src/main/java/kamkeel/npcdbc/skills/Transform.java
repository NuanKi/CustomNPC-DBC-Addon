package kamkeel.npcdbc.skills;

import JinRyuu.DragonBC.common.DBCKiTech;
import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kamkeel.npcdbc.constants.DBCForm;
import kamkeel.npcdbc.data.CustomForm;
import kamkeel.npcdbc.data.PlayerCustomFormData;
import kamkeel.npcdbc.data.SyncedData.DBCData;
import kamkeel.npcdbc.network.PacketRegistry;
import kamkeel.npcdbc.util.Utility;
import net.minecraft.entity.player.EntityPlayerMP;

public class Transform {

    public static int s, id, time, releaseTime, soundTime;
    public static boolean ascending, cantTransform, transformed;
    public static String ascendSound, descendSound;
    public static float rage, rageValue;

    //////////////////////////////////////////////////
    //////////////////////////////////////////////////
    // Client  side handling

    //WIP, only 85% done, but is functional and won't break
    @SideOnly(Side.CLIENT)
    public static void Ascend(CustomForm form) {
        if (cantTransform || (rage > 0 && transformed) || (Utility.getFormDataClient().getCurrentForm() != null && Utility.getFormDataClient().getCurrentForm().getID() == form.id))
            return;

        time++;
        releaseTime++;
        soundTime++;
        Transform.setAscending(true);
        rageValue = getRageMeterIncrementation();

        if (soundTime == 1 || soundTime >= 33) { //plays aura sound every 33 ticks
            if (soundTime != 1)
                soundTime = 0;
            String ar = "jinryuudragonbc:DBC.aura";
            DBCKiTech.soundAsc(ar);
        }
        if (time >= 6) { //increments rage meter and drain ki cost every 6 ticks
            time = 0;
            int cost = JRMCoreH.maxEnergy / 20;
            if (JRMCoreH.curEnergy < cost)
                return;
            JRMCoreH.Cost(cost);
            rage += rageValue;
        }
        if (JRMCoreH.curRelease < 50 && releaseTime >= 10) { //if release is less than 50%, increment it until it is so
            float en = 100.0F / JRMCoreH.maxEnergy * JRMCoreH.curEnergy;
            float re = JRMCoreH.curRelease;
            en = en > 75.0F ? 75.0F : en;
            if (re >= 50.0F ? (re - 50.0F < (en - 50.0F) * 2.0F) : (re < ((en <= 10.0F) ? (en * 5.0F) : 50.0F)))
                JRMCoreH.Rls((byte) 1);
            releaseTime = 0;

        }
        if (rage >= 100) { //transform when rage meter reaches 100 (max)
            PacketRegistry.tellServer("Transform:" + form.getID());
            DBCKiTech.soundAsc(form.getAscendSound());
            resetTimers();
            cantTransform = true;
            transformed = true;

        }

    }

    @SideOnly(Side.CLIENT)
    public static void decrementRage() {
        if (rage > 0) {
            if (rage > 100)
                rage = 100;
            if (rage - (rageValue / 12) >= 0)
                rage -= (rageValue / 12);
            else
                rage = 0;
            if (rage <= 50 && JRMCoreH.StusEfctsMe(1))
                JRMCoreH.Skll((byte) 5, (byte) 1, (byte) 1);
        }
        if (rage == 0 && (JRMCoreH.StusEfctsMe(1) || cantTransform || transformed || ascending))
            setAscending(false);

    }

    @SideOnly(Side.CLIENT)
    public static void setAscending(boolean bo) {
        ascending = bo;
        JRMCoreH.Skll((byte) 5, bo ? (byte) 0 : 1, (byte) 1);
        if (!bo) {
            cantTransform = false;
            transformed = false;
        }
    }

    @SideOnly(Side.CLIENT)
    public static void resetTimers() {
        time = 0;
        releaseTime = 0;
        soundTime = 0;
    }

    @SideOnly(Side.CLIENT)
    public static int getRageMeterIncrementation() {
        double fm = 10;//getFormMasteryValue(k);
        double maxfm = 100;//getMaxFormMasteryValue(k);

        if (Utility.percentBetween(fm, maxfm, 0, 5))
            return 3;
        else if (Utility.percentBetween(fm, maxfm, 5, 10))
            return 6;
        else if (Utility.percentBetween(fm, maxfm, 10, 20))
            return 10;
        else if (Utility.percentBetween(fm, maxfm, 20, 30))
            return 15;
        else if (Utility.percentBetween(fm, maxfm, 30, 40))
            return 20;
        else if (Utility.percentBetween(fm, maxfm, 40, 50))
            return 25;
        else if (Utility.percentBetween(fm, maxfm, 50, 60))
            return 30;
        else if (Utility.percentBetween(fm, maxfm, 60, 70))
            return 35;
        else if (Utility.percentBetween(fm, maxfm, 70, 80))
            return 40;
        else if (Utility.percentBetween(fm, maxfm, 80, 90))
            return 45;
        else if (Utility.percentBetween(fm, maxfm, 90, 100))
            return 50;
        else if (fm >= maxfm)
            return 100;
        return 0;
    }

    //////////////////////////////////////////////////
    //////////////////////////////////////////////////
    // Server side handling

    public static void handleCustomFormAscend(EntityPlayerMP p, int formID) {
        PlayerCustomFormData formData = Utility.getFormData(p);
        if (formData.currentForm != formID) {
            DBCData dbcData = DBCData.get(p);
            formData.currentForm = formID;
            if (dbcData.State > 0)
                dbcData.State = 0;
            formData.updateClient();
            dbcData.saveToNBT(true);
            Utility.sendMessage(p, "§aTransformed to§r " + formData.getCurrentForm().getMenuName());
        }
    }

    public static void handleDescend(EntityPlayerMP p) {
        DBCData dbcData = DBCData.get(p);
        PlayerCustomFormData formData = Utility.getFormData(p);
        if (dbcData.isForm(DBCForm.Kaioken) && dbcData.formSettingOn(DBCForm.Kaioken)) {
            if (dbcData.State2 - 1 > 0)
                dbcData.State2--;
            else if (dbcData.State2 - 1 == 0) {
                dbcData.State2--;
                dbcData.setForm(DBCForm.Kaioken, false);
            }
        } else if (dbcData.isForm(DBCForm.UltraInstinct) && dbcData.formSettingOn(DBCForm.UltraInstinct)) {
            if (dbcData.State2 - 1 > 0)
                dbcData.State2--;
            else if (dbcData.State2 - 1 == 0) {
                dbcData.State2--;
                dbcData.setForm(DBCForm.UltraInstinct, false);
            }
        } else if (dbcData.isForm(DBCForm.Mystic) && dbcData.formSettingOn(DBCForm.Mystic))
            dbcData.setForm(DBCForm.Mystic, false);
        else if (dbcData.isForm(DBCForm.GodOfDestruction) && dbcData.formSettingOn(DBCForm.GodOfDestruction))
            dbcData.setForm(DBCForm.GodOfDestruction, false);

        else if (formData.isInCustomForm()) {
            Utility.sendMessage(p, "§cDescended from§r " + formData.getCurrentForm().getMenuName());
            formData.currentForm = 0;
            formData.updateClient();
        }
        dbcData.saveToNBT(true);

    }
}
