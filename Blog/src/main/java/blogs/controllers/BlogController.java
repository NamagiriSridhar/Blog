package blogs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import blogs.domain.Blog;
import blogs.repositories.BlogRepository;

@Controller
public class BlogController 
{
	private BlogRepository blogRepository;
	
	@Autowired
	public BlogController(BlogRepository blogRepository)
	{
		this.blogRepository = blogRepository;
	}
	@ModelAttribute(name="blog")
	public Blog blog()
	{
		return new Blog();
	}
	@GetMapping("/")
	public String home()
	{
		return "home";
	}
	@GetMapping("/create")
	public String create()
	{
		return "create";
	}
	@PostMapping("/create")
	public String postCreate(Blog blog)
	{
		blogRepository.save(blog);
		return "info";
	}
	@GetMapping("/view")
	public String view(Model model)
	{
		Iterable <Blog> blogs = blogRepository.findAll();
		model.addAttribute("blogs",blogs);
		return "view";
	}
	@GetMapping("/update/{id}")
	public String updateBlogstep1(@PathVariable Long id,Model model)
	{
		Blog blog=blogRepository.findById(id).orElse(null);
		model.addAttribute(blog);
		return "update";
	}
	@PutMapping("/update/{id}")
	public String updateBlogstep2(Blog blog)
	{
		blogRepository.save(blog);
		return "info";
	}
	//@GetMapping("/delete/{id}") //if anchor tag	
	@DeleteMapping("/delete/{id}")
	public String deleteBlog(@PathVariable Long id)
	{
		blogRepository.deleteById(id);
		return "info";
	}
	
}
