package frc.team6223.arsenalFramework.operator

import edu.wpi.first.wpilibj.Joystick

interface ArsenalOperatorInterface {

    val primaryJoystick: Joystick
    val joysticks: List<Joystick>

}