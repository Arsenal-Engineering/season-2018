package frc.team6223.utils.hardware

import edu.wpi.first.wpilibj.IterativeRobot
import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.command.Scheduler
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard

abstract class CoreRobot: TimedRobot() {

    override fun robotInit() {
        super.robotInit()
        this.allocateSubsystems()
        this.allocateOperatorInterface()
        SmartDashboard.putData(this.injectAutonomousCommands())
    }

    override fun disabledInit() {
        super.disabledInit()
        this.clearScheduler()
    }

    override fun disabledPeriodic() {
        super.disabledPeriodic()
        this.runScheduler()
    }

    override fun autonomousInit() {
        super.autonomousInit()
        this.clearScheduler()
    }

    override fun autonomousPeriodic() {
        super.autonomousPeriodic()
        this.runScheduler()
        this.dashboardPeriodic()
    }

    override fun teleopInit() {
        super.teleopInit()
        this.clearScheduler()
        this.setTeleoperatedCommand()
    }

    override fun teleopPeriodic() {
        super.teleopPeriodic()
        this.runScheduler()
    }

    abstract fun dashboardPeriodic()
    abstract fun injectAutonomousCommands(): SendableChooser<Command>
    abstract fun allocateSubsystems()
    abstract fun allocateOperatorInterface()
    abstract fun setTeleoperatedCommand()

    private fun runScheduler() {
        Scheduler.getInstance().run()
    }

    private fun clearScheduler() {
        Scheduler.getInstance().removeAll()
    }
}