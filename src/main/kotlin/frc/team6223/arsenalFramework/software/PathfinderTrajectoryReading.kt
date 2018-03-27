package frc.team6223.arsenalFramework.software

import jaci.pathfinder.Pathfinder
import jaci.pathfinder.Trajectory
import java.nio.file.Path
import java.nio.file.Paths

data class FullTrajectory(val leftTrajectory: Trajectory, val rightTrajectory: Trajectory)

fun readMotionProfile(name: String): FullTrajectory {
    val leftPath: Path = Paths.get("/home/lvuser/6223_resources/${name}_left_detailed.csv")
    val rightPath: Path = Paths.get("/home/lvuser/6223_resources/${name}_right_detailed.csv")

    println(leftPath)
    println(rightPath)

    val leftTrajectory = Pathfinder.readFromCSV(leftPath.toFile())
    val rightTrajectory = Pathfinder.readFromCSV(rightPath.toFile())

    return FullTrajectory(leftTrajectory, rightTrajectory)
}