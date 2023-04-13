package com.example.services;

import java.util.ArrayList;

import com.example.demo.models.Product;

public interface IFilteringProductServices {
	ArrayList <Product> filterByPriceLess(float price);

}
