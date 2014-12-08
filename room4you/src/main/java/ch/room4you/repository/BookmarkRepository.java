package ch.room4you.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ch.room4you.entity.Bookmark;
import ch.room4you.entity.User;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {

	List<Bookmark> findByBookmarker(User bookmarker);


}
