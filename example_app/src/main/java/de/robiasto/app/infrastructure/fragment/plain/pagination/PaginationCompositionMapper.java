package de.robiasto.app.infrastructure.fragment.plain.pagination;

import de.robiasto.fragment_annotation_core.interfaces.AnnotationViewInterface;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import de.robiasto.fragment_annotation_core.utilities.FieldUtility;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;

import java.util.List;

/**
 * The PaginationCompositionMapper class is responsible for mapping fields and models to pagination-related operations. It provides methods to retrieve values, fields, and pages for
 * a given FieldDtoInterface object.
 * <p>
 * This class uses the FieldUtility class to retrieve field values, fields, and annotation views from a FieldDtoInterface object.
 * <p>
 * Example usage:
 * <p>
 * PaginationCompositionMapper pager = new PaginationCompositionMapper();
 * FieldDtoInterface fieldDto = ...; // create a FieldDtoInterface object
 * <p>
 * FragmentInterface[][] value = pager.getValue(fieldDto);
 * FieldDtoInterface[] fields = pager.getFields(fieldDto);
 * boolean isPage = pager.isPage(fieldDto);
 * Page<AnnotationViewInterface> pages = pager.getPages(fieldDto);
 * <p>
 * Please refer to the documentation of the FieldDtoInterface, FragmentInterface, AnnotationViewInterface, and FieldUtility classes for more details on the inputs and outputs of the
 * methods.
 */
public class PaginationCompositionMapper {

    public FragmentInterface[][] getValue(FieldDtoInterface fieldDto) {
        List<AnnotationViewInterface> compositions = FieldUtility.getAnnotationViewList(fieldDto);

        List<FragmentInterface[]> rows = new java.util.ArrayList<>();

        compositions.forEach(
                composition -> rows.add(fieldDto.annotationProcessor().getComposition(composition))
        );

        return rows.toArray(new FragmentInterface[0][0]);
    }

    public FieldDtoInterface[] getFields(FieldDtoInterface fieldDto) {
        return fieldDto
                .annotationProcessor()
                .getFields(FieldUtility.getAnnotationViewList(fieldDto).get(0));
    }

    public boolean isPage(FieldDtoInterface fieldDto) {
        Object model = FieldUtility.getObject(fieldDto);

        return model instanceof Page<?>;
    }

    @SuppressWarnings("unchecked")
    public Page<AnnotationViewInterface> getPages(FieldDtoInterface fieldDto) {
        Object object = FieldUtility.getObject(fieldDto);

        Assert.isInstanceOf(Page.class, object, "Return value must be a Page");

        ((Page<?>) object).getContent().forEach(
                fragment -> Assert.isInstanceOf(
                        AnnotationViewInterface.class,
                        fragment,
                        "Return value must be a FormValueDtoInterface"
                )
        );

        return (Page<AnnotationViewInterface>) FieldUtility.getObject(fieldDto);
    }
}
