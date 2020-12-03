import java.io.File

val numbers: List<String> = File("input1").readLines()
for(number in numbers)
        for(othernumber in numbers)
            for(othernumber2 in numbers)
            if(number.toInt() + othernumber.toInt() + othernumber2.toInt() == 2020)
                 println(number.toInt() * othernumber.toInt() * othernumber2.toInt())