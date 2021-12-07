package traits

import AddCatToLockedQueueException
import impls.Customer
import java.util.*

interface CatCafe {
    fun <T> newCats(cats: CatQueueBuilder<T>) where T : Cat, T : Saleable;
    fun forCustomer(customer: Customer);
    fun close();
}


public class CatQueueBuilder<T>()
        where T : Cat{
    private val list = LinkedList<T>()
    private var locked = false

    public fun addCat(cat: T): CatQueueBuilder<T> {
        if (locked)
            throw AddCatToLockedQueueException()
        list.add(cat)
        return this
    }

    public fun build(): Iterable<T> {
        locked = true
        return list.asIterable()
    }
}