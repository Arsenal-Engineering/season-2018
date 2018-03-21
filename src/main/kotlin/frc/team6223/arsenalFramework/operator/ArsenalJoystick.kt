package frc.team6223.arsenalFramework.operator

import edu.wpi.first.wpilibj.Joystick

/**
 * A joystick that allows for a dead band to be attached to it.
 *
 * @param joystickId The ID number of the joystick as seen on the driver station
 * @param deadBand The _positive_ dead band value, where if the joystick values do not exceed that value, the
 * joystick will report 0.
 */
open class ArsenalJoystick(private val joystickId: Int, var deadBand: Double): Joystick(joystickId) {

    /**
     * The x-value on the joystick with the dead band applied
     */
    open val deadBandX: Double
        get() {
            if (this.x in -deadBand..deadBand) {
                return 0.0
            }
            return x
        }

    /**
     * The y-value on the joystick with the dead band applied
     */
    open val deadBandY: Double
        get() {
            if (this.y in -deadBand..deadBand) {
                return 0.0
            }
            return this.y
        }

}