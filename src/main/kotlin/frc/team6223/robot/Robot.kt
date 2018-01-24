package frc.team6223.robot

import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import frc.team6223.robot.commands.DriveTrainDistance
import frc.team6223.robot.commands.DriveTrainArcade
import frc.team6223.robot.commands.DriveTrainVelocity
import frc.team6223.robot.conf.LEFT_DRIVE_CONTROLLER
import frc.team6223.robot.conf.RIGHT_DRIVE_CONTROLLER
import frc.team6223.robot.controllers.ArcadeDriveController
import frc.team6223.arsenalFramework.drive.ArsenalDrive
import frc.team6223.arsenalFramework.hardware.ArsenalNavXMicro
import frc.team6223.arsenalFramework.hardware.ArsenalRobot
import frc.team6223.arsenalFramework.hardware.ArsenalTalon
import frc.team6223.arsenalFramework.operator.ArsenalOperatorInterface

class Robot(): ArsenalRobot() {

    private lateinit var driveSubsystem: ArsenalDrive

    override fun dashboardPeriodic() {
        // put all SmartDash code here
    }

    override fun injectAutonomousCommands(): SendableChooser<Command> {
        val sendableChooser = SendableChooser<Command>()
        sendableChooser.addDefault("Move 10ft using PID", DriveTrainDistance(10.0, driveSubsystem))
        sendableChooser.addObject("Move 5 ft/s using PID", DriveTrainVelocity(5.0, driveSubsystem))
        return sendableChooser
    }

    override fun allocateSubsystems() {
        driveSubsystem = ArsenalDrive(
                ArcadeDriveController(operatorInterface.primaryJoystick),
                ArsenalNavXMicro(),
                ArsenalTalon(LEFT_DRIVE_CONTROLLER),
                ArsenalTalon(RIGHT_DRIVE_CONTROLLER)
        )
    }

    override fun allocateOperatorInterface(): ArsenalOperatorInterface {
        return OI()
    }

    override fun setTeleoperatedCommand() {
        DriveTrainArcade(driveSubsystem, operatorInterface).start()
    }
}