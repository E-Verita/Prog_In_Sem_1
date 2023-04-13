package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Product;
import com.example.services.impl.ProductServiceImpl;

@Controller
public class FirstController {
	ProductServiceImpl productServiceImpl;

	@GetMapping("/hello")
	public String helloFuntion() {
		System.out.println("Mans pirmais kontrolieris STRĀDĀ!!!");
		return "hello-page";
	}

	@GetMapping("/msg")
	public String msgFuntion(Model model) {
		model.addAttribute("myMsg", "Te ziņa no Everitas");
		model.addAttribute("myDate", "26/03/2023");
		return "msg-page";
	}

	@GetMapping("/product")
	public String productFunction(Model model) {
		Product prod = new Product("Ābols", 3.99f, "Sarkans", 3);
		model.addAttribute("myProduct", prod);
		return "product";
	}

	@GetMapping("/productOne")
	public String productByParamFunction(@RequestParam("title") String title, Model model) throws Exception {
		try {
			model.addAttribute("myProduct", productServiceImpl.retrieveOneProductByTitle(title));
			return "product";
		} catch (Exception e) {
			e.printStackTrace();
			return "error-page";
		}
	}

	@GetMapping("/product/{title}")
	public String productByParamFunction2(@PathVariable("title") String title, Model model) throws Exception {
		try {
			model.addAttribute("myProduct", productServiceImpl.retrieveOneProductByTitle(title));
			return "product";
		} catch (Exception e) {
			e.printStackTrace();
			return "error-page";
		}
	}

	// kontrolieris, kas atgriezīs visus produktus
	@GetMapping("/allproducts")
	public String allProductsFunc(Model model) {
		model.addAttribute("myAllProducts", productServiceImpl.retrieveAllProducts());
		return "all-products-page";
	}

	// TODO kontrolieri, kas izfiltrē visus produktus, kuru cena ir mazaka par
	// padoto vērtību
	@GetMapping("/allproducts/{price}")
	public String allProductsByPrice(@PathVariable("price") float price, Model model) {
		try {
			model.addAttribute("myAllProducts", productServiceImpl.filterByPriceLess(price));
			return "all-products-page";
		} catch (Exception e) {
			e.printStackTrace();
			return "error-page";
		}
	}

	@GetMapping("/insert")
	public String insertProductFunc(Product product) { // padots tukšs produkts
		return "insert-page";
	}

	@PostMapping("/insert")
	public String insertProductPostFunc(Product product) { // saņemts aizpildīts (no form) produkts
		productServiceImpl.insertProductByParams(product.getTitle(), product.getPrice(), product.getDescription(),
				product.getQuantity());
		return "redirect:/allproducts"; // aiziet uz get mapping /allproducts
	}

	@GetMapping("/update/{id}")
	public String updateProductByIdGetFunc(@PathVariable("id") int id, Model model) {
		try {
			model.addAttribute("product", productServiceImpl.retrieveOneProductById(id));
			return "update-page";
		} catch (Exception e) {
			e.printStackTrace();
			return "error-page";
		}
	}

	@PostMapping("/update/{id}")
	public String updateProductByIdPostFunc(@PathVariable("id") int id, Product product) { // ienāķ redigētais produkts
		try {
			productServiceImpl.updateProductByParams(id, product.getTitle(), product.getPrice(),
					product.getDescription(), product.getQuantity());
			return "redirect:/product/" + product.getTitle();
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error";
		}
	}

	@GetMapping("/error")
	public String errorFunc() { // padots tukšs produkts
		return "error-page";
	}

	@GetMapping("/delete/{id}")
	public String deleteProductById(@PathVariable("id") int id, Model model) { // padots tukšs produkts
		try {
			productServiceImpl.deleteProductById(id);
			model.addAttribute("myAllProducts", productServiceImpl.retrieveAllProducts());
			return "all-products-page";
		} catch (Exception e) {
			e.printStackTrace();
			return "error-page";
		}
	}
}
