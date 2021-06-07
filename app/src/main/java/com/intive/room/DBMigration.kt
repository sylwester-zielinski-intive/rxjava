package com.intive.room

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class DBMigration : Migration(1, 2) {

    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS _event (
                eid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                uid INTEGER NOT NULL,
	            title TEXT NOT NULL,
	            description TEXT NOT NULL,
	            importanceLevel INTEGER NOT NULL,
                FOREIGN KEY(uid) REFERENCES User(uid) ON UPDATE NO ACTION ON DELETE CASCADE
            );
        """)

        database.execSQL("""
            INSERT INTO _event SELECT *, 0 FROM event;
        """)

        database.execSQL("""
            DROP TABLE event;
        """)

        database.execSQL("""
            ALTER TABLE _event RENAME TO event
        """)
    }

}
