package com.jv23.echojournal.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jv23.echojournal.data.converter.InstantConverter
import com.jv23.echojournal.data.converter.MoodTypeConverter
import com.jv23.echojournal.data.converter.TopicsConverter
import com.jv23.echojournal.data.entity.JournalEntryDb

@Database(entities = [JournalEntryDb::class], version = 1, exportSchema = false)
@TypeConverters(MoodTypeConverter::class, TopicsConverter::class, InstantConverter::class)
abstract class JournalEntryDatabase: RoomDatabase() {

    abstract fun getJournalEntryDao(): JournalEntryDao

    companion object {
        @Volatile
        private var Instance: JournalEntryDatabase? = null

        fun getDatabase(context: Context): JournalEntryDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context,
                    JournalEntryDatabase::class.java,
                    "journal_entry_database")
                    .fallbackToDestructiveMigration()
                    .build()
            }

        }

    }
}

