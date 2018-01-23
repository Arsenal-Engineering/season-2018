package frc.team6223.robot

import edu.wpi.first.wpilibj.DriverStation

enum class FieldSide {
    LEFT,
    RIGHT,
    INVALID
}

private val fieldString = DriverStation.getInstance().gameSpecificMessage
private val currentAllianceSwitchSide =
        when (fieldString[0]) {
            'L' -> FieldSide.LEFT
            'R' -> FieldSide.RIGHT
            else -> FieldSide.INVALID
        }

private val currentAllianceScaleSide =
        when (fieldString[1]) {
            'L' -> FieldSide.LEFT
            'R' -> FieldSide.RIGHT
            else -> FieldSide.INVALID
        }

private val currentAllianceOpposingSwitchSide =
        when (fieldString[2]) {
            'L' -> FieldSide.LEFT
            'R' -> FieldSide.RIGHT
            else -> FieldSide.INVALID
        }