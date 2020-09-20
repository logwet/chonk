package me.logwet.chonk;

import java.util.Comparator;

import net.minecraft.server.world.ChunkHolder;
import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.ChunkPos;

public class Chonk {
	private static int expiryTicks = 8;
	private static ChunkTicketType<ChunkPos> ticket = ChunkTicketType.create("chonk", Comparator.comparingLong(ChunkPos::toLong), expiryTicks);

	public static void loadTicking(ServerWorld world, ChunkPos pos) {
		load(world, pos, 1);
	}

	public static void loadEntityTicking(ServerWorld world, ChunkPos pos) {
		load(world, pos, 2);
	}

	private static void load(ServerWorld world, ChunkPos pos, int level) {
		ServerChunkManager manager = world.getChunkManager();
//		ticket = ChunkTicketType.create("chonk", Comparator.comparingLong(ChunkPos::toLong), expiryTicks);
		manager.addTicket(ticket, pos, level, pos);

		ChunkHolder holder = manager.getChunkHolder(pos.toLong());
		if (holder.getLevel() > 33 - level) manager.tick();
	}

//	TODO: Command to change expiryTicks
}
