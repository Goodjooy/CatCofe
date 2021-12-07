package util

class ResultException(err: Any, info: Any) : Exception("Result Is Err: [ $err | $info ]")

public sealed class Result<D, E> {
    companion object {
        public fun <D, E> ok(data: D): Result<D, E> {
            return Ok(data)
        }
        public fun <E>emptyOk():Result<Unit,E>{
            return ok(Unit)
        }
        public fun <D, E> err(err: E): Result<D, E> {
            return Err(err)
        }
    }

    public fun except(info: Any): D {
        return when (this) {
            is Ok -> data
            is Err -> throw ResultException(err.toString(), info)
        }
    }

    public fun <R> andThen(fn: (D) -> Result<R, E>): Result<R, E> {
        return when (this) {
            is Ok -> fn(this.data)
            is Err -> err(err)
        }
    }

    public fun <E2> orElse(fn: (E) -> Result<D, E2>): Result<D, E2> {
        return when (this) {
            is Ok -> ok(data)
            is Err -> fn(err)
        }
    }

    public fun toSome(): Option<D> {
        return when (this) {
            is Ok -> Option.some(data)
            is Err -> Option.none()
        }
    }

    public fun unwrap(): D {
        return when (this) {
            is Ok -> this.data
            is Err -> throw ResultException(err.toString(), "Unwrap `Err` Result Data")
        }
    }

    public fun unwrapOr(or: D): D {
        return when (this) {
            is Ok -> this.data
            is Err -> or
        }
    }

    public fun toNull(): D? {
        return when (this) {
            is Ok -> data
            is Err -> null
        }
    }

    public fun isOk(): Boolean {
        return this is Ok;
    }

    public fun isErr(): Boolean {
        return this is Err;
    }

    override fun toString(): String {
        return when (this) {
            is Ok -> "Ok [ $data ]"
            is Err -> "Err [ $err ]"
        }
    }

}

private data class Ok<D, E>(val data: D) : Result<D, E>(){
    override fun toString(): String {
        return " Ok [ $data ]"
    }
}
private data class Err<D, E>(val err: E) : Result<D, E>(){
    override fun toString(): String {
        return "Err [ $err ]"
    }
}
