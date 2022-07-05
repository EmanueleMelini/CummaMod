package it.emanuelemelini.cummamod.items;

import it.emanuelemelini.cummamod.CummaMod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class OnaholeItem extends Item {

	private static final Integer INCREASE_AMOUNT = 10;

	public OnaholeItem(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(@NotNull World world, PlayerEntity user, Hand hand) {

		if(world.isClient) {
			CummaMod.getInstance().stimulationState.increase(INCREASE_AMOUNT);
		}

		return TypedActionResult.success(user.getStackInHand(hand));

	}

}
