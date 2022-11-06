@file:Suppress("SqlResolve")

package me.tech

import com.github.jasync.sql.db.SuspendingConnection
import com.github.jasync.sql.db.asSuspending
import com.github.jasync.sql.db.mysql.MySQLConnectionBuilder
import com.github.jasync.sql.db.mysql.exceptions.MySQLException
import me.tech.migrations.runMigrations
import me.tech.tables.generateTables
import kotlin.system.measureTimeMillis

/**
 * Basic migration tool to help create and alter MySQL tables.
 * @author Tech
 */
suspend fun main(args: Array<String>) {
    val arguments = args.parseArguments()

    // TODO: 9/26/2022 sys env to configure this.
    val connection = establishConnection(
        "root",
        "localhost",
        3306,
        "password",
        "minifactory"
    )

    try {
        generateTables(connection)
            .profile()
            .factoryPlot()
            .factoryPlotBuildings()
            .factory()
            .factoryProfile()
   } catch(ex: MySQLException) {
        ex.printStackTrace()
    }

    val migration = runMigrations(connection)

    val type = arguments["-migration-type"]?.lowercase() ?: run {
        println("No value present for flag 'migration-type', skipping migrations.")
        return
    }

    val migrationId = arguments["-migration-id"]?.lowercase() ?: run {
        println("No value present for flag 'migration-id', skipping migrations.")
        return
    }

    // = false
    val revert = arguments["-revert"].toBoolean()

    try {
        val time = measureTimeMillis {
            when(type) {
                "profile" -> migration.profile(migrationId, revert)
            }
        }

        println("Migrations took ${time}ms.")
    } catch(ex: MySQLException) {
        ex.printStackTrace()
    }
}

fun establishConnection(
    username: String,
    host: String,
    port: Int,
    password: String,
    database: String
): SuspendingConnection {
    return MySQLConnectionBuilder.createConnectionPool(
        "jdbc:mysql://${host}:${port}/${database}?user=${username}&password=${password}"
    ) {
        maxActiveConnections = 12
    }.asSuspending
}