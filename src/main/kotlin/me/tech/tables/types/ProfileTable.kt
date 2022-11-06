package me.tech.tables.types

import com.github.jasync.sql.db.SuspendingConnection
import me.tech.prepareStatement
import me.tech.sql

suspend fun createProfileTable(
    connection: SuspendingConnection
) {
    connection.prepareStatement("""
        CREATE TABLE IF NOT EXISTS profiles(
            uuid VARCHAR(36) NOT NULL,
            username VARCHAR(16) NOT NULL UNIQUE,

            PRIMARY KEY (uuid)
        );
    """.trimIndent())
}