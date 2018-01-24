package frc.team6223.robot.teleoperated;


import frc.team6223.utils.srx.TalonMotor;

public class TeleopUtilities {

    public static void putValuesOnDash(TalonMotor left, TalonMotor right)
    {
        SmartDashboard.putBoolean("Inverted Left", left.getInverted());
        SmartDashboard.putBoolean("Inverted Right", right.getInverted());
        SmartDashboard.putString("Distance Left", left.getPosition().toString());
        SmartDashboard.putString("Distance Right", right.getPosition().toString());
        SmartDashboard.putString("Velocity Left", left.getVelocity().toString());
        SmartDashboard.putString("Velocity Right", right.getVelocity().toString());
    }

}
