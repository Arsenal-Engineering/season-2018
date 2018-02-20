package frc.team6223.robot.teleoperated;


import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team6223.arsenalFramework.hardware.motor.ArsenalTalon;


public class TeleopUtilities {

    public static void putValuesOnDash(ArsenalTalon left, ArsenalTalon right, AHRS navX)
    {
        SmartDashboard.putBoolean("Inverted Left", left.getInverted());
        SmartDashboard.putBoolean("Inverted Right", right.getInverted());
        SmartDashboard.putString("Distance Left", left.getPosition().toString());
        SmartDashboard.putString("Distance Right", right.getPosition().toString());
        SmartDashboard.putString("Velocity Left", left.getVelocity().toString());
        SmartDashboard.putString("Velocity Right", right.getVelocity().toString());
    }

}
