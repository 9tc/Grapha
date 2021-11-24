package io.github._9tc.grapha

import io.github._9tc.grapha.form.InputForm
import io.github._9tc.grapha.service.CreateRandomGraphService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import kotlin.random.Random


@Controller
class GraphaController {
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

        var seed: Long = if(form.seed == null) (Math.random() * 1e10).toLong() else form.seed!!
        model.addAttribute("seed", seed)

        val hasLabel : Boolean = (form.hasLabel == "checked")
        model.addAttribute("dataset", CreateRandomGraphService(form.vertices!!, form.percentage!!).create(hasLabel, seed))
        return "index"
    }
}