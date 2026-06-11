package com.example.blog_app;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository){
        this.blogRepository = blogRepository;
    }

    public List<Blog> getAllBlogs(){    // 全件取得
        return blogRepository.findAll();
    }

    public Blog getById(long id){    // 詳細
        return blogRepository.findById(id);
    }
    public List<Blog> getBlogsByAuthor(long authorId){    // アカウントID検索
        return blogRepository.findByAuthorId(authorId);
    }
    public List<Blog> searchByKeyword(String keyword){    // キーワード検索
        return blogRepository.findByKeyword(keyword);
    }
    public List<Blog> searchByCategory(String category){    // カテゴリ検索
        return blogRepository.findByCategory(category);
    }
    public List<Blog> searchByAuthor(String author){    // 作者検索
        return blogRepository.findByAuthor(author);
    }
}
