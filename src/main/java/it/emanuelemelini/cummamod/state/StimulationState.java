package it.emanuelemelini.cummamod.state;

import it.emanuelemelini.cummamod.items.ItemRegister;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class StimulationState {

	private ServerPlayerEntity user;

	private Integer stimulation;

	private World world;

	private MatrixStack matrixStack;

	private LocalDateTime lastCumma;

	private Boolean cummed;

	private Timer timer;

	private Long decreasingTime;

	private Integer decreasingAmount;

	private Integer chargingTime;

	public StimulationState(@NotNull ServerPlayerEntity user) {

		this.user = user;
		stimulation = 0;
		world = user.getWorld();
		matrixStack = new MatrixStack();
		lastCumma = LocalDateTime.now();
		cummed = false;
		timer = new Timer();
		decreasingTime = 3000L;
		decreasingAmount = 10;
		chargingTime = 2;

	}

	public ServerPlayerEntity getUser() {
		return user;
	}

	public void setUser(ServerPlayerEntity user) {
		this.user = user;
	}

	public Integer getStimulation() {
		return stimulation;
	}

	public void setStimulation(Integer stimulation) {
		this.stimulation = stimulation;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public MatrixStack getMatrixStack() {
		return matrixStack;
	}

	public void setMatrixStack(MatrixStack matrixStack) {
		this.matrixStack = matrixStack;
	}

	public LocalDateTime getLastCumma() {
		return lastCumma;
	}

	public void setLastCumma(LocalDateTime lastCumma) {
		this.lastCumma = lastCumma;
	}

	public Boolean getCummed() {
		return cummed;
	}

	public void setCummed(Boolean cummed) {
		this.cummed = cummed;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public Long getDecreasingTime() {
		return decreasingTime;
	}

	public void setDecreasingTime(Long decreasingTime) {
		this.decreasingTime = decreasingTime;
	}

	public Integer getDecreasingAmount() {
		return decreasingAmount;
	}

	public void setDecreasingAmount(Integer decreasingAmount) {
		this.decreasingAmount = decreasingAmount;
	}

	public Integer getChargingTime() {
		return chargingTime;
	}

	public void setChargingTime(Integer chargingTime) {
		this.chargingTime = chargingTime;
	}

	public void increase(Integer amount) {

		if(cummed) {
			if(lastCumma.isAfter(LocalDateTime.now()
					.minusMinutes(chargingTime))) {
				user.sendMessage(Text.of("Ti stai ricaricando, basta segarti!"));
				return;
			} else
				cummed = false;
		}

		stimulation += amount;
		if(stimulation >= 100) {
			cum();
		} else {
			decreaseAfter(decreasingTime);
		}

	}

	public void decrease(Integer amount) {

		stimulation -= amount;
		if(stimulation < 0) {
			stimulation = 0;
		} else {
			decreaseAfter(decreasingTime);
		}

	}

	public void decreaseAfter(Long delay) {

		try {

			timer.cancel();
			timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					decrease(decreasingAmount);
				}
			}, delay);

		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	public void cum() {

		lastCumma = LocalDateTime.now();

		cummed = true;

		stimulation = 0;

		user.sendMessage(Text.of("Hai cummato!"));

		Vec3d itemPos = switch(user.getMovementDirection()) {
			case NORTH -> new Vec3d(user.getX(), user.getY(), user.getZ() - 2);
			case EAST -> new Vec3d(user.getX() + 2, user.getY(), user.getZ());
			case WEST -> new Vec3d(user.getX() - 2, user.getY(), user.getZ());
			default -> new Vec3d(user.getX(), user.getY(), user.getZ() + 2); //SOUTH, UP, DOWN
		};

		ItemEntity cumshot = new ItemEntity(world, itemPos.getX(), itemPos.getY(), itemPos.getZ(), new ItemStack(ItemRegister.CUMMA));

		world.spawnEntity(cumshot);

	}

	public void write() {

		String testo = stimulation > 0 ? "Stato cummata: " + stimulation + "/ 100" : "";

		MinecraftClient.getInstance().textRenderer.draw(matrixStack, testo, 5f, 5f, -1);

	}

}
