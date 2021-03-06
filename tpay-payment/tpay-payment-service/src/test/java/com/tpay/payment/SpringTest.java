package com.tpay.payment;

import com.tpay.payment.config.AlipayConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/spring-context.xml")
public class SpringTest {

	@Autowired
	private AlipayConfig alipayConfig;

	@Test
	public void test(){
		alipayConfig.init("{\"isSandbox\":1,\"appid\": \"2016080300154678\", \"private_key\": \"MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDHojxgxIzTDVgpiitiF+MpYqA+zvu+3GsFNqt1kCqBq9JaqISPmTinvzblEcvh+4n+95UvRNeN+Ecc5tT4HM4lACsFWa6VKkJuTcayL0hCQSeayDvjsBYeIMu4nPAuLkxNQNS8Q5wu/S2vjmLhByE+pc7iOegPOYnVJ4qu3gYHDFJBYBcmt3c6z1+a1QYEXsxkeK2LTYYCDygG0hUipbjCWiD//RTRwMEAfkXCZUrWdee+bXaQs7Vj20LZiTY+UlxWMzbmqDVMu4aE9Sb8rWQlrLMylEuxmntQ2BlI49E+1ljEJ/VwD8RjHOAYeYVxXz9iVIQcf1Dj7TGvtdgTCjUlAgMBAAECggEABP7iU+mXPJDQ6/kxLLs8TATTzb6QPi513yOHFuIMrkIGeY5OAunqqQf377OKTpVk1qSLbyth2YOlA+wi2DKgVfyzFucvnzv3fZskpcDZJg9LVmw8U25Mn2RitLP4rq8p6aT9rdhjSCm9wu7lOoxbIJ41lyOr0vW1pUfgCJA1xmdVNgun910Ib8Fv1zjPPzDbwKxpU2Xa2gdT4BpS8GIaJy3ipuEoZVCJM3xVmYYzoOXulFMBA4No0dhw7Po+7Qfq4F7oAYSTLcT4mWH9dctmmjqTb3VwIq9m/txsYGHKU5BFlFMpTH4UU+37VGgTvasaOWMudWNXrRSoCZK0RAT64QKBgQDoWV35DiSVnFVt8LOgO1qOPl7SpNQyEjj/ouog+IbYpIQG6qViRX7pKTix+W04xwcRe4JjyNFYt/ZUBUJtB3YJoXGJIf3l0KVPN12sq8lC8qnXECofTmDneM2c8d5UKEbM5k4pve4uuY35NsgJNO8kLI+UmTDc2ZXN2kb8dnB5uwKBgQDb9FxXEYyrLIt9bNa1tQUINk89lW5csk5eLT2lEZtbi7kYFz7VwI9/zGrfC89OJGssEzeqHVwQ7KJQoxQ5t6pi5S5DjkCuBq2SuJUpLcp3vDFt7JSx/OWlmRF5F56qdQ/+CXFE4a6RmjZapThUoIVaMm2LhYXbn4TLwkX8R30unwKBgQC+JUybYnCcI2liZIaLzacJG4+8MPoa1ADja+7Q9rJcHx6RDnw0FQ2MQJjORYKV4YMuUnZdtN1mDn6/OeKnmWCG9yEUCcZfycVPM88sXRWC/VXy0561Kg5xv9H86494LdHrI1q6HF4MzPucBLHlnvVKFFGYWVm8kzBeW5CWUVMwuwKBgQCXz3ugOKOfTVplUiaLOYpklZ9xgN6wKfz+8Ti34oQY1kIFR539yC8C5OLqWWtWoro5rHXh+uudXsXU5AnEoGiAY2/f/qHTuxY3yyBgUJOKuqBfH0et5T/XYON3cX74ZV2JV5kPErj7E7XqMFjFyBjiHSYQgzLVV68/NzI4olrbPwKBgQDLLsqN3qqhnESy07l7yfb9uIWebNHlREcV00loEvL3LJMWGacHvY824yrx6iAI920ZWVCbqFOyZIPSG1XngKUdarh9AV5uIdSX5rueRQCaHbtv30vhF9gcTNYSCxKj6XnvzQuxIYSLCroEPpeG7Q6SU+qv+X34yAV+5RNFhFHE5A==\", \"alipay_public_key\": \"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp61DVvZ25K8Svs9mnT5PE4BinlotyJ1f6CxaRTFb0HvIvOXMNgwtQ8dw4SnQsfcrJDpTh+LFlVLJm/SueP96LLW/VvE80Gddw3fipqIlUGwluYRBOF5l34RCAKhLX3KEE4wZ1M8wcoh3kBt/C2rCLH25sXnGqkNpwaLdTftMr0xNoDK0pXbtZ88PbZWE9GwVKGo7lzm/qUkRsdoQF9PcD7Vk0u6gwx506NgEOazCC8li3OajYsj3vxD207L7MrS11xp/0Gdv6uxQ6bh0CvKwzV0TeUteanHWmyLg7ky3X0dIOEOgdDZzzZhMgJKA6O9BDVZ5XMGXUFaYjR/q9SXfCQIDAQAB\"}");
		System.out.println(alipayConfig.getApp_id());
		System.out.println(alipayConfig.getNotify_url());
	}



	

	

}
