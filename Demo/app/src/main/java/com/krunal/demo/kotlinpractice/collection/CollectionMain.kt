package com.krunal.demo.kotlinpractice.collection

fun main() {

    // Array
    println("\nArray\n")
    val array = Array(3) { 0 }
    array[0] = 1
    array[1] = 2
    array[2] = 3
    println(array.contentToString())


    // List
    println("\nList\n")
    val numList = mutableListOf<Int>()
    numList.addAll(array)
    numList.addAll(listOf(4, 3, 2, 5, 3))
    println(numList)

    // Iterator
    println("\nIterator\n")
    val iterator = numList.iterator().withIndex()
    while (iterator.hasNext()) {
        print("${iterator.next().value}: ${iterator.next().value}")
    }
    println()

    // Progression
    println("\nProgression\n")
    for (i in 1..10 step 2) {
        print("$i ")
    }
    println()

    for (i in 1 until 10 step 2) {
        print("$i ")
    }
    println()

    for (i in 10 downTo 1 step 2) {
        print("$i ")
    }
    println()

    for (i in (10 downTo 1).reversed() step 2) {
        print("$i ")
    }
    println()

    // Sequence
    println("\nSequence\n")
    var charSeq = sequenceOf('a', 'b', 'c', 'd')
    charSeq = ('a'..'k').asSequence()
    charSeq.filter { it in listOf('a', 'e', 'i', 'o', 'u') }.map { print(it); "$it" }.count()
    println()
    val oddNumSeq = generateSequence(2) { it + 2 }
    println(oddNumSeq.take(10).toList())
    val topTen = generateSequence(1) { if (it < 10) it + 1 else null }
    println(topTen.toList())

    val oddSeq = sequence {
        yield(1)
        yieldAll(listOf(3, 5, 7))
    }
    println(oddSeq.toList())

    // Transformation
    println("\nTransformation\n")
    val numStrList = listOf("1", "2", "3", "4", "5", "6", "7", "4", "8", "2")
    numStrList.map { it.toInt() }.mapIndexedNotNull { index, v -> if (index == 0) null else v }
        .forEach(::print)
    println()

    val numMap = mutableMapOf(
        "one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5
    )
    val newNumMap: MutableMap<String, String> = mutableMapOf()
    numMap.mapValuesTo(newNumMap) { "${it.value} -> " }
    println(newNumMap)

    println(newNumMap.values zip newNumMap.keys)
    println(newNumMap.values.zip(newNumMap.keys) { value, key ->
        "$value $key"
    })

    println(numStrList.associateWith { it.length })
    println(numStrList.withIndex().associateBy { it.index }.mapValues { it.value.value })

    val numberSets = listOf(setOf(1, 2, 3), setOf(4, 5, 6), setOf(1, 2))
    println(numberSets.flatten())
    println(numberSets.flatMap { it })

    println(numStrList.joinToString(
        separator = " -> ",
        prefix = "start: ",
        postfix = " :end",
        limit = 4,
        truncated = "more..."
    ) { "${it}x" })

    // Filtering
    println("\nFiltering\n")

    val nums = (1..20).toMutableList()
    println("even: ${nums.filter { it % 2 == 0 }}")
    println("odd: ${nums.filterNot { it % 2 == 0 }}")
    val nullableNums = mutableListOf(null, 1, 2, null)
    println(nullableNums.filterNotNull())
    println("large: ${nums.filterIndexed { index, _ -> index > 10 }}")

    val anyType = listOf(null, 1, "two", 3.0, "four")
    println("strings: ${anyType.filterIsInstance<String?>()}")

    val (odd, even) = nums.partition { it % 2 != 0 }
    println("$odd, $even")

    println("any > 20 ${nums.any { it > 20 }}")
    println("none > 20 ${nums.none { it > 20 }}")
    println("all < 5 ${nums.all { it > 20 }}")

    // Plus and minus operators
    println("\nPlus and minus operators\n")
    val l1 = listOf(1, 3, 5)
    val l2 = listOf(2, 4, 5)
    println(l1 + l2 + "str" - 5)

    // Grouping
    println("\nGrouping\n")
    println(numStrList.groupBy { if (it.toInt() % 2 == 0) "even" else "odd" })
    println(numStrList.groupingBy { if (it.toInt() % 2 == 0) "even" else "odd" }.eachCount())

    // Retrieve collection parts
    println("\nRetrieve collection parts\n")
    val numbers = mutableListOf("one", "two", "three", "four", "five", "six")
    println(nums.slice(5..10))
    println(nums.slice(5 until 10 step 2))
    println(nums.slice(setOf(2, 3, 5)))
    println(nums.subList(5, 10))

    println(nums.take(5))
    println(nums.takeLast(5))
    println(nums.drop(5))
    println(nums.dropLast(5))

    println(nums.takeWhile { it < 10 })
    println(nums.takeLastWhile { it < 10 })
    println(nums.dropWhile { it < 10 })
    println(nums.dropLastWhile { it > 10 })

    println(nums.chunked(5))
    println(nums.windowed(5, step = 3))
    println(nums.windowed(5, step = 3, partialWindows = true))
    println(nums.zipWithNext())

    // Retrieve single elements
    println("\nRetrieve single elements\n")
    val numSet = numbers.toSet()
    println(numSet.elementAt(4))
//    println(numSet.get(4))
    println(numSet.elementAtOrElse(10) { it * 10 })
    println(numSet.elementAtOrNull(10))

    println("${numSet.first()} -> ${numSet.last { it.length > 4 }}")
    println("${nums.firstOrNull { it > 10 }} -> ${nums.findLast { it > 15 }}")

    println(numSet.random())
    println(emptyList<String>().randomOrNull())
    println(emptyList<String>().isNotEmpty())

    // Ordering
    println("\nOrdering\n")
    val randomList = nums.shuffled()
    println(randomList)
    println(randomList.sortedDescending())
    println(randomList.sortedBy { it % 5 })
    println(randomList.sortedWith { i, j -> (i % 5) - (j % 5) })

    val revNum1 = numbers.reversed()
    val revNum2 = numbers.asReversed()
    println("$revNum1 $revNum2")
    numbers.removeLast()
    revNum2.removeLast()
    println("$revNum1 $revNum2")
    println(numbers)

    // Aggregate operations
    println("\nAggregate operations\n")
    println(setOf(2, 5, 3, 1).min())
    println(emptySet<Int>().maxOrNull())
    println(nums.average())
    println(nums.count())
    println(nums.sum())
    println(nums.sumOf { it * 2 })

    println(nums.fold(0) { acc, i -> acc + i })
    println(nums.reduce { acc, i -> acc + i })
    println(nums.foldRight(100) { acc, i -> acc - i })
    println(nums.reduceRight { acc, i -> acc - i })
    println(nums.runningFold(0) { acc, i -> acc + i })

    // Collection write operations
    println("\nCollection write operations\n")
    val mNums = nums.toMutableList()
    mNums.clear()
    println(mNums)
    println(mNums.addAll((1..10)))
    mNums += 100
    mNums -= 4..7
    mNums.addAll(listOf(5, 5, 10, 9, 9, 4, 3, 2))
    mNums.add(5)
    println(mNums)
    mNums.remove(10)
    mNums.removeAll(listOf(5))
    println(mNums)
    mNums.retainAll { it % 2 == 0 }
    println(mNums)
    mNums[mNums.lastIndex] = 101
    println(mNums)
    val mList = mutableListOf(1, 2, 3)
    mList.fill(0)
    println(mList)

    // Find element positions
    println("\nFind element positions\n")
    println(mNums.indexOf(9))
    println(mNums.lastIndexOf(2))
    println(mNums.indexOfFirst { it > 8 })

    println(numbers.binarySearch("two"))
    println(numbers.binarySearch("z"))
    println(numbers.binarySearch("two", 0, 2))

    // Set-specific operations
    println("\nSet-specific operations\n")
    val numSet1 = mutableSetOf(1, 2, 3, 4, 5, 6)
    val numSet2 = mutableSetOf(4, 5, 6, 7, 8, 9)
    println("union: ${numSet1 union numSet2}")
    println("intersect: ${numSet1 intersect numSet2}")
    println("subtract: ${numSet1 subtract numSet2}")

    // Map-specific operations
    println("\nMap-specific operations\n")
    println("${numMap.keys} ${numMap.values}")
    println(numMap.entries)
    println(numMap["one"])
    println(numMap["nine"])
    println(numMap.getOrDefault("nine", 9))
    println(numMap.getOrElse("nine") { 9 })
    val two by numMap
//    val nine by numMap
    println(two)
    numMap += ("ten" to 10)
    println(numMap)
    numMap["nine"] = 9
    numMap.remove("ten")
    numMap.remove("nine", 10)
    numMap -= "five"
    println(numMap)

    // Custom quadruple
    println("\nCustom quadruple\n")
    val quadruple: Quadruple<String, Char, Any, Number> = Quadruple("A", 'b', true, 10)
    println(quadruple)
    println(quadruple.first)
    println(quadruple.toList())
}
