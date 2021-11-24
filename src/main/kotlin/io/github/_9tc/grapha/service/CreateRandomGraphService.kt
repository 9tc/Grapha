package io.github._9tc.grapha.service
import kotlin.random.Random

class CreateRandomGraphService(
    private val vertices: Long,
    private val percentage: Long
) {
    fun create(hasLabel : Boolean, seed: Long): String{
        var s: String
        val dataset : MutableList<Pair<Long, Long>> = mutableListOf()
        var edges = 0
        val random = Random(seed)

        for(u in 1 until vertices){
            for(v in (u+1)..vertices){
                if(random.nextInt(0, 100)< percentage){
                    ++edges
                    dataset.add(Pair(u, v))
                }
            }
        }

        s = "$vertices $edges\n"


        if(hasLabel){
            for(v in 1..vertices){
                s += v.toString() + " " + randomString(random, 3) + "\n"
            }
        }

        for (data in dataset){
            s += "${data.first} ${data.second}\n"
        }
        return s

    }

    private fun randomString(random: Random, length: Int): String{
        if(length <= 0) return ""
        return Char(random.nextInt(0,26) + 'a'.code) + randomString(random, length-1)
    }


}