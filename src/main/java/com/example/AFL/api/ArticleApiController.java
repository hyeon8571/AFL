package com.example.AFL.api;

import com.example.AFL.dto.ArticleDto;
import com.example.AFL.entity.Article;
import com.example.AFL.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board")
public class ArticleApiController {

    @Autowired
    private ArticleService articleService;

    // 게시글 작성 api
    @PostMapping("/write")
    public ResponseEntity<ArticleDto> write(@RequestBody ArticleDto dto) {

        ArticleDto created = articleService.create(dto);

        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    // 게시글 삭제 api
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ArticleDto> delete(@PathVariable Long id) {

        ArticleDto deleted = articleService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body(deleted);
    }

    // 게시글 수정 api
    @PatchMapping("/update/{id}")
    public ResponseEntity<ArticleDto> update(@PathVariable Long id, @RequestBody ArticleDto dto) {

        ArticleDto updated = articleService.update(id, dto);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
}
