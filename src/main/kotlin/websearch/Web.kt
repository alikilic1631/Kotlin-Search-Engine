package websearch
import org.jsoup.nodes.Document

class URL(private val link: String) {

  override fun toString(): String {
    return this.link
  }
}

class WebPage(private val document: Document){
  fun extractWords() = document.text()
    .lowercase()
    .split(" ")
    .map{x->x.filter{it !in ".," }}


}