package frc.team6223.arsenalFramework

import frc.team6223.arsenalFramework.software.units.*
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class AccelerationTest: StringSpec() {
    init {
        val acceleration = Acceleration(Velocity(Distance(1.0, DistanceUnits.METERS), Time(1.0, TimeUnits.SECONDS)), Time(1.0, TimeUnits.SECONDS))

        "acceleration should be properly formatted via toString()" {
            acceleration.toString() shouldBe "1.0 m/s/s"
        }

        "acceleration should convert from m/s/s to km/s/s correctly" {
            acceleration.rescaleTopScalar(VelocityScaleFactor(DistanceUnits.KILOMETERS, TimeUnits.SECONDS)) shouldBe .001
        }

        "acceleration should convert from m/s/s to m/min/s correctly" {
            acceleration.rescaleTopScalar(VelocityScaleFactor(DistanceUnits.METERS, TimeUnits.MINUTE)) shouldBe 60.0
        }

        "acceleration should convert from m/s/s to m/s/min correctly" {
            acceleration.rescaleBottomScalar(TimeUnits.MINUTE) shouldBe 60.0
        }
    }
}