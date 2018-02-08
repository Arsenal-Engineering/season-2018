package frc.team6223.arsenalFramework.logging

import edu.wpi.first.wpilibj.Sendable

/**
 * A loggable object can be sent to the SmartDashboard via the [dashboardPeriodic] method
 */
interface Loggable {

    /**
     * This method is called whenever the primary dashboard periodic method is called on each loggable in turn.
     */
    fun dashboardPeriodic()

}