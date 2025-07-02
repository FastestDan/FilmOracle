package ix.fd.gekinavi.api

data class Movie(
    val id: Int,
    val name: String,
    val alternativeName: String,
    val type: String,
    val typeNumber: Int,
    val year: Int,
    val description: String,
    val shortDescription: String,
    val ratings: List<Float>,
    val poster: List<String>,
    val genres: List<String>,
    val countries: List<String>,
    val persons: List<String>,
)