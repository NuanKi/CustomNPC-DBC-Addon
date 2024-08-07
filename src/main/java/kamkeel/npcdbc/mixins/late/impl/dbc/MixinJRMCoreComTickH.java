package kamkeel.npcdbc.mixins.late.impl.dbc;

import JinRyuu.JRMCore.JRMCoreComTickH;
import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.server.JGPlayerMP;
import com.llamalad7.mixinextras.sugar.Local;
import kamkeel.npcdbc.CommonProxy;
import kamkeel.npcdbc.data.dbcdata.DBCData;
import kamkeel.npcdbc.data.form.Form;
import kamkeel.npcdbc.data.form.FormStackable;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import noppes.npcs.LogWriter;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = JRMCoreComTickH.class, remap = false)
public abstract class MixinJRMCoreComTickH {

    @Shadow
    public static MinecraftServer server;
    @Shadow
    public static boolean start;

    @Unique
    private static int[] customNPC_DBC_Addon$emptyPowerPointGrowthArray;
    @Unique
    private static int[] customNPC_DBC_Addon$emptyPowerPointCostArray;

    @Inject(method = "serverStart", at=@At("HEAD"))
    public void initializeEmptyArrays(MinecraftServer server, CallbackInfo ci){
        if(start){
            customNPC_DBC_Addon$emptyPowerPointGrowthArray = new int[JRMCoreConfig.ArcosianPPGrowth.length];
            customNPC_DBC_Addon$emptyPowerPointCostArray = new int[JRMCoreConfig.ArcosianPPCost.length];
        }
    }

    @Inject(method = "updatePlayersData", at = @At(value = "INVOKE", target = "LJinRyuu/JRMCore/JRMCoreHDBC;DBCsizeBasedOnRace(IIZ)F", shift = At.Shift.BEFORE))
    public void setCurrentTickPlayerServer(MinecraftServer server, int playerID, EntityPlayerMP player, JGPlayerMP jgPlayer, NBTTagCompound nbt, CallbackInfo ci) {
        CommonProxy.CurrentJRMCTickPlayer = player;
    }

    @Redirect(method = "serverTick", at = @At(value = "INVOKE", target = "LJinRyuu/JRMCore/JRMCoreComTickH;updatePlayersData(Lnet/minecraft/server/MinecraftServer;ILnet/minecraft/entity/player/EntityPlayerMP;LJinRyuu/JRMCore/server/JGPlayerMP;Lnet/minecraft/nbt/NBTTagCompound;)V"))
    public void tryCatchPlayerData(JRMCoreComTickH instance, MinecraftServer server, int chunkcoordinates, EntityPlayerMP A, JGPlayerMP divine, NBTTagCompound isp2,
                                   @Local(name = "playerID") int playerID, @Local(name = "player") EntityPlayerMP player, @Local(name = "jgPlayer") JGPlayerMP jgPlayer,
                                   @Local(name = "nbt") NBTTagCompound nbtTagCompound) {
        try {
            this.updatePlayersData(server, playerID, player, jgPlayer, nbtTagCompound);
        }
        catch (NullPointerException ok){
            LogWriter.except(ok);
        }
    }

    @Redirect(method = "serverTick", at = @At(value = "FIELD", target="LJinRyuu/JRMCore/JRMCoreConfig;ArcosianPPGrowth:[I", ordinal = 0, opcode = Opcodes.GETSTATIC))
    public int[] stopNormalPPRegenOnCustoms(@Local(name = "player") EntityPlayerMP player){
        DBCData dbcData = DBCData.get(player);
        if(dbcData == null)
            return JRMCoreConfig.ArcosianPPGrowth;
        Form form = dbcData.getForm();
        if(form == null || (!form.stackable.racialBonusesOn && form.stackable.vanillaStackable))
            return JRMCoreConfig.ArcosianPPGrowth;

        if(form.stackable.powerPointCost < 0 || !form.stackable.vanillaStackable){
            return customNPC_DBC_Addon$emptyPowerPointGrowthArray;
        }
        return JRMCoreConfig.ArcosianPPGrowth;
    }
    @Redirect(method = "serverTick", at = @At(value = "FIELD", target="LJinRyuu/JRMCore/JRMCoreConfig;ArcosianPPCost:[I", ordinal = 0, opcode = Opcodes.GETSTATIC))
    public int[] stopNormalPPCost(@Local(name = "player") EntityPlayerMP player){
        DBCData dbcData = DBCData.get(player);
        if(dbcData == null)
            return JRMCoreConfig.ArcosianPPCost;

        Form form = dbcData.getForm();
        if(form == null || (!form.stackable.racialBonusesOn && form.stackable.vanillaStackable))
            return JRMCoreConfig.ArcosianPPCost;

        if(form.stackable.powerPointCost > 0 || !form.stackable.vanillaStackable){

            return customNPC_DBC_Addon$emptyPowerPointCostArray;
        }

        return JRMCoreConfig.ArcosianPPCost;
    }

    @Inject(method = "serverTick", at = @At(value = "INVOKE", target="LJinRyuu/JRMCore/JRMCoreH;getArcosianFormID(IZZZ)I", shift = At.Shift.AFTER))
    public void customFormPowerPoints(MinecraftServer server, CallbackInfo ci, @Local(name = "player") EntityPlayerMP player){
        DBCData dbcData = DBCData.get(player);
        if(dbcData == null)
            return;
        Form form = dbcData.getForm();
        if(form == null)
            return;

        FormStackable stackable = form.stackable;

        if(!stackable.racialBonusesOn)
            return;

        if(stackable.powerPointCost > 0 && dbcData.Release >= 100){
            updatePowerPointCost(dbcData, form);
        }else if(stackable.powerPointCost < 0 && dbcData.Release < 50){
            updatePowerPointRegen(dbcData, form);
        }

    }

    private void updatePowerPointRegen(DBCData dbcData, Form form) {

    }

    private void updatePowerPointCost(DBCData dbcData, Form form) {

    }

    @Shadow
    public void updatePlayersData(MinecraftServer server, int playerID, EntityPlayerMP player, JGPlayerMP jgPlayer, NBTTagCompound nbt){}
}
