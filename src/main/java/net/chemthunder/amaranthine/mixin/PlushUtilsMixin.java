package net.chemthunder.amaranthine.mixin;

import net.acoyt.acornlib.impl.util.PlushUtils;
import net.chemthunder.amaranthine.init.ModBlocks;
import net.chemthunder.amaranthine.init.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = PlushUtils.class, remap = false)
public class PlushUtilsMixin {
    @Inject(method = "getPlushBlock", at = @At("HEAD"), cancellable = true)
    private static void getPlushBlock(ItemStack stack, CallbackInfoReturnable<Block> cir) {
        if (stack.isOf(ModBlocks.CHEM_PLUSH.asItem())) {
            cir.setReturnValue(ModBlocks.CHEM_PLUSH);
        }
    }

    @Inject(method = "getPlushItem", at = @At("HEAD"), cancellable = true)
    private static void getPlushItem(Block block, CallbackInfoReturnable<Item> cir) {
        if (block == ModBlocks.CHEM_PLUSH) {
            cir.setReturnValue(ModBlocks.CHEM_PLUSH.asItem());
        }
    }

    @Inject(method = "getPlushSound(Lnet/minecraft/block/BlockState;)Lnet/minecraft/sound/SoundEvent;", at = @At("HEAD"), cancellable = true)
    private static void getPlushSound(BlockState state, CallbackInfoReturnable<SoundEvent> cir) {
        if (state.getBlock() == ModBlocks.CHEM_PLUSH) {
            cir.setReturnValue(ModSounds.CHEM_SQUISH);
        }
    }

    @Inject(method = "getPlushSound(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/sound/SoundEvent;", at = @At("HEAD"), cancellable = true)
    private static void getPlushSound(ItemStack stack, CallbackInfoReturnable<SoundEvent> cir) {
        if (stack.isOf(ModBlocks.CHEM_PLUSH.asItem())) {
            cir.setReturnValue(ModSounds.CHEM_SQUISH);
        }
    }

    @Inject(method = "getPlushStack", at = @At("HEAD"), cancellable = true)
    private static void getPlushStack(Block block, CallbackInfoReturnable<ItemStack> cir) {
        if (block == ModBlocks.CHEM_PLUSH) {
            cir.setReturnValue(ModBlocks.CHEM_PLUSH.asItem().getDefaultStack());
        }
    }
}