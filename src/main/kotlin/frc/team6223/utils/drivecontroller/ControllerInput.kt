package frc.team6223.utils.drivecontroller

import com.kauailabs.navx.frc.AHRS
import edu.wpi.first.wpilibj.Encoder

class ControllerInput(leftEncoder: Double, leftEncoderRate: Double, rightEncoder: Double, rightEncoderRate: Double,
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