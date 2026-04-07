package com.telestream.tv.db

import androidx.room.*
import com.telestream.tv.model.PortalType

@Entity(tableName = "portals")
data class PortalEntity(
    @PrimaryKey val id: String,
    val name: String,
    val type: PortalType,
    val url: String,
    val username: String? = null,
    val password: String? = null,
    val macAddress: String? = null
)

@Entity(tableName = "history")
data class HistoryEntity(
    @PrimaryKey val id: String,
    val sourceId: String,
    val name: String,
    val url: String,
    val logo: String?,
    val timestamp: Long,
    val lastPosition: Long,
    val type: String // "LIVE_TV", "VOD", "TELEGRAM"
)

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history WHERE type = :type ORDER BY timestamp DESC LIMIT 10")
    suspend fun getRecentHistory(type: String): List<HistoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateHistory(history: HistoryEntity)
}

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey val id: String,
    val sourceId: String,
    val name: String,
    val url: String,
    val logo: String? = null,
    val type: String // "LIVE_TV", "VOD", "TELEGRAM"
)

@Dao
interface PortalDao {
    @Query("SELECT * FROM portals")
    suspend fun getAllPortals(): List<PortalEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPortal(portal: PortalEntity)
}

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites")
    suspend fun getAllFavorites(): List<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteEntity)

    @Query("DELETE FROM favorites WHERE id = :id")
    suspend fun deleteFavorite(id: String)
}

@Database(entities = [PortalEntity::class, FavoriteEntity::class, HistoryEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TeleStreamDatabase : RoomDatabase() {
    abstract fun portalDao(): PortalDao
    abstract fun favoriteDao(): FavoriteDao
    abstract fun historyDao(): HistoryDao
}

class Converters {
    @TypeConverter
    fun fromPortalType(value: PortalType) = value.name

    @TypeConverter
    fun toPortalType(value: String) = PortalType.valueOf(value)
}
