package net.chemthunder.amaranthine.mixin;

import net.chemthunder.amaranthine.init.ModBlocks;
import net.chemthunder.amaranthine.init.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FlintAndSteelItem.class)
public class FlintAndSteelItemMixin extends Item {
    public FlintAndSteelItemMixin(Settings settings) {
        super(settings);
    }

    // first mixin i ever wrote by MYSELF!
    @Inject(method = "useOnBlock", at = @At("HEAD"))
    private void useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        BlockPos pos = context.getBlockPos();
        BlockState state = context.getWorld().getBlockState(pos);
        PlayerEntity user = context.getPlayer();
        World world = context.getWorld();

        if (user != null && user.isSneaking() && state.isOf(ModBlocks.CHEM_PLUSH)) {
            if (world instanceof ServerWorld serverWorld) {
                FallingBlockEntity chemPlushEntity = FallingBlockEntity.spawnFromBlock(world, pos, state);
                BlockPos chemPlushEntityPos = chemPlushEntity.getBlockPos();

                user.playSound(ModSounds.CHEM_SQUISH, 50, -2);
                world.createExplosion(chemPlushEntity, chemPlushEntityPos.getX(), chemPlushEntityPos.getY(), chemPlushEntityPos.getZ(), 0, World.ExplosionSourceType.TNT);
                chemPlushEntity.kill(serverWorld);
            }
        }
    }
}
