import java.io.File

val numbers: String = File("input").readText()
fun parsenumbers(input: String): List<String> = input.split("\r\n\r\n")

fun main(){
    println(part1(parsenumbers(numbers)))
    println(part2(parsenumbers(numbers)))
}

fun part1(input : List<String>): Int {
    return input.map(){ it-> countUniq(it) }.sum()
}
fun part2(input : List<String>): Int {
    return input.map(){ it -> countAll(it)}.sum()
}

fun countAll(input: String): Int {
    val uniq: List<MutableSet<Char>> = input.lines().map {it.fold( mutableSetOf<Char>(),{ acc, c -> acc.add(c); acc} )}
    val finalSet = uniq.reduce { acc, it -> acc.apply { retainAll(it) } }
    return  finalSet.size
}
fun countUniq(input: String): Int
{
    val thestring = input.lines().joinToString(separator = "")
    val uniques = thestring.fold(mutableSetOf<Char>()) { acc, c -> acc.add(c); acc }
    return uniques.size
}

main()