package dev.camel.command.commands

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import dev.camel.chat.ChatUtils
import dev.camel.command.CamelSubCommand
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource

object DebugCommand : CamelSubCommand {
    override val description = "Provides debug commands for testing."

    private val _subCommands = listOf(
        PingCommand,
    )

    override val subCommands: List<CamelSubCommand> get() = _subCommands

    override fun build(): LiteralArgumentBuilder<FabricClientCommandSource> {
        val root = literal("debug")
            .executes {
                ChatUtils.sendError("Please provide a debug subcommand!")
                0
            }

        subCommands.forEach {
            root.then(it.build())
        }

        return root
    }
}