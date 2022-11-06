package me.tech

import com.github.jasync.sql.db.QueryResult
import com.github.jasync.sql.db.RowData
import com.github.jasync.sql.db.SuspendingConnection
import org.intellij.lang.annotations.Language

fun Array<String>.parseArguments(): Map<String, String> {
    val flags = filter { it.startsWith("-") }
    val argument = filter { !it.startsWith("-") }

    return flags.mapIndexed { index, flag ->
        Pair(flag, argument[index])
    }.toMap()
}

fun sql(@Language("SQL") s: String): String = s

suspend fun SuspendingConnection.querySingle(
    @Language("SQL") q: String,
    args: List<Any?> = listOf()
): RowData? {
    return sendPreparedStatement(q, args).rows.firstOrNull()
}

suspend fun SuspendingConnection.query(
    @Language("SQL")
    q: String,
    args: List<Any?> = listOf()
): List<RowData> {
    return sendPreparedStatement(q, args).rows
}

suspend fun SuspendingConnection.prepareStatement(
    @Language("SQL") q: String
): QueryResult {
    return sendPreparedStatement(q)
}