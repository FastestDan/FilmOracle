package ix.fd.gekinavi.api

data class PersonsForMovie(
    val id: Int,
    val photo: String,
    val name: String,
    val enName: String,
    val description: String,
    val profession: String,
    val enProfession: String
)
