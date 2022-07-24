package ir.alishayanpoor.dictionarywithcaching.exception

class AppException(message: String? = null) : Exception(message) {
    override fun getLocalizedMessage(): String? {
        return message
    }
}