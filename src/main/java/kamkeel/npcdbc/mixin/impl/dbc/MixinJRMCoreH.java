package kamkeel.npcdbc.mixin.impl.dbc;


import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.server.config.dbc.JGConfigUltraInstinct;
import kamkeel.npcdbc.constants.DBCForm;
import kamkeel.npcdbc.data.DBCData;
import kamkeel.npcdbc.data.PlayerFormData;
import kamkeel.npcdbc.data.form.Form;
import kamkeel.npcdbc.data.form.FormMastery;
import kamkeel.npcdbc.util.Utility;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static JinRyuu.JRMCore.JRMCoreH.*;
import static kamkeel.npcdbc.util.DBCUtils.lastSetDamage;

@Mixin(value = JRMCoreH.class, remap = false)
public abstract class MixinJRMCoreH {


    @Shadow
    public static int curBody;

    @Inject(method = "getPlayerAttribute(Lnet/minecraft/entity/player/EntityPlayer;[IIIIILjava/lang/String;IIZZZZZZI[Ljava/lang/String;ZLjava/lang/String;)I", at = @At("HEAD"), remap = false, cancellable = true)
    private static void onGetPlayerAttribute(EntityPlayer player, int[] currAttributes, int attribute, int st, int st2, int race, String SklX, int currRelease, int arcRel, boolean legendOn, boolean majinOn, boolean kaiokenOn, boolean mysticOn, boolean uiOn, boolean GoDOn, int powerType, String[] Skls, boolean isFused, String majinAbs, CallbackInfoReturnable<Integer> info) {
        {
            Form form = null;
            float currentFormLevel = 0f;
            if (player == null)
                return;

            if (Utility.isServer()) {
                PlayerFormData formData = Utility.getFormData(player);
                if (formData != null && formData.isInCustomForm()) {
                    currentFormLevel = formData.getCurrentLevel();
                    form = formData.getCurrentForm();
                }
            } else {
                form = Utility.getFormClient(player);
                currentFormLevel = Utility.getFormLevelClient((AbstractClientPlayer) player);
            }

            if (form != null) {
                int skillX = powerType == 1 ? JRMCoreH.SklLvlX(1, SklX) - 1 : 0;
                int mysticLvl = powerType == 1 ? JRMCoreH.SklLvl(10, 1, Skls) : 0;
                int result = 0;
                mysticOn = false;
                uiOn = false;
                GoDOn = false;
                switch (race) {
                    case 0:
                        result = JRMCoreH.getAttributeHuman(player, currAttributes, attribute, 0, skillX, mysticOn, mysticLvl, isFused, uiOn, powerType, GoDOn);
                        break;
                    case 1:
                        result = JRMCoreH.getAttributeSaiyan(player, currAttributes, attribute, 0, skillX, mysticOn, mysticLvl, isFused, uiOn, powerType, GoDOn);
                        break;
                    case 2:
                        result = JRMCoreH.getAttributeHalfSaiyan(player, currAttributes, attribute, 0, skillX, mysticOn, mysticLvl, isFused, uiOn, powerType, GoDOn);
                        break;
                    case 3:
                        result = JRMCoreH.getAttributeNamekian(player, currAttributes, attribute, 0, skillX, mysticOn, mysticLvl, isFused, uiOn, powerType, GoDOn);
                        break;
                    case 4:
                        result = JRMCoreH.getAttributeArcosian(player, currAttributes, attribute, 0, currRelease, arcRel, skillX, mysticOn, mysticLvl, isFused, uiOn, powerType, GoDOn);
                        break;
                    case 5:
                        result = JRMCoreH.getAttributeMajin(player, currAttributes, attribute, 0, skillX, mysticOn, mysticLvl, isFused, uiOn, powerType, GoDOn, majinAbs);
                        break;
                    default:
                        result = currAttributes[attribute];
                }

                DBCData d = DBCData.get(player);
                float[] multis = form.getAllMulti();
                float stackableMulti = d.isForm(DBCForm.Kaioken) ? form.getFormMulti(DBCForm.Kaioken) : d.isForm(DBCForm.UltraInstinct) ? form.getFormMulti(DBCForm.UltraInstinct) : d.isForm(DBCForm.GodOfDestruction) ? form.getFormMulti(DBCForm.GodOfDestruction) : d.isForm(DBCForm.Mystic) ? form.getFormMulti(DBCForm.Mystic) : 1.0f;
                double fmvalue = 1.0f;

                //don't forget to multiply this by legend/divine/majin multis
                if (d.isForm(DBCForm.Kaioken) && d.State2 > 1) {
                    fmvalue = JRMCoreH.getFormMasteryAttributeMulti(player, "Kaioken", st, st2, race, kaiokenOn, mysticOn, uiOn, GoDOn);
                    stackableMulti += stackableMulti * form.getState2Factor(DBCForm.Kaioken) * d.State2 / (JRMCoreH.TransKaiDmg.length - 1);
                } else if (d.isForm(DBCForm.UltraInstinct) && d.State2 > 1) {
                    fmvalue = JRMCoreH.getFormMasteryAttributeMulti(player, "UltraInstict", st, st2, race, kaiokenOn, mysticOn, uiOn, GoDOn);
                    stackableMulti += stackableMulti * form.getState2Factor(DBCForm.UltraInstinct) * d.State2 / JGConfigUltraInstinct.CONFIG_UI_LEVELS;
                } else if (d.isForm(DBCForm.GodOfDestruction))
                    fmvalue = JRMCoreH.getFormMasteryAttributeMulti(player, "GodOfDestruction", st, st2, race, kaiokenOn, mysticOn, uiOn, GoDOn);
                else if (d.isForm(DBCForm.Mystic))
                    fmvalue = JRMCoreH.getFormMasteryAttributeMulti(player, "Mystic", st, st2, race, kaiokenOn, mysticOn, uiOn, GoDOn);


                stackableMulti *= fmvalue;
                if (attribute == 0) //str
                    result *= multis[0];
                else if (attribute == 1) //dex
                    result *= multis[1];
                else if (attribute == 3) //will
                    result *= multis[2];

                if (attribute == 0 || attribute == 1 || attribute == 3)
                    result *= (int) (stackableMulti * ((FormMastery) form.getMastery()).calculateMulti("attribute", currentFormLevel));

                result = (int) (Math.min((double) result, Double.MAX_VALUE));
                info.setReturnValue(result);
            }
        }
    }

