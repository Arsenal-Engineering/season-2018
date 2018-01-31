package frc.team6223.robot.auto;


import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoUtilities {

    //For easier reference
    public static String RIGHT = "Right";
    public static String CENTER = "Center";
    public static String LEFT = "Left";

    public static SendableChooser<String> generateSendableChooser() {
        SendableChooser<String> sendableChooser = new SendableChooser<>();
        sendableChooser.addObject(RIGHT, RIGHT);
        sendableChooser.addDefault(CENTER, CENTER);
        sendableChooser.addObject(LEFT, LEFT);
        return sendableChooser;
    }
}
