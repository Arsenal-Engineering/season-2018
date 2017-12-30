package frc.team6223.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.team6223.robot.controllers.PIDDistanceController
import frc.team6223.robot.driveSubsystem

class DriveTrainDistance(val dist: Double): Command() {
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