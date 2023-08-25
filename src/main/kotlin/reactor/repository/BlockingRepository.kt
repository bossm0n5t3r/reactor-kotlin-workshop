package reactor.repository

interface BlockingRepository<T> {

    fun save(value: T)

    fun findFirst(): T?

    fun findAll(): Iterable<T>

    fun findById(id: String): T?
}
