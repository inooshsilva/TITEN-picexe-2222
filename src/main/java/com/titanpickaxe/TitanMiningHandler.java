package com.titanpickaxe;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TitanMiningHandler {

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        if (!(event.getLevel() instanceof ServerLevel level)) return;
        if (!TitanPickaxeMod.isTitanPickaxe(event.getPlayer().getMainHandItem())) return;

        ItemStack tool = event.getPlayer().getMainHandItem();
        BlockPos center = event.getPos();

        // 10 x 10 horizontal square, including the originally mined block.
        for (int dx = -4; dx <= 5; dx++) {
            for (int dz = -4; dz <= 5; dz++) {
                BlockPos target = center.offset(dx, 0, dz);
                if (target.equals(center)) continue;

                BlockState state = level.getBlockState(target);
                if (state.isAir() || state.getDestroySpeed(level, target) < 0) continue;
                if (!state.canHarvestBlock(level, target, event.getPlayer())) continue;

                level.destroyBlock(target, true, event.getPlayer());

                if (!tool.isEmpty()) {
                    tool.hurtAndBreak(1, event.getPlayer(),
                            player -> player.broadcastBreakEvent(
                                    event.getPlayer().getUsedItemHand()));
                }
                if (tool.isEmpty()) return;
            }
        }
    }
}
