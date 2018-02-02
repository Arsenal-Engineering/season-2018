package frc.team6223.arsenalFramework.software.math

/**
 * A class that runs a definite integration over a finite window of points. This uses [RunningLinearRegression] to
 * compute the approximate slope and approximate y-intercept as well as the x-intervals for the integration.
 *
 * @param bufferSize The buffer size passed into the [RunningLinearRegression] to compute the approximate slope and
 * approximate y-intercept
 */
class RunningLinearIntegration(private val bufferSize: Int) {

    /**
     * The internal linear regression used for calculating the area under the curve
     */
    private val linearRegression: RunningLinearRegression = RunningLinearRegression(bufferSize)

    /**
     * The slope of the linear regression
     */
    val slope: Double
        get() = linearRegression.slope

    /**
     * The y-intercept value of the linear regression
     */
    val yIntercept: Double
        get() = linearRegression.intercept

    /**
     * Add a point to the internal linear regression
     */
    fun addPoint(x: Double, y: Double) {
        linearRegression.addPoint(x, y)
    }

    /**
     * The current area under the curve
     */
    val areaUnderCurve: Double
        get() {
            return linearIntegration(this.slope, this.yIntercept, linearRegression.furthestXPoint, linearRegression.closestXPoint)
        }

}

/**
 * Calculate the definite integral of the function y=mx+b between a and b, where a is the lower bound and b is the
 * upper bound.
 *
 * @param slope The slope of the linear function
 * @param yIntercept The y-intercept of the linear function
 * @param aTerm The lower boundary of the definite integral
 * @param bTerm The upper boundary of the definite integral
 * @return The area under the curve of the function y=mx+b
 */
fun linearIntegration(slope: Double, yIntercept: Double, aTerm: Double, bTerm: Double): Double {
    // Integrate y=mx+b using the area of a trapezoid (since the graph is linear) 0.5(y(a)+y(b))*b
    return 0.5 * (solveLinear(slope, yIntercept, aTerm) + solveLinear(slope, yIntercept, bTerm)) * bTerm
}

/**
 * Solve an equation f(x)=mx+b
 *
 * @param slope The slope of the equation (m)
 * @param yIntercept The y-intercept of the equation (b)
 * @param xTerm The x-term of the equation (x)
 * @returns The solution to the function
 */
private fun solveLinear(slope: Double, yIntercept: Double, xTerm: Double): Double {
    return (slope * xTerm) + yIntercept
}

interface CanLinearIntegrate: CanLinearRegress {
    val areaUnderCurve: Double
}