package ru.netology.repository;

import ru.netology.domain.Product;
import ru.netology.manager.NotFoundException;

public class ProductRepository {
    Product[] items = new Product[0];

    public Product[] findAll() {
        return items;
    }

    public void save(Product item) {
        Product[] tmp = new Product[items.length + 1];
        for (int i = 0; i < items.length; i++) {
            tmp[i] = items[i];
        }
        tmp[tmp.length - 1] = item;
        items = tmp;
    }

    public void removeById(int id) {
        if (findById(id) != null) {
            Product[] tmp = new Product[items.length - 1];
            int copyToIndex = 0;
            for (Product item : items) {
                if (item.getId() != id) {
                    tmp[copyToIndex] = item;
                    copyToIndex++;
                }
                items = tmp;
            }
        } else {
            throw new NotFoundException(
                    "Элемент с таким ID " + id + " не найден. Введите существующий ID."
            );
        }
    }

    public Product[] findById(int id) {
        for (Product item : items) {
            if (item.getId() == id) {
                return new Product[]{item};
            }
        }
        return null;
    }

}
