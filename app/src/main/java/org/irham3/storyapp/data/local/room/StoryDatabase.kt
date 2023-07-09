package org.irham3.storyapp.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import org.irham3.storyapp.data.local.entity.RemoteKeys
import org.irham3.storyapp.data.local.entity.StoryEntity

@Database(
    entities = [StoryEntity::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class StoryDatabase : RoomDatabase() {
    abstract fun getStoryDao() : StoryDao
    abstract fun getRemoteKeysDao() : RemoteKeysDao
}