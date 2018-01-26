package frc.team6223.utils.logging

import java.nio.file.Path

class Logger(private val logObjects: MutableList<Loggable>, private val logPath: Path): Runnable {

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
        synchronized(logObjects) {
            for (loggable in logObjects) {
                // do the logging here
            }
        }
    }
}