package com.pet.buyselltrade.controllers;

import com.pet.buyselltrade.models.ProductModel;
import com.pet.buyselltrade.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public String products(@RequestParam(name = "title", required = false) String title, Model model,Principal principal) {
        model.addAttribute("products", productService.listProduct(title));
        model.addAttribute("user",productService.getUserModelByPrincipal(principal));
        return "products";
    }

    @GetMapping("product/{id}")
    public String infoProduct(@PathVariable Long id, Model model) {
        ProductModel product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImageModels());
        return "product_info";
    }

    @PostMapping("product/create")
    public String createProduct(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2, @RequestParam("file3") MultipartFile file3, ProductModel productModel, Principal principal) throws IOException {
        productService.saveProduct(principal,productModel, file1, file2, file3);
        return "redirect:/";
    }

    @PostMapping("product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }

}
