package com.chicken.controller;

import com.chicken.dto.ImageResponseDto;
import com.chicken.dto.ProductDto;
import com.chicken.entity.Product;
import com.chicken.service.ImageFileService;
import com.chicken.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Console;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
@Log4j2
public class ProductController {

    private final ProductService productService;
    private final ImageFileService imageFileService;

    /**
     *  제품 페이징
     * **/
    @GetMapping("/info")
    public String chickenInfo(Model model, @RequestParam(value = "page", required = false, defaultValue = "0") String productPage,
                              ProductDto productDto){

        // 어디서 부터 시작하고 몇 페이지 까지 출력할 거고 정렬 기준을 뭘로 할 것인지 설정
        Pageable pageable = PageRequest.of(Integer.parseInt(productPage), 5, Sort.by("regTime").descending());

        // 상품 목록 가져오기
        Page<Product> pageProduct = productService.getProductList(pageable);

        // 각 상품 사진 찾기
        Map<Long, ImageResponseDto> images = new HashMap<>();
        for(Product product : pageProduct){
            ImageResponseDto image = imageFileService.findImage(product.getProductNo());

            if(image != null){
                images.put(product.getProductNo(), image);
            }
        }


        model.addAttribute("productPage", productService.getProductList(pageable));
        model.addAttribute("images", images);


        return "product/chickenInfo";
    }

    @GetMapping("/info/{page}")
    public String chickenPages(@PathVariable Integer page, Model model){
        Pageable pageable = PageRequest.of(page, 5, Sort.by("regTime").descending());
        model.addAttribute("productPage", productService.getProductList(pageable));

        return "product/card/pageCard";
    }

    @GetMapping("/register")
    public String productRegister(Model model){
        model.addAttribute("productDto", new ProductDto());
        return "product/registerProduct";
    }

    @PostMapping("/register")
    public String productSave(@Valid ProductDto productDto, BindingResult bindingResult, Authentication authentication){
        if(bindingResult.hasErrors()){
            log.info("들어오는지 확인" + bindingResult.hasErrors());
            return "product/registerProduct";
        }
        log.info(authentication.getName() + " 상품 등록");
        productService.saveProduct(productDto, authentication.getName());
        return "redirect:/product/info";
    }

    @GetMapping("/detail")
    public String productDetail(@RequestParam Long productNo,
                                @RequestParam(required = false, defaultValue = "0") Long page,
                                Model model, Authentication authentication){
        // 여기서 pageNum을 쓰는 이유?  목록으로 돌아갈 때 page 값 넘기기 위해 사용
        model.addAttribute("pageNum", page);
        model.addAttribute("memberId", authentication.getName());
        log.info(authentication.getName() + "어센티케이션 네임 ");
        model.addAttribute("productDto", productService.showDetail(productNo));
        log.info(productService.showDetail(productNo) + "겟매핑 프로덕트");
        return "product/productDetail";
    }

    // 일부 값만 수정하기 위해 PatchMapping 사용
    @PatchMapping("/update")
    public String productUpdate(Model model, @RequestBody ProductDto productDto){

        log.info(productDto + " 뭐 뜨노 ");
        productService.updateProduct(productDto);
        log.info(productDto + "업데이트 프로덕트");
        model.addAttribute("updateProductDto", productService.showDetail(productDto.getProductNo()));
        return "product/card/productDetailCard";
    }
}
