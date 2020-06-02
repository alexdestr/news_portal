package ru.vegd.controller.SearchUtils;

public enum SearchType {

    SEARCH_BY_TITLE(1),
    SEARCH_BY_AUTHOR(2),
    SEARCH_BY_TAGS(3);

    SearchType(Integer searchId) {this.searchId = searchId;}

    private final Integer searchId;

    public Integer getSearchTypeId() {
        return searchId;
    }

    public static SearchType getSearchTypeByID(Integer id) {
        SearchType searchType;
        switch (id) {
            case 1:
                searchType = SearchType.SEARCH_BY_TITLE;
                break;
            case 2:
                searchType = SearchType.SEARCH_BY_AUTHOR;
                break;
            case 3:
                searchType = SearchType.SEARCH_BY_TAGS;
                break;
            default:
                searchType = SearchType.SEARCH_BY_TITLE;
        }
        return searchType;
    }

}
