package de.dreamlinemedia.luxaforintellij.actions

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import de.dreamlinemedia.luxaforintellij.requests.SolidColorRequest
import kong.unirest.HttpResponse
import kong.unirest.Unirest
import java.util.*

class SolidColorSwitchAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val color = parseColorByText(e.presentation.text)
        val solidColorRequest = SolidColorRequest("") // TODO: Settings for userId
        solidColorRequest.addActionField("color", color)

        // TODO: To Async and handle it properly
        val httpResponse: HttpResponse<*> = Unirest.post(SOLID_COLOR_WEBHOOK_URL)
            .header("Content-Type", "application/json")
            .body(solidColorRequest).asJson()

        val projectName = "luxafor Plugin"

        if (!httpResponse.isSuccess) {
            Notifications.Bus.notify(
                Notification(
                    projectName,
                    projectName.uppercase(Locale.getDefault()),
                    "Could not update color.",
                    NotificationType.ERROR
                )
            )
        } else {
            Notifications.Bus.notify(
                Notification(
                    projectName,
                    projectName.uppercase(Locale.getDefault()),
                    "Switched to color $color",
                    NotificationType.INFORMATION
                )
            )
        }
    }

    // TODO: Find better parsing for colors.
    private fun parseColorByText(actionText: String): String {
        if (actionText.endsWith("Red")) {
            return SolidColorRequest.SOLID_COLOR_RED
        }
        if (actionText.endsWith("Yellow")) {
            return SolidColorRequest.SOLID_COLOR_YELLOW
        }
        if (actionText.endsWith("Green")) {
            return SolidColorRequest.SOLID_COLOR_GREEN
        }

        return SolidColorRequest.SOLID_COLOR_WHITE
    }

    companion object {
        private const val SOLID_COLOR_WEBHOOK_URL = "https://api.luxafor.com/webhook/v1/actions/solid_color"
    }
}
