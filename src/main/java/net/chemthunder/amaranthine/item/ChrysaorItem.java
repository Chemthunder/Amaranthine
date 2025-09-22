package net.chemthunder.amaranthine.item;

import net.acoyt.acornlib.api.item.CustomHitParticleItem;
import net.acoyt.acornlib.api.item.CustomHitSoundItem;
import net.acoyt.acornlib.api.item.CustomKillSourceItem;
import net.acoyt.acornlib.impl.client.particle.SweepParticleEffect;
import net.chemthunder.amaranthine.init.ModDamageSources;
import net.chemthunder.amaranthine.init.ModItems;
import net.minecraft.advancement.criterion.UsedTotemCriterion;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.function.Consumer;

public class ChrysaorItem extends Item implements CustomHitParticleItem, CustomKillSourceItem, CustomHitSoundItem {
    public boolean chryShield = false;

    public ChrysaorItem(Settings settings) {
        super(settings);
    }

    @SuppressWarnings("deprecation")
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.amaranthine.chrysaor.desc").styled(style -> style.withColor(0x35253B)));
    }

    @Override
    public void spawnHitParticles(PlayerEntity playerEntity, Entity entity) {
        spawnHitParticles(playerEntity);
    }

    public static final SweepParticleEffect[] EFFECTS = new SweepParticleEffect[]{new SweepParticleEffect(0xd2a855, 0xb27c35), new SweepParticleEffect(0x3c1c1b, 0x280c0b)};


    public void spawnHitParticles(PlayerEntity player) {
        double deltaX = -MathHelper.sin((float) (player.getYaw() * (Math.PI / 180.0F)));
        double deltaZ = MathHelper.cos((float) (player.getYaw() * (Math.PI / 180.0F)));
        World var7 = player.getWorld();
        if (var7 instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(
                    EFFECTS[player.getRandom().nextInt(EFFECTS.length)],
                    player.getX() + deltaX,
                    player.getBodyY(0.5F),
                    player.getZ() + deltaZ,
                    0, deltaX, 0.0F, deltaZ, 0.0F
            );
        }
    }

    @Override
    public DamageSource getKillSource(LivingEntity livingEntity) {
        return ModDamageSources.chry_kill(livingEntity);
    }

    @Override
    public void playHitSound(PlayerEntity playerEntity, Entity entity) {
        playerEntity.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK);
    }

    @Override
    public void onItemEntityDestroyed(ItemEntity entity) {
        Entity user = entity.getOwner();
        World ownerWorld = user.getWorld();
        BlockPos pos = entity.getBlockPos();

        if (ownerWorld instanceof ServerWorld serverWorld && user instanceof PlayerEntity player) {
            serverWorld.spawnParticles(ParticleTypes.END_ROD, pos.getX(), pos.getY(), pos.getZ(), 50, 0, 0, 0, 1);
            player.giveItemStack(ModItems.BLIND_OBEDIENCE.getDefaultStack());
        }

        super.onItemEntityDestroyed(entity);
    }
}
