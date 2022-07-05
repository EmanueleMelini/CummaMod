package it.emanuelemelini.cummamod;

import it.emanuelemelini.cummamod.items.ItemRegister;
import it.emanuelemelini.cummamod.state.StimulationState;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CummaMod implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	public static final String MOD_ID = "cummamod";

	private static CummaMod instance;

	public static CummaMod getInstance() {
		return instance;
	}

	public StimulationState stimulationState;

	public MatrixStack matrixStack;

	@Override
	public void onInitialize() {

		instance = this;

		LOGGER.info("Hello Fabric world!");

		ItemRegister.registerModItems();

		ServerPlayConnectionEvents.JOIN.register(((handler, sender, server) -> {

			ServerPlayerEntity player = handler.player;

			stimulationState = new StimulationState(player);

		}));

		HudRenderCallback.EVENT.register(((matrixStack1, tickDelta) -> {

			matrixStack = matrixStack1;

			stimulationState.setMatrixStack(matrixStack);

			stimulationState.write();

		}));

	}

}
