package dev.camel.chat

import net.minecraft.client.MinecraftClient
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.util.Formatting

object ChatUtils {

    private fun getPrefix(symbol: String = "", symbolColor: Formatting = Formatting.GRAY): MutableText {
        return Text.empty()
            .append(
                Text.literal("\uD83D\uDC2B ")
                    .formatted(Formatting.GOLD)
            )
            .append(
                Text.literal("Camel")
                    .formatted(Formatting.YELLOW, Formatting.BOLD)
            )
            .append(
                Text.literal(" $symbol \u00BB ")
                    .formatted(symbolColor)
            )
    }

    fun send(message: String) {
        send(Text.literal(message))
    }

    fun send(message: Text) {
        MinecraftClient.getInstance().player?.sendMessage(message, false)
    }

    fun sendSuccess(message: String) {
        val symbol = "\u2705"
        val color  = Formatting.GREEN
        send(Text.empty().append(getPrefix(symbol, color)).append(Text.literal(message).formatted(Formatting.GREEN)))
    }

    fun sendError(message: String) {
        val symbol = "\u274C"
        val color  = Formatting.RED
        send(Text.empty().append(getPrefix(symbol, color)).append(Text.literal(message).formatted(Formatting.RED)))
    }

    fun sendInfo(message: String) {
        val symbol = "\u2139"
        val color  = Formatting.AQUA
        send(Text.empty().append(getPrefix(symbol, color)).append(Text.literal(message).formatted(Formatting.GRAY)))
    }
}
