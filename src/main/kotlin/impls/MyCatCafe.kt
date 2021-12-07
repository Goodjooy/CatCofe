package impls

import NoCatFoundException
import err.InsufficientBalanceException
import traits.Cat
import traits.CatCafe
import traits.CatQueueBuilder
import traits.Saleable
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class MyCatCafe private constructor(
    private var remain: Double,
    private val catHome: ArrayList<CarPair<Cat>>,
    private val customerRecord: LinkedList<Customer>
) :
    CatCafe {


    companion object {
        public fun new(): MyCatCafe {
            return MyCatCafe(0.0, ArrayList(), LinkedList())
        }

        public fun newInitRemain(initMoney: Double): MyCatCafe {
            return MyCatCafe(initMoney, ArrayList(), LinkedList())
        }
    }

    override fun <T> newCats(cats: CatQueueBuilder<T>) where T : Cat, T : Saleable {
        for (cat in cats.build()) {
            val price = cat.getPrice()
            if (price > remain)
                throw InsufficientBalanceException(price, remain)
            else {
                remain -= price;
                catHome.add(CarPair(cat))
            }
        }
    }

    override fun forCustomer(customer: Customer) {

        val resting = catHome.filter { p -> p.isResting() }

        if (resting.isEmpty())
            throw NoCatFoundException()
        else {
            val id = Random.nextInt(0, resting.size-1)
            val cat = resting[id]
            cat.intoWork()
            customerRecord.add(customer)
            remain += 15 * customer.getPlayTime();

            cat.leaveWork()
        }
    }

    override fun close() {
        val today=LocalDate.now()
        val todayCustomer= customerRecord.filter { customer -> customer.getArriveTime().toLocalDate() == today }
        val incomes=todayCustomer.map { customer -> 15*customer.getPlayTime() }
            .sum();
        todayCustomer.forEach { customer -> println(customer) }

        println("收入: $incomes")
    }
}

class CarPair<T : Cat>(private val cat: T, private var onWork: Boolean = false) {
    fun isResting() = !onWork;

    fun intoWork() {
        onWork = true
    }

    fun leaveWork() {
        onWork = false
    }
}