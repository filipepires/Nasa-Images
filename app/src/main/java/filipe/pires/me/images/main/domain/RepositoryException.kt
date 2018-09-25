package filipe.pires.me.images.main.domain

import java.io.IOException


class RepositoryException(
        val type: RepositoryExceptionType,
        override val message: String
) : IOException()