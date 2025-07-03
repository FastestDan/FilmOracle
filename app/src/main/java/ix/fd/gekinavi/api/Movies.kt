package ix.fd.gekinavi.api

data class Movies(
    val docs: List<Movie>,
    val total: Int,
    val limit: Int,
    val page: Int,
    val pages: Int
)
