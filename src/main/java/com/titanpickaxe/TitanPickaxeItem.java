package com.titanpickaxe;

import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;

public class TitanPickaxeItem extends PickaxeItem {
    public TitanPickaxeItem(Tier tier, int attackDamageModifier,
                            float attackSpeedModifier, Properties properties) {
        super(tier, attackDamageModifier, attackSpeedModifier, properties);
    }
}
