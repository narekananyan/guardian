package am.codeschool.common.net

fun <T>smartRequest(apiCallBack:ApiCallBack<T>){

}

interface ApiCallBack<T>{
    fun success(response:T)
    fun failure()
}