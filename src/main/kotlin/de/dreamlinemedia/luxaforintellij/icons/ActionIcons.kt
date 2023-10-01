package de.dreamlinemedia.luxaforintellij.icons

import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

interface ActionIcons {
    companion object {
        val ACTION_SOLID_COLOR_RED: Icon = IconLoader.getIcon("/icons/icon_solid_red.png", Companion::class.java)
        val ACTION_SOLID_COLOR_YELLOW: Icon = IconLoader.getIcon("/icons/icon_solid_yellow.png", Companion::class.java)
        val ACTION_SOLID_COLOR_GREEN: Icon = IconLoader.getIcon("/icons/icon_solid_green.png", Companion::class.java)
    }
}
