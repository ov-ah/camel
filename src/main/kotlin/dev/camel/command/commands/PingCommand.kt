package dev.camel.command.commands

import com.mojang.brigadier.CommandDispatcher
import dev.camel.chat.ChatUtils
import dev.camel.command.CamelSubCommand
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource

object PingCommand : CamelSubCommand {
    override val description = "Simple ping-pong command."

    override fun build() = 
        literal("ping")
            .executes {
                ChatUtils.sendSuccess("Pong!")
                1
            }
}
