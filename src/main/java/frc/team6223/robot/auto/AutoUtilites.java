package frc.team6223.robot.auto;

public class AutoUtilites {
    public static SendableChooser<String> generateSendableChooser() {
        SendableChooser sendableChooser = new SendableChooser<>();
        sendableChooser.addObject("Right");
        sendableChooser.addDefault("Center");
        sendableChooser.addObject("Left");
        return sendableChooser;
    }
}
