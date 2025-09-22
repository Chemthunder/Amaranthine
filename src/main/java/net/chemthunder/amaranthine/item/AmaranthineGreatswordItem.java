package net.chemthunder.amaranthine.item;

import net.acoyt.acornlib.api.item.CustomHitParticleItem;
import net.acoyt.acornlib.impl.client.particle.SweepParticleEffect;
import net.chemthunder.amaranthine.init.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class AmaranthineGreatswordItem extends Item implements CustomHitParticleItem {
    public AmaranthineGreatswordItem(Settings settings) {
        super(settings);
    }



    public static final SweepParticleEffect[] EFFECTS = new SweepParticleEffect[]{new SweepParticleEffect(0xf5c564, 0xE78633)};

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
    public ActionResult useOnBlock(ItemUsageContext context) {

        BlockState state = context.getWorld().getBlockState(context.getBlockPos());
        PlayerEntity user = context.getPlayer();
        if (user != null && user.isSneaking() && state.isOf(Blocks.AMETHYST_CLUSTER)) {
            ItemStack stack = user.getMainHandStack();
            if (stack.isOf(ModItems.AMARANTHINE_GREATSWORD)) {
                stack.decrement(1);
                user.giveItemStack(ModItems.CHRYSAOR.getDefaultStack());
                user.playSound(SoundEvents.BLOCK_BEACON_ACTIVATE, 0.8F, 1.0F);
            }
            return ActionResult.SUCCESS;
        }
        return super.useOnBlock(context);
    }



    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.playSound(SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE);
        target.setOnFireFor(4);

        super.postHit(stack, target, attacker);
    }

    @Override
    public void spawnHitParticles(PlayerEntity playerEntity, Entity entity) {
        spawnHitParticles(playerEntity);
    }
}

// target.setVelocity(attacker.getRotationVec(0).multiply(-3));
