package frc.team6223.arsenalFramework.hardware.motor

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.can.VictorSPX
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard

class ArsenalVictor(private val victorId: Int, startInverted: Boolean = false): ArsenalCANMotorController {

    override fun dashboardPeriodic() {
        SmartDashboard.putBoolean("Victor {$victorId} Inverted", inverted)
        SmartDashboard.putString("Victor {$victorId} MCM", internalControlMode.toString())
    }

    override val internalMotorController: VictorSPX = VictorSPX(victorId)
    override var internalControlMode: MotorControlMode = MotorControlMode.VoltagePercentOut
        private set
    override var inverted: Boolean = startInverted
        set(value) {
            this.internalMotorController.inverted = value
            field = value
        }
        get() {
            return this.internalMotorController.inverted
        }

    private val followers: MutableList<ArsenalCANMotorController> = ArrayList()

    override fun set(mode: MotorControlMode, output: Double) {
        this.internalMotorController.set(ControlMode.PercentOutput, output)
    }

    override fun addFollower(controller: ArsenalCANMotorController) {
        controller.internalMotorController.follow(this.internalMotorController)
        followers.add(controller)
    }
}