data class Case(
    val value: Int,
    var drawnIndex: Int? = null
) {
    val isDrawn: Boolean
        get() = drawnIndex != null
}