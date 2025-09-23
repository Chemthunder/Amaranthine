package net.chemthunder.amaranthine.init;

import net.acoyt.acornlib.api.item.AcornItemSettings;
import net.chemthunder.amaranthine.Amaranthine;
import net.chemthunder.amaranthine.item.*;
import net.chemthunder.amaranthine.item.cookie.*;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BlocksAttacksComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Rarity;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static net.acoyt.acornlib.api.util.ItemUtils.modifyItemNameColor;


public interface ModItems {
    Item AMARANTHINE_GREATSWORD = create("amaranthine_greatsword", AmaranthineGreatswordItem::new, new Item.Settings()
            .sword(ToolMaterial.NETHERITE, 3.5F, -2.7F)
            .rarity(Rarity.UNCOMMON)
            .fireproof()
    );

    Item AMARANTHINE_SHARD = create("amaranthine_shard", AmaranthineItem::new, new Item.Settings()
            .fireproof()
    );

    Item CHEM_COOKIE = create("chem_cookie", ChemCookie::new, new Item.Settings()
            .food(new FoodComponent(5, 4, true))
    );

    Item ARC_COOKIE = create("arc_cookie", ArcCookie::new, new Item.Settings()
            .food(new FoodComponent(5, 4, true))
    );

    Item ARC_COOKIE_BREEZE = create("arc_cookie_breeze", BreezeCookie::new, new Item.Settings()
            .food(new FoodComponent(5, 4, true)
            )
            .maxCount(16)
    );

    Item INKWELL_COOKIE = create("inkwell_cookie", InkwellCookie::new, new Item.Settings()
            .food(new FoodComponent(5, 4, true)
            )
            .maxCount(16)
    );

    Item INTEL_COOKIE = create("intel_cookie", IntelCookie::new, new Item.Settings()
            .food(new FoodComponent(5, 4, true))
            .maxCount(16));

    Item AMARANTHINE_CLEAVER = create("amaranthine_cleaver", CleaverItem::new, new Item.Settings()
            .axe(ToolMaterial.NETHERITE, 3.0f, -2.5f)
            .rarity(Rarity.COMMON)
            .fireproof()
    );

    Item AMARANTHINE_DUST = create("amaranthine_dust", Item::new, new Item.Settings()
            .food(new FoodComponent.Builder()

                    .alwaysEdible()
                    .build()));

    Item BLIND_OBEDIENCE = create("blind_obedience", BlindObedienceItem::new, new Item.Settings()
            .sword(ToolMaterial.NETHERITE, -2.5f, -3.1f)
    );

    Item CAPTAINS_CUTLASS = create("captains_cutlass", CutlassItem::new, new Item.Settings()
            .sword(ToolMaterial.NETHERITE, 2.5f, -2.3f)
            .maxCount(1)
            .maxDamage(99999)
            .fireproof()
    );


    Item IRRADIATED_AMARANTHINE_SHARD = create("irradiated_amaranthine_shard", IrradiatedItem::new, new Item.Settings()
            .maxCount(1)
            .rarity(Rarity.UNCOMMON)
    );

    Item CHRYSAOR = create("chrysaor", ChrysaorItem::new, new AcornItemSettings()
            .maxCount(1)

            .component(DataComponentTypes.BLOCKS_ATTACKS, new BlocksAttacksComponent(0.10f, 1.0f, List.of(new BlocksAttacksComponent.DamageReduction(80f, Optional.empty(), 0.0f, 1.0f)),
            new BlocksAttacksComponent.ItemDamage(1.5f, 1.0f, 1.0f),
            Optional.of(DamageTypeTags.BYPASSES_SHIELD),
            Optional.of(SoundEvents.ITEM_SHIELD_BLOCK), Optional.of(SoundEvents.ITEM_SHIELD_BREAK)))

            .sword(ToolMaterial.NETHERITE, 4.5f, -2.7f)
    );


    static Item create(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        return Items.register(RegistryKey.of(RegistryKeys.ITEM, Amaranthine.id(name)), factory, settings);
    }

    static void init() {
        modifyItemNameColor(AMARANTHINE_CLEAVER, 0x90403e);
        modifyItemNameColor(AMARANTHINE_GREATSWORD, 0xf3dd1e);
        modifyItemNameColor(AMARANTHINE_SHARD, 0xE29242);
        modifyItemNameColor(AMARANTHINE_DUST, 0x985DCE);
        modifyItemNameColor(BLIND_OBEDIENCE, 0x0a0a0a);
        modifyItemNameColor(CAPTAINS_CUTLASS, 0x93E9BE);
        modifyItemNameColor(CHRYSAOR, 0x3c1c1b);
    }
}
