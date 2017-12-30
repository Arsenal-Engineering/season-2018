package frc.team6223.robot

import edu.wpi.first.wpilibj.IterativeRobot
import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.command.Scheduler
import frc.team6223.robot.commands.DriveTrainMovement
import frc.team6223.robot.controllers.ArcadeDriveController
import frc.team6223.robot.subsystems.DriveSystem

val operatorInterface = OI()
val driveSubsystem = DriveSystem(ArcadeDriveController(operatorInterface.primaryJoystick))

class Robot(): IterativeRobot() {

    private val currentCommandList: ArrayList<Command> = ArrayList();

    override fun robotInit() {
        super.robotInit()
    }

    override fun autonomousInit() {
        super.autonomousInit()
    }

    override fun teleopInit() {
        super.teleopInit()
        val mutIter = currentCommandList.iterator();
        while (mutIter.hasNext()) {
            val item = mutIter.next();
            item.cancel();
            mutIter.remove();
        }
        DriveTrainMovement().start()
    }

    override fun disabledInit() {
        super.disabledInit()
        val mutIter = currentCommandList.iterator();
        while (mutIter.hasNext()) {
            val item = mutIter.next();
            item.cancel();
            mutIter.remove();
        }
    }

    override fun autonomousPeriodic() {
        this.runScheduler()
    }

    override fun disabledPeriodic() {
        this.runScheduler()
    }

    override fun teleopPeriodic() {
        this.runScheduler()
    }

    private fun runScheduler() {
        Scheduler.getInstance().run()
    }

}