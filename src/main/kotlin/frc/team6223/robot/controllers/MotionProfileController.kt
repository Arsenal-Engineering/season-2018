package frc.team6223.robot.controllers

import frc.team6223.robot.conf.wheelBaseWidth
import frc.team6223.robot.conf.wheelRadius
import frc.team6223.utils.drivecontroller.ControllerInput
import frc.team6223.utils.drivecontroller.DriveController
import frc.team6223.utils.drivecontroller.DriveControllerOutput
import frc.team6223.utils.srx.MotorControlMode
import frc.team6223.utils.units.*
import jaci.pathfinder.Pathfinder
import jaci.pathfinder.Trajectory
import jaci.pathfinder.Waypoint
import jaci.pathfinder.followers.EncoderFollower
import jaci.pathfinder.modifiers.TankModifier

class MotionProfileController(private val waypoints: Array<Waypoint>,
                              private val trajectoryConfig: Trajectory.Config,
                              private val maxVelocity: Velocity): DriveController {

    private lateinit var trajectory: Trajectory
    private lateinit var leftTrajectory: Trajectory
    private lateinit var rightTrajectory: Trajectory
    private lateinit var leftTrajectoryFollower: EncoderFollower
    private lateinit var rightTrajectoryFollower: EncoderFollower

    override fun calculateMotorOutput(controllerInput: ControllerInput): DriveControllerOutput {
        val leftMotor = leftTrajectoryFollower.calculate(controllerInput.leftRotations.toInt())
        val rightMotor = rightTrajectoryFollower.calculate(controllerInput.rightRotations.toInt())

        val yawHeading = Angle(controllerInput.yawRotation.toDouble(), AngleUnits.DEGREES)
        val desiredHeading = Angle(leftTrajectoryFollower.heading, AngleUnits.RADIANS)

        val angleDifference = Pathfinder.boundHalfDegrees((desiredHeading - yawHeading).numericValue())
        val turn = 0.8 * (-1.0 / 80.0) * angleDifference

        return DriveControllerOutput(MotorControlMode.VoltagePercentOut, leftMotor + turn, rightMotor - turn)
    }

    override fun start(leftInitial: Distance, rightInitial: Distance) {
        println("Generating trajectory")
        trajectory = Pathfinder.generate(waypoints, trajectoryConfig)
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

    override fun stop() {
        if (leftTrajectoryFollower.isFinished) {
            println("Finished moving on motion profile")
        } else {
            println("Not finished moving on motion profile. Robot is in unknown state")
        }
    }
}