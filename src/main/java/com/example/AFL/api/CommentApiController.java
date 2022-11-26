package com.example.AFL.api;

import com.example.AFL.dto.ArticleDto;
import com.example.AFL.dto.CommentDto;
import com.example.AFL.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentApiController {

    @Autowired
    private CommentService commentService;

    // 댓글 조회 api
    @GetMapping("/board/free/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId) {
        List<CommentDto> dtos = commentService.comments(articleId);

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 댓글 생성 api
    @PostMapping("/board/free/{articleId}/create-comment")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto dto) {

        CommentDto created = commentService.create(articleId, dto);

        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    // 댓글 수정 api
    @PatchMapping("/board/free/edit-comment/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto dto) {

        CommentDto updated = commentService.update(id, dto);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // 댓글 삭제 api
    @DeleteMapping ("/board/free/delete-comment/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id) {

        CommentDto deleted = commentService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body(deleted);
    }

}
