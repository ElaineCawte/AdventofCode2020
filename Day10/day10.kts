import java.io.File

val numbers = File("input").readLines().map { it.toInt() }

fun part1() : Int {
    val a = numbers.sorted().foldIndexed(mutableListOf<Int>(),{ index, acc, i ->
        if(index > 0) { acc.add(i - numbers.sorted()[index - 1]) }
        acc
    })
    return a.count { it == 1 } * (a.count { it == 3 } + 1)
}

fun main() {
    println("part 1 ")
    println(part1())
}
main()