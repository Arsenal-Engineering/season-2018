package frc.team6223.utils.drive

import com.kauailabs.navx.frc.AHRS

class ControllerInput(leftEncoder: Int, leftEncoderRate: Int, rightEncoder: Int, rightEncoderRate: Int,
                      navX: AHRS) {
    val leftRotations = leftEncoder
    val leftRotationRate = leftEncoderRate
    val rightRotations = rightEncoder
    val rightRotationRate = rightEncoderRate

    val yawRotation = navX.yaw
    val yawRate = navX.rate

    val pitchRotation = navX.pitch
    val rollRotation = navX.roll
}