package de.robiasto.app.infrastructure.fragment.plain.pagination;

import de.robiasto.fragment_annotation_core.interfaces.AnnotationViewInterface;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeMapperInterface;
import de.robiasto.fragment_annotation_core.utilities.FieldUtility;
import de.robiasto.fragment_annotation_core.utilities.templates.AbstractLocalFragment;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;


public class PaginationFragment extends AbstractLocalFragment {
    public final Page<AnnotationViewInterface> page;

    public final int firstShownEntry;

    public final int lastShownEntry;

    public final int firstPageToShow;

    public final int lastPageToShow;

    public final boolean showSummary;

    public PaginationFragment(Page<AnnotationViewInterface> page, int maxShownPageLinks, boolean showSummary) {
        this.page = page;
        this.showSummary = showSummary;
        this.lastShownEntry = Math.min((int) this.page.getTotalElements(), this.page.getNumber() * this.page.getSize() + this.page.getSize());
        this.firstShownEntry = Math.max(1, this.page.getNumber() * this.page.getSize() + 1);
        this.firstPageToShow = this.getFirstPageToShow(page, maxShownPageLinks);
        this.lastPageToShow = Math.min(page.getTotalPages(), this.firstPageToShow + maxShownPageLinks -1);
    }

    private int getFirstPageToShow(Page<AnnotationViewInterface> page, int maxShownPageLinks){
        return Math.max(1, Math.min(page.getTotalPages() - maxShownPageLinks + 1, Math.max(1, page.getNumber() - 1)));
    }

    public static class Mapper implements FragmentTypeMapperInterface {
        @Override
        @SuppressWarnings("unchecked")
        public FragmentInterface getAnnotationMapper(FieldDtoInterface fieldDto) {
            PaginationConfiguration config = fieldDto.field().getAnnotation(PaginationConfiguration.class);

            Page<?> page = (Page<?>) FieldUtility.getObject(fieldDto);

            page.getContent().forEach(fragment ->
                                              Assert.isInstanceOf(
                                                      AnnotationViewInterface.class,
                                                      fragment,
                                                      "Fragment must implement FragmentInterface."
                                              )
            );
            if (config == null) {
                return new PaginationFragment((Page<AnnotationViewInterface>) page, 5, true);
            }

            return new PaginationFragment(
                    (Page<AnnotationViewInterface>) page,
                    config.maxShownPages(),
                    config.showSummary()
            );
        }
    }
}
