package com.example.AFL.controller;

import com.example.AFL.dto.ArticleDto;
import com.example.AFL.dto.CommentCountDto;
import com.example.AFL.dto.CommentDto;
import com.example.AFL.service.ArticleService;
import com.example.AFL.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/board")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    // 자유게시판
    @GetMapping("/free")
    public String showFree(Model model) {

        List<ArticleDto> articleDtos = articleService.showAll();

        List<Long> ids = articleDtos.stream().map(article -> article.getId()).collect(Collectors.toList()); // 각 게시글의 id를 가져온다


        List<CommentCountDto> counts = new ArrayList<>();

        for (int i = 0; i < ids.size(); i++) {
            List<CommentDto> dtos = commentService.comments(ids.get(i));

            int count = dtos.size();

            CommentCountDto temp = new CommentCountDto(count);
            
            counts.add(temp);
        }

        model.addAttribute("counts", counts);

        model.addAttribute("articleList", articleDtos);
        return "board/free";
    }

    // 게시글 작성 폼
    @GetMapping("/write")
    public String showWriteForm() {
        return "board/write";
    }


    // 게시글 상세페이지
    @GetMapping("/free/{id}")
    public String showDetail(@PathVariable Long id, Model model) {

        ArticleDto articleDto = articleService.show(id);

        List<CommentDto> dtos = commentService.comments(id);

        int count = dtos.size(); // 댓글 수

        model.addAttribute("article", articleDto);

        model.addAttribute("CommentDtos", dtos);

        model.addAttribute("count", count);

        return "board/detail";
    }

    // 게시글 수정 폼
    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable Long id, Model model) {

        ArticleDto dto = articleService.show(id);

        model.addAttribute("article", dto);

        return "board/edit";
    }
}
