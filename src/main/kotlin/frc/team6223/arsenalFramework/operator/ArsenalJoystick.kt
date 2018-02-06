package frc.team6223.arsenalFramework.operator

import edu.wpi.first.wpilibj.Joystick

open class ArsenalJoystick(private val joystickId: Int, var deadBand: Double): Joystick(joystickId) {

    open val deadBandX: Double
        get() {
            if (x in -deadBand..deadBand) {
                return 0.0
            }
            return x
        }

    open val deadBandY: Double
        get() {
            if (y in -deadBand..deadBand) {
                return 0.0
            }
            return y
        }

}