package util



class OptionException(info: Any) : Exception("Option Is None: [ $info ]")

public sealed class Option<T> {
    companion object {
        public fun <T> some(data: T): Option<T> {
            return Some(data)
        }

        public fun <T> none(): Option<T> {
            return None(Unit)
        }

        public fun <T> fromNull(data: T?): Option<T> {
            return when {
                data != null -> some(data)
                else -> none()
            }
        }
    }

    public fun expect(info: Any): T {
        return when (this) {
            is Some -> this.data;
            is None -> throw OptionException(info)
        }
    }

    public fun <R> andThen(fn: (T) -> Option<R>): Option<R> {
        return when (this) {
            is Some -> fn(this.data)
            is None -> none()
        }
    }

    public fun orElse(fn: () -> Option<T>): Option<T> {
        return when (this) {
            is Some -> this
            is None -> fn()
        }
    }

    public fun <E>toOk(fn:()->E):Result<T,E>{
        return when(this){
            is Some -> Result.ok(data)
            is None -> Result.err(fn())
        }
    }

    public fun unwrap(): T {
        return when (this) {
            is Some -> this.data
            is None -> throw OptionException("Unwrap `None` Option Data")
        }
    }

    public fun unwrapOr(or: T): T {
        return when (this) {
            is Some -> this.data
            is None -> or
        }
    }

    public fun toNull(): T? {
        return when (this) {
            is Some -> data
            is None -> null
        }
    }

    public fun isSome(): Boolean {
        return this is Some;
    }

    public fun isNone(): Boolean {
        return this is None;
    }

    override fun toString(): String {
        return when (this){
            is Some -> "Some [ $data ]"
            is None -> "None"
        }
    }
}

private data class Some<T> (val data: T) : Option<T>(){
    override fun toString(): String {
        return "Some [ $data ]"
    }
}
private data class None<T> (private val __empty__: Unit) : Option<T>(){
    override fun toString(): String {
        return "None"
    }
}