package de.dreamlinemedia.luxaforintellij.actions;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import de.dreamlinemedia.luxaforintellij.requests.SolidColorRequest;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.jetbrains.annotations.NotNull;

public class SolidColorSwitchAction extends AnAction {

    private final static String SOLID_COLOR_WEBHOOK_URL = "https://api.luxafor.com/webhook/v1/actions/solid_color";

    @Override
    public void update(@NotNull AnActionEvent e) {
        super.update(e);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        final String color = parseColorByText(e.getPresentation().getText());
        final SolidColorRequest solidColorRequest = new SolidColorRequest(""); // TODO: Settings for userId
        solidColorRequest.addActionField("color", color);

        // TODO: To Async and handle it properly
        final HttpResponse httpResponse = Unirest.post(SOLID_COLOR_WEBHOOK_URL)
                .header("Content-Type", "application/json")
                .body(solidColorRequest).asJson();

        final String projectName = "luxafor Plugin";

        if (!httpResponse.isSuccess()) {
            Notifications.Bus.notify(new Notification(projectName, projectName.toUpperCase(), "Could not update color.", NotificationType.ERROR));
        } else {
            Notifications.Bus.notify(new Notification(projectName, projectName.toUpperCase(), "Switched to color " + color, NotificationType.INFORMATION));
        }
    }

    // TODO: Find better parsing for colors.
    private String parseColorByText(final String actionText) {
        if (actionText.endsWith("Red")) {
            return SolidColorRequest.SOLID_COLOR_RED;
        }
        if (actionText.endsWith("Yellow")) {
            return SolidColorRequest.SOLID_COLOR_YELLOW;
        }
        if (actionText.endsWith("Green")) {
            return SolidColorRequest.SOLID_COLOR_GREEN;
        }

        return SolidColorRequest.SOLID_COLOR_WHITE;
    }

}
