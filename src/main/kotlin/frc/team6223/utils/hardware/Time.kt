package frc.team6223.utils.hardware

import edu.wpi.first.wpilibj.Timer
import edu.wpi.first.wpilibj.util.BaseSystemNotInitializedException
import frc.team6223.utils.software.units.Time
import frc.team6223.utils.software.units.TimeUnits

val currentTimeSec: Time
    get() {
        var time = Time(System.currentTimeMillis().toDouble(), TimeUnits.MILLISECONDS).rescale(TimeUnits.SECONDS)
        try {
            time = Time(Timer.getFPGATimestamp(), TimeUnits.SECONDS)
        } catch (e: BaseSystemNotInitializedException) {}
        return time
    };