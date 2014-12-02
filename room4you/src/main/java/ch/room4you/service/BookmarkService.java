package ch.room4you.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.room4you.entity.Ad;
import ch.room4you.entity.Bookmark;
import ch.room4you.entity.User;
import ch.room4you.repository.AdRepository;
import ch.room4you.repository.BookmarkRepository;
import ch.room4you.repository.UserRepository;

@Service
public class BookmarkService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AdRepository adRepository;

	@Autowired
	private BookmarkRepository bookmarkRepository;

	@Transactional
	public void bookmarkAd(int adId, String currentUser) {

		if (!isBookmarkedAd(adId, currentUser)) {
			Ad bookmarkedAd = adRepository.findOne(adId);
			User user = userRepository.findByName(currentUser);

			Bookmark bookmark = new Bookmark();
			bookmark.setBookmarker(user);
			bookmark.setBookmarks(bookmarkedAd);

			List<Bookmark> userBookmarks = user.getBookmarkedAds();
			userBookmarks.add(bookmark);
			user.setBookmarkedAds(userBookmarks);

		//	List<Bookmark> adBookmarks = bookmarkedAd.getBookmarks();
		//	adBookmarks.add(bookmark);
		//	bookmarkedAd.setBookmarks(adBookmarks);

			bookmarkRepository.save(bookmark);
			userRepository.save(user);
		//	adRepository.save(bookmarkedAd);
		}
		// User bookmarker = userRepository.findByName(user);
		// java.util.List<Bookmark> bookmark = bookmarker.getBookmarkedAds();
		// System.out.println(bookmark.get(0).getBookmarkedAd().getTitle());
	}

	@Transactional
	public void unBookmarkAd(int adId, String userName) {
		User user = userRepository.findByName(userName);
		Ad ad = adRepository.findOne(adId);
		java.util.List<Bookmark> bookmarks = user.getBookmarkedAds();
		Bookmark bookmarkToDelete = null;
		for (Bookmark bookmark : bookmarks) {
			if (bookmark.getBookmarkedAd().getId() == adId) {
				bookmarkToDelete = bookmark;
			}
		}

		// Bookmark bookmark = bookmarkRepository.findByAdAndUser(adId,
		// user.getId());
		bookmarkRepository.delete(bookmarkToDelete);
	}

	@Transactional
	public boolean isBookmarkedAd(int adId, String userName) {
		User user = userRepository.findByName(userName);
		List<Bookmark> bookmarks = user.getBookmarkedAds();

		for (Bookmark bkmrk : bookmarks) {
			if (bkmrk.getBookmarkedAd().getId() == adId) {
				return true;
			}
		}
		return false;
	}

	@Transactional
	public List<Bookmark> findAllBookmarks(String name) {
		User user = userRepository.findByName(name);
		List<Bookmark> bookmarks = bookmarkRepository.findByBookmarker(user);
		return bookmarks;
	}

}
