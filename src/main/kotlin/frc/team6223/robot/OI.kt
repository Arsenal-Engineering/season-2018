package frc.team6223.robot

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.buttons.JoystickButton
import frc.team6223.arsenalFramework.operator.ArsenalFlightStick
import frc.team6223.arsenalFramework.operator.ArsenalJoystick
import frc.team6223.arsenalFramework.operator.ArsenalOperatorInterface
import frc.team6223.robot.commands.ClawActuationCommand
import frc.team6223.robot.conf.JOYSTICK_PORT
import frc.team6223.robot.subsystem.Claw


public class OI(claw: Claw) {
    public val primaryJoystick = Joystick(JOYSTICK_PORT)

    init {
        // Place anything to start the controllers here
        JoystickButton(primaryJoystick, 1).whenPressed(ClawActuationCommand(claw, ClawActuationCommand.ClawActuation.OPEN))
        JoystickButton(primaryJoystick, 2).whenPressed(ClawActuationCommand(claw, ClawActuationCommand.ClawActuation.CLOSE))
    }

}