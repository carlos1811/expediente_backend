package com.carlosrey.springboot.backend.apirest.auth;

public class JwtConfig {

	public static final String RSA_PRIVADA = // "-----BEGIN RSA PRIVATE KEY-----\r\n"
			"MIIEpAIBAAKCAQEAuE2c85jBwgY/a66H5jAuPTnSkIKeRt3zhaI3/noOVSi9k2W4\r\n"
			+ "TQC0CwG7SWceavOwZKQdHxaD4QfDqG3kmYrZZ1JWRQwoEp8+BsP+cJVKcFT06/RJ\r\n"
			+ "OesjCvJNf6G4fQHa1/D2ZWTTUzCcyu2/p3/ac8dOBzb8t0jV5ums2e40PDx9ypP6\r\n"
			+ "LFfUJKUT2L571VBZoCtMTJBOWAsjvK5ThFUGCKmuUCyJwuOo0XCcMOwvn2JGHU6D\r\n"
			+ "sUkqNm2TQnolPXMAGXsy+1YFPc0e2oESE8PxJZ3wLAx/2D2nQQ7yvsQ5EMR7v2rk\r\n"
			+ "QVUVKQsIRkTjgT7Ef1zEeXoQwZBxMD4C9GRQAQIDAQABAoIBAHdVOaS4aex5IyNT\r\n"
			+ "9IZk9+uIvv/2swjruTvzlxHccd9N68G+gSDKrcjarCuQ6y8Q7kubv4dbirRv1I3/\r\n"
			+ "6Z5JOw8dTuKSRngYR48V5G99Gp+1QohsueUYjK4PyYI0ckHuq4ZabQ+lwvcAaOU0\r\n"
			+ "H34S2J1SlQEy51q9fFJxRwwsogHFh9urL3E+Fx3ZniURdjvuYor5xgQb0HNPjotk\r\n"
			+ "+Ac/zjtJQ+nQhvM129UyhFes14of7j8k4UsWEPEKi/B73msrVzGsAbmbsLhWdjXv\r\n"
			+ "Ppe83SBIDITNt92c9UAqB0zDABvVx9VIQnEWmrzj9q+2m8rt/iI1m5neOvulUmjq\r\n"
			+ "7cIPy4kCgYEA2ZEoC6Rn968IUT5J3gDaAy1AKbEGENIhu6PtvTZ70aWBp7X076DL\r\n"
			+ "rcLJpkbUO5mO8y4Mielsm0BsOHwhU4rJduuJHV5uCkawTnXlviqFxHM/YHgRKL2D\r\n"
			+ "GSEmupIQBnMCKFlEZsJgDkPnE8Wt1zyoTkRPIE1Rffvmd6HMyOOUuwcCgYEA2Nwx\r\n"
			+ "6adiHCrMy0xz2TiwaFJ71ZSK+RRAfIGbZEpA/FXKXSTTj1kSl9JejnkjdOdcz7yZ\r\n"
			+ "ROP3dwo03p+kGrCQcul3CoPDu5JInAyXDaVjWy3qgfePlrH38NQBaoIj1lgUbGeu\r\n"
			+ "oVjrYBanfKSDn/4EJUSZUOvyAbSvgCk22TIK8rcCgYEAv5A3mkif4k2THTWjBH3r\r\n"
			+ "d3CYVh0wIg9i9DxaT4dxbh40Et77UyJZNB2WP3mN1QxX+XZKRAPrRKdsoi9piI1i\r\n"
			+ "5fptOBa0/QdQpUTZjJ8UOOzgmKgfM6fpjm7KEuwYgBUziSNQPspWmTjgdWBQuHsI\r\n"
			+ "FiG/KcnqPutU+XDVRuxbQHECgYEAhGHPGmqyyq0Z/ZT7N4SSs+x+agBW4fyJ7TeV\r\n"
			+ "a6CkkSWx9jBP8bcXUZT7Yg/zJB9Emg/wpIxhecrCQZDEtgJI+80nfUS4jN2c853/\r\n"
			+ "7DWlNHkQKiy8++pCiEJzZthxFh0IwrB8e0Rz02Gg9sbbDyUa1MFOsoaGYqIxbJQ8\r\n"
			+ "RlR1OHsCgYAvnxH70z/ULdE6wttj1Gb3XgMLHOBqIv0D+iT7G34nGvsGiNrei7UW\r\n"
			+ "YtUG2HSYnnHY5L1qt6mpmcakzvoh3NgL8VW8yO0/jmfhOTuIF1+ihHAMH9db0XLr\r\n"
			+ "52KXcfJnWCi9zgWPIDpm3KRw8THhO7BOYejo2onNEPv0GJUHuirvyw==\r\n"
			+ "-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyJxESsfMqywGfLROSWOn\r\n"
			+ "f+l75a4nV27qbB/Z5a/ZgjJvRegThEDLVVaybf7mNGAh7MlBw0kx0bamqK9MXa7o\r\n"
			+ "pZJVJIJhnoR8soZ4ybsUFT9zNARNfax0XLpZ3CBFN9yAFvJXw9wI+WAgZs7ndNIL\r\n"
			+ "Yk/EkGCt0Tobs6yvbhnw0reDzfDMeQjv9NJh3uFQ4eOf1OhZHY/qhaRspRhj7bC8\r\n"
			+ "HuCSMJZllsfbkRmGD4xJHL27TFvmlAcDrX6WwYEgMYBzc//091z69MFFkkInbJK+\r\n"
			+ "Odc4l+EKxJVQm4uQ2CK1AzyZwlynAdIHiPflCV87tpW5mUbsGFVFbD4gEBGT7rjI\r\n"
			+ "eQIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";
	
	
}
