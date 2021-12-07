package impls.cats

import traits.Cat
import traits.CatSex
import traits.Saleable

class OrangeCat(
    override val name: String,
    override val sex: CatSex,
    override var age: Int,
    var fat: Boolean = false
) : Cat(name, age, sex), Saleable {
    override fun getPrice() = 200.0

    override fun toString(): String {
        return "Cat: Type[OrangeCat]\n Name: [ $name ]\n Sex: [$sex]\n age: [ $age ] fat: [$fat]"
    }

    public fun isFat() = fat
}