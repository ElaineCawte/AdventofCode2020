import java.io.File
import java.lang.Exception

val instructions = File("input").readLines()

//parse ops
fun getops(i : List<String>):List<Pair<String,Int>>{
    val thestuff = i.map { Pair(it.split(" ")[0],it.split(" ")[1].toInt())}
    return thestuff
}

//run the code
fun ac(inlist : MutableList<Pair<String,Int>>)
{
    var accu = 0
    val hitlist = mutableListOf<Int>()
    //process the instruction
    fun process(thelist :MutableList<Pair<String,Int>>, ins: Pair<String,Int>, i : Int){
        // find the instructions which go to the end
        if (i >= thelist.lastIndex){
            println("The accumulator contains ")
            println(accu)
            return
            }
            else
            {
                //only do it one loop
                if(hitlist.contains(i))
                    return
                if (ins.first == "acc") {
                    accu = accu + ins.second
                    hitlist.add(i)
                    process(thelist, thelist[i + 1], i + 1)
                }
                if (ins.first == "jmp") {
                    hitlist.add(i)
                    process(thelist, thelist[i + ins.second], i + ins.second)
                }
                if (ins.first == "nop") {
                    hitlist.add(i)
                    process(thelist, thelist[i + 1], i + 1)
                }
            }
    }

    fun fixtheList(firstList : MutableList<Pair<String,Int>>, operationToChange : String , operationToChangeTo : String)
    {
        firstList.forEachIndexed() {
            index, pair ->
            val newlist = firstList.toMutableList()
            if(pair.first.equals(operationToChange)) {
                accu = 0
                newlist[index] = Pair(operationToChangeTo,firstList[index].second)
                hitlist.clear()
                process(newlist, newlist[0], 0)
            }
        }
    }

    fixtheList(inlist,"jmp","nop")
}



fun main(){
    val ops = getops(instructions)
    ac(ops.toMutableList())
}

main()