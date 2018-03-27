package frc.team6223.robot.auto;


import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;


public class AutoUtilities {

    //For easier reference
    public static final String RIGHT = "Right";
    public static final String CENTER = "Center";
    public static final String LEFT = "Left";

    public static SendableChooser<String> generateSendableChooser() {
        SendableChooser<String> sendableChooser = new SendableChooser<>();
        sendableChooser.addObject(RIGHT, RIGHT);
        sendableChooser.addDefault(CENTER, CENTER);
        sendableChooser.addObject(LEFT, LEFT);
        return sendableChooser;
    }
}
