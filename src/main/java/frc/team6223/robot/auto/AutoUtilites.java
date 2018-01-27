package frc.team6223.robot.auto;


import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;


public class AutoUtilites {
    public static SendableChooser<String> generateSendableChooser() {
        SendableChooser<String> sendableChooser = new SendableChooser<>();
        sendableChooser.addObject("Right", "Right");
        sendableChooser.addDefault("Center", "Center");
        sendableChooser.addObject("Left", "Left");
        return sendableChooser;
    }
}
