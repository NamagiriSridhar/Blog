package blogs.repositories;

import org.springframework.data.repository.CrudRepository;

import blogs.domain.Blog;

public interface BlogRepository extends CrudRepository<Blog,Long>
{

}
