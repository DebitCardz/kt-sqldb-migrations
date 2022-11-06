package me.tech.tables.types.factory.plot

import com.github.jasync.sql.db.SuspendingConnection
import me.tech.prepareStatement

suspend fun createFactoryPlotTable(
    connection: SuspendingConnection
) {
    connection.prepareStatement("""
        CREATE TABLE IF NOT EXISTS factory_plots(
            id INT AUTO_INCREMENT,
            
            PRIMARY KEY (id)
        )
    """.trimIndent())
}