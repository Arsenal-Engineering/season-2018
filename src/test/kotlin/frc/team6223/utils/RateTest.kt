package frc.team6223.utils

import frc.team6223.utils.units.*
import io.kotlintest.matchers.plusOrMinus
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class RateTest: StringSpec() {
    init {
        val rate = Rate(Distance(1.0, DistanceUnits.METERS), Time(1.0, TimeUnits.SECONDS))
        "rate converts from m/s to m/ms correctly" {
            rate.getAsBottom(TimeUnits.MILLISECONDS) shouldBe 0.001

            rate.getAsBottomRate(TimeUnits.MILLISECONDS).getAs() shouldBe 0.001
        }

        "rate converts from m/s to m/min correctly" {
            rate.getAsBottom(TimeUnits.MINUTE) shouldBe (60.0 plusOrMinus .1)

            rate.getAsBottomRate(TimeUnits.MINUTE).getAs() shouldBe (60.0 plusOrMinus .1)
        }

        "rate converts from m/s to km/s correctly" {
            rate.getAsTop(DistanceUnits.KILOMETERS) shouldBe 0.001

            rate.getAsTopRate(DistanceUnits.KILOMETERS).getAs() shouldBe 0.001
        }

        "rate converts from m/s to mm/s correctly" {
            rate.getAsTop(DistanceUnits.MILLIMETERS) shouldBe 1000.0

            rate.getAsTopRate(DistanceUnits.MILLIMETERS).getAs() shouldBe 1000.0
        }

        "rate converts from m/s to km/min correctly" {
            rate.getAsTopBottom(DistanceUnits.KILOMETERS, TimeUnits.MINUTE) shouldBe (0.06 plusOrMinus .001)

            rate.getAsTopBottomRate(DistanceUnits.KILOMETERS, TimeUnits.MINUTE).getAs() shouldBe (0.06 plusOrMinus .01)
        }

        "rate converts from m/s to mm/ms correctly" {
            rate.getAsTopBottom(DistanceUnits.MILLIMETERS, TimeUnits.MILLISECONDS) shouldBe 1.0

            rate.getAsTopBottomRate(DistanceUnits.MILLIMETERS, TimeUnits.MILLISECONDS).getAs() shouldBe 1.0
        }
    }
}