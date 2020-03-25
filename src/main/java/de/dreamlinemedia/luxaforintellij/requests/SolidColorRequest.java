package de.dreamlinemedia.luxaforintellij.requests;

import java.util.HashMap;
import java.util.Map;

public class SolidColorRequest {

    public static final String SOLID_COLOR_RED = "red";
    public static final String SOLID_COLOR_YELLOW = "yellow";
    public static final String SOLID_COLOR_GREEN = "green";
    public static final String SOLID_COLOR_BLUE = "blue";
    public static final String SOLID_COLOR_WHITE = "white";
    public static final String SOLID_COLOR_CYAN = "cyan";
    public static final String SOLID_COLOR_MAGENTA = "magenta";


    private final String userId;
    private Map<String, String> actionFields = new HashMap<>();


    public SolidColorRequest(String userId) {
        this.userId = userId;
    }


    public String getUserId() {
        return userId;
    }

    public Map<String, String> getActionFields() {
        return actionFields;
    }

    public void setActionFields(Map<String, String> actionFields) {
        this.actionFields = actionFields;
    }

    public void addActionField(final String actionField, final String value) {
        this.actionFields.put(actionField, value);
    }
}
