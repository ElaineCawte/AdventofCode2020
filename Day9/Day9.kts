package day9
import java.io.File

val numbers = File("input").readLines().map { it.toLong() }

//split first 25 numbers from the list
fun getpreambles(li : List<Long>): List<List<Long>> = li.windowed(25)

fun preamblePairs(preamble : List<Long>):MutableList<Pair<Long,Long>> =
        preamble.fold(mutableListOf<Pair<Long,Long>>(), { acc, _ ->
        for (i in 0 until preamble.size)
            for (j in 0 until preamble.size)
                if (i != j) {
                    acc.add(Pair(preamble[i], preamble[j]))
                }
        acc
    })


fun aresumof(test : Long, preamble : MutableList<Pair<Long,Long>>):Boolean = (true in preamble.fold(mutableListOf<Boolean>(), { acc, a ->
            if (a.first + a.second == test) {
                acc.add(true)
            }
            else
                acc.add(false)
            acc
        }))

fun part1() {
    val m = getpreambles(numbers).map { it -> preamblePairs(it) }
    val theone = numbers.filterIndexed() {
        i, l ->
        (i >= 25 && !aresumof(l,m[i-25]))
    }
    println(theone)
}

fun main()
{
    part1()
}

main()