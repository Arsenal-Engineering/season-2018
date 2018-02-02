package frc.team6223.arsenalFramework.hardware

import edu.wpi.first.wpilibj.PowerDistributionPanel
import frc.team6223.arsenalFramework.software.math.CanLinearIntegrate
import frc.team6223.arsenalFramework.software.math.RunningLinearIntegration

class ArsenalPDP(canId: Int): PowerDistributionPanel(canId), CanLinearIntegrate {

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
