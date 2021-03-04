package am.codeschool.theguardianapi.model.data.impl

import am.codeschool.theguardianapi.model.data.ApiCallBack
import am.codeschool.theguardianapi.model.repository.SearchRepository
import am.codeschool.theguardianapi.model.retrofit.ApiCall
import am.codeschool.theguardianapi.model.data.pojo.Content
import am.codeschool.theguardianapi.model.data.pojo.Description
import am.codeschool.theguardianapi.model.data.pojo.Section

class SearchRepositoryImpl(private val call: ApiCall) : SearchRepository {
    override fun searchContent(callBack: ApiCallBack<Content>) {
        call.getContent().enqueue(call(callBack) )
    }

    override fun searchFromFilter(
        mutableMap: MutableMap<String, String>,
        callBack: ApiCallBack<Content>,
    ) {
        call.callFromFilter(mutableMap).enqueue(call(callBack))
    }

    override fun loadPathData(url: String, callBack: ApiCallBack<Description>) {
        call.callPathData(url).enqueue(call(callBack))
    }

    override fun callSection(callBack: ApiCallBack<Section>) {
        call.callSection().enqueue(call(callBack))
    }

    override fun callSectionItem(url: String, callBack: ApiCallBack<Content>) {
        call.callSectionItem(url).enqueue(call(callBack))
    }


}