package work.racka.thinkrchive.v2.common.database.repository

import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import io.ktor.client.engine.java.*
import org.koin.dsl.module
import work.racka.thinkrchive.v2.common.database.db.ThinkpadDatabase
import work.racka.thinkrchive.v2.common.database.di.ThinkrchiveDatabaseWrapper

actual fun platformModule() = module {
    single { Java.create() }

    single {
        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
            .also { ThinkpadDatabase.Schema.create(it) }
        ThinkrchiveDatabaseWrapper(ThinkpadDatabase(driver))
    }
}