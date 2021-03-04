package am.codeschool.theguardianapi.model.repository

import am.codeschool.theguardianapi.model.data.ApiCallBack
import am.codeschool.theguardianapi.model.data.pojo.Content
import am.codeschool.theguardianapi.model.data.pojo.Description
import am.codeschool.theguardianapi.model.data.pojo.Section
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface SearchRepository {
    //share prefances ,delegate

     fun <T> call(callBack: ApiCallBack<T>): Callback<T> {
        return object : Callback<T> {

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if(response.isSuccessful){
                    callBack.onSuccess(response.body())
                }else callBack.onError(response.message())
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                callBack.onFailure(t.message)
            }

        }
    }
    fun searchFromFilter(mutableMap: MutableMap<String,String>,callBack : ApiCallBack<Content>)
    fun searchContent(callBack : ApiCallBack<Content>)
    fun loadPathData(url:String,callBack : ApiCallBack<Description>)
    fun callSection(callBack : ApiCallBack<Section>)
    fun callSectionItem(url:String,callBack : ApiCallBack<Content>)
}