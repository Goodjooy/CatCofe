package impls

import java.time.LocalDateTime
import java.time.LocalTime

class Customer(private val name: String, private val playTime: Int, private val arriveTime: LocalDateTime) {
    override fun toString() = "顾客: $name\n 撸猫次数: $playTime \n 到达时间: $arriveTime"

    public fun getPlayTime()=playTime
    public fun getArriveTime()=arriveTime


}