package am.codeschool.authorization

import am.codeschool.common.net.ApiCallBack
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseAuthorizationViewModel(private val authorizationSharedRepository: AuthorizationSharedRepository) :
    ViewModel() {

     var signInLiveData: MutableLiveData<Any?> = MutableLiveData()
     var forgotPassLiveData: MutableLiveData<Any?> = MutableLiveData()

    fun signIn(userName: String, password: String) {
        authorizationSharedRepository.signIn(userName, password, object : ApiCallBack<Any> {
            override fun success(response: Any) {
                signInLiveData.value = response
            }

            override fun failure() {
                signInLiveData.value = null

            }

        })
    }
    fun forgotPassword(email: String) {
        authorizationSharedRepository.forgotPassword(email,  object : ApiCallBack<Any> {
            override fun success(response: Any) {
                forgotPassLiveData.value = response
            }

            override fun failure() {
                forgotPassLiveData.value = null

            }

        })
    }
}