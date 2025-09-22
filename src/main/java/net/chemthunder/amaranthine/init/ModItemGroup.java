package net.chemthunder.amaranthine.init;

import net.chemthunder.amaranthine.Amaranthine;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

public interface ModItemGroup {
    RegistryKey<ItemGroup> GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Amaranthine.id("amaranthine"));
    ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.AMARANTHINE_SHARD))
            .displayName(Text.translatable("itemGroup.amaranthine").styled(style -> style.withColor(0x3b1847)))
            .build();

    static void init() {
        Registry.register(Registries.ITEM_GROUP, GROUP_KEY, ITEM_GROUP);

        ItemGroupEvents.modifyEntriesEvent(GROUP_KEY).register(ModItemGroup::addEntries);
    }

    private static void addEntries(FabricItemGroupEntries itemGroup) {
        itemGroup.add(ModItems.AMARANTHINE_SHARD);
        itemGroup.add(ModItems.AMARANTHINE_DUST);
        itemGroup.add(ModItems.AMARANTHINE_GREATSWORD);
        itemGroup.add(ModItems.CHRYSAOR);
        itemGroup.add(ModItems.AMARANTHINE_CLEAVER);
        itemGroup.addAfter(ModItems.CHRYSAOR, ModItems.BLIND_OBEDIENCE);
        itemGroup.add(ModItems.CAPTAINS_CUTLASS);
        itemGroup.add(ModItems.ARC_COOKIE);
        itemGroup.add(ModItems.ARC_COOKIE_BREEZE);
        itemGroup.add(ModItems.INKWELL_COOKIE);
        itemGroup.add(ModItems.INTEL_COOKIE);
        itemGroup.add(ModItems.CHEM_COOKIE);
        itemGroup.add(ModItems.IRRADIATED_AMARANTHINE_SHARD);
    }
}
