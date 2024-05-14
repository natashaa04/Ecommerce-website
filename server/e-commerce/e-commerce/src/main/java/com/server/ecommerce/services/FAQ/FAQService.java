package com.server.ecommerce.services.FAQ;

import com.server.ecommerce.dto.FAQDto;

public interface FAQService {
	public FAQDto postFAQ(Long productId,FAQDto faqDto) ;
}
