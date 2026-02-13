package dev.camel.chat

import dev.camel.command.CamelSubCommand
import dev.camel.command.commands.CamelRootCommand
import net.minecraft.client.MinecraftClient
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.util.Formatting

object HelpMenu {
    private fun padCommand(cmd: String, width: Int = 15): String {
        return if (cmd.length >= width) cmd else cmd + " ".repeat(width - cmd.length)
    }

    fun sendHelp() {
        val header: MutableText = Text.empty()
            .append(Text.literal("\uD83D\uDC2B Camel \u00BB Commands\n")
                .formatted(Formatting.GOLD, Formatting.BOLD))
        
        val tree: MutableText = Text.empty()

        CamelRootCommand.subCommands.forEachIndexed { index, command ->
            tree.append(buildTree(command, "", index == CamelRootCommand.subCommands.lastIndex))
        }

        val full: MutableText = Text.empty()
            .append(header)
            .append(tree)

        MinecraftClient.getInstance().player?.sendMessage(full, false)
    }

    private fun buildTree(
        command: CamelSubCommand,
        indent:  String,
        isLast:  Boolean
    ): MutableText {
        val builder: MutableText = Text.empty()

        val prefix = if (indent.isEmpty()) {
            if (isLast) "\u2514\u2500  " else "\u251C\u2500  "
        } else {
            if (isLast) "${indent}\u2514\u2500  " else "${indent}\u251C\u2500  "
        }

        val commandText = padCommand("${prefix}${command.build().literal}")
        builder.append(Text.literal(commandText).formatted(Formatting.YELLOW))

        if (command.description.isNotEmpty()) {
            builder.append(Text.literal(" - ${command.description}\n").formatted(Formatting.GRAY))
        } else {
            builder.append(Text.literal("\n"))
        }

        command.subCommands.forEachIndexed { index, sub -> 
            val childIndent = indent + if(isLast) "   " else "\u2502  "
            builder.append(buildTree(sub, childIndent, index == command.subCommands.lastIndex))
        }

        return builder
    }
}
