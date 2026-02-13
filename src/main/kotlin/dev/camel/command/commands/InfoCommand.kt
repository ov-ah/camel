package dev.camel.command.commands

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import dev.camel.chat.ChatUtils
import dev.camel.command.CamelSubCommand
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource

object InfoCommand : CamelSubCommand {

    override fun build(): LiteralArgumentBuilder<FabricClientCommandSource> {
        return literal("info")
            .executes {
                ChatUtils.sendInfo("Camel mod v0.1.0")
                1
            }
    }
}