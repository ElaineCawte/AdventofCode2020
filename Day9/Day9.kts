package day9
import java.io.File

val numbers = File("input").readLines().map { it.toLong() }

//get preamble using window of specified size
fun getpreambles(li : List<Long>,windowsize : Int): MutableList<Pair<List<Long>,Long>> =
        li.windowed(windowsize).fold(mutableListOf<Pair<List<Long>,Long>>(), {
        acc, list ->
        acc.add(Pair(list.slice(0..windowsize-2),list[windowsize-1]))
        acc
    })

//get list of preamble numbers into permissable pairs w/ no duplicates
fun preamblePairs(preamble : List<Long>):MutableList<Pair<Long,Long>> =
        preamble.fold(mutableListOf<Pair<Long,Long>>(), { acc, _ ->
        for (i in 0 until preamble.size)
            for (j in 0 until preamble.size)
                if (i != j) {
                    acc.add(Pair(preamble[i], preamble[j]))
                }
        acc
    })

// true when numbers add up to tested number
fun aresumof(test : Long, preamble : MutableList<Pair<Long,Long>>):Boolean = (true in preamble.fold(mutableListOf<Boolean>(), { acc, a ->
            if (a.first + a.second == test) {
                acc.add(true)
            }
            else
                acc.add(false)
            acc
        }))

// return the first number that does not match the rules
fun part1():Long {
    val m = getpreambles(numbers,26).fold(mutableListOf<Pair<MutableList<Pair<Long,Long>>,Long>>(),{
        acc, pair ->
        acc.add(Pair(preamblePairs(pair.first),pair.second))
        acc
    })

    return m.filter { !aresumof(it.second,it.first) }.toList()[0].second
}

//call window algorithm using different window size to find the numbers that add up to the sum to get
fun part2(sumtoget:Long):Long{
    fun getthesum(i : Int): MutableList<Pair<List<Long>,Long>> = getpreambles(numbers, i).filter { it.first.sum() == sumtoget }.toMutableList()

    for(i in 1 until numbers.size) {
        val n = getthesum(i)
            if(n.size > 0 && n[0].first.size > 2) {
            return (n.first().first.sortedDescending()[0] + n.first().first.sortedDescending()[n.first().first.size-1])
        }
    }
    return 0
}

fun main()
{
    val p1 = part1()
    val p2 = part2(p1)

    println("Part 1 ")
    println(p1)
    println("Part 2")
    println(p2)
}

main()