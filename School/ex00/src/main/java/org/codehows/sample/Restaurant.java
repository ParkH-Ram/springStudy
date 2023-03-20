package org.codehows.sample;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

@Component
@Data
public class Restaurant {
	@Setter(onMethod = @Autowired)
	private Chief chief
	
}
