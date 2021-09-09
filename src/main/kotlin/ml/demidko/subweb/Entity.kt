package ml.demidko.subweb

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import ml.demidko.subweb.Tag.Identifier.Uri
import ml.demidko.subweb.Tag.Note

/**
 * Сущность состоящая из тегов. То что собственно ищут пользователи, результат в выдаче.
 * Типы сущностей, по сути, представляют собой разные способы разбить теги по критериям,
 * по человеку, по ресурсу, по месту, по организации. Любой внесенный пользователем в систему текст, парсится в сущность.
 */
sealed interface Entity {

  /**
   * Сущность идентифицируется по набору тегов
   */
  val tags: Set<Tag>

  /**
   * Индивидуальная сущность, без вложенности. Один человек, один сетевой ресурс
   */
  sealed interface Individual : Entity {

    /**
     * Человек, бот, нечто индивидуально-одушевленное
     */
    class Person(override val tags: Set<Tag>) : Individual {

    }

    /**
     * Сетевой ресурс, закладка, чат, группа, нечто индивидуально-неодушевленное
     */
    class NetworkResource(override val tags: Set<Tag>) : Individual {
      companion object {
        fun parseFirefox(bookmarks: JsonObject, by: Long, tags: Set<Note> = emptySet()): List<NetworkResource> {
          val updatedTags = when (val title = bookmarks["title"]?.jsonPrimitive?.content?.takeIf(String::isNotBlank)) {
            null -> tags
            else -> tags + Note(title, by)
          }
          return when (val uri = bookmarks["uri"]?.jsonPrimitive?.content) {
            null ->
              bookmarks["children"]
                ?.jsonArray
                ?.flatMap { parseFirefox(it.jsonObject, by, updatedTags) }
                ?: emptyList()
            else -> listOf(NetworkResource(updatedTags + Uri(uri, by)))
          }
        }
      }
    }
  }

  /**
   * Сущность сущностей. Сущность в которой помимо собственных тегов, можно обнаружить вложенные сущности.
   * Это может быть город, дом, организация. Мета-сущность выделяется в результате обнаружения некоторых
   * общих тегов (таких, как геолокация или компания) у базовых сущностей.
   * Может появиться в результате работы по агрегации и пересмотру тегов индивидуальных сущностей.
   */
  sealed interface Group : Entity {

    val entities: Set<Individual>

    class Place(override val tags: Set<Tag>, override val entities: Set<Individual>) : Group

    class Organization(override val tags: Set<Tag>, override val entities: Set<Individual>) : Group
  }
}