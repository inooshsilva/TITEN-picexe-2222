package com.titanpickaxe;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(TitanPickaxeMod.MOD_ID)
public class TitanPickaxeMod {
    public static final String MOD_ID = "titanpickaxe";

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<Item> TITAN_PICKAXE =
            ITEMS.register("titan_pickaxe",
                    () -> new TitanPickaxeItem(Tiers.DIAMOND, 1, -2.8F,
                            new Item.Properties().stacksTo(1)));

    public TitanPickaxeMod() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(modBus);
        modBus.addListener(this::addCreativeTabItems);
    }

    private void addCreativeTabItems(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(TITAN_PICKAXE);
        }
    }

    public static boolean isTitanPickaxe(ItemStack stack) {
        return stack.is(TITAN_PICKAXE.get());
    }
}
