package frc.team6223.utils.pdp

import edu.wpi.first.wpilibj.PowerDistributionPanel
import frc.team6223.utils.math.CanLinearIntegrate
import frc.team6223.utils.math.RunningLinearIntegration

class PDP(canId: Int): PowerDistributionPanel(canId), CanLinearIntegrate {

    private val runningLinearIntegration: RunningLinearIntegration = RunningLinearIntegration(15)

    override val areaUnderCurve: Double
        get() = runningLinearIntegration.areaUnderCurve

    override val slope: Double
        get() = runningLinearIntegration.slope
    val estimatedResistance: Double
        get() = this.slope

    override val intercept: Double
        get() = runningLinearIntegration.yIntercept

    override fun addPoint(x: Double, y: Double) {
        runningLinearIntegration.addPoint(x, y)
    }
}
