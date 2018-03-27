package frc.team6223.arsenalFramework.hardware

import edu.wpi.first.wpilibj.Preferences
import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.command.Scheduler
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.team6223.arsenalFramework.operator.ArsenalOperatorInterface
import frc.team6223.arsenalFramework.software.FullTrajectory

/**
 * The ArsenalRobot class is a wrapper around [TimedRobot] to make it so people can easily begin coding a robot.
 *
 * This class defines all of the primary lifecycle methods and creates injection points to more easily focus on when
 * developing a robot. Since all of the lifecycle methods can be overridden from subclasses, this ensures that if
 * necessary, more flexibility can be added in certain portions of the lifecycle of the robot. Furthermore, the
 * inheritance from [TimedRobot] means that the period of the robot lifecycle methods can be changed easily.
 *
 * @param robotPeriod The amount of time it takes for each cycle of the robot to occur.
 * @param updatePeriod The amount of time it takes for each cycle of the updater to occur.
 */
abstract class ArsenalRobot(robotPeriod: Double, private val updatePeriod: Double) : TimedRobot() {

    /**
     * The [SendableChooser] for autonomous commands.
     */
    private lateinit var autonomousChooser: SendableChooser<Command>

    /**
     * The [ArsenalOperatorInterface] for the robot.
     */
    lateinit var operatorInterface: ArsenalOperatorInterface
        private set

    lateinit var motionProfiles: List<FullTrajectory>
        private set

    /**
     * The amount of time it takes for each cycle of the robot to occur.
     */
    protected var robotPeriod: Double = robotPeriod
        set(value) {
            field = value
            super.setPeriod(value)
        }

    override fun robotInit() {
        this.allocateSubsystems(Preferences.getInstance())
        this.operatorInterface = this.allocateOperatorInterface(Preferences.getInstance())
        motionProfiles = this.retrieveMotionProfiles()
        autonomousChooser = this.injectAutonomousCommands()
        SmartDashboard.putData(this.autonomousChooser)
    }

    override fun disabledInit() {
        this.clearScheduler()
    }

    override fun disabledPeriodic() {
        this.dashboardPeriodic()
        this.runScheduler()
    }

    override fun autonomousInit() {
        this.clearScheduler()
        autonomousChooser.selected.start()
    }

    override fun autonomousPeriodic() {
        this.dashboardPeriodic()
        this.runScheduler()
    }

    override fun teleopInit() {
        this.clearScheduler()
        this.setTeleoperatedCommand()
    }

    override fun teleopPeriodic() {
        this.dashboardPeriodic()
        this.runScheduler()
    }

    /**
     * A method called periodically to update the dashboard.
     */
    abstract fun dashboardPeriodic()

    /**
     * Creates the sendable chooser for the commands for autonomous
     */
    abstract fun injectAutonomousCommands(): SendableChooser<Command>

    /**
     * Allocates all the subsystems _before_ creating the operator interface.
     */
    abstract fun allocateSubsystems(preferences: Preferences)

    /**
     * Allocates the operator interface after allocating the subsystems
     */
    abstract fun allocateOperatorInterface(preferences: Preferences): ArsenalOperatorInterface

    /**
     * Sets the teleoperated command(s) that are called as soon as teleoperated is started.
     */
    abstract fun setTeleoperatedCommand()

    /**
     * Retrieve all of the motion profiles for the MotionProfileController(s)
     */
    abstract fun retrieveMotionProfiles(): List<FullTrajectory>

    /**
     * Convenience method to run the scheduler
     */
    private fun runScheduler() {
        Scheduler.getInstance().run()
    }

    /**
     * Convenience method to clear the scheduler
     */
    private fun clearScheduler() {
        Scheduler.getInstance().removeAll()
    }
}