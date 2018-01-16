package frc.team6223.robot

import com.kauailabs.navx.frc.AHRS
import edu.wpi.first.wpilibj.IterativeRobot
import edu.wpi.first.wpilibj.SerialPort
import edu.wpi.first.wpilibj.command.Scheduler
import frc.team6223.robot.commands.DriveTrainMovement
import frc.team6223.robot.conf.LEFT_DRIVE_CONTROLLER
import frc.team6223.robot.conf.RIGHT_DRIVE_CONTROLLER
import frc.team6223.robot.controllers.ArcadeDriveController
import frc.team6223.robot.subsystems.DriveSystem
import frc.team6223.utils.srx.TalonMotor

class Robot(): IterativeRobot() {

    private val operatorInterface = OI()
    private val driveSubsystem = DriveSystem(
            ArcadeDriveController(operatorInterface.primaryJoystick),
            AHRS(SerialPort.Port.kMXP),
            TalonMotor(LEFT_DRIVE_CONTROLLER),
            TalonMotor(RIGHT_DRIVE_CONTROLLER)
    )

    override fun robotInit() {
        super.robotInit()
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
    }

    override fun disabledPeriodic() {
        this.runScheduler()
    }

    override fun teleopPeriodic() {
        this.runScheduler()
    }

    private fun runScheduler() {
        Scheduler.getInstance().run()
    }

    private fun clearScheduler() {
        Scheduler.getInstance().removeAll()
    }

}