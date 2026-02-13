package dev.camel.gui.windows

import dev.camel.gui.ImGuiManager
import dev.camel.gui.ImGuiRenderable
import imgui.ImGui

object DemoWindow : ImGuiRenderable {

    override fun render() {
        ImGui.begin("Camel")

        ImGui.text("Camel ImGui integration is working!")
        ImGui.text("FPS: ${ImGui.getIO().framerate.toInt()}")

        if (ImGui.button("Close")) {
            ImGuiManager.visible = false
        }

        ImGui.end()
    }
}
