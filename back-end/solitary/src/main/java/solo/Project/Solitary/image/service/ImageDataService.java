package solo.Project.Solitary.image.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import solo.Project.Solitary.exception.BusinessLogicException;
import solo.Project.Solitary.exception.ExceptionCode;
import solo.Project.Solitary.image.entity.FileData;
import solo.Project.Solitary.image.entity.ImageData;
import solo.Project.Solitary.image.repository.FileDataRepository;
import solo.Project.Solitary.image.repository.ImageRepository;
import solo.Project.Solitary.util.ImageUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
@RequiredArgsConstructor
public class ImageDataService {

    private final ImageRepository imageRepository;
    private final FileDataRepository fileDataRepository;
    private final String FOLDER_PATH = "/Users/jw/Desktop/myProject/solitary/back-end/solitary/src/main/resources/static/images/";

    public String uploadImage(MultipartFile file) throws IOException {


        ImageData imageData = imageRepository.save(ImageData.builder()
                .imageName(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes()))
                .build());

        if (imageData != null) {
            return "file was uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String fileName) {

        ImageData imageData = findVerifiedImage(fileName);

        byte[] image = ImageUtils.decompressImage(imageData.getImageData());

        return image;
    }

    public ImageData findVerifiedImage(String fileName) {

        return imageRepository.findByImageName(fileName).orElseThrow(() -> new BusinessLogicException(ExceptionCode.IMAGE_NOT_FOUNT));
    }

    public String uploadImageToFileSystem(MultipartFile file) throws IOException {

        String filePath = FOLDER_PATH + file.getOriginalFilename();

        FileData fileData = fileDataRepository.save(FileData.builder()
                .imageName(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath)
                .build());
        file.transferTo(new File(filePath));

        if (fileData != null) {
            return "file was uploaded successfully : " + fileData.getFilePath();
        }
        return null;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {

        FileData fileData = fileDataRepository.findByImageName(fileName).orElseThrow(() -> new BusinessLogicException(ExceptionCode.IMAGE_NOT_FOUNT));
        String filePath = fileData.getFilePath();

        byte[] image = Files.readAllBytes(new File(filePath).toPath());

        return image;
    }
}
