import java.io.File

val numbers = File("input").readLines().map { it.toInt() }

fun part1() : Int {
    val a = numbers.sorted().foldIndexed(mutableListOf<Int>(),{ index, acc, i ->
        if(index > 0) { acc.add(i - numbers.sorted()[index - 1]) }
        acc
    })
    return a.count { it == 1 } * (a.count { it == 3 } + 1)
}

fun part2() : Long {
    val num = numbers.sorted()
    val matches: MutableMap<Int,Long> = mutableMapOf(0 to 1L)

    num.drop(1).forEach { i ->
        matches[i] = (1 .. 3).map { a ->
            matches.getOrDefault(i - a, 0)
        }.sum()
    }

    return matches.getValue(num.last())
}

fun main() {
    println("part 1 ")
    println(part1())
    println("part 2 ")
    println(part2())
}
main()