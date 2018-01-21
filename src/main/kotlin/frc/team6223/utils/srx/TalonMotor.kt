package frc.team6223.utils.srx

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.FeedbackDevice
import com.ctre.phoenix.motorcontrol.SensorCollection
import com.ctre.phoenix.motorcontrol.can.TalonSRX
import frc.team6223.utils.units.Distance
import frc.team6223.utils.units.Velocity

class TalonMotor(talonId: Int, quadratureEnabled: Boolean) {
    private val talonSrx: TalonSRX  = TalonSRX(talonId)
    //todo: followers should be managed by methods
    val followers: List<FollowerSRX> = ArrayList()

    private var sensorCollection: SensorCollection? = null

    val position: Distance
        get() {
            return Distance.convertMagPulseToDistance(sensorCollection?.pulseWidthPosition ?: 0)
        }

    val velocity: Velocity
        get() {
            return Velocity.convertMagPulseRateToVelocity(sensorCollection?.pulseWidthVelocity ?: 0)
        }

    fun setPercentOut(percentOut: Double) {
        talonSrx.set(ControlMode.PercentOutput, percentOut)
    }


    inner class FollowerSRX(followerId: Int) {
        private val follower: TalonSRX = TalonSRX(followerId)
        init {
            follower.follow(this@TalonMotor.talonSrx)
        }
    }

    init {
        if (quadratureEnabled) {
            // we have CTRE mag encoder, swap to relative mode
            this.talonSrx.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0)
            // todo: decrease status 3 frame period - see SRM 20.3 and 20.8
            this.sensorCollection = talonSrx.sensorCollection

        }
    }

}