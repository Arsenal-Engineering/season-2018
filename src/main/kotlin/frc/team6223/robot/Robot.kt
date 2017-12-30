package frc.team6223.robot

import edu.wpi.first.wpilibj.IterativeRobot
import edu.wpi.first.wpilibj.command.Scheduler
import frc.team6223.robot.controllers.ArcadeDriveController
import frc.team6223.robot.subsystems.DriveSystem

public val operatorInterface = OI()
public val driveSubsystem = DriveSystem(ArcadeDriveController(operatorInterface.primaryJoystick))

class Robot(): IterativeRobot() {

    override fun robotInit() {
        super.robotInit()
    }

    override fun autonomousInit() {
        super.autonomousInit()
    }

    override fun teleopInit() {
        super.teleopInit()
    }

    override fun disabledInit() {
        super.disabledInit()
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

}