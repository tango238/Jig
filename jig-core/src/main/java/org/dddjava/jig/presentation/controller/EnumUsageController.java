package org.dddjava.jig.presentation.controller;

import org.dddjava.jig.application.service.BusinessRuleService;
import org.dddjava.jig.application.service.GlossaryService;
import org.dddjava.jig.domain.model.categories.CategoryAngles;
import org.dddjava.jig.domain.model.implementation.analyzed.AnalyzedImplementation;
import org.dddjava.jig.domain.model.implementation.analyzed.japanese.JapaneseNameFinder;
import org.dddjava.jig.presentation.view.JigDocument;
import org.dddjava.jig.presentation.view.JigModelAndView;
import org.dddjava.jig.presentation.view.ViewResolver;
import org.dddjava.jig.presentation.view.handler.DocumentMapping;
import org.springframework.stereotype.Controller;

@Controller
public class EnumUsageController {

    BusinessRuleService businessRuleService;
    GlossaryService glossaryService;
    ViewResolver viewResolver;

    public EnumUsageController(BusinessRuleService businessRuleService, GlossaryService glossaryService, ViewResolver viewResolver) {
        this.businessRuleService = businessRuleService;
        this.glossaryService = glossaryService;
        this.viewResolver = viewResolver;
    }

    @DocumentMapping(JigDocument.CategoryUsageDiagram)
    public JigModelAndView<CategoryAngles> enumUsage(AnalyzedImplementation implementations) {
        CategoryAngles categoryAngles = businessRuleService.categories(implementations.typeByteCodes());
        JapaneseNameFinder japaneseNameFinder = new JapaneseNameFinder.GlossaryServiceAdapter(glossaryService);
        return new JigModelAndView<>(categoryAngles, viewResolver.enumUsage(japaneseNameFinder));
    }

    @DocumentMapping(JigDocument.CategoryDiagram)
    public JigModelAndView<CategoryAngles> categories(AnalyzedImplementation implementations) {
        CategoryAngles categoryAngles = businessRuleService.categories(implementations.typeByteCodes());
        JapaneseNameFinder japaneseNameFinder = new JapaneseNameFinder.GlossaryServiceAdapter(glossaryService);
        return new JigModelAndView<>(categoryAngles, viewResolver.categories(japaneseNameFinder));
    }
}
