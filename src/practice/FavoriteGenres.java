package practice;

import java.util.*;

public class FavoriteGenres {
	public Map<String, List<String>> favoritegenre(Map<String, List<String>> userMap,
			Map<String, List<String>> genreMap) {
		Map<String, List<String>> res = new HashMap<>();
		Map<String, String> songToGenreMap = new HashMap<>();
		for (Map.Entry<String, List<String>> entry : genreMap.entrySet()) {
			List<String> songs = entry.getValue();
			for (String song : songs) {
				songToGenreMap.put(song, entry.getKey());
			}
		}

		Map<String, Map<String, Integer>> userGenreCountMap = new HashMap<>();
		for (Map.Entry<String, List<String>> entry : userMap.entrySet()) {
			String user = entry.getKey();
			List<String> songs = entry.getValue();
			if (!userGenreCountMap.containsKey(user)) {
				userGenreCountMap.put(user, new HashMap<>());
			} else {
				for (String song : songs) {
					String genre = songToGenreMap.get(song);
					int count = userGenreCountMap.get(user).getOrDefault(genre, 0) + 1;
					userGenreCountMap.get(user).put(genre, count);
				}
			}
		}
		for (String user : userGenreCountMap.keySet()) {
			if (!res.containsKey(user)) {
				res.put(user, new ArrayList<>());
			} else {
				Map<String, Integer> genreCountMap = userGenreCountMap.get(user);
				int max = 0;
				List<String> favgenre = new ArrayList<>();
				for (String genre : genreCountMap.keySet()) {
					if (favgenre.size() == 0) {
						favgenre.add(genre);
						max = genreCountMap.get(genre);
					} else if (genreCountMap.get(genre) > max) {
						favgenre.clear();
						favgenre.add(genre);
						max = genreCountMap.get(genre);
					} else if (genreCountMap.get(genre) == max) {
						favgenre.add(genre);
					}
				}
				res.put(user, favgenre);
			}
		}
		return res;
	}
}
