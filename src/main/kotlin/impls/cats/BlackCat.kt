package impls.cats

import traits.Cat
import traits.CatSex
import traits.Saleable

class BlackCat(override val name: String, override val sex: CatSex, override var age: Int) : Cat(name, age, sex),
    Saleable {
    override fun getPrice()=350.0

    override fun toString(): String {
        return "Cat: Type[OrangeCat]\n Name: [ $name ]\n Sex: [$sex]\n age: [ $age ]"
    }
}