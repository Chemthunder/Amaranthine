package net.chemthunder.amaranthine.init;

import net.chemthunder.amaranthine.Amaranthine;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public interface ModDamageSources {
    RegistryKey<DamageType> IMPALED = of("impaled");
    RegistryKey<DamageType> BLIND = of("blind");
    RegistryKey<DamageType> CHRY_KILL = of("chry_kill");


    static DamageSource impaled(LivingEntity entity) {
        return entity.getDamageSources().create(IMPALED);
    }

    static DamageSource blind(LivingEntity entity) {
        return entity.getDamageSources().create(BLIND);
    }

    static DamageSource chry_kill(LivingEntity entity) {
        return entity.getDamageSources().create(CHRY_KILL);
    }



    private static RegistryKey<DamageType> of(String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Amaranthine.id(name));
    }
}
