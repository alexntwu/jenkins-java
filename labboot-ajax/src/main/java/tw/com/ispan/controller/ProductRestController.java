package tw.com.ispan.controller;

import java.net.URI;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.domain.ProductBean;
import tw.com.ispan.dto.ProductSelectDTO;
import tw.com.ispan.service.ProductService;

@RestController
@RequestMapping("/rest/pages/products")
@CrossOrigin
public class ProductRestController {
    private static final String END_POINT = "http://localhost:8080/rest/pages/products";
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody String json) {
        JSONObject obj = new JSONObject(json);
        Integer id = obj.isNull("id") ? null : obj.getInt("id");
        if(id!=null && !productService.exists(id)) {
            ProductBean insert = productService.create(json);
            if(insert!=null) {
                URI uri = URI.create(END_POINT+"/"+insert.getId());
                ResponseEntity.BodyBuilder builder = ResponseEntity.created(uri);
                ResponseEntity<ProductBean> response = builder.body(insert);
                return response;
            }
        }

        ResponseEntity.HeadersBuilder<?> builder = ResponseEntity.noContent();
        ResponseEntity<Void> response = builder.build();
        return response;
    }

    @PutMapping("/{pk}")
    public ResponseEntity<?> modify(@PathVariable("pk") Integer id, @RequestBody ProductBean json) {
        if(id!=null && productService.exists(id)) {
            ProductBean update = productService.update(json);
            if(update!=null) {
                return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(update);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{pk}")
    public ResponseEntity<Void> remove(@PathVariable("pk") Integer id) {
        if(id!=null && productService.exists(id)) {
            boolean delete = productService.remove(id);
            if(delete) {
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/find")
    public ResponseEntity<ProductSelectDTO> find(@RequestBody String json) {
        long count = productService.count(json);
        List<ProductBean> products = productService.find(json);

        ProductSelectDTO productDto = new ProductSelectDTO();
        productDto.setCount(count);
        productDto.setList(products);

        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(productDto);
    }

    @GetMapping("/{pk}")
    public ResponseEntity<?> findByPrimaryKey(@PathVariable("pk") Integer id) {
        if(id!=null) {
            ProductBean product = productService.findById(id);
            if(product!=null) {
                return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(product);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
