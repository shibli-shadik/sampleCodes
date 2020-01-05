package codes.controller;

import codes.model.Post;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumeWebController
{
    @Autowired
    private RestTemplate restTemplate;
    
   @RequestMapping(value = "/template/posts")
   public String getPostList() 
   {
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity<String> entity = new HttpEntity<String>(headers);
      
      return restTemplate.exchange("http://localhost:8080/posts", HttpMethod.GET, entity, String.class).getBody();
   }
   
   @RequestMapping(value = "/template/posts", method = RequestMethod.POST)
   public String createPosts(@RequestBody Post post) 
   {
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity<Post> entity = new HttpEntity<Post>(post, headers);
      
      return restTemplate.exchange("http://localhost:8080/posts", HttpMethod.POST, entity, String.class).getBody();
   }
   
   @RequestMapping(value = "/template/posts/{id}", method = RequestMethod.PUT)
   public String updatePost(@PathVariable("id") String id, @RequestBody Post post) 
   {
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity<Post> entity = new HttpEntity<Post>(post,headers);
      
      return restTemplate.exchange("http://localhost:8080/posts/" + id, HttpMethod.PUT, entity, String.class).getBody();
   }
   
   @RequestMapping(value = "/template/posts/{id}", method = RequestMethod.DELETE)
   public String deletePost(@PathVariable("id") String id) 
   {
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity<Post> entity = new HttpEntity<Post>(headers);
      
      return restTemplate.exchange("http://localhost:8080/posts/" + id, HttpMethod.DELETE, entity, String.class).getBody();
   }
}
