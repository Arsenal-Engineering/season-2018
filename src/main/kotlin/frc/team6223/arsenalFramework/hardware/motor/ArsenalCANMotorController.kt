package frc.team6223.arsenalFramework.hardware.motor

import com.ctre.phoenix.motorcontrol.can.BaseMotorController
import frc.team6223.arsenalFramework.logging.Loggable

interface ArsenalCANMotorController: Loggable {
    val internalMotorController: BaseMotorController
    var inverted: Boolean
    val internalControlMode: MotorControlMode

    fun set(mode: MotorControlMode = MotorControlMode.VoltagePercentOut, output: Double)
    fun addFollower(controller: ArsenalCANMotorController)
}