package frc.team6223.arsenalFramework

import frc.team6223.arsenalFramework.software.units.Angle
import frc.team6223.arsenalFramework.software.units.AngleUnits
import io.kotlintest.matchers.plusOrMinus
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec
import kotlin.math.PI

class AngularTest: StringSpec() {
    init {
        val angleDeg = Angle(60.0, AngleUnits.DEGREES)
        val angleRad = Angle(1.0, AngleUnits.RADIANS)

        "angle in degrees formats correctly via toString()" {
            angleDeg.toString() shouldBe "60.0 deg"
        }

        "angle in radians formats correctly via toString()" {
            angleRad.toString() shouldBe "1.0 rad"
        }

        "angle in degrees correctly transforms to radians" {
            angleDeg.numericValue(AngleUnits.RADIANS) shouldBe PI/3
        }

        "angle in radians correctly transforms to degrees" {
            angleRad.numericValue(AngleUnits.DEGREES) shouldBe (57.2958 plusOrMinus .0001)
        }
    }
}