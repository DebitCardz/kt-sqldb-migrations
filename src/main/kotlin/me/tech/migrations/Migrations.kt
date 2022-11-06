package me.tech.migrations

import com.github.jasync.sql.db.SuspendingConnection
import me.tech.migrations.types.profile.TestMigration

private val migrations = mapOf<String, List<Migration>>(
    Pair(
        "profile",
        listOf(TestMigration)
    )
)

fun runMigrations(
    connection: SuspendingConnection
) = object : TableMigration {
    override suspend fun profile(migrationId: String, revert: Boolean) {
        handleMigration(
            connection,
            "profile",
            migrationId,
            revert
        )
    }

    @Throws(NullPointerException::class)
    private suspend fun handleMigration(
        connection: SuspendingConnection,
        tableType: String,
        migrationId: String?,
        revert: Boolean,
    ) {
        if(migrationId == null) {
            throw NullPointerException("migration-id is null.")
        }

        val migration = migrations[tableType]?.firstOrNull { it.id.equals(migrationId, true) }
            ?: throw NullPointerException("cannot get migration from id.")

        if(!revert) {
            migration.run(connection)
        } else {
            migration.revert(connection)
        }
    }
}

interface TableMigration {
    suspend fun profile(
        migrationId: String,
        revert: Boolean = false
    )
}

interface Migration {
    val id: String

    /**
     * Run a table or data migration.
     */
    suspend fun run(connection: SuspendingConnection)

    /**
     * Revert the migration.
     */
    suspend fun revert(connection: SuspendingConnection)
}