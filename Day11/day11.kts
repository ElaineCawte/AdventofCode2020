import java.io.File

val numbers = File("input").readLines().asSequence()

// empty seat L
// occupied seat #
// floor tile .
// if seat empty and no seats occupied, it becomes occupied
// if seat is occupied and 4 or more occupied adjacent it becomes empty

//returns a list of tiles within the range of the coordinate specified
//edges are denoted as floor tiles
fun withinrange(x:Int,y:Int,l:Sequence<String>):MutableList<Char>
{
    val list = mutableListOf<Char>()
    for (along in x-1 until x+2) {
        for (down in y - 1 until y + 2) {
            list.add(l.elementAtOrNull(down)?.getOrNull(along) ?: '.')
        }
    }
    return list
}

fun part1()
{

}



fun main()
{
    println("part 1 ")
    part1()
}
main()