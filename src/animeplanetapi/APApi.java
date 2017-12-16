/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animeplanetapi;

import animeplanetapi.Anime.AnimePage;
import animeplanetapi.Anime.AnimePreview;
import animeplanetapi.Anime.AnimeSearchFilter;
import animeplanetapi.Anime.AnimeSearchers;
import java.util.List;

/**
 *
 * @author bowen
 */
public class APApi {
    
    public final static String MAINURL = "http://www.anime-planet.com/";
    
    @Deprecated
    public List<AnimePreview> searchAnimeByName(String name) {
        AnimeSearchFilter filter = new AnimeSearchFilter();
        filter.setName(name);
        filter.setSortType(AnimeSearchFilter.SortType.MOST_WATCHED);
        return searchAnime(filter);
    }
    
    public List<AnimePreview> searchAnime(SearchFilter filter) {
        return AnimeSearchers.searchAnime(filter);
    }
    
    public AnimePage fetchAnimePage(AnimePreview preview) {
        return AnimeSearchers.fetchAnimePage(preview);
    }
    
    
    
}
