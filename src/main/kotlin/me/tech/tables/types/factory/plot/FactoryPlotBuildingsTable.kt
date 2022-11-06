package me.tech.tables.types.factory.plot

import com.github.jasync.sql.db.SuspendingConnection
import me.tech.prepareStatement

suspend fun createFactoryPlotBuildingsTable(
    connection: SuspendingConnection
) {
    connection.prepareStatement("""
        CREATE TABLE IF NOT EXISTS factory_plot_buildings(
            id INT AUTO_INCREMENT,
            
            plot_id INT NOT NULL,
            
            structure_id VARCHAR(12) NOT NULL,
            
            x_offset DOUBLE NOT NULL,
            y_offset DOUBLE DEFAULT 0.0 NOT NULL,
            z_offset DOUBLE NOT NULL,
            
            PRIMARY KEY (id),
            FOREIGN KEY (plot_id) REFERENCES factory_plots(id)
        )
    """.trimIndent())
}