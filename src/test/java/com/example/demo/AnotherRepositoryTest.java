package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(value = {PostgreSqlTestConfig.class})
@DataJpaTest
class AnotherRepositoryTest {
    @Autowired
    AnotherRepository anotherRepository;

    @Test
    void should_When() {
        // Arrange
        AnotherEntity expected = new AnotherEntity("Name goes here");

        // Act
        AnotherEntity actual = anotherRepository.save(expected);

        // Assert
        assertThat(actual).usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expected);
    }

}