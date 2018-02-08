package frc.team6223.arsenalFramework.operator

/**
 * A flight stick, inverting the y-axis so that moving the stick forward in the y direction is not inverted.
 *
 * @param joystickId The ID of the flight-stick on the driver station
 * @param deadZone The deadzone value
 */
class ArsenalFlightStick(joystickId: Int, deadZone: Double): ArsenalJoystick(joystickId, deadZone) {

    /**
     * Inverts the original y-value with dead band applied.
     */
    override val deadBandY: Double
        get() {
            return -super.deadBandY
        }

}