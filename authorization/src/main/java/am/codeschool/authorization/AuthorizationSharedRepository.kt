package am.codeschool.authorization

interface AuthorizationSharedRepository {
    fun signIn(userName:String,password:String)
    fun forgotPassword(email:String)
}