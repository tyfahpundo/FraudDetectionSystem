package zw.ca.hit.frauddetectionsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zw.ca.hit.frauddetectionsystem.domain.FinancialStatement;
import zw.ca.hit.frauddetectionsystem.helper.ExcelHelper;
import zw.ca.hit.frauddetectionsystem.message.ResponseMessage;
import zw.ca.hit.frauddetectionsystem.service.FinancialService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/excel")
public class ExcelController {
    @Autowired
    FinancialService fileService;
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                fileService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }
    @GetMapping("/statements")
    public ResponseEntity<List<FinancialStatement>> getAllTutorials() {
        try {
            List<FinancialStatement> statements = fileService.getAllStatements();
            if (statements.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(statements, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
