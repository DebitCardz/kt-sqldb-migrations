package me.tech.migrations.types.profile

import com.github.jasync.sql.db.SuspendingConnection
import me.tech.migrations.Migration
import me.tech.prepareStatement

@Suppress("SqlResolve")
object TestMigration: Migration {
    override val id: String
        get() = "profile-add-column"

    override suspend fun run(connection: SuspendingConnection) {
        connection.prepareStatement(
            "ALTER TABLE profiles ADD COLUMN other VARCHAR(5) AFTER test;"
        )
    }

    override suspend fun revert(connection: SuspendingConnection) {
        connection.prepareStatement(
            "ALTER TABLE profiles DROP COLUMN other;"
        )
    }
}