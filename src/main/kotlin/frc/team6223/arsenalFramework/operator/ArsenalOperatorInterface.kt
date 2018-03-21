package frc.team6223.arsenalFramework.operator

import edu.wpi.first.wpilibj.Joystick

/**
 * An operator interface that reports the primary joystick (for the drive system) and a list of the rest of the
 * joysticks.
 */
interface ArsenalOperatorInterface {

    /**
     * The primary driving joystick.
     */
    val primaryJoystick: ArsenalJoystick

    /**
     * A list of the remainder of the joysticks.
     */
    val joysticks: List<ArsenalJoystick>

}