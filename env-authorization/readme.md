#### JWT keygen

```bash
keytool -genkey -alias env-authorization -keystore ~/env-authorization.p12 -storetype PKCS12 -keyalg RSA -keysize 4096
openssl pkcs12 -info -in ~/env-authorization.p12 -nodes 
```