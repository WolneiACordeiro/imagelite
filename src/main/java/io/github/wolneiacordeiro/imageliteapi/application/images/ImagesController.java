package io.github.wolneiacordeiro.imageliteapi.application.images;

import io.github.wolneiacordeiro.imageliteapi.domain.entity.Image;
import io.github.wolneiacordeiro.imageliteapi.domain.enums.ImageExtension;
import io.github.wolneiacordeiro.imageliteapi.domain.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/images")
@Slf4j
@RequiredArgsConstructor
public class ImagesController {
    private final ImageService service;
    private final ImageMapper mapper;
    @PostMapping
    public ResponseEntity save(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("tags") List<String> tags
            ) throws IOException {
        log.info("Imagem recebida: name: {}, size: {}", file.getOriginalFilename(), file.getSize());
        Image savedImage = mapper.mapToImage(file, name, tags);
        service.save(savedImage);
        URI imageUri = buildImageURL(savedImage);
        return ResponseEntity.created(imageUri).build();
    }
@GetMapping("{id}")
public ResponseEntity<byte[]> getImage(@PathVariable String id){
 var possibleImage = service.getById(id);
 if(possibleImage.isEmpty()){
     return ResponseEntity.notFound().build();
 }
    var image = possibleImage.get();
    HttpHeaders  headers = new HttpHeaders();
    headers.setContentType(image.getExtension().getMediaType());
    headers.setContentLength(image.getSize());
    headers.setContentDispositionFormData("inline; filename=\"" + image.getFileName() + "\"", image.getFileName());
    return new ResponseEntity<>(image.getFile(), headers, HttpStatus.OK);
}
    private URI buildImageURL(Image image){
        String imagePath = "/" + image.getId();
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(imagePath)
                .build()
                .toUri();
    }
}
