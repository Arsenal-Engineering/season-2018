package frc.team6223.arsenalFramework.software.math

import edu.wpi.first.wpilibj.CircularBuffer


/**
 * A class that runs a linear regression over a finite window of points.
 *
 * The linear regression class uses a circular buffer of points in order to keep only a limited amount of points in
 * memory (due to memory restrictions on the RIO). This class can generate the slope and the y-intercept.
 *
 * Originally written by Noah Gleason of Team 449, modified by Evan Merlock of Team 6223 to work better with Kotlin
 * and to update documentation.
 *
 * @param bufferSize The size of the buffers used to contain points. The higher this number is, the more accurate the
 * slope and y-intercept will be and the less points that will drop out of the linear math. However, more
 * memory will be used.
 */
class RunningLinearRegression(private val bufferSize: Int) {

    /**
     * The buffer holding the x-values of the current window. As points leave the window, they will be removed from
     * the circular buffer. Points are removed based on if the amount of points in the circular buffer exceed the
     * buffer size.
     */
    private val xBuffer: CircularBuffer = CircularBuffer(bufferSize)

    /**
     * The buffer holding the y-values of the current window. As points leave the window, they will be removed from
     * the circular buffer. Points are removed based on if the amount of points in the circular buffer exceed the
     * buffer size.
     */
    private val yBuffer: CircularBuffer = CircularBuffer(bufferSize)

    /**
     * A current running sum of the x-values from the [xBuffer]
     */
    private var xSum: Double = 0.0

    /**
     * A current running sum of the y-values from the [yBuffer]
     */
    private var ySum: Double = 0.0

    /**
     * A current running sum of the x-values squared from the [xBuffer]
     */
    private var xSquaredSum: Double = 0.0

    /**
     * A current running sum of the x-values multiplied by the y-values.
     */
    private var xySum: Double = 0.0

    /**
     * The number of points currently in the buffer(s).
     */
    private var numPoints: Double = 0.0

    /**
     * The current slope of the line created by the linear math.
     */
    val slope: Double
        get() {
            // Avoid division by zero
            if (numPoints < 2) {
                return 0.0
            }
            val covariance = xSquaredSum / numPoints - Math.pow(xSum / numPoints, 2.0)
            val variance = (xySum - xSum * ySum / numPoints) / (numPoints - 1)
            return if (covariance == 0.0) {
                0.0
            } else covariance / variance // Covariance over variance equals slope
        }

    /**
     * The current y-intercept of the line created by the linear math
     */
    val intercept: Double
        get() = ySum / numPoints - slope * xSum / numPoints

    /**
     * The x-point that is furthest back in the buffer
     */
    val furthestXPoint: Double
        get() = xBuffer[bufferSize-1]

    /**
     * The x-point that was last added to the buffer (i.e it is the closest to the front of the buffer)
     */
    val closestXPoint: Double
        get() = xBuffer[0]

    /**
     * Add an x and y point to the buffer and pop out old points if necessary.
     *
     * @param x The x point to add
     * @param y The y point to add
     */
    fun addPoint(x: Double, y: Double) {
        if (numPoints >= bufferSize) {
            //Pop the last point and remove it from the sums
            val backX = xBuffer.removeLast()
            val backY = yBuffer.removeLast()
            xSum -= backX
            ySum -= backY
            xSquaredSum -= backX * backX
            xySum -= backX * backY
        } else {
            numPoints++
        }
        xBuffer.addFirst(x)
        yBuffer.addFirst(y)
        xSum += x
        ySum += y
        xSquaredSum += x * x
        xySum += x * y
    }
}

interface CanLinearRegress {
    val slope: Double
    val intercept: Double

    fun addPoint(x: Double, y: Double)
}
