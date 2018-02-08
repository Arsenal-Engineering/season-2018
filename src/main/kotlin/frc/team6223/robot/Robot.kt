package frc.team6223.robot

import edu.wpi.first.wpilibj.Preferences
import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import frc.team6223.robot.auto.AutoUtilities
import frc.team6223.arsenalFramework.software.controllers.ArcadeDriveController
import frc.team6223.arsenalFramework.drive.ArsenalDrive
import frc.team6223.arsenalFramework.hardware.ArsenalNavXMicro
import frc.team6223.arsenalFramework.hardware.ArsenalRobot
import frc.team6223.arsenalFramework.hardware.ArsenalTalon
import frc.team6223.arsenalFramework.logging.Loggable
import frc.team6223.arsenalFramework.operator.ArsenalOperatorInterface
import frc.team6223.arsenalFramework.software.commands.MoveDriveTrainCommand
import frc.team6223.arsenalFramework.software.controllers.PIDDistanceController
import frc.team6223.robot.conf.LEFT_DRIVE_CONTROLLER
import frc.team6223.robot.conf.RIGHT_DRIVE_CONTROLLER
import frc.team6223.robot.controllers.VelocityController

class Robot: ArsenalRobot(TimedRobot.DEFAULT_PERIOD, 0.05) {

    private lateinit var driveSubsystem: ArsenalDrive

    //private val pdpSubsystem = PDP(PDP_CAN_ID)
    private val robotSideChooser = AutoUtilities.generateSendableChooser()

    override fun injectAutonomousCommands(): SendableChooser<Command> {
        val sendableChooser = SendableChooser<Command>()
        sendableChooser
                .addDefault(
                        "Move 10ft using PID",
                        MoveDriveTrainCommand(PIDDistanceController(10.0), driveSubsystem)
                )
        sendableChooser
                .addObject(
                        "Move 5 ft/s using PID",
                        MoveDriveTrainCommand(VelocityController(5.0), driveSubsystem)
                )
        return sendableChooser
    }

    override fun allocateSubsystems(preferences: Preferences) {
        driveSubsystem = ArsenalDrive(
                ArcadeDriveController(operatorInterface.primaryJoystick),
                ArsenalNavXMicro(),
                ArsenalTalon(LEFT_DRIVE_CONTROLLER, true, true, true),
                ArsenalTalon(RIGHT_DRIVE_CONTROLLER, true, true, false)
        )
    }

    override fun allocateOperatorInterface(preferences: Preferences): ArsenalOperatorInterface {
        return OI()
    }

    override fun setTeleoperatedCommand() {
        MoveDriveTrainCommand(ArcadeDriveController(operatorInterface.primaryJoystick), driveSubsystem).start()
    }

    override fun injectLoggedItems(): MutableList<Loggable> {
        return mutableListOf(driveSubsystem)
    }
}
