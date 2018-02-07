package frc.team6223.robot

import com.kauailabs.navx.frc.AHRS
import edu.wpi.first.wpilibj.IterativeRobot
import edu.wpi.first.wpilibj.SerialPort
import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.command.Scheduler
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.team6223.robot.auto.AutoUtilities
import frc.team6223.robot.commands.DriveTrainDistance
import frc.team6223.robot.commands.DriveTrainMovement
import frc.team6223.robot.commands.DriveTrainVelocity
import frc.team6223.robot.conf.LEFT_DRIVE_CONTROLLER
import frc.team6223.robot.conf.PDP_CAN_ID
import frc.team6223.robot.conf.RIGHT_DRIVE_CONTROLLER
import frc.team6223.robot.controllers.ArcadeDriveController
import frc.team6223.robot.subsystems.DriveSystem
import frc.team6223.utils.pdp.PDP
import frc.team6223.utils.srx.TalonMotor

class Robot(): IterativeRobot() {

    private val operatorInterface = OI()
    private val driveSubsystem = DriveSystem(
            ArcadeDriveController(operatorInterface.primaryJoystick),
            AHRS(SerialPort.Port.kMXP),
            TalonMotor(LEFT_DRIVE_CONTROLLER, true, false, false),
            TalonMotor(RIGHT_DRIVE_CONTROLLER, true, true, false)
    )
    //private val pdpSubsystem = PDP(PDP_CAN_ID)
    private val commandChooser = SendableChooser<Command>()
    private val robotSideChooser = AutoUtilities.generateSendableChooser()

    override fun robotInit() {
        super.robotInit()
        commandChooser.addDefault("Move 10ft using PID", DriveTrainDistance(10.0, driveSubsystem))
        commandChooser.addObject("Move 5 ft/s using PID", DriveTrainVelocity(5.0, driveSubsystem))
        SmartDashboard.putData(commandChooser)
        SmartDashboard.putData(robotSideChooser)
        this.driveSubsystem.resetEncoders()
    }

    override fun autonomousInit() {
        super.autonomousInit()
        this.clearScheduler()

    }

    override fun teleopInit() {
        super.teleopInit()
        this.clearScheduler()
        DriveTrainMovement(driveSubsystem, operatorInterface).start()
    }

    override fun disabledInit() {
        super.disabledInit()
        this.clearScheduler()
    }

    override fun autonomousPeriodic() {
        this.runScheduler()
        this.dashboardPeriodic()
    }

    override fun disabledPeriodic() {
        this.runScheduler()
        this.dashboardPeriodic()
    }

    override fun teleopPeriodic() {
        this.runScheduler()
        this.dashboardPeriodic()
    }

    private fun dashboardPeriodic() {
        this.driveSubsystem.dashboardPeriodic()
    }

    private fun runScheduler() {
        Scheduler.getInstance().run()
    }

    private fun clearScheduler() {
        Scheduler.getInstance().removeAll()
    }

}