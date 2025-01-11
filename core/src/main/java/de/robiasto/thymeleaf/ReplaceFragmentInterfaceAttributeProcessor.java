package de.robiasto.thymeleaf;

import org.springframework.util.Assert;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.AttributeValueQuotes;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.processor.AbstractStandardExpressionAttributeTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.Map;

public class ReplaceFragmentInterfaceAttributeProcessor extends AbstractStandardExpressionAttributeTagProcessor {
    public static final int ATTR_PRECEDENCE = 1000;

    private static final String TAG_NAME = "value";

    private static final TemplateMode TEMPLATE_MODE = TemplateMode.HTML;

    public ReplaceFragmentInterfaceAttributeProcessor(String dialectPrefix) {
        super(TEMPLATE_MODE, dialectPrefix, TAG_NAME, ATTR_PRECEDENCE, false, true);
    }

    private String getAttributeValueItemName(String attributesValuesName) {
        return attributesValuesName
                .replaceAll("[${}(]", "")
                .replaceAll("[.)]", "_")
                + "item";
    }

    @Override
    protected void doProcess(
            ITemplateContext context,
            IProcessableElementTag tag,
            AttributeName attributeName,
            String attributeValue,
            Object expressionResult,
            IElementTagStructureHandler structureHandler
    ) {
        String attributesValuesItem = this.getAttributeValueItemName(attributeValue);

        Map<String, String> attributesMap = this.getAttributeMap(
                tag,
                attributeValue,
                attributesValuesItem
        );

        structureHandler.replaceWith(
                this.createModel(context, tag, attributesMap, attributesValuesItem)
                , true
        );
    }

    private Map<String, String> getAttributeMap(
            IProcessableElementTag tag,
            String attributeValue,
            String attributesValuesItem
    ) {
        Map<String, String> attributesMap = tag.getAttributeMap();
        attributesMap.remove("af:" + TAG_NAME);

        Assert.isNull(attributesMap.get("th:each"), "th:each is incompatible with sf:value.");
        Assert.isNull(attributesMap.get("th:replace"), "th:each is incompatible with sf:replace.");
        attributesMap.put("th:each", attributesValuesItem + " : " + attributeValue);

        return attributesMap;
    }

    private IModel createModel(
            ITemplateContext context,
            IProcessableElementTag tag,
            Map<String, String> attributes,
            String attributesValuesItem
    ) {
        String elementName = tag.getElementCompleteName();

        IModel model = context.getModelFactory().createModel();
        model.add(
                context.getModelFactory().createOpenElementTag(
                        elementName,
                        attributes,
                        AttributeValueQuotes.DOUBLE,
                        false
                )
        );
        model.add(
                context.getModelFactory().createStandaloneElementTag(
                        "th:block",
                        "th:replace",
                        "~{__${" + attributesValuesItem + ".getPath()}__(${" + attributesValuesItem + "})}",
                        true,
                        false
                )
        );
        model.add(context.getModelFactory().createCloseElementTag(elementName));

        return model;
    }
}
