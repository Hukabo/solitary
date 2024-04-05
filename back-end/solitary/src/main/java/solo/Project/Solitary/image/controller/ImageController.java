package solo.Project.Solitary.image.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import solo.Project.Solitary.image.service.ImageDataService;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageDataService imageDataService;


    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {

        String message = imageDataService.uploadImage(file);

        return ResponseEntity.ok().body(message);
    }

    @GetMapping("{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
        byte[] image = imageDataService.downloadImage(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/jpeg"))
                .body(image);
    }

    @PostMapping("/file")
    public ResponseEntity<?> uploadImageToFileSystem(@RequestParam("image") MultipartFile file) throws IOException {

        String message = imageDataService.uploadImageToFileSystem(file);

        return ResponseEntity.ok().body(message);
    }

    @GetMapping("/file/{fileName}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
        byte[] image = imageDataService.downloadImageFromFileSystem(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/jpeg"))
                .body(image);
    }
}