package err

class InsufficientBalanceException(exp:Double,res:Double):Exception("Expect At least $exp, but only have $res")