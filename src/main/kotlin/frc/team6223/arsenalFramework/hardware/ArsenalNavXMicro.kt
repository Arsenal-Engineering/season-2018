package frc.team6223.arsenalFramework.hardware

import com.kauailabs.navx.frc.AHRS
import edu.wpi.first.wpilibj.I2C
import edu.wpi.first.wpilibj.SerialPort
import edu.wpi.first.wpilibj.interfaces.Gyro
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.team6223.arsenalFramework.logging.Loggable

/**
 * A NavX-MXP with integrated logging and that can be treated as a WPILib [Gyro].
 */
class ArsenalNavXMicro: AHRS(I2C.Port.kMXP), Gyro, Loggable {

    /**
     * Calibration is not handled in code currently for us, but it is required for the [Gyro] interface, so define an
     * empty method.
     */
    override fun calibrate() {
        // we don't calibrate
    }

    val correctYaw: Float
        get() {
            return when {
                this.yaw >= 0 -> {
                    yaw
                }
                this.yaw < 0 -> {
                    360 + yaw
                }
                else -> {
                    0.0f
                }
            }
        }

    /**
     * Log the Yaw, Pitch, and Roll of the NavX.
     */
    override fun dashboardPeriodic() {
        SmartDashboard.putNumber("NavX Yaw", this.correctYaw.toDouble())
        SmartDashboard.putNumber("NavX Pitch", this.pitch.toDouble())
        SmartDashboard.putNumber("NavX Roll", this.roll.toDouble())
    }
}