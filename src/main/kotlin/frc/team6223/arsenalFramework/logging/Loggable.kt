package frc.team6223.arsenalFramework.logging

import edu.wpi.first.wpilibj.Sendable

/**
 * A loggable object can be written out to a text file or sent to the SmartDashboard via the [dashboardPeriodic] method
 */
interface Loggable {

    /**
     * The headers for the data, where the length of the headers array is equal to the length of the data array
     */
    val headers: Array<String>

    /**
     * The data to be logged, where the length of the data array is equal to the length of the headers array
     */
    val data: Array<Any>

    /**
     * This method is called whenever the primary dashboard periodic method is called on each loggable in turn.
     */
    fun dashboardPeriodic()

}