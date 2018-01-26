package frc.team6223.utils.logging

import edu.wpi.first.wpilibj.Sendable

/**
 * A loggable object can be written out to a text file or sent to the SmartDashboard via the [Sendable] interface
 */
interface Loggable: Sendable {

    /**
     * The headers for the data, where the length of the headers array is equal to the length of the data array
     */
    val headers: Array<String>

    /**
     * The data to be logged, where the length of the data array is equal to the length of the headers array
     */
    val data: Array<Any>

}