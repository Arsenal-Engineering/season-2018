package frc.team6223.arsenalFramework.hardware

import edu.wpi.first.wpilibj.PowerDistributionPanel
import frc.team6223.arsenalFramework.software.math.CanLinearIntegrate
import frc.team6223.arsenalFramework.software.math.RunningLinearIntegration

/**
 * A wrapper around the PDP that provides the estimated resistance, and the estimate power being used by the robot
 * over a certain window of time
 *
 * @param canId The CAN ID of the PDP
 */
class ArsenalPDP(canId: Int): PowerDistributionPanel(canId), CanLinearIntegrate {

    /**
     * The linear integration tool
     */
    private val runningLinearIntegration: RunningLinearIntegration = RunningLinearIntegration(15)

    /**
     * The area under the curve/estimated power value of the PDP
     */
    override val areaUnderCurve: Double
        get() = runningLinearIntegration.areaUnderCurve

    /**
     * The area under the curve/estimated power value of the PDP
     */
    val estimatedPower: Double
        get() = this.areaUnderCurve

    /**
     * The slope/estimated resistance value of the PDP
     */
    override val slope: Double
        get() = runningLinearIntegration.slope

    /**
     * The slope/estimated resistance value of the PDP
     */
    val estimatedResistance: Double
        get() = this.slope

    /**
     * The y-intercept of the line generated by the linear regression
     */
    override val intercept: Double
        get() = runningLinearIntegration.yIntercept

    /**
     * Add a point to the PDP.
     */
    override fun addPoint(x: Double, y: Double) {
        runningLinearIntegration.addPoint(x, y)
    }
}
