package frc.team6223.arsenalFramework.software

import edu.wpi.first.networktables.NetworkTable

fun genNetworkTables(inputTable: NetworkTable, name: String): NetworkTable {
    return inputTable.getSubTable(name)
}