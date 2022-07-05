package it.emanuelemelini.cummamod.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class CummaItem extends Item {

	public CummaItem(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

		if(world.isClient) {
			user.sendMessage(Text.of("Questa è la cumma di qualcuno..."));
		}

		return TypedActionResult.success(user.getStackInHand(hand));

	}

}
