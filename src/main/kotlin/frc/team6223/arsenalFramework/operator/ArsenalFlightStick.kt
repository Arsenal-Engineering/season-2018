package frc.team6223.arsenalFramework.operator

class ArsenalFlightStick(joystickId: Int, deadZone: Double): ArsenalJoystick(joystickId, deadZone) {

    override val deadBandY: Double
        get() {
            return -super.deadBandY
        }

}