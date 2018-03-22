package frc.team6223.robot

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.buttons.JoystickButton
import frc.team6223.arsenalFramework.operator.ArsenalFlightStick
import frc.team6223.arsenalFramework.operator.ArsenalJoystick
import frc.team6223.arsenalFramework.operator.ArsenalOperatorInterface
import frc.team6223.robot.commands.ClawActuationCommand
import frc.team6223.robot.conf.JOYSTICK_PORT
import frc.team6223.robot.subsystem.Claw


class OI(claw: Claw): ArsenalOperatorInterface {
    override val primaryJoystick: ArsenalJoystick
        get() = ArsenalFlightStick(1, 0.15)
    override val joysticks: List<ArsenalJoystick>
        get() = listOf(primaryJoystick)

    init {
        // Place anything to start the controllers here
        JoystickButton(primaryJoystick, 1).whenPressed(ClawActuationCommand(claw, ClawActuationCommand.ClawActuation.OPEN))
        JoystickButton(primaryJoystick, 2).whenPressed(ClawActuationCommand(claw, ClawActuationCommand.ClawActuation.CLOSE))
    }

}