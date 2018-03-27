package frc.team6223.arsenalFramework.software.commands

import edu.wpi.first.wpilibj.command.Command
import frc.team6223.arsenalFramework.drive.ArsenalDrive
import frc.team6223.arsenalFramework.drive.DriveController
import frc.team6223.arsenalFramework.software.controllers.NoMovementController

/**
 * A command that will move the drive train based on a provided [DriveController].
 *
 * @param controller The drive controller responsible for providing the output to the motor
 * @param driveSystem The [ArsenalDrive] responsible for moving currently
 */
class MoveDriveTrainCommand(
        private val controller: DriveController,
        private val driveSystem: ArsenalDrive): Command() {

    init {
        this.requires(driveSystem)
        this.isInterruptible = true
    }

    override fun start() {
        super.start()
        driveSystem.driveMode = controller
    }

    override fun end() {
        driveSystem.driveMode = NoMovementController()
        driveSystem.driveMotors()
    }

    override fun interrupted() {
        driveSystem.driveMode = NoMovementController()
        driveSystem.driveMotors()
    }

    override fun execute() {
        driveSystem.driveMotors()
    }

    override fun isFinished(): Boolean {
        return controller.isFinished()
    }
}