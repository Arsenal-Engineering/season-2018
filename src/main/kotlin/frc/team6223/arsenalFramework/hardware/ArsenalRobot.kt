package frc.team6223.arsenalFramework.hardware

import edu.wpi.first.wpilibj.Notifier
import edu.wpi.first.wpilibj.Preferences
import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.command.Scheduler
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.team6223.arsenalFramework.logging.Loggable
import frc.team6223.arsenalFramework.logging.Logger
import frc.team6223.arsenalFramework.operator.ArsenalOperatorInterface

abstract class ArsenalRobot(robotPeriod: Double, val loggerPeriod: Double) : TimedRobot() {

    private val autonomousChooser = this.injectAutonomousCommands()
    private val logger = Notifier(Logger(this.injectLoggedItems()))
    lateinit var operatorInterface: ArsenalOperatorInterface
        private set

    protected var robotPeriod: Double = robotPeriod
        set(value) {
            field = value
            super.setPeriod(value)
        }

    override fun robotInit() {
        super.robotInit()
        this.allocateSubsystems(Preferences.getInstance())
        this.operatorInterface = this.allocateOperatorInterface(Preferences.getInstance())
        SmartDashboard.putData(this.autonomousChooser)
        this.logger.startPeriodic(loggerPeriod)
    }

    override fun disabledInit() {
        super.disabledInit()
        this.clearScheduler()
    }

    override fun disabledPeriodic() {
        super.disabledPeriodic()
        this.dashboardPeriodic()
        this.runScheduler()
    }

    override fun autonomousInit() {
        super.autonomousInit()
        this.clearScheduler()
        autonomousChooser.selected.start()
    }

    override fun autonomousPeriodic() {
        super.autonomousPeriodic()
        this.dashboardPeriodic()
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
        this.dashboardPeriodic()
        this.runScheduler()
    }

    protected fun dashboardPeriodic() {}

    abstract fun injectAutonomousCommands(): SendableChooser<Command>
    abstract fun allocateSubsystems(preferences: Preferences)
    abstract fun allocateOperatorInterface(preferences: Preferences): ArsenalOperatorInterface
    abstract fun setTeleoperatedCommand()
    abstract fun injectLoggedItems(): MutableList<Loggable>

    private fun runScheduler() {
        Scheduler.getInstance().run()
    }

    private fun clearScheduler() {
        Scheduler.getInstance().removeAll()
    }
}