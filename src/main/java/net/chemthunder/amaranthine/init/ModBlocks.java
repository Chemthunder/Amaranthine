package net.chemthunder.amaranthine.init;

import net.acoyt.acornlib.impl.block.PlushBlock;
import net.acoyt.acornlib.impl.init.AcornBlockEntities;
import net.acoyt.acornlib.impl.init.AcornBlocks;
import net.chemthunder.amaranthine.Amaranthine;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Function;

public interface ModBlocks {
//    Block CHEM_PLUSH = createWithItem("chem_plush", PlushBlock::new, AbstractBlock.Settings.copy(Blocks.RED_WOOL)
//            .nonOpaque()
//    );


    static Block create(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        return Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Amaranthine.id(name)), factory, settings);
    }

    // Create and Register with an item, always
    static Block createWithItem(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        Block block = create(name, factory, settings);
        ModItems.create(name, itemSettings -> new BlockItem(block, itemSettings), new Item.Settings().useBlockPrefixedTranslationKey().equippableUnswappable(EquipmentSlot.HEAD)
              );
        return block;
    }

    static void init() {
//        AcornBlockEntities.PLUSH.addSupportedBlock(CHEM_PLUSH);
//        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(ModBlocks::addFunctionalEntries);
    }

    static void clientInit() {
       // BlockRenderLayerMap.INSTANCE.putBlock(CHEM_PLUSH, RenderLayer.getCutout());
    }


}
