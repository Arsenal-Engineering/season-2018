package frc.team6223.arsenalFramework.logging

import edu.wpi.first.wpilibj.Sendable
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder

class SendableDelegation(var internalName: String,
                         var internalSubsystem: String,
                         val buildFunc: (SendableBuilder?) -> Unit): Sendable {


    override fun setName(name: String?) {
        internalName = name ?: internalName
    }

    override fun getName(): String {
        return internalName
    }

    override fun initSendable(builder: SendableBuilder?) {
        this.buildFunc(builder)
    }

    override fun getSubsystem(): String {
        return internalSubsystem
    }

    override fun setSubsystem(subsystem: String?) {
        internalSubsystem = subsystem ?: internalSubsystem
    }
}