package de.robiasto.app.infrastructure.fragment.grid.fragments.table;

import de.robiasto.app.infrastructure.fragment.grid.fragments.text.TextFragment;
import de.robiasto.app.infrastructure.fragment.plain.pagination.PaginationCompositionMapper;
import de.robiasto.app.infrastructure.fragment.plain.pagination.PaginationConfiguration;
import de.robiasto.fragment_annotation_core.interfaces.AnnotationViewInterface;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import de.robiasto.fragment_annotation_core.utilities.FieldUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FragmentTableMapperTest {
    @Mock
    private PaginationCompositionMapper compositionMapper;

    @Mock
    private PaginationConfiguration paginationConfiguration;

    @Mock
    private FieldDtoInterface fieldDto;

    @Mock
    private FragmentColBody mockColBody;

    @Mock
    private Page<AnnotationViewInterface> page;

    private FragmentTable.Mapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mapper = new FragmentTable.Mapper(compositionMapper);
    }

    @Test
    void testMapperWithEmptyData() {
        // Given
        FragmentInterface[][] emptyData = new FragmentInterface[0][];
        when(compositionMapper.getValue(fieldDto)).thenReturn(emptyData);

        // When
        FragmentInterface result = mapper.getAnnotationMapper(fieldDto);

        // Then
        assertTrue(result instanceof TextFragment);
        TextFragment textFragment = (TextFragment) result;
        assertEquals("Keine Daten gefunden.", textFragment.getText());
    }

    @Test
    void testMapperWithValidData() {
        // Given
        FragmentInterface[][] tableData = new FragmentInterface[][]{
                new FragmentInterface[]{mockColBody}
        };
        when(compositionMapper.getValue(fieldDto)).thenReturn(tableData);
        when(compositionMapper.isPage(fieldDto)).thenReturn(false);
        when(compositionMapper.getFields(fieldDto)).thenReturn(new FieldDtoInterface[0]);

        // When
        FragmentInterface result = mapper.getAnnotationMapper(fieldDto);

        // Then
        assertTrue(result instanceof FragmentTable);
        FragmentTable table = (FragmentTable) result;
        assertNotNull(table.getRows());
        assertEquals(1, table.getRows().length);
        assertEquals(mockColBody, table.getRows()[0][0]);
    }

    @Test
    void testMapperWithInvalidColBody() {
        // Given
        FragmentInterface[][] tableData = new FragmentInterface[][]{
                new FragmentInterface[]{mock(FragmentInterface.class)}
        };
        when(compositionMapper.getValue(fieldDto)).thenReturn(tableData);
        when(compositionMapper.getFields(fieldDto)).thenReturn(new FieldDtoInterface[0]);

        // When/Then
        assertThrows(InvalidTableFragmentException.class, () ->
                mapper.getAnnotationMapper(fieldDto)
        );
    }

    @Test
    void testMapperWithPagination() {
        // Given
        FragmentInterface[][] tableData = new FragmentInterface[][]{
                new FragmentInterface[]{mockColBody}
        };

        Sort sort = Sort.by(Sort.Direction.DESC, "testKey");
        Pageable pageable = PageRequest.of(0, 10, sort);
        List<AnnotationViewInterface> content = new ArrayList<>();
        FieldUtility fieldUtility = mock(FieldUtility.class);

        Field field = mock(Field.class);
        when(field.getAnnotation(PaginationConfiguration.class)).thenReturn(paginationConfiguration);
        when(fieldDto.field()).thenReturn(field);
        @SuppressWarnings("unchecked")
        Page<Object> mockPage = mock(Page.class);

        when(fieldUtility.getObject(fieldDto)).thenReturn(mockPage);

        when(compositionMapper.getValue(fieldDto)).thenReturn(tableData);
        when(compositionMapper.isPage(fieldDto)).thenReturn(true);
        when(compositionMapper.getFields(fieldDto)).thenReturn(new FieldDtoInterface[0]);
        when(compositionMapper.getPages(fieldDto)).thenReturn(page);
        when(page.getPageable()).thenReturn(pageable);
        when(page.getContent()).thenReturn(content);

        // When
        FragmentInterface result = mapper.getAnnotationMapper(fieldDto);

        // Then
        assertTrue(result instanceof FragmentTable);
        FragmentTable table = (FragmentTable) result;
        assertNotNull(table.getPagination());
    }
}