    // fix for descending from a CF sets release to 0 as game registers you as base in a CF
    @Inject(method = "Rls", at = @At("HEAD"), cancellable = true)
    private static void fix0ReleaseOnCFDescend(byte b, CallbackInfo ci) {
        if (b == 0) {
            PlayerFormData formData = Utility.getSelfData();
            if (formData != null && formData.isInCustomForm())
                ci.cancel();
        }
    }

    //delete all player CF data on jrmc startnew
    @Inject(method = "resetChar(Lnet/minecraft/entity/player/EntityPlayer;ZZZF)V", at = @At("TAIL"))
    private static void resetChar(EntityPlayer p, boolean keepSkills, boolean keepTechs, boolean keepMasteries, float perc, CallbackInfo ci) {
        Utility.getFormData(p).resetAll();
        if (!keepMasteries) {
            NBTTagCompound PlayerPersisted = nbt(p);
            for (int i = 0; i < Races.length; i++)
                if (PlayerPersisted.hasKey(getNBTFormMasteryRacialKey(i))) //remove all form mastery tags that are not player's race
                    PlayerPersisted.removeTag(getNBTFormMasteryRacialKey(i));

            if (PlayerPersisted.hasKey("jrmcFormMasteryNonRacial"))
                PlayerPersisted.removeTag("jrmcFormMasteryNonRacial");

        }
    }

    //if release becomes 0%, force descend player from CF on server side
    @Inject(method = "setByte(ILnet/minecraft/entity/player/EntityPlayer;Ljava/lang/String;)V", at = @At("HEAD"), cancellable = true)
    private static void descendOn0Release(int s, EntityPlayer Player, String string, CallbackInfo ci) {
        if (s == 0 && string.equals("jrmcRelease")) {
            PlayerFormData formData = Utility.getFormData(Player);
            Form form = Utility.getCurrentForm(Player);
            if (form != null) {
                formData.currentForm = -1;
                formData.updateClient();
            }
        }
    }

    //if ki becomes 0, force descend player from CF on server side
    @Inject(method = "setInt(ILnet/minecraft/entity/player/EntityPlayer;Ljava/lang/String;)V", at = @At("HEAD"), cancellable = true)
    private static void descendOn0Ki(int s, EntityPlayer Player, String string, CallbackInfo ci) {
        if (s == 0 && string.equals("jrmcEnrgy")) {
            PlayerFormData formData = Utility.getFormData(Player);
            Form form = Utility.getCurrentForm(Player);
            if (form != null) {
                formData.currentForm = -1;
                formData.updateClient();
            }
        }
    }

    @Inject(method = "addToFormMasteriesValue(Lnet/minecraft/entity/player/EntityPlayer;DDIIIZZZZI)V", at = @At("HEAD"), cancellable = true)
    private static void addFormMasteries(EntityPlayer player, double value, double valueKK, int race, int state, int state2, boolean isKaiokenOn, boolean isMysticOn, boolean isUltraInstinctOn, boolean isGoDOn, int gainMultiID, CallbackInfo ci) {
        if (gainMultiID == 0)
            Utility.getFormData(player).updateCurrentFormMastery("update");
        else if (gainMultiID == 1)
            Utility.getFormData(player).updateCurrentFormMastery("attack");
        else if (gainMultiID == 2)
            Utility.getFormData(player).updateCurrentFormMastery("damaged");
        else if (gainMultiID == 3)
            Utility.getFormData(player).updateCurrentFormMastery("fireki");

    }

    @ModifyArgs(method = "jrmcDam(Lnet/minecraft/entity/Entity;ILnet/minecraft/util/DamageSource;)I", at = @At(value = "INVOKE", target = "LJinRyuu/JRMCore/JRMCoreH;setInt(ILnet/minecraft/entity/player/EntityPlayer;Ljava/lang/String;)V"))
    private static void setDamage(Args args) {
        String type = args.get(2);
        if (lastSetDamage != -1 && type.equals("jrmcBdy")) {
            int curBody = getInt(args.get(1), "jrmcBdy");
            int newHealth = curBody - lastSetDamage;
            args.set(0, Math.max(0, newHealth));
            lastSetDamage = -1;
        }
    }
}

