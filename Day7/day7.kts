import java.io.File

fun parseData(input : List<String>): MutableMap<String, List<Pair<Int, String>>>{
    fun sortbags(b : String):List<Pair<Int,String>>{
        if(b.contains("no other"))
            return emptyList()
        else
        {
            val thelist= b.split(",")
                    .fold(mutableListOf<Pair<Int,String>>(),{acc,c ->
                val match = Regex("(^\\d+)(\\D+)").find(c.trim())!!
                val (num,col) = match.destructured
                acc.add(Pair(num.toInt(),col.trimStart()))
                acc
            })
            return thelist
        }
    }
    val bags = input.map { Pair<String,List<Pair<Int,String>>>(it.split("contain")[0].trim(),sortbags(it.split("contain")[1])) }
    return bags.fold(mutableMapOf<String,List<Pair<Int,String>>>()){ acc,pair ->
        acc[pair.first] = pair.second
        acc
    }
}

fun part1(map : MutableMap<String, List<Pair<Int, String>>>):Int
{
    fun gettheBag(bagtoget: String, hit: MutableSet<String>){
        map.keys.map() { k -> map[k]!!.filter { v -> v.second.contains(bagtoget)  }.forEach() { _ ->
            println(bagtoget)
            print(" found in ")
            println(k)
            gettheBag(k, hit)
            hit.add(k)
            }
        }
    }
    val hitlist = mutableSetOf<String>()
    gettheBag("shiny gold",hitlist)
    return(hitlist.size)
}

fun bagCeption(map : MutableMap<String, List<Pair<Int, String>>>, a : String):Int
{
    return map[a]!!.map { pair -> pair.first*(1 + bagCeption(map,pair.second))}.sum()
}


val numbers: String = File("input").readText().replace(".","").replace("bags","").replace("bag","")

fun main(){
    val themap = parseData(numbers.lines())
    println(part1(themap))
println(bagCeption(themap,"shiny gold"))

}
main()