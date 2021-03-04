package am.codeschool.theguardianapi.model.data.pojo


import androidx.annotation.Keep

@Keep
data class Section(
    val response: Response
) {
    @Keep
    data class Response(
        val results: List<Result>,
        val status: String, // ok
        val total: Int, // 75
        val userTier: String // developer
    ) {
        @Keep
        data class Result(
            val apiUrl: String, // https://content.guardianapis.com/about
            val editions: List<Edition>,
            val id: String, // about
            val webTitle: String, // About
            val webUrl: String, // https://www.theguardian.com/about
            var favorite:Boolean
        )
        {
            @Keep
            data class Edition(
                val apiUrl: String, // https://content.guardianapis.com/about
                val code: String, // default
                val id: String, // about
                val webTitle: String, // About
                val webUrl: String // https://www.theguardian.com/about
            )
        }
    }
}