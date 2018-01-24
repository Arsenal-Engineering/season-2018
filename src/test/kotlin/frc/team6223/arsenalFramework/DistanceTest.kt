package frc.team6223.arsenalFramework

import frc.team6223.arsenalFramework.software.units.Distance
import frc.team6223.arsenalFramework.software.units.DistanceUnits
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.matchers.plusOrMinus

class DistanceTest: StringSpec() {
    init {
        val dist = Distance(1.0, DistanceUnits.METERS)
        "distance should input correctly as meters" {
            dist.numericValue() shouldBe 1.0
            dist.numericValue(DistanceUnits.MILLIMETERS) shouldBe 1000.0
            dist.numericValue(DistanceUnits.CENTIMETERS) shouldBe 100.0
            dist.numericValue(DistanceUnits.KILOMETERS) shouldBe .001
            dist.numericValue(DistanceUnits.INCHES) shouldBe (39.370 plusOrMinus .001)
            dist.numericValue(DistanceUnits.FEET) shouldBe (3.280 plusOrMinus .001)
        }

        "distance should input correctly as millimeters" {
            Distance(1.0, DistanceUnits.MILLIMETERS).numericValue() shouldBe .001
        }

        "distance should input correctly as centimeters" {
            Distance(1.0, DistanceUnits.CENTIMETERS).numericValue() shouldBe .01
        }

        "distance should input correctly as kilometers" {
            Distance(1.0, DistanceUnits.KILOMETERS).numericValue() shouldBe 1000.0
        }

        "distance should input correctly as inches" {
            Distance(1.0, DistanceUnits.INCHES).numericValue() shouldBe .0254
        }

        "distance should input correctly as feet" {
            Distance(1.0, DistanceUnits.FEET).numericValue() shouldBe .3048
        }

        "distance should properly output via toString()" {
            dist.toString() shouldBe "1.0 m"
        }
    }
}