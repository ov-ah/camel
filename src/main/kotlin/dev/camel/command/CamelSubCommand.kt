package dev.camel.command

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource

interface CamelSubCommand {
    val description: String get() = ""
    val subCommands: List<CamelSubCommand> get() = emptyList()
    fun build(): LiteralArgumentBuilder<FabricClientCommandSource>
}