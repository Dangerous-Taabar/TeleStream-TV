package org.drinkless.tdlib

/**
 * Temporary STUB for TDLib to allow successful build.
 * This should be replaced with the real TDLib library once a stable source is identified.
 */
class TdApi {
    open class Object
    class TdlibParameters : Object() {
        var useMessageDatabase = false
        var useSecretChats = false
        var useFileDatabase = false
        var systemLanguageCode = ""
        var deviceModel = ""
        var applicationVersion = ""
        var databaseDirectory = ""
        var apiId = 0
        var apiHash = ""
    }
    class SetTdlibParameters(val parameters: TdlibParameters) : Object()
    class CheckAuthenticationBotToken(val token: String) : Object()
    class GetChatHistory(val chatId: Long, val from: Long, val offset: Int, val limit: Int, val onlyLocal: Boolean) : Object()
    class Messages : Object() { val messages = listOf<Message>() }
    class Message : Object() { val content: Object = Object() }
    class MessageVideo : Object()
    class GetFile(val fileId: Int) : Object()
    class File : Object() { val local = LocalFile() }
    class LocalFile { val path = "" }
    class Chat : Object()
}

class Client {
    companion object {
        fun create(handler: (TdApi.Object) -> Unit, updateHandler: Any?, exceptionHandler: Any?): Client = Client()
    }
    fun send(query: TdApi.Object, handler: (TdApi.Object) -> Unit) { 
        // Stub implementation: do nothing or return error
    }
}
