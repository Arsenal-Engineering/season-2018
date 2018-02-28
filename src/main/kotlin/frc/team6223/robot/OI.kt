package frc.team6223.robot

import edu.wpi.first.wpilibj.Joystick
import frc.team6223.arsenalFramework.operator.ArsenalFlightStick
import frc.team6223.arsenalFramework.operator.ArsenalOperatorInterface
import frc.team6223.robot.conf.JOYSTICK_PORT


class OI: ArsenalOperatorInterface {
    override val primaryJoystick = ArsenalFlightStick(JOYSTICK_PORT, 0.25)
    override val joysticks: List<Joystick>
        get() = listOf(primaryJoystick)

    init {
        // Place anything to start the controllers here
    }

}