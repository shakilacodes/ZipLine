package com.ziplinegreen.vault.Repository;

import com.ziplinegreen.vault.Model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.Collection;

@RepositoryRestResource
public interface PostRepository extends JpaRepository<Post,Long> {
    Collection<Post> findByUserId(Long userId);
    Iterable<Post> findAllByUserId(Long userId);

}
