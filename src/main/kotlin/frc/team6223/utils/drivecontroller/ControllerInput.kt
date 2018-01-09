package frc.team6223.utils.drivecontroller

import com.kauailabs.navx.frc.AHRS
import edu.wpi.first.wpilibj.Encoder

class ControllerInput(leftEncoder: Encoder, rightEncoder: Encoder, navX: AHRS) {
    val leftRotations = leftEncoder.distance
    val rightRotations = rightEncoder.distance

    val yawRotation = navX.yaw
    val yawRate = navX.rate

    val pitchRotation = navX.pitch
    val rollRotation = navX.roll
}