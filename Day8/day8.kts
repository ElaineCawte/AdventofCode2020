import java.io.File

val instructions = File("input").readLines()

fun getops(i : List<String>):List<Pair<String,Int>>{
    val thestuff = i.map { Pair(it.split(" ")[0],it.split(" ")[1].toInt())}
    return thestuff
}

fun ac(inlist : List<Pair<String,Int>>)
{
    var accu = 0
    val hitlist = mutableListOf<Int>()

    //process the instruction
    fun process(ins: Pair<String,Int>, i : Int)
    {
        if(hitlist.contains(i))
        {
            println("gottem all")
            println(accu)
            return
        }
        if(ins.first == "acc")
        {
            accu = accu + ins.second
            hitlist.add(i)
            process(inlist[i+1],i+1)
        }
        if(ins.first == "jmp")
        {
            hitlist.add(i)
            process(inlist[i+ins.second],i+ins.second)
        }
        if(ins.first == "nop")
        {
            hitlist.add(i)
            process(inlist[i+1],i+1)
        }
    }
    process(inlist[0],0)
}

fun main(){
    val ops = getops(instructions)
    ac(ops)
}

main()