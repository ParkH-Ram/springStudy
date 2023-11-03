package com.chicken.controller;

import com.chicken.dto.ProductDto;
import com.chicken.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
@Log4j2
public class ProductController {

    private final ProductService productService;

    /**
     *  제품 페이징
     * **/
    @GetMapping("/info")
    public String chickenInfo(Model model, @RequestParam(value = "page", required = false, defaultValue = "0") String productPage){

        // 어디서 부터 시작하고 몇 페이지 까지 출력할 거고 정렬 기준을 뭘로 할 것인지 설정
        Pageable pageable = PageRequest.of(Integer.parseInt(productPage), 5, Sort.by("regTime").descending());
        model.addAttribute("productPage", productService.getProductList(pageable));

        return "product/chickenInfo";
    }

    @GetMapping("/info/{page}")
    public String chickenPages(@PathVariable Integer page, Model model){
        Pageable pageable = PageRequest.of(page, 5, Sort.by("regTime").descending());
        model.addAttribute("productPage", productService.getProductList(pageable));

        return "product/pageCard";
    }

    @GetMapping("/register")
    public String productRegister(Model model){

        model.addAttribute("productDto", new ProductDto());
        return "product/registerProduct";
    }

    @PostMapping("/register")
    public String productSave(@Valid ProductDto productDto, BindingResult bindingResult, Authentication authentication){
        if(bindingResult.hasErrors()){
            log.info("들어오는지 확인" + authentication.getName());
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
        // 여기서 페이지 num을 쓰는 이유?  목록으로 돌아갈 때 page 값 넘기기 위해 사용
        model.addAttribute("pageNum", page);
        model.addAttribute("memberId", authentication.getName());
        model.addAttribute("productDto", productService.showDetail(productNo));
        return "product/productDetail";
    }
}
