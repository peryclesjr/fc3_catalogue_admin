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
                          O cuidado em identificar pontos cr??ticos no fen??meno da Internet ainda n??o demonstrou convincentemente que vai participar na mudan??a do levantamento das vari??veis envolvidas. 
                          Pensando mais a longo prazo, a valoriza????o de fatores subjetivos ?? uma das consequ??ncias do impacto na agilidade decis??ria. Neste sentido, o julgamento imparcial das eventualidades 
                          acarreta um processo de reformula????o e moderniza????o dos procedimentos normalmente adotados. A n??vel organizacional, a 
                          cont??nua expans??o de nossa atividade possibilita uma melhor vis??o global das posturas dos ??rg??os dirigentes com rela????o ??s suas atribui????es.
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
