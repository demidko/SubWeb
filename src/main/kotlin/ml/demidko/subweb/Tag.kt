package ml.demidko.subweb

/**
 * Тег для описания сущностей. Атомарная единица нашей системы.
 * Просто кусок текста который мы наделяем смыслом, типизируем.
 */
sealed interface Tag {

  /**
   * Собственно текстовые данные тега
   */
  val text: String

  /**
   * Telegram ID того кто добавил тег. Пригодится, чтобы связаться и выяснить подробности в случае чего
   */
  val whoAdded: Long

  class Note(override val text: String, override val whoAdded: Long) : Tag


  class Organization(override val text: String, override val whoAdded: Long) : Tag

  /**
   * Адрес, метка, координаты, дом, город
   */
  class Geolocation(override val text: String, override val whoAdded: Long) : Tag

  /**
   * Должность, пост
   */
  class Position(override val text: String, override val whoAdded: Long) : Tag

  /**
   * Тег предназначен для однозначной идентификации одной конкретной сущности.
   * ИНН, паспорт, номер телефона, почта, идентификатор ресурса.
   * Эта абстракция нужна для склейки сущностей-дубликатов в одну.
   * Для склейки, должно быть полное вхождение всех тегов вклеиваемой сущности, во избежание ложных срабатываний.
   */
  sealed interface Identifier : Tag {

    class FullName(override val text: String, override val whoAdded: Long) : Identifier

    class PhoneNumber(override val text: String, override val whoAdded: Long) : Identifier

    /**
     * Данные паспорта, ИНН и т п
     */
    class DocumentNumber(override val text: String, override val whoAdded: Long) : Identifier

    class Email(override val text: String, override val whoAdded: Long) : Identifier

    /**
     * Сетевой уникальный адрес
     */
    class Uri(override val text: String, override val whoAdded: Long) : Identifier
  }
}