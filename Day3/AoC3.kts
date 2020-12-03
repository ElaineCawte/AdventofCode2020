package day3
import java.io.File

val numbers: List<String> = File("input").readLines()

fun iterate(input: List<String>, d:Int, r:Int):Int{
    var count = 0
    var i :List<Int> = listOf(0,0)
    do{
        if(i[1] + r >= input[i[0]].length)
            i = listOf(i[0]+d, i[1] + r  - input[0].length)
        else
            i = listOf(i[0]+d,i[1]+r)

        if (input[i[0]][i[1]].compareTo('#') == 0) {
            count++
        }
    }while(i[0] < input.size-1)
    println(count)
        return count
}

fun part1():Int
{
    return iterate(numbers, 1, 3)
}

fun part2():Long
{
    val list = listOf(Pair(1, 1), Pair(3,1), Pair(5,1), Pair(7,1), Pair(1,2))
    var part2 : Long = 1
    for(item in list)
        part2 *= iterate(numbers,item.second,item.first)
    return part2
}

fun main(){
    println(part1())
    println(part2())
}

main()

