package net.chemthunder.amaranthine.effect;

import net.chemthunder.amaranthine.init.ModSounds;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;

public class InsanityEffect extends StatusEffect {
    public InsanityEffect() {
        super(StatusEffectCategory.HARMFUL, 0x695200);
    }

    public boolean applyUpdateEffect(ServerWorld world, LivingEntity player, int amplifier) {
if (player instanceof PlayerEntity playerEntity) {
    playerEntity.playSoundToPlayer(ModSounds.INSANITY_VOICES, SoundCategory.MASTER, 1, 0);
}
        return super.applyUpdateEffect(world, player, amplifier);
    }

    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 50;
        return duration % i == 0;
    }

    public ParticleEffect createParticle(StatusEffectInstance effect) {
        return new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.AIR.getDefaultState());
    }
}


//