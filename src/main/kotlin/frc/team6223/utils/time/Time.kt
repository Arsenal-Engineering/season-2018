package frc.team6223.utils.time

import edu.wpi.first.wpilibj.Timer
import edu.wpi.first.wpilibj.util.BaseSystemNotInitializedException

public val currentTimeSec: Double
    get() {
        var time: Double = System.currentTimeMillis() / 1000.0;
        try {
            time = Timer.getFPGATimestamp();
        } catch (e: BaseSystemNotInitializedException) {}
        return time;
    };