package frc.team6223.robot

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.buttons.JoystickButton
import frc.team6223.arsenalFramework.hardware.ArsenalLaunchpad
import frc.team6223.arsenalFramework.operator.ArsenalFlightStick
import frc.team6223.arsenalFramework.operator.ArsenalJoystick
import frc.team6223.arsenalFramework.operator.ArsenalOperatorInterface
import frc.team6223.robot.commands.ClawActuationCommand
import frc.team6223.robot.commands.StageThreeMove
import frc.team6223.robot.commands.WinchActuationCommand
import frc.team6223.robot.commands.StageTwoMove
import frc.team6223.robot.conf.JOYSTICK_PORT
import frc.team6223.robot.subsystem.Claw
import frc.team6223.robot.subsystem.Climber


class OI(/*claw: Claw,*/ climber: Climber): ArsenalOperatorInterface {
    override val primaryJoystick: ArsenalJoystick
        get() = ArsenalFlightStick(0, 0.15)
    override val joysticks: List<ArsenalJoystick>
        get() = listOf(primaryJoystick)

    // 14 extend winch, 15 shorten
    private val launchpad: ArsenalLaunchpad = ArsenalLaunchpad(1)

    init {
        // Place anything to start the controllers here
        //JoystickButton(primaryJoystick, 1).whileHeld(ClawActuationCommand(claw, ClawActuationCommand.ClawActuation.OPEN))
        //JoystickButton(primaryJoystick, 2).whileHeld(ClawActuationCommand(claw, ClawActuationCommand.ClawActuation.CLOSE))

        //JoystickButton(launchpad, 14).whileHeld(WinchActuationCommand(claw, WinchActuationCommand.WinchDirection.DOWN))
        //JoystickButton(launchpad, 15).whileHeld(WinchActuationCommand(claw, WinchActuationCommand.WinchDirection.UP))

		JoystickButton(primaryJoystick, 11).whileHeld(StageTwoMove(climber, StageTwoMove.StageTwoDirection.UP, true))
		JoystickButton(primaryJoystick, 12).whileHeld(StageTwoMove(climber, StageTwoMove.StageTwoDirection.DOWN, true))

        JoystickButton(primaryJoystick, 9).whileHeld(StageTwoMove(climber, StageTwoMove.StageTwoDirection.UP, false))
        JoystickButton(primaryJoystick, 10).whileHeld(StageTwoMove(climber, StageTwoMove.StageTwoDirection.DOWN, false))
    }

}