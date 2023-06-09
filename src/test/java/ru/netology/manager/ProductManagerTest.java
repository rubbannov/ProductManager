package ru.netology.manager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manage = new ProductManager(repository);
    Product product1 = new Book(1, "Ведьмак", 600, "Анджей Сапковский");
    Product product2 = new Book(2, "На Западном фронте без перемен", 1200, "Эрих Мария Ремарк");
    Product product3 = new Smartphone(3, "OPPO Reno 5", 50_000, "Китай");
    Product product4 = new Smartphone(4, "Honor 8", 10_000, "Китай");
    Product product5 = new Product(5, "Влажные салфетки",200);
    Product product6 = new Product(6, "Ботинки", 3000);
    Product product7 = new Product(7, "Ботинки", 2500);

    @BeforeEach
    public void setup() {
        manage.add(product1);
        manage.add(product2);
        manage.add(product3);
        manage.add(product4);
        manage.add(product5);
        manage.add(product6);
        manage.add(product7);
    }

    @Test
    public void testFindByName() {
        String name = "Ведьмак";

        Product[] expected = new Product[]{product1};
        Product[] actual = manage.searchBy(name);

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void testProductDoesNotExist() {
        String nameToSearch = "Нетология";

        Product[] expected = {};
        Product[] actual = manage.searchBy(nameToSearch);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testFindByAuthor() {
        String author = "Анджей Сапковский";

        Product[] expected = new Book[]{};
        Product[] actual = manage.searchBy(author);

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void testFindByManufacturer() {
        String manufacturer = "Китай";

        Product[] expected = new Smartphone[]{};
        Product[] actual = manage.searchBy(manufacturer);

        Assertions.assertArrayEquals(expected, actual);

    }
    @Test
    public void testRemoveById() { //Успешное удаление элемента
        repository.removeById(6);

        Product[] expected = {product1, product2, product3, product4, product5, product7};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void testRemoveByDoesNotExistId() { //Тест переделан под исключение

        assertThrows(NotFoundException.class, () -> {
            repository.removeById(777);
        });
    }
    @Test
    public void testFindFewItems() {

        Product[] expected = {product6, product7};
        Product[] actual = manage.searchBy("Ботинки");

        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void testFindByID() {

        Product[] expected = {product3};
        Product[] actual = repository.findById(3);

        Assertions.assertArrayEquals(expected, actual);
    }
}