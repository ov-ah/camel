package dev.camel.gui

import imgui.ImGui
import imgui.flag.ImGuiConfigFlags
import imgui.gl3.ImGuiImplGl3
import imgui.glfw.ImGuiImplGlfw
import net.minecraft.client.MinecraftClient
import java.util.concurrent.ConcurrentLinkedQueue

object ImGuiManager {

    private val implGlfw = ImGuiImplGlfw()
    private val implGl3 = ImGuiImplGl3()
    private val renderables = ConcurrentLinkedQueue<ImGuiRenderable>()

    private var initialized = false
    var visible = false

    fun register(renderable: ImGuiRenderable) {
        renderables.add(renderable)
    }

    fun onFrameRender() {
        if (!visible) return

        if (!initialized) {
            initialize()
            initialized = true
        }

        implGl3.newFrame()
        implGlfw.newFrame()
        ImGui.newFrame()

        for (renderable in renderables) {
            renderable.render()
        }

        ImGui.render()
        implGl3.renderDrawData(ImGui.getDrawData())
    }

    private fun initialize() {
        ImGui.createContext()

        val io = ImGui.getIO()
        io.addConfigFlags(ImGuiConfigFlags.NavEnableKeyboard)

        val windowHandle = MinecraftClient.getInstance().window.handle
        implGlfw.init(windowHandle, true)
        implGl3.init("#version 150")
    }

    fun shutdown() {
        if (!initialized) return
        implGl3.shutdown()
        implGlfw.shutdown()
        ImGui.destroyContext()
    }
}
