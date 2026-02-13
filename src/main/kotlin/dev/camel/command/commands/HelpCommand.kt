package dev.camel.command.commands

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import dev.camel.chat.HelpMenu
import dev.camel.command.CamelSubCommand
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource

object HelpCommand : CamelSubCommand {
    override val description = "Shows this help menu."

    override fun build(): LiteralArgumentBuilder<FabricClientCommandSource> {
        return literal("help")
            .executes {
                HelpMenu.sendHelp()
                1
            }
    }
}