package net.chemthunder.amaranthine.item;

import net.acoyt.acornlib.api.item.CustomHitParticleItem;
import net.acoyt.acornlib.api.item.CustomHitSoundItem;
import net.acoyt.acornlib.api.item.CustomKillSourceItem;
import net.acoyt.acornlib.api.item.KillEffectItem;
import net.acoyt.acornlib.impl.client.particle.SweepParticleEffect;
import net.chemthunder.amaranthine.init.ModDamageSources;
import net.chemthunder.amaranthine.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Fertilizable;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.UseAction;
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

public class ChrysaorItem extends Item implements CustomHitParticleItem, CustomKillSourceItem, CustomHitSoundItem, KillEffectItem {
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

    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return Integer.MAX_VALUE;
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }

    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        super.use(world, user, hand);
        user.setCurrentHand(hand);
        BlockPos pos = user.getBlockPos();

        if (user.isSneaking() && !user.isOnGround()) {
            user.setVelocity(user.getRotationVec(0).multiply(2));
            user.velocityModified = true;
            user.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE);

            if (!user.isInCreativeMode()) {
                user.getItemCooldownManager().set(user.getStackInHand(hand), 60);
            }

            if (world instanceof ServerWorld serverWorld) {
                serverWorld.spawnParticles(ParticleTypes.END_ROD,
                        pos.getX(),
                        pos.getY(),
                        pos.getZ(),
                        35,
                        1,
                        1,
                        1,
                        0.04
                );
            }
        }
        return ActionResult.CONSUME;
    }

    @Override
    public void killEntity(World world, ItemStack itemStack, LivingEntity user, LivingEntity victim) {
        BlockPos pos = victim.getBlockPos();
        BlockState state = world.getBlockState(pos);

        if (state.isOf(Blocks.AIR) && !state.isOf(Blocks.BEDROCK) || !state.isOf(Blocks.OBSIDIAN)) {
            world.setBlockState(pos, Blocks.TORCHFLOWER.getDefaultState());
            if (world instanceof ServerWorld sworld) {
                sworld.spawnParticles(ParticleTypes.WAX_ON,
                        pos.getX() + 0.5,
                        pos.getY(),
                        pos.getZ() + 0.5,
                        50,
                        1,
                        1,
                        1,
                        0.5
                );
            }
        }
    }
}
