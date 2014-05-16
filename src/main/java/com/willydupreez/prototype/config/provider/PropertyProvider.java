package com.willydupreez.prototype.config.provider;

import java.util.Optional;

public interface PropertyProvider {

	Optional<String> getProperty(String key);

}
