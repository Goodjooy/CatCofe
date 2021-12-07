package traits

enum class CatSex {
    MALE, FEMALE;

    override fun toString(): String {
        return if (this == MALE) {
            "male"
        } else {
            "female"
        }
    }
}

interface Saleable {
    fun getPrice(): Double
}



abstract class Cat
    (
    open val name: String,
    open var age: Int,
    open val sex: CatSex
) {
    abstract override fun toString(): String
}

