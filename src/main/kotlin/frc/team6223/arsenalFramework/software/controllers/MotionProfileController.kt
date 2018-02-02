package frc.team6223.arsenalFramework.software.controllers

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.team6223.arsenalFramework.drive.ControllerInput
import frc.team6223.arsenalFramework.drive.DriveController
import frc.team6223.arsenalFramework.drive.DriveControllerOutput
import frc.team6223.arsenalFramework.hardware.MotorControlMode
import frc.team6223.arsenalFramework.software.units.*
import frc.team6223.robot.conf.wheelBaseWidth
import frc.team6223.robot.conf.wheelRadius
import jaci.pathfinder.Pathfinder
import jaci.pathfinder.Trajectory
import jaci.pathfinder.Waypoint
import jaci.pathfinder.followers.EncoderFollower
import jaci.pathfinder.modifiers.TankModifier

/**
 * MotionProfileController uses a [Trajectory] and a maximum [Velocity] to run motors to follow a path pre-calculated
 * by the user. A [MotionProfileController] should be pre-calculated during the robotInit() function, preferably
 * after you allocate the subsystems.
 *
 * @param trajectory The pre-generated trajectory for the Motion Profile
 * @param maxVelocity The maximum velocity that the robot can go
 */
class MotionProfileController(private val trajectory: Trajectory,
                              private val maxVelocity: Velocity): DriveController {

    /**
     * The internal left trajectory
     */
    private lateinit var leftTrajectory: Trajectory

    /**
     * The internal right trajectory
     */
    private lateinit var rightTrajectory: Trajectory

    /**
     * The follower for the left trajectory that ensures that the left side of the robot follows correctly
     */
    private lateinit var leftTrajectoryFollower: EncoderFollower

    /**
     * The follower for the right trajectory that ensures that the right side of the robot follows correctly
     */
    private lateinit var rightTrajectoryFollower: EncoderFollower

    /**
     * Calculates the motor output based on the provided motion profile
     */
    override fun calculateMotorOutput(controllerInput: ControllerInput): DriveControllerOutput {
        val leftMotor = leftTrajectoryFollower.calculate(
                Distance.convertDistanceToMagPulse(controllerInput.leftEncoder).toInt()
        )
        val rightMotor = rightTrajectoryFollower.calculate(
                Distance.convertDistanceToMagPulse(controllerInput.rightEncoder).toInt()
        )

        val yawHeading = Angle(controllerInput.yawRotation.toDouble(), AngleUnits.DEGREES)
        val desiredHeading = Angle(leftTrajectoryFollower.heading, AngleUnits.RADIANS)

        val angleDifference = Pathfinder.boundHalfDegrees((desiredHeading - yawHeading).numericValue())
        val turn = 0.8 * (-1.0 / 80.0) * angleDifference

        return DriveControllerOutput(MotorControlMode.VoltagePercentOut, leftMotor + turn, rightMotor - turn)
    }

    /**
     * Generate the trajectories for the left and the right
     */
    override fun start(leftInitial: Distance, rightInitial: Distance) {
        println("Generating trajectory")
        val tankModifier = TankModifier(trajectory).modify(wheelBaseWidth)

        leftTrajectory = tankModifier.leftTrajectory
        rightTrajectory = tankModifier.rightTrajectory

        leftTrajectoryFollower = EncoderFollower(leftTrajectory)
        rightTrajectoryFollower = EncoderFollower(rightTrajectory)

        leftTrajectoryFollower.configureEncoder(
                Distance.convertDistanceToMagPulse(leftInitial).toInt(),
                4096,
                wheelRadius * 2)
        rightTrajectoryFollower.configureEncoder(
                Distance.convertDistanceToMagPulse(rightInitial).toInt(),
                4096,
                wheelRadius * 2
        )

        leftTrajectoryFollower.configurePIDVA(1.0,
                0.0,
                0.0,
                1 / maxVelocity.rescaleScalar(DistanceUnits.METERS, TimeUnits.SECONDS),
                0.0)

        rightTrajectoryFollower.configurePIDVA(1.0,
                0.0,
                0.0,
                1 / maxVelocity.rescaleScalar(DistanceUnits.METERS, TimeUnits.SECONDS),
                0.0)
        println("Finished generating trajectory")
        println("Started motion profile controller")
    }

    /**
     * Determine if the trajectory is complete and report it to the user
     */
    override fun stop() {
        if (leftTrajectoryFollower.isFinished) {
            println("Finished moving on motion profile")
        } else {
            println("Not finished moving on motion profile. Robot is in unknown state")
        }
    }

    override val headers: Array<String>
        get() = arrayOf("CurrentController")
    override val data: Array<Any>
        get() = arrayOf("MotionProfileController")

    override fun dashboardPeriodic() {
        // there isn't much to do here yet
        SmartDashboard.putString("Current Controller", "MotionProfileController")
    }

    /**
     * A secondary constructor that will accept an array of [Waypoint]s and a trajectory configuration.
     *
     * @param points The array of [Waypoint]s that turns into the trajectory
     * @param trajectoryConfig The configuration of the trajectory
     * @param maxVelocity The maximum velocity the robot can move at
     */
    constructor(points: Array<Waypoint>, trajectoryConfig: Trajectory.Config, maxVelocity: Velocity):
            this(Pathfinder.generate(points, trajectoryConfig), maxVelocity)
}