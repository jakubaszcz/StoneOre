package net.onticentity.stoneore;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.commands.arguments.blocks.BlockStateArgument;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class StoneOre implements ModInitializer {
	public static final String MOD_ID = "stoneore";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final Map<Block, Item> ORE_TO_STONE = Map.ofEntries(

			Map.entry(Blocks.COAL_ORE,      Items.COBBLESTONE),
			Map.entry(Blocks.IRON_ORE,      Items.COBBLESTONE),
			Map.entry(Blocks.GOLD_ORE,      Items.COBBLESTONE),
			Map.entry(Blocks.COPPER_ORE,    Items.COBBLESTONE),
			Map.entry(Blocks.DIAMOND_ORE,   Items.COBBLESTONE),
			Map.entry(Blocks.EMERALD_ORE,   Items.COBBLESTONE),
			Map.entry(Blocks.LAPIS_ORE,     Items.COBBLESTONE),
			Map.entry(Blocks.REDSTONE_ORE,  Items.COBBLESTONE),

			Map.entry(Blocks.DEEPSLATE_COAL_ORE,     Items.COBBLED_DEEPSLATE),
			Map.entry(Blocks.DEEPSLATE_IRON_ORE,     Items.COBBLED_DEEPSLATE),
			Map.entry(Blocks.DEEPSLATE_GOLD_ORE,     Items.COBBLED_DEEPSLATE),
			Map.entry(Blocks.DEEPSLATE_COPPER_ORE,   Items.COBBLED_DEEPSLATE),
			Map.entry(Blocks.DEEPSLATE_DIAMOND_ORE,  Items.COBBLED_DEEPSLATE),
			Map.entry(Blocks.DEEPSLATE_EMERALD_ORE,  Items.COBBLED_DEEPSLATE),
			Map.entry(Blocks.DEEPSLATE_LAPIS_ORE,    Items.COBBLED_DEEPSLATE),
			Map.entry(Blocks.DEEPSLATE_REDSTONE_ORE, Items.COBBLED_DEEPSLATE)
	);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
			if (world.isClientSide()) return;
			if (player.isCreative()) return;

			Block block = state.getBlock();

			Item stone = ORE_TO_STONE.get(block);

			if (stone == null) return;

			ItemEntity itemEntity = new ItemEntity(
				world,
				pos.getX() + 0.5,
				pos.getY() + 0.5,
				pos.getZ() + 0.5,
					new ItemStack(stone)
				);
			world.addFreshEntity(itemEntity);
		});
	}
}