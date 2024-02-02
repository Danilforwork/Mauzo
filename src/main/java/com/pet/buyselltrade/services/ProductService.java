package com.pet.buyselltrade.services;

import com.pet.buyselltrade.models.ImageModel;
import com.pet.buyselltrade.models.ProductModel;
import com.pet.buyselltrade.models.UserModel;
import com.pet.buyselltrade.repositories.Productrepository;
import com.pet.buyselltrade.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final Productrepository productrepository;
    private final UserRepository userRepository;

    public List<ProductModel> listProduct(String title) {
        if (title != null) productrepository.findByTitle(title);
        return productrepository.findAll();
    }

    public void saveProduct(Principal principal, ProductModel productModel, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        productModel.setUser(getUserModelByPrincipal(principal));
        ImageModel imageModel1;
        ImageModel imageModel2;
        ImageModel imageModel3;
        if (file1.getSize() != 0) {
            imageModel1 = toImageEntity(file1);
            imageModel1.setPreviewImage(true);
            productModel.addImageToProduct(imageModel1);
        }
        if (file2.getSize() != 0) {
            imageModel2 = toImageEntity(file2);
            productModel.addImageToProduct(imageModel2);
        }
        if (file3.getSize() != 0) {
            imageModel3 = toImageEntity(file3);
            productModel.addImageToProduct(imageModel3);
        }
        log.info("Saving new product. Title: {};User email: {}", productModel.getTitle(), productModel.getUser().getEmail());
        ProductModel productFromdb = productrepository.save(productModel);
        productFromdb.setPreviewImageId(productFromdb.getImageModels().get(0).getId());
        productrepository.save(productModel);
    }

    public UserModel getUserModelByPrincipal(Principal principal) {
        if (principal == null){
            return new UserModel();
        }
        return userRepository.findByEmail(principal.getName());
    }

    private ImageModel toImageEntity(MultipartFile file1) throws IOException {
        ImageModel imageModel = new ImageModel();
        imageModel.setName(file1.getName());
        imageModel.setFileName(file1.getOriginalFilename());
        imageModel.setType(file1.getContentType());
        imageModel.setSize(file1.getSize());
        imageModel.setBytes(file1.getBytes());
        return imageModel;
    }

    public void deleteProduct(Long id) {
        productrepository.deleteById(id);
    }

    public ProductModel getProductById(Long id) {
        return productrepository.findById(id).orElse(null);
    }
}
