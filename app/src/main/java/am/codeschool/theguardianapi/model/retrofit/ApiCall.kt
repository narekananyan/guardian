package am.codeschool.theguardianapi.model.retrofit
import am.codeschool.theguardian.model.retrofit.Common
import am.codeschool.theguardianapi.model.data.pojo.Content
import am.codeschool.theguardianapi.model.data.pojo.Description
import am.codeschool.theguardianapi.model.data.pojo.Section
import retrofit2.Call
import retrofit2.http.*

interface ApiCall {
    @Headers("api-key:${Common.API_KEY}")
    @GET("search?order-by=newest&page-size=50&show-fields=thumbnail")
    fun getContent():Call<Content>
    @Headers("api-key:${Common.API_KEY}")
    @GET("search?")
    fun callFromFilter(@QueryMap map: MutableMap<String,String>,
                       @Query("show-fields")fields:String="thumbnail",
                       @Query("page-size") page:String="30"):Call<Content>
    @Headers("api-key:${Common.API_KEY}")
    @GET()
    fun callPathData(@Url url: String,
                     @Query("show-fields")
                     fields:String="thumbnail,headline,body"):Call<Description>

    @Headers("api-key:${Common.API_KEY}")
    @GET("sections?")
    fun callSection():Call<Section>

    @Headers("api-key:${Common.API_KEY}")
    @GET()
    fun callSectionItem(@Url q:String ,
                    @Query("show-fields")fields:String="thumbnail",
                    @Query("order-by") order: String="newest",
                    @Query("page-size") page:String="30"):Call<Content>
}
