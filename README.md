# Authorization Service

**Spring Boot 2.x based OAuth2 w/Json Web Tokens auth service**

* Spring Boot 2.x
* Spring Security 5.x
* Spring Security OAuth2 2.x
* Spring Security JWT 1.x

---- 

**Example (Three legged OAuth flow...)**

* Access resource server and get redirected to auth server requesting authorization

* Get Auth Code from auth server and redirect back to resource server (requires basic auth with username/password)
  GET http://127.0.0.1:9999/oauth/authorize?client_id=volume-service&response_type=code&state=ABCDEF&redirect_uri=http://google.com
  
* Exchange auth code for JWT from auth server
  POST http://volume-service:password@127.0.0.1:9999/oauth/token?grant_type=authorization_code&redirect_uri=http://google.com&client_id=volume-service&client_secret=password&code=
  
* Access resource server with JWT bearer token (Header: "Authorization : Bearer ....")
  POST http://...
  
---

**TODO**
* Setup JdbcClientDetailsService instead of hard coded client details 

