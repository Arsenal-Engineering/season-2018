package frc.team6223.arsenalFramework.hardware

import com.kauailabs.navx.frc.AHRS
import edu.wpi.first.wpilibj.SerialPort
import edu.wpi.first.wpilibj.interfaces.Gyro
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.team6223.arsenalFramework.logging.Loggable

class ArsenalNavXMicro(): AHRS(SerialPort.Port.kMXP), Gyro, Loggable {
    override fun calibrate() {
        // we don't calibrate
    }

    override fun dashboardPeriodic() {
        SmartDashboard.putNumber("NavX Yaw", this.yaw.toDouble())
        SmartDashboard.putNumber("NavX Pitch", this.pitch.toDouble())
        SmartDashboard.putNumber("NavX Roll", this.roll.toDouble())
    }
}