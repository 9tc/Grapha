package io.github._9tc.grapha.domain.repository

import io.github._9tc.grapha.domain.entity.Graph
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface RandomGraphRepository {

    @Select("SELECT * FROM random_graph WHERE vertices = #{vertices} and percentage = #{percentage} and seed = #{seed} and haslabel = #{haslabel}")
    fun selectGraphByData(@Param("vertices") vertices: Int?, @Param("percentage") percentage: Int?, @Param("seed") seed: Long?, @Param("haslabel") haslabel: Boolean): List<Graph>

    @Insert("INSERT INTO random_graph values(#{vertices}, #{percentage}, #{seed}, #{haslabel}, #{graph})")
    fun insertGraph(graph: Graph): Int
}