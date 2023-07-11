package com.board.controller;

import com.board.dto.upload.UploadFileDto;
import com.board.dto.upload.UploadResultDto;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Log4j2
@RestController
public class UpDownController {

    @Value("${com.board.upload.path}") // import 시에 springframwork로 시작하는 value
    private String uploadPath;

    @ApiOperation(value = "Upload POST", notes = "POST 방식으로 파일 등록")
    @PostMapping(value ="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UploadResultDto> upload(UploadFileDto uploadFileDto){

        log.info(uploadFileDto);

        //  파일이 올라오면
        if(uploadFileDto.getFiles() != null){

            // 업로드 결과의 반환처리
            // List<UploadResultDto>를 반환하도록 수정
            final List<UploadResultDto> uploadResultDtoList = new ArrayList<>();

            uploadFileDto.getFiles().forEach(multipartFile -> {   //반복
                String originalName = multipartFile.getOriginalFilename();
                log.info(originalName);

                //// 첨부 파일 저장 - 중복 가능성이 거의 없는 코드 값을 생성하는 UUID
                String uuid = UUID.randomUUID().toString();

                Path savePath = Paths.get(uploadPath, uuid + "_" + originalName);

                // 업로드 결과의 반환 처리때 추가
                boolean image = false;

                try{
                    multipartFile.transferTo(savePath);  // 실제 파일 저장

                    // 이미지 파일의 종류라면
                    if(Files.probeContentType(savePath).startsWith("image")){

                        // 업로드 결과의 반환 처리
                        image = true;

                        File thumbFile = new File(uploadPath, "s_" + uuid + "_" + originalName);

                        Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200, 200);
                    }


                } catch (IOException e){
                    e.printStackTrace();
                }

                // 업로드 결과의 반환 처리
                uploadResultDtoList.add(UploadResultDto.builder().uuid(uuid)
                        .fileName(originalName)
                        .img(image).build()
                );


            }); // end each

            return uploadResultDtoList;
        } //end if




        return null;
    }


    // 첨부파일 조회
    @ApiOperation(value = "view 파일", notes = "GET 방식으로 첨부파일 조회")
    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGet(@PathVariable String fileName){

        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

        String resourceName = resource.getFilename();
        HttpHeaders headers = new HttpHeaders();

        try{
            headers.add("Content-Type", Files.probeContentType( resource.getFile().toPath()));
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().headers(headers).body(resource);

    }

    // 첨부파일 삭제
    @ApiOperation(value = "remove 파일", notes = "DELETE 방식으로 파일 삭제 ")
    @DeleteMapping("/remove/{fileName}")
    public Map<String, Boolean> removeFile(@PathVariable String fileName){
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

        String resourceName = resource.getFilename();

        Map<String, Boolean> resultMap = new HashMap<>();
        boolean removed = false;

        try{
            String contentType = Files.probeContentType(resource.getFile().toPath());
            removed = resource.getFile().delete();

            // 이미지 파일이면  ( 썸네일이 존재하면 )
            if (contentType.startsWith("image")){
                File thumbnailFile = new File(uploadPath + File.separator + "s_" + fileName);
                thumbnailFile.delete();
            }

            log.info("삭제된 파일 이름은 : " + resourceName);

        } catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();

        }
        resultMap.put("result", removed);

        return resultMap;

    }
}
