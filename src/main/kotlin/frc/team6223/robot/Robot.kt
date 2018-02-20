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
import frc.team6223.arsenalFramework.operator.ArsenalOperatorInterface
import frc.team6223.arsenalFramework.software.FullTrajectory
import frc.team6223.arsenalFramework.software.commands.MoveDriveTrainCommand
import frc.team6223.arsenalFramework.software.controllers.MotionProfileController
import frc.team6223.arsenalFramework.software.controllers.NoMovementController
import frc.team6223.arsenalFramework.software.controllers.PIDDistanceController
import frc.team6223.arsenalFramework.software.readMotionProfile
import frc.team6223.arsenalFramework.software.units.*
import frc.team6223.robot.conf.LEFT_DRIVE_CONTROLLER
import frc.team6223.robot.conf.RIGHT_DRIVE_CONTROLLER
import frc.team6223.robot.controllers.VelocityController

class Robot: ArsenalRobot(TimedRobot.DEFAULT_PERIOD, 0.05) {

    private lateinit var driveSubsystem: ArsenalDrive
    //private val pdpSubsystem = PDP(PDP_CAN_ID)
    private val robotSideChooser = AutoUtilities.generateSendableChooser()

    override fun injectAutonomousCommands(): SendableChooser<Command> {
        val sendableChooser = SendableChooser<Command>()
        val fullTrajectory: FullTrajectory = this.motionProfiles[0]
        sendableChooser
                .addDefault(
                        "Move 5ft using Motion Profiling",
                        MoveDriveTrainCommand(
                                MotionProfileController(
                                        fullTrajectory.leftTrajectory,
                                        fullTrajectory.rightTrajectory,
                                        Velocity(Distance(4.0, DistanceUnits.FEET), Time(1.0, TimeUnits.SECONDS))),
                                driveSubsystem)
                )
        return sendableChooser
    }

    override fun allocateSubsystems(preferences: Preferences) {
        driveSubsystem = ArsenalDrive(
                NoMovementController(),
                ArsenalNavXMicro(),
                ArsenalTalon(LEFT_DRIVE_CONTROLLER, true, startInverted = true, invertSensorOutput = true),
                ArsenalTalon(RIGHT_DRIVE_CONTROLLER, true, startInverted = true, invertSensorOutput = false)
        )
    }

    override fun retrieveMotionProfiles(): List<FullTrajectory> {
        return listOf(readMotionProfile("5ftForward"))
    }

    override fun allocateOperatorInterface(preferences: Preferences): ArsenalOperatorInterface {
        return OI()
    }

    override fun setTeleoperatedCommand() {
        MoveDriveTrainCommand(ArcadeDriveController(operatorInterface.primaryJoystick), driveSubsystem).start()
    }
}
