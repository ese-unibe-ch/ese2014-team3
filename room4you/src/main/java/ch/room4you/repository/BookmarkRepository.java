package ch.room4you.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ch.room4you.entity.Ad;
import ch.room4you.entity.Bookmark;
import ch.room4you.entity.User;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {

	
		//@Query("SELECT bookmark FROM Bookmark WEHERE bookmarkadid=adId AND bookmarkerid = userId")
		// Bookmark findByAdAndUser( Ad bookmarkedAd,  User bookmarker);

}
