package am.codeschool.authorization

import am.codeschool.common.net.ApiCallBack
import am.codeschool.common.net.smartRequest

interface AuthorizationSharedRepository {
    fun signIn(userName:String,password:String,apiCallBack: ApiCallBack<Any>)
    fun forgotPassword(email:String,apiCallBack: ApiCallBack<Any>)

}
class AuthorizationSharedRepositoryImpl():AuthorizationSharedRepository{
    override fun signIn(userName: String, password: String, apiCallBack: ApiCallBack<Any>) {
       smartRequest(apiCallBack)
    }

    override fun forgotPassword(email: String, apiCallBack: ApiCallBack<Any>) {
        smartRequest(apiCallBack)
    }

}