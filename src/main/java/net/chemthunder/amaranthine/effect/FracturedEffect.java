package net.chemthunder.amaranthine.effect;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;

import static net.chemthunder.amaranthine.init.ModDamageSources.impaled;

public class FracturedEffect extends StatusEffect {
    public FracturedEffect() {
        super(StatusEffectCategory.NEUTRAL, 0xE142FD);
    }

    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        entity.damage(world, impaled(entity), 0.5f);
        world.spawnParticles(ParticleTypes.END_ROD, entity.getX(), entity.getY(), entity.getZ(), 3, 0, 1.5, 0, 0.09);
        return super.applyUpdateEffect(world, entity, amplifier);
    }

    public ParticleEffect createParticle(StatusEffectInstance effect) {
        return new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.AIR.getDefaultState());
    }

    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 20;
        return duration % i == 0;
    }
}
