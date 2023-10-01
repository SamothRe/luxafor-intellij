package de.dreamlinemedia.luxaforintellij.requests

class SolidColorRequest(val userId: String) {
    private var actionFields: MutableMap<String, String> = HashMap()


    fun getActionFields(): Map<String, String> {
        return actionFields
    }

    fun setActionFields(actionFields: MutableMap<String, String>) {
        this.actionFields = actionFields
    }

    fun addActionField(actionField: String, value: String) {
        actionFields[actionField] = value
    }

    companion object {
        const val SOLID_COLOR_RED: String = "red"
        const val SOLID_COLOR_YELLOW: String = "yellow"
        const val SOLID_COLOR_GREEN: String = "green"
        const val SOLID_COLOR_BLUE: String = "blue"
        const val SOLID_COLOR_WHITE: String = "white"
        const val SOLID_COLOR_CYAN: String = "cyan"
        const val SOLID_COLOR_MAGENTA: String = "magenta"
    }
}
