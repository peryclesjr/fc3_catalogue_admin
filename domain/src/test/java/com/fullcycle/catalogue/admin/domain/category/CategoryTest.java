package com.fullcycle.catalogue.admin.domain.category;

;
import com.fullcycle.catalogue.admin.domain.exceptions.DomainException;
import com.fullcycle.catalogue.admin.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    @Test
    public void givenValidParams_whenNewCategory_thenInstatiateCategory(){
        final var expectedName = "Movies";
        final var expectedDescription = "Category most view";
        final var expectedIsActive = true;

        final var actualCategory =  Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreateAt());
        Assertions.assertNotNull(actualCategory.getUpdateAt());
        Assertions.assertNull(actualCategory.getDeleteAt());

    }

    @Test
    public void givenAnInvalidNullName_whenCallNewCategoryAndValidate_thenShouldReceiveError(){
        final String expectedName = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedDescription = "Category most view";
        final var expectedIsActive = true;

        final var actualCategory =  Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException  = Assertions.assertThrows(
                                                                    DomainException.class,
                                                                    () -> actualCategory.validate(new ThrowsValidationHandler()) );

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals( expectedErrorMessage, actualException.getErrors().get(0).message());

    }

    @Test
    public void givenAnInvalidBlankName_whenCallNewCategoryAndValidate_thenShouldReceiveError(){
        final String expectedName = " ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be Blank";
        final var expectedDescription = "Category most view";
        final var expectedIsActive = true;

        final var actualCategory =  Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException  = Assertions.assertThrows(
                DomainException.class,
                () -> actualCategory.validate(new ThrowsValidationHandler()) );

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals( expectedErrorMessage, actualException.getErrors().get(0).message());

    }

    @Test
    public void givenAValidIsNotActive_whenCallNewCategory_thenShouldOk(){
        final String expectedName = " ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be Blank";
        final var expectedDescription = "Category most view";
        final var expectedIsActive = false;

        final var actualCategory =  Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertEquals(false, actualCategory.isActive());


    }

    @Test
    public void givenAnInvalidNameLengthLessThan3_whenCallNewCategoryAndValidate_thenShouldReceiveError(){
        final String expectedName = "Fa ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 letters and 255 characteres";
        final var expectedDescription = "Category most view";
        final var expectedIsActive = true;

        final var actualCategory =  Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException  = Assertions.assertThrows(
                DomainException.class,
                () -> actualCategory.validate(new ThrowsValidationHandler()) );

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals( expectedErrorMessage, actualException.getErrors().get(0).message());

    }

    @Test
    public void givenAnInvalidNameLengthMolreThan255_whenCallNewCategoryAndValidate_thenShouldReceiveError(){
        final String expectedName = """
                          O cuidado em identificar pontos críticos no fenômeno da Internet ainda não demonstrou convincentemente que vai participar na mudança do levantamento das variáveis envolvidas. 
                          Pensando mais a longo prazo, a valorização de fatores subjetivos é uma das consequências do impacto na agilidade decisória. Neste sentido, o julgamento imparcial das eventualidades 
                          acarreta um processo de reformulação e modernização dos procedimentos normalmente adotados. A nível organizacional, a 
                          contínua expansão de nossa atividade possibilita uma melhor visão global das posturas dos órgãos dirigentes com relação às suas atribuições.
                """;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 letters and 255 characteres";
        final var expectedDescription = "Category most view";
        final var expectedIsActive = true;

        final var actualCategory =  Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException  = Assertions.assertThrows(
                DomainException.class,
                () -> actualCategory.validate(new ThrowsValidationHandler()) );

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals( expectedErrorMessage, actualException.getErrors().get(0).message());

    }


    @Test
    public void givenAValidDescription_whenCallNewCategoryAndValidate_thenShouldReceiveOK(){
        final String expectedName = "Faavor";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 letters and 255 characteres";
        final var expectedDescription = "Category most view";
        final var expectedIsActive = true;

        final var actualCategory =  Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()) );

    }

    @Test
    public void givenValidActiveCategory_whenCAllDeactivate_thenReturnCategoryInactivate(){
        final var expectedName = "Movies";
        final var expectedDescription = "Category most view";
        final var expectedIsActive = true;

        final var aCategory =  Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()) );

        final var updateAt = aCategory.getUpdateAt();

        Assertions.assertTrue(aCategory.isActive());
        Assertions.assertNull(aCategory.getDeleteAt());

        final var actualCategory = aCategory.deActivate();

        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(aCategory.getName(), actualCategory.getName());
        Assertions.assertEquals(aCategory.getDescription(), actualCategory.getDescription());
        Assertions.assertFalse(actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreateAt());
        Assertions.assertTrue(actualCategory.getUpdateAt().isAfter(updateAt));
        Assertions.assertNotNull(actualCategory.getDeleteAt());


    }

    @Test
    public void givenValidInactiveCategory_whenCAllActivate_thenReturnCategoryInactivate(){
        final var expectedName = "Movies";
        final var expectedDescription = "Category most view";
        final var expectedIsActive = false;

        final var aCategory =  Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()) );

        final var createAt = aCategory.getCreateAt();
        final var updateAt = aCategory.getUpdateAt();

        Assertions.assertFalse(aCategory.isActive());
        Assertions.assertNotNull(aCategory.getDeleteAt());

        final var actualCategory = aCategory.activate();

        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(aCategory.getName(), actualCategory.getName());
        Assertions.assertEquals(aCategory.getDescription(), actualCategory.getDescription());
        Assertions.assertTrue(actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreateAt());
        Assertions.assertEquals(createAt, actualCategory.getCreateAt());
        Assertions.assertTrue(actualCategory.getUpdateAt().isAfter(updateAt));
        Assertions.assertNull(actualCategory.getDeleteAt());

    }

    @Test
    public void givenAValidCategory_whenCallUpdate_thenReturnCategoryUpdated(){
        final String expectedName = "Matrix";
        final var expectedDescription = "Category action most viewed";
        final var expectedIsActive = true;

        final var aCategory =  Category.newCategory("Revolutions", "Category action not viewed", false);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()) );

        final var createAt = aCategory.getCreateAt();
        final var updateAt = aCategory.getUpdateAt();

        final var actualCategory = aCategory.update(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreateAt());
        Assertions.assertEquals(createAt, actualCategory.getCreateAt());
        Assertions.assertTrue(actualCategory.getUpdateAt().isAfter(updateAt));
        Assertions.assertNull(actualCategory.getDeleteAt());

    }

    @Test
    public void givenAValidCategory_whenCallUpdateToInactivate_thenReturnCategoryUpdated(){
        final String expectedName = "Matrix";
        final var expectedDescription = "Category action most viewed";
        final var expectedIsActive = false;

        final var aCategory =  Category.newCategory("Revolutions", "Category action not viewed", true);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()) );
        Assertions.assertTrue(aCategory.isActive());
        Assertions.assertNull(aCategory.getDeleteAt());

        final var createAt = aCategory.getCreateAt();
        final var updateAt = aCategory.getUpdateAt();

        final var actualCategory = aCategory.update(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreateAt());
        Assertions.assertEquals(createAt, actualCategory.getCreateAt());
        Assertions.assertTrue(actualCategory.getUpdateAt().isAfter(updateAt));
        Assertions.assertNotNull(actualCategory.getDeleteAt());

    }


    @Test
    public void givenAValidCategory_whenCallUpdateWithInvalidParam_thenCategoryUpdate(){
        final String expectedName = null;
        final var expectedDescription = "Category action most viewed";
        final var expectedIsActive = true;

        final var aCategory =  Category.newCategory("Revolutions", "Category action not viewed", false);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()) );

        Assertions.assertFalse(aCategory.isActive());
        Assertions.assertNotNull(aCategory.getDeleteAt());

        final var createAt = aCategory.getCreateAt();
        final var updateAt = aCategory.getUpdateAt();

        final var actualCategory = aCategory.update(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreateAt());
        Assertions.assertEquals(createAt, actualCategory.getCreateAt());
        Assertions.assertTrue(actualCategory.getUpdateAt().isAfter(updateAt));
        Assertions.assertNull(actualCategory.getDeleteAt());

    }

}
