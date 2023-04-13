package com.example.services;

import java.util.ArrayList;

import com.example.demo.models.Product;

public interface ICRUDProductService {
	//CRUD  -create - retrieve - update - delete 

		//retrieve all
		ArrayList<Product> retrieveAllProducts();

		//retrieve one by id
		Product retrieveOneProductById(int id) throws Exception;

		//retrieve one by title
		Product retrieveOneProductByTitle(String title) throws Exception;

		//create (insert)
		Product insertProductByParams(String title, float price, String description, int quantity);

		//update
		Product updateProductByParams(int id, String title, float price, String description, int quantity) throws Exception;

		//delete
		void deleteProductById(int id) throws Exception;

	

}
