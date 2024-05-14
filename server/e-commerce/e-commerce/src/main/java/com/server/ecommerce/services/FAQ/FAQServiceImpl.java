package com.server.ecommerce.services.FAQ;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.ecommerce.dto.FAQDto;
import com.server.ecommerce.entity.FAQ;
import com.server.ecommerce.entity.Product;
import com.server.ecommerce.repository.FAQRepository;
import com.server.ecommerce.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FAQServiceImpl implements FAQService{

	@Autowired
	private final  FAQRepository faqRepository;
	
	@Autowired
	private final ProductRepository productRepository;
	
	public FAQDto postFAQ(Long productId,FAQDto faqDto) {
		Optional<Product>optionalProduct=productRepository.findById(productId);
		
		if(optionalProduct.isPresent()) {
			FAQ faq= new FAQ();
			faq.setQuestion(faqDto.getQuestion());
			faq.setAnswer(faqDto.getAnswer());
			faq.setProduct(optionalProduct.get());
			return faqRepository.save(faq).getFAQDto();
		}
		return null;
	}
}
