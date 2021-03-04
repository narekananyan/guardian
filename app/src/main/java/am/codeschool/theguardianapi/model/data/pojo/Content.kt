package am.codeschool.theguardianapi.model.data.pojo


import androidx.annotation.Keep

@Keep
data class Content(
    val response: Response
) {
    @Keep
    data class Response(
        val currentPage: Int, // 1
        val orderBy: String, // newest
        val pageSize: Int, // 30
        val pages: Int, // 75002
        val results: List<Result>,
        val startIndex: Int, // 1
        val status: String, // ok
        val total: Int, // 2250049
        val userTier: String // developer
    ) {
        @Keep
        data class Result(
            var favorite:Boolean,
            val apiUrl: String, // https://content.guardianapis.com/sport/live/2021/feb/18/australian-open-2021-tennis-live-scores-day-11-serena-williams-naomi-osaka-novak-djokovic-tv-schedule-draw-results-fixtures-order-of-play-melbourne-park-coverage-latest-news-updates
            val fields: Fields,
            val id: String, // sport/live/2021/feb/18/australian-open-2021-tennis-live-scores-day-11-serena-williams-naomi-osaka-novak-djokovic-tv-schedule-draw-results-fixtures-order-of-play-melbourne-park-coverage-latest-news-updates
            val isHosted: Boolean, // false
            val pillarId: String, // pillar/sport
            val pillarName: String, // Sport
            val sectionId: String, // sport
            val sectionName: String, // Sport
            val type: String, // liveblog
            val webPublicationDate: String, // 2021-02-18T08:10:25Z
            val webTitle: String, // Novak Djokovic v Aslan Karatsev: Australian Open men's semi-final – live!
            val webUrl: String // https://www.theguardian.com/sport/live/2021/feb/18/australian-open-2021-tennis-live-scores-day-11-serena-williams-naomi-osaka-novak-djokovic-tv-schedule-draw-results-fixtures-order-of-play-melbourne-park-coverage-latest-news-updates
        ) {
            @Keep
            data class Fields(
                val thumbnail: String // https://media.guim.co.uk/0a0ded600ae757df60ff72f643a9127234998861/0_22_4800_2880/500.jpg
            )
        }
    }
}