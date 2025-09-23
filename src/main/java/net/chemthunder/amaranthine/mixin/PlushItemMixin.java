package net.chemthunder.amaranthine.mixin;

import net.acoyt.acornlib.impl.block.PlushItem;
import net.chemthunder.amaranthine.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(PlushItem.class)
public abstract class PlushItemMixin extends BlockItem {
    public PlushItemMixin(Block block, Settings settings) {
        super(block, settings);
    }

    @Inject(method = "appendTooltip", at = @At("HEAD"))
    private void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> tooltip, TooltipType type, CallbackInfo ci) {
        if (stack.isOf(ModBlocks.CHEM_PLUSH.asItem())) {
            tooltip.accept(Text.translatable(this.getTranslationKey() + ".desc").formatted(Formatting.BOLD).withColor(0x47091d));
        }
    }
}
