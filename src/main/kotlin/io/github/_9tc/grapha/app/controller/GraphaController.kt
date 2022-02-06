package io.github._9tc.grapha.app.controller

import io.github._9tc.grapha.domain.form.InputForm
import io.github._9tc.grapha.domain.service.CreateRandomGraphService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestMapping


@Controller
class GraphaController(
    @Autowired
    val jdbcTemplate: JdbcTemplate
) {

    @RequestMapping("/")
    fun index(): String {
        return "index"
    }

    @RequestMapping("/confirm")
    fun confirm(model: Model, @Validated form: InputForm, bindingResult: BindingResult): String {
        if(bindingResult.hasErrors()) return "redirect:/"

        model.addAttribute("vertices", form.vertices)
        model.addAttribute("percentage", form.percentage)
        model.addAttribute("hasLabel", form.hasLabel)

        val seed: Long = if(form.seed == null) (Math.random() * 1e10).toLong() else form.seed!!
        model.addAttribute("seedout", seed)
        if(form.seed != null) model.addAttribute("seed", seed)

        val hasLabel : Boolean = form.hasLabel

        model.addAttribute("dataset", CreateRandomGraphService().graph(jdbcTemplate, form.vertices!!, form.percentage!!, hasLabel, seed))
        return "index"
    }
}
