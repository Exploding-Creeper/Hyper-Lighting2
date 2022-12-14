package me.hypherionmc.hyperlighting.client;

import me.hypherionmc.hyperlighting.client.init.ClientRegistration;
import me.hypherionmc.hyperlighting.client.model.NeonFlyModel;
import me.hypherionmc.hyperlighting.client.particles.ParticleRegistryHandler;
import me.hypherionmc.hyperlighting.client.renderer.blockentity.AdvancedCampfireRenderer;
import me.hypherionmc.hyperlighting.client.renderer.entity.NeonFlyRenderer;
import me.hypherionmc.hyperlighting.common.init.HLBlockEntities;
import me.hypherionmc.hyperlighting.common.init.HLEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;

public class HyperLightingFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientRegistration registration = new ClientRegistration();
        registration.registerEvents();
        registration.registerAll();

        // TODO: Move to CraterLib as an Event
        ParticleRegistryHandler.registerParticles(new ParticleRegistryHandler.ParticleStrategy() {
            @Override
            public <T extends ParticleOptions> void register(ParticleType<T> particle, ParticleEngine.SpriteParticleRegistration<T> provider) {
                ParticleFactoryRegistry.getInstance().register(particle, provider::create);
            }
        });

        BlockEntityRendererRegistry.register(HLBlockEntities.CAMPFIRE.get(), AdvancedCampfireRenderer::new);
        EntityRendererRegistry.register(HLEntities.NEONFLY.get(), NeonFlyRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(NeonFlyModel.LAYER_LOCATION, NeonFlyModel::createBodyLayer);
    }
}
