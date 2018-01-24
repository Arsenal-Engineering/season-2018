package frc.team6223.arsenalFramework.hardware

import com.kauailabs.navx.frc.AHRS
import edu.wpi.first.wpilibj.SerialPort
import edu.wpi.first.wpilibj.interfaces.Gyro

class ArsenalNavXMicro(): AHRS(SerialPort.Port.kMXP), Gyro {
    override fun calibrate() {
        // we don't calibrate
    }
}