package com.example.services.impl;

import java.util.ArrayList;
import java.util.Arrays;

import com.example.demo.models.Product;
import com.example.services.ICRUDProductService;
import com.example.services.IFilteringProductServices;

public class ProductServiceImpl implements ICRUDProductService, IFilteringProductServices {

	private ArrayList<Product> allProducts = new ArrayList<>(Arrays.asList(new Product("Ābols", 3.99f, "Sarkans", 3),
			new Product("Tomāts", 1.99f, "Dzeltens", 12), new Product("Avokado", 0.99f, "Zaļš", 10)));

	@Override
	public ArrayList<Product> retrieveAllProducts() {
		return allProducts;
	}

	@Override
	public Product retrieveOneProductById(int id) throws Exception {
		for (Product temp : allProducts) {
			if (temp.getId() == id) {
				return temp;
			}
		}
		throw new Exception("Wrong ID!");
	}

	@Override
	public Product retrieveOneProductByTitle(String title) throws Exception {
		for (Product temp : allProducts) {
			if (temp.getTitle().equals(title)) {
				return temp;
			}
		}
		throw new Exception("Wrong Title!");
	}

	@Override
	public Product insertProductByParams(String title, float price, String description, int quantity) {
		for (Product temp : allProducts) {
			if (temp.getTitle().equals(title) && temp.getDescription().equals(description)
					&& temp.getPrice() == price) {
				temp.setQuantity(temp.getQuantity() + quantity);
				return temp;
			}
		}
		Product newProduct = new Product(title, price, description, quantity);
		allProducts.add(newProduct);
		return newProduct;
	}

	@Override
	public Product updateProductByParams(int id, String title, float price, String description, int quantity)
			throws Exception {
		for (Product temp : allProducts) {
			if (temp.getId() == id) {
				temp.setTitle(title);
				temp.setPrice(price);
				temp.setDescription(description);
				temp.setQuantity(quantity);
				return temp;
			}
		}
		throw new Exception("No product with this ID!");
	}

	@Override
	public void deleteProductById(int id) throws Exception {
		for (Product temp : allProducts) {
			if (temp.getId() == id) {
				allProducts.remove(temp);
			}
		}
		throw new Exception("No product with this ID!");
	}

	@Override
	public ArrayList<Product> filterByPriceLess(float price) {
		if (price > 0) {
			ArrayList<Product> allProductsWithPriceLess = new ArrayList<>();
			for (Product temp : allProducts) {
				if (temp.getPrice() < price) {
					allProductsWithPriceLess.add(temp);
				}
			}

			return allProductsWithPriceLess;
		}
		
		return new ArrayList<Product>();
	}

}
