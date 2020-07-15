package com.test.consumer1.services1;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.consumer1.controller.dto.Manufacturer;
import com.test.consumer1.controller.dto.Products;
import com.test.consumer1.services.connector.Provider1Connector;



@Service
public class ProductConsumerService {

	@Autowired
	private Provider1Connector providerConnector;

	/*
	 * public ProductConsumerService(Provider1Connector providerConnector2) {
	 * this.providerConnector = providerConnector2; }
	 */

	public Optional<Manufacturer> getProductList(String manuname) throws IOException {
		System.out.println("ProductConsumerService: getProductList(): start : ");
		return Optional.ofNullable(providerConnector.serializeData(
				providerConnector.getData(String.format("/manufacturers/name/%s", manuname)).getBody(), Manufacturer.class));
	}

	public Optional<Products> getProductListById(String prodid) throws IOException {
        System.out.println("ProductConsumerService: getProductListById(): start : ");
       return Optional.ofNullable(providerConnector.serializeData(
               providerConnector.getData(String.format("/products/%s", prodid)).getBody(), Products.class));
   }
}
