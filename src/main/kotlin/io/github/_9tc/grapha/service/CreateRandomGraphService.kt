package io.github._9tc.grapha.service

class CreateRandomGraphService(
    private val vertices: Long,
    private val percentage: Long
) {
    fun create(hasLabel : Boolean): String{
        var s = ""
        val dataset : MutableList<Pair<Long, Long>> = mutableListOf()
        var edges = 0

        for(u in 1 until vertices){
            for(v in (u+1)..vertices){
                if(Math.random() * 100 < percentage){
                    ++edges
                    dataset.add(Pair(u, v))
                }
            }
        }

        s = "$vertices $edges\n"


        if(hasLabel){
            for(v in 1..vertices){
                s += v.toString() + " " + ('a'..'z').random() + ('a'..'z').random() + ('a'..'z').random() + "\n"
            }
        }

        for (data in dataset){
            s += "${data.first} ${data.second}\n"
        }
        return s

    }


}