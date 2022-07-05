package it.emanuelemelini.cummamod.items;

import it.emanuelemelini.cummamod.CummaMod;
import it.emanuelemelini.cummamod.groups.GroupRegister;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegister {

	public static final CummaItem CUMMA = registerItem("cumma", new CummaItem(new FabricItemSettings().group(GroupRegister.CUMMA)));

	public static final OnaholeItem ONAHOLE = registerItem("onahole", new OnaholeItem(new FabricItemSettings().group(GroupRegister.CUMMA)));

	public static final PlasticItem PLASTIC = registerItem("plastic", new PlasticItem(new FabricItemSettings().group(GroupRegister.CUMMA)));

	private static <T extends Item> T registerItem(String name, T item) {
		return Registry.register(Registry.ITEM, new Identifier(CummaMod.MOD_ID, name), item);
	}

	public static void registerModItems() {
		CummaMod.LOGGER.info("Items registered");
	}

}
