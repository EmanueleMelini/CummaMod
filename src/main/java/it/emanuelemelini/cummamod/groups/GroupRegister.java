package it.emanuelemelini.cummamod.groups;

import it.emanuelemelini.cummamod.CummaMod;
import it.emanuelemelini.cummamod.items.ItemRegister;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class GroupRegister {

	public static final ItemGroup CUMMA = FabricItemGroupBuilder.build(new Identifier(CummaMod.MOD_ID, "cumma"), () -> new ItemStack(ItemRegister.CUMMA));

}
