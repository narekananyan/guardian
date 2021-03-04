package am.codeschool.theguardian.model.retrofit

import am.codeschool.theguardianapi.model.retrofit.ApiCall


object Common {
    const val API_KEY="4048a17a-3490-4917-9190-ad35ac5449a4"
    private const val BASE_URL="https://content.guardianapis.com/"

    val retrofitServices: ApiCall?
    get() = RetrofitClient.getClient(BASE_URL)?.create(ApiCall::class.java)
}