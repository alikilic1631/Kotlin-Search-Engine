package websearch



class SearchResult(val url: URL , val numRefs: Int)

class SearchEngine(private val searchMap: Map<URL,WebPage>){
  private var indexMap : Map<String,List<SearchResult>> = emptyMap()

  private fun rank(listUrl:List<URL>):List<SearchResult> = listUrl
    .groupBy{it}
    .map{(url,occ)-> SearchResult(url,occ.size)}
    .sortedByDescending { it.numRefs }

  fun compileIndex(){
    val urlWords = searchMap
    .flatMap{(url,webpage)-> webpage.extractWords()
    .map{ word -> Pair(word, url)}}

    val stringListMap = urlWords
      .groupBy { it.first }

    indexMap = stringListMap
      .mapValues{ entry -> rank(entry.value.map{it.second})}
  }

  fun searchFor(query:String): SearchResultSummary {
    return SearchResultSummary(indexMap.getOrDefault(query, emptyList()),query)
  }

}


class SearchResultSummary(val results: List<SearchResult>, val query:String){

  override fun toString(): String {
    TODO()
  }

}