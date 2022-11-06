package me.tech.tables.types.factory

import com.github.jasync.sql.db.SuspendingConnection
import me.tech.prepareStatement

suspend fun createFactoryProfileTable(
    connection: SuspendingConnection
) {
    connection.prepareStatement("""
        CREATE TABLE IF NOT EXISTS factory_profiles(
            id INT AUTO_INCREMENT,
            
            profile_uuid VARCHAR(36),
            factory_id INT NOT NULL,
    
            PRIMARY KEY (id),
            
            FOREIGN KEY (profile_uuid) REFERENCES profiles(uuid),
            
            INDEX(factory_id),
            FOREIGN KEY (factory_id) REFERENCES factories(id)
        )
    """.trimIndent())
}