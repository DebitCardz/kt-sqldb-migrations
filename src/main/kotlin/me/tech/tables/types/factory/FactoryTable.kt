package me.tech.tables.types.factory

import com.github.jasync.sql.db.SuspendingConnection
import me.tech.prepareStatement
import me.tech.sql

suspend fun createFactoryTable(
    connection: SuspendingConnection
) {
    connection.prepareStatement("""
        CREATE TABLE IF NOT EXISTS factories(
            id INT AUTO_INCREMENT,
            founder_uuid VARCHAR(36),
            plot_id INT NOT NULL,
            
            PRIMARY KEY (id),
            FOREIGN KEY (founder_uuid) REFERENCES profiles(uuid),
            FOREIGN KEY (plot_id) REFERENCES factory_plots(id)
        )
    """.trimIndent())
}