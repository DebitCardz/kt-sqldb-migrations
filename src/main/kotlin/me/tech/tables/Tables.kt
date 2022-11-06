package me.tech.tables

import com.github.jasync.sql.db.SuspendingConnection
import me.tech.tables.types.createProfileTable
import me.tech.tables.types.factory.createFactoryProfileTable
import me.tech.tables.types.factory.createFactoryTable
import me.tech.tables.types.factory.plot.createFactoryPlotBuildingsTable
import me.tech.tables.types.factory.plot.createFactoryPlotTable

suspend fun generateTables(
    connection: SuspendingConnection
) = object : Tables {
    override suspend fun profile(): Tables {
        createProfileTable(connection)
        return this
    }

    override suspend fun factoryProfile(): Tables {
        createFactoryProfileTable(connection)
        return this
    }

    override suspend fun factory(): Tables {
        createFactoryTable(connection)
        return this
    }

    override suspend fun factoryPlot(): Tables {
        createFactoryPlotTable(connection)
        return this
    }

    override suspend fun factoryPlotBuildings(): Tables {
        createFactoryPlotBuildingsTable(connection)
        return this
    }
}

interface Tables {
    suspend fun profile(): Tables

    suspend fun factoryProfile(): Tables

    suspend fun factory(): Tables

    suspend fun factoryPlot(): Tables

    suspend fun factoryPlotBuildings(): Tables
}