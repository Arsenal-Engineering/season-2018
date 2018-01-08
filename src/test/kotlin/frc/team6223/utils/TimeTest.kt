package frc.team6223.utils

import frc.team6223.utils.units.Time
import frc.team6223.utils.units.TimeUnits
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class TimeTest: StringSpec() {
    init {
        "time should output correctly as milliseconds" {
            val time = Time(1.0, TimeUnits.MILLISECONDS)
            time.numericValue() shouldBe 1.0
            time.numericValue(TimeUnits.MICROSECONDS) shouldBe 1000.0
            time.numericValue(TimeUnits.SECONDS) shouldBe .001
        }

        "time should rescale properly" {
            val time = Time(1.0, TimeUnits.MILLISECONDS)
            time.rescale(TimeUnits.SECONDS).time shouldBe .001
        }
    }
}