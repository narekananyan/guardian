package am.codeschool.theguardianapi.model.data


interface ApiCallBack<T> {
    fun onSuccess(response: T?)
    fun onFailure(error: String?){}
    fun onError(error: String){}
}