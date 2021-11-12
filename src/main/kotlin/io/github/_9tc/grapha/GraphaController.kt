package io.github._9tc.grapha

import io.github._9tc.grapha.service.CreateRandomGraphService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping


@Controller
class GraphaController {
    @RequestMapping("/")
    fun index(): String {
        return "index"
    }

    @RequestMapping("/confirm")
    fun confirm(model: Model, @ModelAttribute("vertices") verticesS: String, @ModelAttribute("percentage") percentageS: String): String {
        model.addAttribute("dataset", CreateRandomGraphService(verticesS.toLong(), percentageS.toLong()).create())
        return "index"
    }
}