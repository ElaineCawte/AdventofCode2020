import java.io.File

val numbers: List<String> = File("input2").readLines()
var count=0

for(entry in numbers) {
    var parts = entry.split("\\s".toRegex())
    var minmax = parts[0].split("-")
    var string = parts[2].toCharArray()
    var letter = parts[1].replace(":","")
// 1st part
//   var inc = string.count{ it.equals(letter[0].toChar()) }
 //   if( inc >= minmax[0].toInt() && inc <= minmax[1].toInt())
 //       count = count + 1

   if(string[minmax[0].toInt()-1].equals(letter[0].toChar()) && !string[minmax[1].toInt()-1].equals(letter[0].toChar()) )
       count = count + 1
    if(!string[minmax[0].toInt()-1].equals(letter[0].toChar()) && string[minmax[1].toInt()-1].equals(letter[0].toChar()) )
       count=count+1

}
println(count)