package com.telestream.tv.engine

import android.content.Context
import org.drinkless.tdlib.Client
import org.drinkless.tdlib.TdApi

class TelegramEngine(private val context: Context) {
    private var client: Client? = null
    
    companion object {
        const val API_ID = 35419333
        const val API_HASH = "8816a4fc88ccb0cae79cd86b37d547b6"
    }

    fun initialize(onResult: (TdApi.Object) -> Unit) {
        val parameters = TdApi.TdlibParameters().apply {
            useMessageDatabase = true
            useSecretChats = true
            useFileDatabase = true
            systemLanguageCode = "en"
            deviceModel = "Android TV"
            applicationVersion = "1.0"
            databaseDirectory = context.filesDir.absolutePath + "/tdlib"
            apiId = API_ID
            apiHash = API_HASH
        }
        
        client = Client.create({ obj: TdApi.Object -> onResult(obj) }, null, null)
        client?.send(TdApi.SetTdlibParameters(parameters)) { /* Handle response */ }
    }

    fun loginWithPhone(phoneNumber: String) {
        client?.send(TdApi.CheckAuthenticationBotToken(phoneNumber)) { /* Handle auth */ }
    }

    fun getVideosFromChat(chatId: Long, onResult: (List<TdApi.Message>) -> Unit) {
        client?.send(TdApi.GetChatHistory(chatId, 0, 0, 100, false)) { result ->
            if (result is TdApi.Messages) {
                val videos = result.messages.filter { it.content is TdApi.MessageVideo }
                onResult(videos)
            }
        }
    }

    fun getDownloadUrl(fileId: Int, onResult: (String) -> Unit) {
        client?.send(TdApi.GetFile(fileId)) { result ->
            if (result is TdApi.File) {
                onResult(result.local.path) // If file is downloaded, or use a proxy for streaming
            }
        }
    }
}
