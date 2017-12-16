# AP4J
An Anime-Planet API using Jsoup DOM Parsing  
Requires the Jsoup library and Java JRE 8  
  
Small example which searches for an anime based on name and prints out some information  
```Java

  public static void main(String[] args) {
      APApi api = new APApi();
        
      AnimeSearchFilter filter = new AnimeSearchFilter();
      filter.setName("kimi no na wa");
      List<AnimePreview> list = api.searchAnime(filter);
        
      if (!list.isEmpty()) {
            
          AnimePreview preview = list.get(0);
            
          System.out.println("Title: " + preview.getTitle());
          System.out.println("Studio: " + preview.getStudio());
          System.out.println("Episodes: " + preview.getEpisodes());
          System.out.println("Rating: " + preview.getRating());
          System.out.println("Tags: " + preview.getTags());
            
          AnimePage page = api.fetchAnimePage(preview);
            
          System.out.println("Rank: #" + page.getRank());
          System.out.println("Watched Count: " + page.getUserStats().getWatched());
          System.out.println("Dropped Count: " + page.getUserStats().getDropped());
            
            
          List<AnimePreview> recommendedList = page.getRecommendedList();
            
          System.out.println("");
          System.out.println("Similar Anime:");
          for (AnimePreview recPreview : recommendedList) {
              System.out.println(recPreview.getTitle());
          }
      }
  }
```
The output should be:  
```
Title: your name.
Studio: CoMix Wave Inc.
Episodes: 1
Rating: 4.721
Tags: [Drama, Romance, Body Swapping, Original Work, School Life, Supernatural]
Rank: #1
Watched Count: 16644
Dropped Count: 29

Similar Anime:
5 Centimeters per Second
ERASED
Your lie in April
The Girl Who Leapt Through Time
```

There are currently two search filters:  
`AnimeSearchFilter` which searches through all anime  
`UserAnimeSearchFilter` which searches through an user's anime list  


Some other search examples:  

```java
    AnimeSearchFilter filter = new AnimeSearchFilter();
    filter.setName("kimi");
    filter.setStudio("comix");
    filter.addType(AnimeSearchFilter.Type.MOVIE);
    List<AnimePreview> list = api.searchAnime(filter);

    UserAnimeSearchFilter userFilter = new UserAnimeSearchFilter("sothis");
    userFilter.setStatusType(UserAnimeSearchFilter.StatusType.WATCHED);
    userFilter.addTag("scifi");
    userFilter.setSortType(UserAnimeSearchFilter.SortType.RATING);
    userFilter.setSortOrder(UserAnimeSearchFilter.SortOrder.DESCENDING);
    List<AnimePreview> userWatchedList = api.searchAnime(userFilter);
```
