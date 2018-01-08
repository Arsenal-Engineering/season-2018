package frc.team6223.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.team6223.robot.controllers.PIDDistanceController
import frc.team6223.robot.subsystems.DriveSystem

class DriveTrainDistance(private val dist: Double, private val driveSubsystem: DriveSystem): Command() {
    override fun initialize() {
        super.initialize()
        driveSubsystem.driveMode = PIDDistanceController(this.dist)
    }

    override fun execute() {
        super.execute()
        driveSubsystem.driveMotors()
    }

    override fun isFinished(): Boolean {
        return false
    }

    init {
        requires(driveSubsystem)
    }
}