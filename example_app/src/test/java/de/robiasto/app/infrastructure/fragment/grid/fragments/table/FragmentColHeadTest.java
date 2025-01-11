package de.robiasto.app.infrastructure.fragment.grid.fragments.table;

import de.robiasto.app.infrastructure.fragment.grid.TableCol;
import de.robiasto.app.infrastructure.fragment.plain.pagination.PaginationCompositionMapper;
import de.robiasto.fragment_annotation_core.interfaces.AnnotationViewInterface;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FragmentColHeadTest {
    
    @Mock
    private PaginationCompositionMapper compositionMapper;
    
    @Mock
    private FieldDtoInterface fieldDto;
    
    @Mock
    private Field field;
    
    @Mock
    private TableCol tableCol;
    
    private FragmentColHead.Mapper mapper;
    
    @Mock
    private Page<AnnotationViewInterface> page;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mapper = new FragmentColHead.Mapper(compositionMapper);
    }

    @Test
    void testFragmentColHeadCreation() {
        // Given
        String name = "testColumn";
        String label = "Test Column";
        boolean sortable = true;
        String[] sortableKeys = {"key1", "key2"};
        boolean activeSorting = true;
        String sortingDirection = "DESC";
        boolean hideOnMobile = true;

        // When
        FragmentColHead colHead = new FragmentColHead(
            name, 
            label, 
            sortable, 
            sortableKeys, 
            activeSorting, 
            sortingDirection,
            hideOnMobile
        );

        // Then
        assertEquals(name, colHead.getName());
        assertEquals(label, colHead.getLabel());
        assertTrue(colHead.isSortable());
        assertEquals("sort=key1,DESC&sort=key2,DESC&update=1", colHead.getSortParams());
        assertTrue(colHead.isActiveSorting());
        assertEquals("ASC", colHead.getSortingDirectionChosen());
        assertTrue(colHead.isHideOnMobile());
    }

    @Test
    void testSimpleConstructor() {
        // Given
        String name = "simpleColumn";
        String title = "Simple Column";

        // When
        FragmentColHead colHead = new FragmentColHead(name, title);

        // Then
        assertEquals(name, colHead.getName());
        assertEquals(title, colHead.getLabel());
        assertFalse(colHead.isSortable());
        assertEquals("&update=1", colHead.getSortParams());
        assertFalse(colHead.isActiveSorting());
        assertEquals("DESC", colHead.getSortingDirectionChosen());
        assertFalse(colHead.isHideOnMobile());
    }

    @Test
    void testMapperWithPageFragments() {
        // Given
        Sort sort = Sort.by(Sort.Direction.DESC, "testKey");
        Pageable pageable = PageRequest.of(0, 10, sort);
        
        when(compositionMapper.isPage(fieldDto)).thenReturn(true);
        when(compositionMapper.getPages(fieldDto)).thenReturn(page);
        when(page.getPageable()).thenReturn(pageable);
        when(compositionMapper.getFields(fieldDto)).thenReturn(new FieldDtoInterface[]{fieldDto});
        when(fieldDto.field()).thenReturn(field);
        when(field.getName()).thenReturn("testField");
        when(field.getAnnotation(TableCol.class)).thenReturn(tableCol);
        when(tableCol.label()).thenReturn("Test Label");
        when(tableCol.sortable()).thenReturn(true);
        when(tableCol.sortableKey()).thenReturn("testKey");
        when(tableCol.sortableKeys()).thenReturn(new String[]{});
        when(tableCol.hideOnMobile()).thenReturn(false);

        // When
        FragmentColHead[] results = mapper.getFragments(fieldDto);

        // Then
        assertNotNull(results);
        assertEquals(1, results.length);
        FragmentColHead result = results[0];
        assertEquals("testField", result.getName());
        assertEquals("Test Label", result.getLabel());
        assertTrue(result.isSortable());
        assertTrue(result.isActiveSorting());
        assertEquals("DESC", result.getSortingDirectionChosen());
    }

    @Test
    void testMapperWithListFragments() {
        // Given
        when(compositionMapper.isPage(fieldDto)).thenReturn(false);
        when(compositionMapper.getFields(fieldDto)).thenReturn(new FieldDtoInterface[]{fieldDto});
        when(fieldDto.field()).thenReturn(field);
        when(field.getName()).thenReturn("testField");
        when(field.getAnnotation(TableCol.class)).thenReturn(tableCol);
        when(tableCol.label()).thenReturn("Test Label");

        // When
        FragmentColHead[] results = mapper.getFragments(fieldDto);

        // Then
        assertNotNull(results);
        assertEquals(1, results.length);
        FragmentColHead result = results[0];
        assertEquals("testField", result.getName());
        assertEquals("Test Label", result.getLabel());
        assertFalse(result.isSortable());
        assertFalse(result.isActiveSorting());
    }

    @Test
    void testMapperWithMultipleSortableKeys() {
        // Given
        Sort sort = Sort.by(Sort.Direction.ASC, "key1");
        Pageable pageable = PageRequest.of(0, 10, sort);
        String[] sortableKeys = {"key1", "key2"};
        
        when(compositionMapper.isPage(fieldDto)).thenReturn(true);
        when(compositionMapper.getPages(fieldDto)).thenReturn(page);
        when(page.getPageable()).thenReturn(pageable);
        when(compositionMapper.getFields(fieldDto)).thenReturn(new FieldDtoInterface[]{fieldDto});
        when(fieldDto.field()).thenReturn(field);
        when(field.getName()).thenReturn("testField");
        when(field.getAnnotation(TableCol.class)).thenReturn(tableCol);
        when(tableCol.label()).thenReturn("Test Label");
        when(tableCol.sortable()).thenReturn(true);
        when(tableCol.sortableKeys()).thenReturn(sortableKeys);
        when(tableCol.hideOnMobile()).thenReturn(false);

        // When
        FragmentColHead[] results = mapper.getFragments(fieldDto);

        // Then
        assertNotNull(results);
        assertEquals(1, results.length);
        FragmentColHead result = results[0];
        assertTrue(result.isSortable());
        assertTrue(result.isActiveSorting());
        assertEquals("sort=key1,DESC&sort=key2,DESC&update=1", result.getSortParams());
    }
} 
