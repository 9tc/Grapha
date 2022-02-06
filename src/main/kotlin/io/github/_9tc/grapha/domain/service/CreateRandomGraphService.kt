package io.github._9tc.grapha.domain.service
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.queryForList
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class CreateRandomGraphService{

    fun graph(jdbcTemplate: JdbcTemplate, vertices: Long, percentage: Long, hasLabel : Boolean, seed: Long): String {

        val list = jdbcTemplate.queryForList<String>("SELECT graph FROM random_graph WHERE vertices = $vertices and percentage = $percentage and seed = $seed and haslabel = $hasLabel")

        if(list.isNotEmpty()) return list.first()

        val graph = create(vertices, percentage, hasLabel, seed)

        jdbcTemplate.update("INSERT INTO random_graph values($vertices, $percentage, $seed, $hasLabel, E\'$graph\')")

        return graph
    }


    fun create(vertices: Long, percentage: Long, hasLabel : Boolean, seed: Long): String{
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

        //頂点ラベル付与
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