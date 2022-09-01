package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.repository.*;

public class ProductManagerTest {
    ProductRepository repo = new ProductRepository();
    ProductManager manager = new ProductManager(repo);

    Book book1 = new Book(11, "Евгений Онегин", 450, "Пушкин");
    Book book2 = new Book(14, "Война и мир", 450, "Толстой");
    Book book3 = new Book(23, "Война и мир", 230, "Толстой");
    Book book4 = new Book(24, "Анна Каренина", 223, "Толстой");
    Book book5 = new Book(23, "Мертвые души", 520, "Гоголь");

    @BeforeEach
    public void setup() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(book4);
        manager.add(book5);
    }

    @Test
    public void testsMatchesT() {
        boolean expected = true;
        boolean actual = manager.matches(book2, "Война и мир");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testMatchesF() {
        boolean expected = false;
        boolean actual = manager.matches(book2, "rthkgj");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSearchBy() {
        Product[] expected = {book2, book3};
        Product[] actual = manager.searchBy("Война и мир");
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void testFindAll() {
        Product[] expected = {book1, book2, book3, book4, book5};
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testRemoveById() {
        Product[] expected = {book1, book3, book4, book5};
        Product[] actual = repo.removeById(14);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchByZero() {
        Product[] expected = {};
        Product[] actual = manager.searchBy("Чудаки");
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void testSearchByOne() {
        Product[] expected = {book1};
        Product[] actual = manager.searchBy("Евгений Онегин");
        Assertions.assertArrayEquals(expected, actual);
    }

}
