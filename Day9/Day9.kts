package day9
import java.io.File

val numbers = File("input").readLines().map { it.toLong() }

//split first 25 numbers from the list
fun getpreambles(li : List<Long>): MutableList<Pair<List<Long>,Long>> =
        li.windowed(26).fold(mutableListOf<Pair<List<Long>,Long>>(), {
        acc, list ->
        acc.add(Pair(list.slice(0..24),list[25]))
        acc
    })

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
    val m = getpreambles(numbers).fold(mutableListOf<Pair<MutableList<Pair<Long,Long>>,Long>>(),{
        acc, pair ->
        acc.add(Pair(preamblePairs(pair.first),pair.second))
        acc
    })

    val theone = m.filter { !aresumof(it.second,it.first) }.toList()[0].second
    println(theone)
}
/*
fun findtheweakness(): List<List<Long>>{
    val sumtoget : Long = 776203571
    for(i in 1 until numbers.size)
    {
        val n = numbers.windowed(i).filter { it.sum() == sumtoget }

    }
}

fun part2()
{
println(findtheweakness())
}
*/
fun main()
{
    part1()
   // part2()
}

main()