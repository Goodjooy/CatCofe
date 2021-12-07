import impls.Customer
import impls.MyCatCafe
import impls.cats.BlackCat
import impls.cats.OrangeCat
import traits.CatQueueBuilder
import traits.CatSex

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import java.time.LocalDateTime

fun main(args: Array<String>) {
    val carCafe = MyCatCafe.newInitRemain(114145.0);

    carCafe.newCats(
        CatQueueBuilder<OrangeCat>()
            .addCat(OrangeCat("AAA", CatSex.FEMALE, 11))
            .addCat(OrangeCat("BBB", CatSex.MALE, 5))
    )
    carCafe.newCats(
        CatQueueBuilder<BlackCat>()
            .addCat(BlackCat("AABA", CatSex.MALE, 8))
            .addCat(BlackCat("VVV", CatSex.FEMALE, 5))
    )

    carCafe.forCustomer(Customer("A",14, LocalDateTime.now()));
    carCafe.forCustomer(Customer("e",1, LocalDateTime.now()));
    carCafe.forCustomer(Customer("b",144, LocalDateTime.now()));
    carCafe.forCustomer(Customer("c",0xff, LocalDateTime.now()));
    carCafe.forCustomer(Customer("Ad",0x14fffff, LocalDateTime.now()));

    carCafe.close()


}


