package net.onticentity.stoneore;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.commands.arguments.blocks.BlockStateArgument;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StoneOre implements ModInitializer {
	public static final String MOD_ID = "stoneore";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private ItemStack returnStone(Block block) {
		if (block == Blocks.COAL_ORE) {
			return new ItemStack(Items.COBBLESTONE);
		} else if (block == Blocks.DEEPSLATE_COAL_ORE) {
			return new ItemStack(Items.COBBLED_DEEPSLATE);
		}

		return ItemStack.EMPTY;
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
			if (world.isClientSide()) return;
			if (player.isCreative()) return;

			Block block = state.getBlock();

			if (state.is(BlockTags.COAL_ORES)) {

				ItemEntity itemEntity = new ItemEntity(
						world,
						pos.getX() + 0.5,
						pos.getY() + 0.5,
						pos.getZ() + 0.5,
						returnStone(block)
				);
				world.addFreshEntity(itemEntity);
			}

		});
	}
}