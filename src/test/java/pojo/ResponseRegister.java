package pojo;

 public class ResponseRegister {
     private int id;
     private String token;
     private String error;

     public void setId(int id) {
         this.id = id;
     }

     public void setToken(String token) {
         this.token = token;
     }

     public void setError(String error) {
         this.error = error;
     }

     public String getError() {
         return error;
     }

     public String getToken() {
         return token;
     }

     public int getId() {
         return id;
     }
 }