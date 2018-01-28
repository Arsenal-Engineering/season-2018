package frc.team6223.arsenalFramework.logging

import java.nio.file.Path

class Logger(private val logObjects: MutableList<Loggable>, private val logPath: Path? = null): Runnable {

    fun addLoggable(loggable: Loggable) {
        logObjects.add(loggable)
    }

    fun removeLoggableAt(idx: Int): Loggable {
        return logObjects.removeAt(idx)
    }

    fun removeLoggableObject(loggable: Loggable): Boolean {
        return logObjects.remove(loggable)
    }

    override fun run() {
        for (loggable in logObjects) {
            // do the logging here
            loggable.dashboardPeriodic()
        }
    }
}