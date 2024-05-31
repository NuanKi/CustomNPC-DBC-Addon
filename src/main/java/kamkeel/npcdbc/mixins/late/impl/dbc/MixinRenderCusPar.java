package kamkeel.npcdbc.mixins.late.impl.dbc;

import JinRyuu.JRMCore.entity.EntityCusPar;
import JinRyuu.JRMCore.entity.RenderCusPar;
import kamkeel.npcdbc.config.ConfigDBCClient;
import kamkeel.npcdbc.mixins.late.IEntityCusPar;
import kamkeel.npcdbc.mixins.late.IRenderCusPar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderCusPar.class, remap = false)
public class MixinRenderCusPar implements IRenderCusPar {

   
    
    @Inject(method = "renderAura", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glPushMatrix()V", ordinal = 0, shift = At.Shift.BEFORE))
    private void disableLightMap(EntityCusPar entity, double parX, double parY, double parZ, float par8, float par9, CallbackInfo ci) {
        if (ConfigDBCClient.RevampAura)
            Minecraft.getMinecraft().entityRenderer.disableLightmap(0);
    }


    @Inject(method = "renderAura", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glPopMatrix()V", ordinal = 1, shift = At.Shift.BEFORE))
    private void enableLightmap(EntityCusPar entity, double parX, double parY, double parZ, float par8, float par9, CallbackInfo ci) {
        if (ConfigDBCClient.RevampAura)
            Minecraft.getMinecraft().entityRenderer.enableLightmap(0);
    }

    @Inject(method = "doRender", at = @At("HEAD"), cancellable = true)
    private void disableLightMap(Entity par1Entity, double par2, double par4, double par6, float par8, float par9, CallbackInfo ci) {
        IEntityCusPar particle = (IEntityCusPar) par1Entity;
        if (particle.isEnhancedRendering())
            ci.cancel();
    }

    @Unique
    public void renderParticle(EntityCusPar particle, float partialTicks) {
        double interPosX = (particle.lastTickPosX + (particle.posX - particle.lastTickPosX) * (double) partialTicks) - RenderManager.renderPosX;
        double interPosY = (particle.lastTickPosY + (particle.posY - particle.lastTickPosY) * (double) partialTicks) - RenderManager.renderPosY;
        double interPosZ = (particle.lastTickPosZ + (particle.posZ - particle.lastTickPosZ) * (double) partialTicks) - RenderManager.renderPosZ;
        float interYaw = particle.prevRotationYaw + (particle.rotationYaw - particle.prevRotationYaw) * partialTicks;

        ((RenderCusPar) (Object) this).renderAura(particle, interPosX, interPosY, interPosZ, interYaw, partialTicks);

    }



}
