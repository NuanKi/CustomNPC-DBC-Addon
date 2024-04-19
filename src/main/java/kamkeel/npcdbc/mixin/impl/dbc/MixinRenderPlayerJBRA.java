package kamkeel.npcdbc.mixin.impl.dbc;

import JinRyuu.JBRA.ModelBipedDBC;
import JinRyuu.JBRA.RenderPlayerJBRA;
import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreH;
import com.llamalad7.mixinextras.sugar.Local;
import kamkeel.npcdbc.CustomNpcPlusDBC;
import kamkeel.npcdbc.data.CustomForm;
import kamkeel.npcdbc.data.PlayerCustomFormData;
import kamkeel.npcdbc.data.SyncedData.DBCData;
import kamkeel.npcdbc.util.DBCUtils;
import kamkeel.npcdbc.util.Utility;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = RenderPlayerJBRA.class, remap = false)
public abstract class MixinRenderPlayerJBRA extends RenderPlayer {
    @Shadow
    ModelBipedDBC modelMain;

    @Unique
    int id;

    @Unique
    int faceM;
    @Unique
    float currentBodyPerc;

    @Unique
    int playerID;

    @Inject(method = "renderEquippedItemsJBRA", at = @At(value = "FIELD", target = "LJinRyuu/JRMCore/JRMCoreConfig;HHWHO:Z", ordinal = 1, shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void renderSSJ4(AbstractClientPlayer par1AbstractClientPlayer, float par2, CallbackInfo ci, @Local(ordinal = 0) int pl) {
        if (Utility.getFormDataClient().isInCustomForm()) {
            DBCData dbcData = DBCData.getClient();
            if (pl != DBCUtils.getPlayerID(dbcData.player))
                ci.cancel();
            PlayerCustomFormData formData = Utility.getFormDataClient(par1AbstractClientPlayer);
            CustomForm f = formData.getCurrentForm();
            dbcData = DBCData.getClient();
            int playerBodyCM = JRMCoreH.dnsBodyCM(dbcData.DNS);
            if (f.hairType.equals("ssj4")) {
                renderSSJ4Face(f.hairColor, f.furColor, f.eyeColor, playerBodyCM);
                renderSSJ4Hair(f.hairColor, f.hairCode, dbcData.Race, DBCUtils.getPlayerID(dbcData.player));
                renderSSJ4Fur(f.furColor);
            } else if (f.hairType.equals("oozaru")) {
                renderOozaru(f.bodyCM, f.furColor, formData.parent.player);
            }
        }

    }

    @Unique
    private void renderSSJ4Face(int hairColor, int furColor, int eyeColor, int bodyCM) {
        GL11.glColor3f(1.0F + getR(), 1.0F + getG(), 1.0F + getB());
        this.bindTexture(new ResourceLocation(CustomNpcPlusDBC.ID + ":textures/faces/ss4/ssj4eyewhite.png"));
        this.modelMain.renderBody(1F / 16F);
        RenderPlayerJBRA.glColor3f(eyeColor);
        this.bindTexture(new ResourceLocation(CustomNpcPlusDBC.ID + ":textures/faces/ss4/ssj4pupils.png"));
        this.modelMain.renderBody(0.0625F);
        RenderPlayerJBRA.glColor3f(furColor);
        this.bindTexture(new ResourceLocation(CustomNpcPlusDBC.ID + ":textures/faces/ss4/ssj4brows.png"));
        this.modelMain.renderBody(1F / 16F);
        RenderPlayerJBRA.glColor3f(hairColor);
        this.bindTexture(new ResourceLocation(CustomNpcPlusDBC.ID + ":textures/faces/ss4/ssj4brows2.png"));
        this.modelMain.renderBody(1F / 16F);
        RenderPlayerJBRA.glColor3f(bodyCM);
        this.bindTexture(new ResourceLocation(CustomNpcPlusDBC.ID + ":textures/faces/ss4/ssj4mouth" + getSSJ4Mouth() + ".png"));
        this.modelMain.renderBody(1F / 16F);
        this.bindTexture(new ResourceLocation(CustomNpcPlusDBC.ID + ":textures/faces/ss4/ssj4shade.png"));
        this.modelMain.renderBody(0.0625F);
    }

    @Unique
    private void renderSSJ4Hair(int hairCol, String hairCode, int race, int pl) {
        RenderPlayerJBRA.glColor3f(hairCol);
        this.bindTexture(new ResourceLocation("jinryuumodscore:gui/superall.png"));
        hairCode = !hairCode.isEmpty() ? hairCode : "373852546750347428545480193462285654801934283647478050340147507467501848505072675018255250726750183760656580501822475071675018255050716750189730327158501802475071675018973225673850189765616160501820414547655019545654216550195754542165501920475027655019943669346576193161503065231900475030655019406534276538199465393460501997654138655019976345453950189760494941501897615252415018976354563850189763494736501897614949395018976152523950189763525234501897584749395018976150493850189760545234501897585250415018885445474550189754475041501897545250435018885454523950185143607861501897415874585018514369196150185147768078391865525680565018974356806150188843567861501868396374615018975056805650189750568056501885582374615018975823726150187149568054501877495680565018774950785650189163236961501820";
        this.modelMain.renderHairsV2(0.0625F, hairCode, 0.0F, 0, 0, pl, race, (RenderPlayerJBRA) (Object) this);
    }

    @Unique
    private void renderSSJ4Fur(int furColor) {
        this.bindTexture(new ResourceLocation("jinryuudragonbc:cc/ss4b.png"));
        RenderPlayerJBRA.glColor3f(furColor);
        this.modelMain.renderBody(0.0625F);
        this.bindTexture(new ResourceLocation("jinryuudragonbc:cc/ss4b.png"));
        this.modelMain.renderBody(0.0625F);


    }

    @Unique
    private int getSSJ4Mouth() {
        if (faceM == 3 || faceM == 4)
            if (currentBodyPerc > 60)
                return 0;
            else if (currentBodyPerc < 70 && currentBodyPerc > 50)
                return 1;
            else
                return 2;
        else
            return 2;

    }

    @Unique
    private void renderSSJ4Arm(int furColor, EntityPlayer p) {
        JRMCoreClient.mc.getTextureManager().bindTexture(new ResourceLocation("jinryuudragonbc:cc/ss4b.png"));
        RenderPlayerJBRA.glColor3f(furColor);
        this.modelMain.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, p);
        if (id == -1) {
            this.modelMain.RA.render(0.0625F);
        } else {
            this.func_aam(this.modelMain.RA, this.modelMain.LA, id, true);
            this.func_aam2(this.modelMain.RA, this.modelMain.LA, id, true);
        }
    }


    @Unique
    private void renderOozaru(int bodyCM, int furColor, EntityPlayer player) {
        ResourceLocation bdyskn = new ResourceLocation("jinryuudragonbc:cc/oozaru1.png");
        this.bindTexture(bdyskn);
        RenderPlayerJBRA.glColor3f(bodyCM);
        this.modelMain.renderBody(0.0625F);
        bdyskn = new ResourceLocation("jinryuudragonbc:cc/oozaru2.png");
        this.bindTexture(bdyskn);
        RenderPlayerJBRA.glColor3f(furColor);
        this.modelMain.renderBody(0.0625F);
        this.bindTexture(new ResourceLocation("jinryuudragonbc:cc/oozaru0.png"));
        GL11.glColor3f(1.0F + getR(), 1.0F + getG(), 1.0F + getB());
        this.modelMain.renderHairs(0.0625F, "EYEBASE");
        RenderPlayerJBRA.glColor3f(bodyCM);
        this.modelMain.renderHairs(0.0625F, "OOZARU");
    }


    @Unique
    private void renderOozaruArm(int bodyCM, int furColor, EntityPlayer player) {
        ResourceLocation bdyskn = new ResourceLocation("jinryuudragonbc:cc/oozaru1.png");
        JRMCoreClient.mc.getTextureManager().bindTexture(bdyskn);
        RenderPlayerJBRA.glColor3f(bodyCM);
        this.modelMain.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
        if (id == -1) {
            this.modelMain.RA.render(0.0625F);
        } else {
            this.func_aam(this.modelMain.RA, this.modelMain.LA, id, true);
            this.func_aam2(this.modelMain.RA, this.modelMain.LA, id, true);
        }


        JRMCoreClient.mc.getTextureManager().bindTexture(bdyskn);
        RenderPlayerJBRA.glColor3f(furColor);
        this.modelMain.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
        if (id == -1) {
            this.modelMain.RA.render(0.0625F);
        } else {
            this.func_aam(this.modelMain.RA, this.modelMain.LA, id, true);
            this.func_aam2(this.modelMain.RA, this.modelMain.LA, id, true);
        }
    }

    @Shadow
    private void func_aam(ModelRenderer ra, ModelRenderer lA, int id, boolean c) {
    }

    @Shadow
    private void func_aam2(ModelRenderer ra, ModelRenderer la, int id, boolean c) {
    }


    @Shadow
    private static float getR() {
        return 0;
    }

    @Shadow
    private static float getG() {
        return 0;
    }

    @Shadow
    private static float getB() {
        return 0;
    }


}
