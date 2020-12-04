
import java.io.File

val numbers: String = File("input").readText()

// make a class for the passport object
class Passport( val bits : List<Pair<String,String>>)

//list of keys and eyecolors valid
val keys = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
val eyecolours = listOf("amb","blu","brn","gry","grn","hzl","oth")

// split numbers by the blank line - input = entire file. it = each passport record
fun getPassportList(input: String): List<Passport> =
        input.split("\r\n\r\n").map { getPassport(it) }

// make a passport record from the lines by flat mapping - transform chops ing them into pairs
fun getPassport(input:String): Passport = Passport(input.lines().flatMap { getPairs(it) })

//   split the record string into the kv pairs of the record
fun getPairs(input:String): List<Pair<String,String>> =
        input.split(' ')
                .map { Pair(it.split(':')[0],it.split(':')[1]) }

fun part1(): Int
{
    return validatePassports(getPassportList(numbers)).count()
}
fun part2():Int {
    //validate valid passports - am sure this could be more concise
    return validatePassports2(validatePassports(getPassportList(numbers))).count()
}

// true if valid
fun validatevalues(p : Passport):Boolean {
    for (pair in p.bits) {
        val key = pair.first
        val value = pair.second
        if (key == "byr")
            if (value.toInt() < 1920 || value.toInt() > 2002)
                return false
        if (key == "iyr")
            if (value.toInt() > 2020 || value.toInt() < 2010)
                return false
        if (key == "eyr")
            if (value.toInt() < 2020 || value.toInt() > 2030)
                return false
        if (key == "hgt") {
            if(value.takeLast(2) != "cm" && value.takeLast(2) != "in")
                return false
            if (value.takeLast(2) == "cm")
            {
                val cmvalue = value.removeSuffix("cm")
                    if(cmvalue.toInt() < 150 || cmvalue.toInt() > 193)
                        return false
            }
            else if (value.takeLast(2) == "in")
            {
                val invalue = value.removeSuffix("in")
                    if(invalue.toInt() < 59 || invalue.toInt() > 76)
                        return false
            }
        }
        if (key == "hcl") {
            if (!value[0].equals('#'))
                return false
            if(value.length != 7)
                return false
        }
        if (key == "ecl")
            if (!eyecolours.contains(value))
                return false
        if (key == "pid")
            if (value.length != 9)
                return false
    }
    return true
}

//this is a better way to validate
fun validatePassports2(input : MutableList<Passport>) : List<Passport> =
        input.fold(mutableListOf<Passport>(), { acc, passport -> if(validatevalues(passport)) acc.add(passport)
        acc
        })

//return list of valid passports using fold
fun validatePassports(input : List<Passport>) : MutableList<Passport>{
    val validPassports = mutableListOf<Passport>()
    for (passport in input) {
        val theList = passport.bits.fold(mutableListOf<String>(), { acc, keyvalues ->
            val k = keyvalues.first
            if (k != "cid") acc.add(k)
            acc
        })
        if(theList.containsAll(keys))
            validPassports.add(passport)
    }
    return validPassports
}

fun main()
{
    println(part1())
    println(part2())
}

main()