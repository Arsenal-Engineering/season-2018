package frc.team6223.arsenalFramework

import frc.team6223.arsenalFramework.software.units.Time
import frc.team6223.arsenalFramework.software.units.TimeUnits
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class TimeTest: StringSpec() {
    init {
        val time = Time(1.0, TimeUnits.MILLISECONDS)
        "time should output correctly as milliseconds" {
            time.numericValue() shouldBe 1.0
            time.numericValue(TimeUnits.MICROSECONDS) shouldBe 1000.0
            time.numericValue(TimeUnits.SECONDS) shouldBe .001
        }

        "time should rescale properly" {
            time.rescale(TimeUnits.SECONDS).time shouldBe .001
        }

        "time should be formatted via toString() correctly" {
            time.toString() shouldBe "1.0 ms"
        }
    }
}